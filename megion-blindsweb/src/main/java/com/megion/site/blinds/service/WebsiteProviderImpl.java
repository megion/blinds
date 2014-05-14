package com.megion.site.blinds.service;

import org.springframework.stereotype.Service;

import com.megion.site.core.provider.WebsiteProvider;

@Service
public class WebsiteProviderImpl implements WebsiteProvider {

	@Override
	public String getSiteRootPath() {
		return "/";
	}

}