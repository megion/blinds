package com.megion.site.blinds.service;

import javax.jcr.RepositoryException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megion.site.blinds.model.SiteConfig;
import com.megion.site.core.provider.WebsiteProvider;

@Service
public class SiteConfigServiceImpl implements SiteConfigService {
	
	@Autowired
	private WebsiteProvider websiteProvider;

	@Override
	public SiteConfig getSiteConfig() throws RepositoryException {
		return null;
	}

}