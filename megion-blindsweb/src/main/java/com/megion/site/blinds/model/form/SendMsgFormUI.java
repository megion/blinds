package com.megion.site.blinds.model.form;

import com.megion.site.core.model.yandex.cleanweb.CheckCaptcha;

/**
 * Данные с UI формы "Отправить сообщение"
 */
public class SendMsgFormUI extends CheckCaptcha {

	private String name;
	private String phone;
	private String message;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
