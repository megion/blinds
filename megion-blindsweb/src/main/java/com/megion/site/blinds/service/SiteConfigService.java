package com.megion.site.blinds.service;

import javax.jcr.RepositoryException;

import com.megion.site.blinds.model.SiteConfig;

public interface SiteConfigService {

	SiteConfig getSiteConfig() throws RepositoryException;

}
