package com.megion.site.blinds.service;

import info.magnolia.module.blossom.dialog.TabBuilder;

import javax.jcr.Node;

import com.megion.site.blinds.model.form.SendMsgForm;

public interface SendMsgFormService {

	void addSendMsgFormDialogControls(TabBuilder tabBuilder);

	SendMsgForm getSendMsgForm(Node sendMsgFormComponent);

}
