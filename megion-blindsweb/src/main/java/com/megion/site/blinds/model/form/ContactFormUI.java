package com.megion.site.blinds.model.form;

import com.megion.site.core.model.yandex.cleanweb.CheckCaptcha;

/**
 * Данные пришедшие с формы "Задать вопрос"
 */
public class ContactFormUI extends CheckCaptcha {

	private String name;
	private String email;
	private String messageTitle;
	private String message;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
