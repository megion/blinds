package com.megion.site.blinds.web.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.megion.site.blinds.model.form.SendMsgFormUI;
import com.megion.site.core.exception.MegionsiteException;
import com.megion.site.core.service.YandexCaptchaService;

/**
 * Validating contact forms.
 */
public class SendMsgFormValidator implements Validator {

	private final YandexCaptchaService yandexCaptchaService;
	private final String yandexKey;

	public SendMsgFormValidator(YandexCaptchaService yandexCaptchaService,
			String yandexKey) {
		this.yandexCaptchaService = yandexCaptchaService;
		this.yandexKey = yandexKey;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(SendMsgFormUI.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required",
				"Пожалуйста, укажите имя");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "required",
				"Пожалуйста, укажите телефон");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message",
				"required", "Пожалуйста, укажите текст сообщения");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "captchaText",
				"required", "Необходимо ввести символы");

		SendMsgFormUI formUI = (SendMsgFormUI) target;
		try {
			if (StringUtils.isNotBlank(formUI.getCaptchaText())) {
				boolean isValidCaptcha = yandexCaptchaService.checkCaptcha(
						formUI, yandexKey);
				if (!isValidCaptcha) {
					errors.rejectValue("captchaText", "required", null,
							"Вы неверно ввели символы. Попробуйте еще раз");
				}
			}
		} catch (Exception e) {
			throw new MegionsiteException(e.getMessage(), e);
		}
	}
}
