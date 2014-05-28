package com.megion.site.blinds.service;

import info.magnolia.jcr.util.PropertyUtil;
import info.magnolia.module.blossom.dialog.TabBuilder;

import javax.jcr.Node;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megion.site.blinds.model.form.ContactForm;
import com.megion.site.core.enums.TabNumber;
import com.megion.site.core.service.TemplatingService;

@Service
public class ContactFormServiceImpl implements ContactFormService {

	@Autowired
	private TemplatingService templatingService;

	@Override
	public void addContactFormDialogControls(TabBuilder tabBuilder,
			TabNumber tabNumber) {
		if (TabNumber.FIRST.equals(tabNumber)) {
			tabBuilder.addEdit("title", "Заголовок формы", "");
			tabBuilder
					.addEdit("fromEmail", "От",
							"Email адреса отправителя. Если оставить пустым тогда назначится с формы");
			tabBuilder.addEdit("toEmailList", "Кому",
					"Email адреса для отправки разделенные через ';'");
			tabBuilder
					.addEdit("emailSubject", "Тема",
							"Тема письма. Если оставить пустым тогда назначится с формы");
			tabBuilder
					.addEdit(
							"yandexKey",
							"Ключ yandex",
							"Ключ API yandex для проверки Captcha. Подробнее http://api.yandex.ru/cleanweb/")
					.setRequired(true);
		} else {
			tabBuilder.addEdit("successTitle",
					"Заголовок успешности отправки сообщения", "");
			tabBuilder.addFckEditor("successText",
					"Текст успешности отправки сообщения", "");
		}
	}

	@Override
	public ContactForm getContactForm(Node contactFormComponent) {
		ContactForm dialogData = new ContactForm();
		dialogData.setTitle(PropertyUtil.getString(contactFormComponent,
				"title"));
		dialogData.setFromEmail(PropertyUtil.getString(contactFormComponent,
				"fromEmail"));
		dialogData.setToEmailList(PropertyUtil.getString(contactFormComponent,
				"toEmailList"));
		dialogData.setEmailSubject(PropertyUtil.getString(contactFormComponent,
				"emailSubject"));
		dialogData.setSuccessTitle(PropertyUtil.getString(contactFormComponent,
				"successTitle"));

		dialogData.setSuccessText(templatingService.getNodePropertyAsHtml(
				contactFormComponent, "successText"));

		dialogData.setYandexKey(PropertyUtil.getString(contactFormComponent,
				"yandexKey"));

		return dialogData;
	}

}