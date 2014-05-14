package com.megion.site.blinds.model;

/**
 * Хранит конфигурационные свойства сайта
 */
public class SiteConfig {

	/**
	 * Id зарегистрированного в VK приложения как веб сайт:
	 * http://vk.com/editapp?act=create
	 */
	private String vkAppWebsiteId;

	/**
	 * Google Analytics Account ID http://www.google.com/analytics/
	 */
	private String googleAnalyticsAccountId;

	private Boolean enabledSocialLikeButtons;

	private Boolean enabledGoogleAnalytics;

	/**
	 * Доменное имя, например megion.ru
	 */
	private String domainName;

	/**
	 * Email компании
	 */
	private String companyEmail;

	/**
	 * https://www.facebook.com/CompanyIT
	 */
	private String facebookSocialLink;
	/**
	 * https://twitter.com/corp_IT
	 */
	private String twitterSocialLink;
	/**
	 * https://plus.google.com/117435726468106273064/posts
	 */
	private String googleSocialLink;
	/**
	 * http://www.linkedin.com/company/i.t.-co.
	 */
	private String linkedinSocialLink;
	/**
	 * http://vk.com/corp_it
	 */
	private String vkSocialLink;

	public String getVkAppWebsiteId() {
		return vkAppWebsiteId;
	}

	public void setVkAppWebsiteId(String vkAppWebsiteId) {
		this.vkAppWebsiteId = vkAppWebsiteId;
	}

	public Boolean getEnabledSocialLikeButtons() {
		return enabledSocialLikeButtons;
	}

	public void setEnabledSocialLikeButtons(Boolean enabledSocialLikeButtons) {
		this.enabledSocialLikeButtons = enabledSocialLikeButtons;
	}

	public String getGoogleAnalyticsAccountId() {
		return googleAnalyticsAccountId;
	}

	public void setGoogleAnalyticsAccountId(String googleAnalyticsAccountId) {
		this.googleAnalyticsAccountId = googleAnalyticsAccountId;
	}

	public Boolean getEnabledGoogleAnalytics() {
		return enabledGoogleAnalytics;
	}

	public void setEnabledGoogleAnalytics(Boolean enabledGoogleAnalytics) {
		this.enabledGoogleAnalytics = enabledGoogleAnalytics;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getFacebookSocialLink() {
		return facebookSocialLink;
	}

	public void setFacebookSocialLink(String facebookSocialLink) {
		this.facebookSocialLink = facebookSocialLink;
	}

	public String getTwitterSocialLink() {
		return twitterSocialLink;
	}

	public void setTwitterSocialLink(String twitterSocialLink) {
		this.twitterSocialLink = twitterSocialLink;
	}

	public String getGoogleSocialLink() {
		return googleSocialLink;
	}

	public void setGoogleSocialLink(String googleSocialLink) {
		this.googleSocialLink = googleSocialLink;
	}

	public String getLinkedinSocialLink() {
		return linkedinSocialLink;
	}

	public void setLinkedinSocialLink(String linkedinSocialLink) {
		this.linkedinSocialLink = linkedinSocialLink;
	}

	public String getVkSocialLink() {
		return vkSocialLink;
	}

	public void setVkSocialLink(String vkSocialLink) {
		this.vkSocialLink = vkSocialLink;
	}

	@Override
	public String toString() {
		return "SiteConfig [vkAppWebsiteId=" + vkAppWebsiteId
				+ ", enabledSocialLikeButtons=" + enabledSocialLikeButtons
				+ "]";
	}

}
