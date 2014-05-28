package com.megion.site.blinds.web.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.megion.site.blinds.model.form.ContactFormUI;
import com.megion.site.core.exception.MegionsiteException;
import com.megion.site.core.service.YandexCaptchaService;

/**
 * Validating contact forms.
 */
public class ContactFormValidator implements Validator {

	private final YandexCaptchaService yandexCaptchaService;
	private final String yandexKey;

	public ContactFormValidator(YandexCaptchaService yandexCaptchaService,
			String yandexKey) {
		this.yandexCaptchaService = yandexCaptchaService;
		this.yandexKey = yandexKey;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ContactFormUI.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required",
				"Имя должно быть заполнено.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required",
				"E-mail должен быть заполнен.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message",
				"required", "Текст запроса должен быть заполнен.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "captchaText",
				"required", "Необходимо ввести символы.");

		ContactFormUI contactFormUI = (ContactFormUI) target;
		if (StringUtils.isNotBlank(contactFormUI.getEmail())) {
			if (!com.megion.site.core.util.ValidationUtils.isValidEmailAddress(contactFormUI.getEmail())) {
				errors.rejectValue("email", "required", null,
						"Неверный формат E-mail.");
			}
		}
		try {
			if (StringUtils
					.isNotBlank(contactFormUI.getCaptchaText())) {
				boolean isValidCaptcha = yandexCaptchaService.checkCaptcha(
						contactFormUI, yandexKey);
				if (!isValidCaptcha) {
					errors.rejectValue("captchaText", "required", null,
							"Вы неверно ввели символы. Попробуйте еще раз.");
				}
			}
		} catch (Exception e) {
			throw new MegionsiteException(e.getMessage(), e);
		}
	}
}
