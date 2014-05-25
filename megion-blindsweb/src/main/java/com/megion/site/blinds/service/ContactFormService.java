package com.megion.site.blinds.service;

import info.magnolia.module.blossom.dialog.TabBuilder;

import javax.jcr.Node;

import com.megion.site.core.enums.TabNumber;
import com.megion.site.core.model.ContactForm;

public interface ContactFormService {

	void addContactFormDialogControls(TabBuilder tabBuilder, TabNumber tabNumber);

	ContactForm getContactForm(Node contactFormComponent);

}
