package com.megion.site.blinds.web.template;

import info.magnolia.module.blossom.annotation.Area;
import info.magnolia.module.blossom.annotation.TabFactory;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.dialog.TabBuilder;

import java.util.List;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.megion.site.core.model.MenuItem;
import com.megion.site.core.model.areas.column.ColumnArea;
import com.megion.site.core.model.areas.table.TableArea;
import com.megion.site.core.service.PageService;
import com.megion.site.core.service.area.ColumnAreaService;
import com.megion.site.core.service.area.TableAreaService;

/**
 * Template for home page
 */
@Controller
@Template(title = "Blinds home", id = "blinds-site:pages/home")
public class HomeTemplate {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory
			.getLogger(HomeTemplate.class);

	@Autowired
	private PageService pageService;

	@Area("tableCenterArea")
	@Controller
	public static class TableCenterArea {

		@Autowired
		TableAreaService tableAreaService;

		@TabFactory("Content")
		public void contentTab(TabBuilder tab) throws RepositoryException {
			tableAreaService.addTableAreaDialogControls(tab);
		}

		@RequestMapping("/homeTemplate/tableCenterArea")
		public String render(Node page, ModelMap model,
				HttpServletRequest request) throws RepositoryException,
				JAXBException {
			TableArea tableArea = tableAreaService.getTableArea(page);
			model.put("tableArea", tableArea);

			return "areas/tableArea.jsp";
		}
	}

	@Area("columnsCenterArea")
	@Controller
	public static class ColumnsCenterArea {

		@Autowired
		ColumnAreaService columnAreaService;

		@TabFactory("Content")
		public void contentTab(TabBuilder tab) throws RepositoryException {
			columnAreaService.addColumnAreaDialogControls(tab);
		}

		@RequestMapping("/homeTemplate/columnsCenterArea")
		public String render(Node page, ModelMap model,
				HttpServletRequest request) throws RepositoryException,
				JAXBException {
			ColumnArea columnArea = columnAreaService.getColumnArea(page);
			model.put("columnArea", columnArea);

			return "areas/columnArea.jsp";
		}
	}

	@RequestMapping("/homeTemplate")
	public String render(Node page, ModelMap model) throws RepositoryException {
		List<MenuItem> topMenus = pageService.getRootMenus(page);
		model.put("topMenus", topMenus);
		
		model.put("pageModel", pageService.getPageModel(page));
		
		return "pages/home.jsp";
	}

	@TabFactory("Content")
	public void propertiesDialog(TabBuilder tab) throws RepositoryException {
		pageService.addMainPageDialogControls(tab);
	}
}
