package com.megion.site.blinds.web.component;

import java.util.HashMap;
import java.util.Map;

import info.magnolia.module.blossom.annotation.TabFactory;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;
import info.magnolia.module.blossom.dialog.TabBuilder;

import javax.jcr.Node;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.megion.site.blinds.model.form.ContactForm;
import com.megion.site.blinds.model.form.ContactFormUI;
import com.megion.site.blinds.service.ContactFormService;
import com.megion.site.blinds.web.validator.ContactFormValidator;
import com.megion.site.core.enums.TabNumber;
import com.megion.site.core.model.EmailHeader;
import com.megion.site.core.model.yandex.cleanweb.CheckSpam;
import com.megion.site.core.model.yandex.cleanweb.CheckSpamResult;
import com.megion.site.core.model.yandex.cleanweb.GetCaptchaResult;
import com.megion.site.core.service.MailService;
import com.megion.site.core.service.YandexCaptchaService;

/**
 * Форма задать вопрос.
 */
@Controller
@Template(title = "Contact form", id = "blinds-site:components/contactForm")
@TemplateDescription("Задать вопрос")
public class ContactFormComponent {

	private static final Logger log = LoggerFactory
			.getLogger(ContactFormComponent.class);

	@Autowired
	private ContactFormService contactFormService;

	@Autowired
	private YandexCaptchaService yandexCaptchaService;
	
	@Autowired
	private MailService mailService;

	@RequestMapping("/contactForm")
	public String handleRequest(ModelMap model,
			@ModelAttribute ContactFormUI contactFormUI,
			BindingResult result, HttpServletRequest request, Node node) {

		ContactForm contactForm = contactFormService.getContactForm(node);
		model.put("contactForm", contactForm);

		try {
			// проверить на спам. Данная проверка сейчас используется только для
			// получения id проверки. В будущем можно избавить добросовестных
			// пользователей от ввода Captcha.
			CheckSpam checkSpam = new CheckSpam(
					contactFormUI.getName(),
					contactFormUI.getMessageTitle(),
					contactFormUI.getMessage());
			CheckSpamResult checkSpamResult = yandexCaptchaService.checkSpam(
					checkSpam, contactForm.getYandexKey(),
					request);
			model.put("checkSpamResult", checkSpamResult);

			// получить Captcha всегда в независимости от проверки выше
			GetCaptchaResult captchaResult = yandexCaptchaService.getCaptcha(
					checkSpamResult, contactForm.getYandexKey());
			model.put("getCaptchaResult", captchaResult);

			if ("POST".equals(request.getMethod())) {
				// проверяем ввод пользователем данных
				new ContactFormValidator(yandexCaptchaService,
						contactForm.getYandexKey()).validate(
						contactFormUI, result);
				if (result.hasErrors()) {
					return "components/contactForm.jsp";
				}

				// send mail
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("notification", contactFormUI);				
				EmailHeader mailHeader = new EmailHeader();
				mailHeader.setToList(contactForm.getToEmailList());
				if (contactForm.isHasFromEmail()) {
					mailHeader.setFrom(contactForm.getFromEmail());
				} else {
					mailHeader.setFrom(contactFormUI.getEmail());
				}
				if (contactForm.isHasEmailSubject()) {
					mailHeader.setSubject(contactForm.getEmailSubject());
				} else {
					mailHeader.setSubject(contactFormUI.getMessageTitle());
				}
				mailService.sendMail(mailHeader, "contactFormNotification", params);

				return "components/contactFormSubmitted.jsp";
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			model.put("errorMessage", "<b>Ошибка</b> " + e.getMessage());
			return "components/contactForm.jsp";
		}

		return "components/contactForm.jsp";
	}

	@TabFactory("Content")
	public void contentTab(TabBuilder tab) {
		contactFormService.addContactFormDialogControls(tab, TabNumber.FIRST);
	}

	@TabFactory("Success")
	public void successTab(TabBuilder tab) {
		contactFormService.addContactFormDialogControls(tab, TabNumber.SECOND);
	}

}
