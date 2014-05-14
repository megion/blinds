package com.megion.site.blinds.web.template;

import info.magnolia.module.blossom.annotation.Area;
import info.magnolia.module.blossom.annotation.TabFactory;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.dialog.TabBuilder;

import java.util.List;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.megion.site.core.model.MenuItem;
import com.megion.site.core.model.areas.promo.PromoArea;
import com.megion.site.core.model.navigation.BreadcrumbsNavigation;
import com.megion.site.core.service.PageService;
import com.megion.site.core.service.area.PromoAreaService;

@Controller
@Template(title = "Blinds common", id = "blinds-site:pages/common")
public class CommonTemplate {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory
			.getLogger(CommonTemplate.class);

	@Autowired
	private PageService pageService;

	@Area("centerArea")
	@Controller
	public static class CenterArea {

		@TabFactory("Content")
		public void contentTab(TabBuilder tab) {
		}

		@RequestMapping("/commonTemplate/centerArea")
		public String render(Node page, ModelMap model,
				HttpServletRequest request) throws PathNotFoundException,
				RepositoryException {
			return "areas/verticalArea.jsp";
		}
	}
	
	@Area("promosArea")
	@Controller
	public static class PromosArea {
		@Autowired
		PromoAreaService promoAreaService;

		@TabFactory("Content")
		public void contentTab(TabBuilder tab) {
			promoAreaService.addPromoAreaDialogControls(tab);
		}

		@RequestMapping("/commonTemplate/promoArea")
		public String render(Node page, ModelMap model,
				HttpServletRequest request) throws PathNotFoundException,
				RepositoryException {
			PromoArea promoArea = promoAreaService.getPromoArea(page);
			promoArea.setRelativeStaticPromosPath("../includes/staticPromos.jsp");
			model.put("promoArea", promoArea);
			return "areas/promosArea.jsp";
		}
	}

	@RequestMapping("/commonTemplate")
	public String render(Node page, ModelMap model) throws RepositoryException {

		List<MenuItem> topMenus = pageService.getRootMenus(page);
		model.put("topMenus", topMenus);
		List<MenuItem> oneLevelSubMenus = pageService.getSecondLevelMenus(page);
		model.put("subMenus", oneLevelSubMenus);
		
		BreadcrumbsNavigation breadcrumbs = pageService.getBreadcrumbsNavigation(page);
        model.put("breadcrumbs", breadcrumbs);
        
        model.put("pageModel", pageService.getPageModel(page));

		return "pages/common.jsp";
	}

	@TabFactory("Content")
	public void propertiesDialog(TabBuilder tab) throws RepositoryException {
		 pageService.addMainPageDialogControls(tab);
	}
}
