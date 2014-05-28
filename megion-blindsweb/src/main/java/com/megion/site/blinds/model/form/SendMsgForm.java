package com.megion.site.blinds.model.form;

import com.megion.site.core.model.yandex.YandexAccount;

/**
 * Данные компонента диалога "Отправить сообщение"
 */
public class SendMsgForm extends YandexAccount {

	/**
	 * Текст успешного завершения отправки сообщения
	 */
	private String successText;

	/**
	 * E-mail адреса разделенные через ';'
	 */
	private String toEmailList;

	public String getToEmailList() {
		return toEmailList;
	}

	public void setToEmailList(String toEmailList) {
		this.toEmailList = toEmailList;
	}

	public String getSuccessText() {
		return successText;
	}

	public void setSuccessText(String successText) {
		this.successText = successText;
	}

	public boolean isHasSuccessText() {
		return !org.apache.commons.lang.StringUtils.isBlank(successText);
	}

}
