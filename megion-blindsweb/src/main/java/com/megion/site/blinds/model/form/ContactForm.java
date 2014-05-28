package com.megion.site.blinds.model.form;

import com.megion.site.core.model.yandex.YandexAccount;

/**
 * Данные компонента диалога "Задать вопрос"
 */
public class ContactForm extends YandexAccount {

	/**
	 * Заголовок формы
	 */
	private String title;

	/**
	 * E-mail адрес отправителя
	 */
	private String fromEmail;

	/**
	 * тема письма
	 */
	private String emailSubject;

	/**
	 * Заголовок об успешно отправленном сообщении
	 */
	private String successTitle;

	/**
	 * Текст успешного завершения отправки сообщения
	 */
	private String successText;

	/**
	 * E-mail адреса разделенные через ';'
	 */
	private String toEmailList;

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isHasTitle() {
		return !org.apache.commons.lang.StringUtils.isBlank(title);
	}

	public String getToEmailList() {
		return toEmailList;
	}

	public void setToEmailList(String toEmailList) {
		this.toEmailList = toEmailList;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public boolean isHasFromEmail() {
		return !org.apache.commons.lang.StringUtils.isBlank(fromEmail);
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public boolean isHasEmailSubject() {
		return !org.apache.commons.lang.StringUtils.isBlank(emailSubject);
	}

	public String getSuccessTitle() {
		return successTitle;
	}

	public void setSuccessTitle(String successTitle) {
		this.successTitle = successTitle;
	}

	public boolean isHasSuccessTitle() {
		return !org.apache.commons.lang.StringUtils.isBlank(successTitle);
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
