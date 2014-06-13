package com.megion.site.blinds.service;

import info.magnolia.jcr.util.PropertyUtil;
import info.magnolia.module.blossom.dialog.TabBuilder;

import javax.jcr.Node;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megion.site.blinds.model.form.SendMsgForm;
import com.megion.site.core.service.DialogService;
import com.megion.site.core.service.TemplatingService;

@Service
public class SendMsgFormServiceImpl implements SendMsgFormService {

	@Autowired
	private TemplatingService templatingService;
	@Autowired
	private DialogService dialogService;

	@Override
	public void addSendMsgFormDialogControls(TabBuilder tabBuilder) {
		tabBuilder.addEdit("toEmailList", "Кому",
				"Email адреса для отправки разделенные через ';'");
		tabBuilder
				.addEdit(
						"yandexKey",
						"Ключ yandex",
						"Ключ API yandex для проверки Captcha. Подробнее http://api.yandex.ru/cleanweb/")
				.setRequired(true);

		dialogService.addFckEditor(tabBuilder, "successText",
				"Текст успешности отправки сообщения", "");
	}

	@Override
	public SendMsgForm getSendMsgForm(Node sendMsgFormComponent) {
		SendMsgForm dialogData = new SendMsgForm();

		dialogData.setToEmailList(PropertyUtil.getString(sendMsgFormComponent,
				"toEmailList"));
		dialogData.setSuccessText(templatingService.getNodePropertyAsHtml(
				sendMsgFormComponent, "successText"));
		dialogData.setYandexKey(PropertyUtil.getString(sendMsgFormComponent,
				"yandexKey"));
		return dialogData;
	}

}