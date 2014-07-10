package com.megion.site.blinds.web.component;

import info.magnolia.module.blossom.annotation.TabFactory;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;
import info.magnolia.module.blossom.dialog.TabBuilder;

import java.util.HashMap;
import java.util.Map;

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

import com.megion.site.blinds.model.form.SendMsgForm;
import com.megion.site.blinds.model.form.SendMsgFormUI;
import com.megion.site.blinds.service.SendMsgFormService;
import com.megion.site.blinds.web.validator.SendMsgFormValidator;
import com.megion.site.core.model.EmailHeader;
import com.megion.site.core.model.yandex.cleanweb.GetCaptchaResult;
import com.megion.site.core.service.MailService;
import com.megion.site.core.service.YandexCaptchaService;

/**
 * Форма послать сообщение
 */
@Controller
@Template(title = "Send message form", id = "blinds-site:components/sendMsg")
@TemplateDescription("Отправить сообщение")
public class SendMsgFormComponent {

	private static final Logger log = LoggerFactory
			.getLogger(SendMsgFormComponent.class);

	@Autowired
	private SendMsgFormService sendMsgFormService;

	@Autowired
	private YandexCaptchaService yandexCaptchaService;
	
	@Autowired
	private MailService mailService;

	@RequestMapping("/sendMsgForm")
	public String handleRequest(ModelMap model,
			@ModelAttribute(value="formData") SendMsgFormUI formData,
			BindingResult result, HttpServletRequest request, Node node) {

		SendMsgForm formDialog = sendMsgFormService.getSendMsgForm(node);
		model.put("formDialog", formDialog);

		try {
			// проверить на спам. Данная проверка сейчас используется только для
			// получения id проверки. В будущем можно избавить добросовестных
			// пользователей от ввода Captcha.
			//CheckSpam checkSpam = new CheckSpam(
					//formData.getName(),
					//"Сообщение",
					//formData.getMessage());
			//CheckSpamResult checkSpamResult = yandexCaptchaService.checkSpam(
					//checkSpam, formDialog.getYandexKey(),
					//request);
			//model.put("checkSpamResult", checkSpamResult);

			// получить Captcha всегда в независимости от проверки выше
			GetCaptchaResult captchaResult = yandexCaptchaService.getCaptcha(
					null, formDialog.getYandexKey());
			model.put("getCaptchaResult", captchaResult);

			if ("POST".equals(request.getMethod())) {
				// проверяем ввод пользователем данных
				new SendMsgFormValidator(yandexCaptchaService,
						formDialog.getYandexKey()).validate(
						formData, result);
				if (result.hasErrors()) {
					return "components/sendMsgForm.jsp";
				}

				// send mail
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("notification", formData);				
				EmailHeader mailHeader = new EmailHeader();
				mailHeader.setToList(formDialog.getToEmailList());
				mailHeader.setFrom("test@mail.ru");
				mailHeader.setSubject("Сайт: сообщение от пользователя");
				mailService.sendMail(mailHeader, "sendMsgFormNotification", params);

				return "components/sendMsgFormSubmitted.jsp";
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			model.put("errorMessage", "<b>Ошибка</b> " + e.getMessage());
			return "components/sendMsgForm.jsp";
		}

		return "components/sendMsgForm.jsp";
	}

	@TabFactory("Content")
	public void contentTab(TabBuilder tab) {
		sendMsgFormService.addSendMsgFormDialogControls(tab);
	}

}
