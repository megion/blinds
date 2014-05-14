/**
 * Объект управляющий pop-up menu
 */
var popupMaster = (function() {

	/*
	 * хранит информацию об всплывающем окне
	 */
	var lastPopup = null;

	function showPopup(openCtrl) {
		parentLi = openCtrl.parentNode;
		menuLink = null;
		menuWindow = null;
		for ( var x1 = 0; parentLi.childNodes[x1]; x1++) {
			var subChild = parentLi.childNodes[x1];
			if (subChild.nodeName.toLowerCase() == "a" && jQuery(subChild).hasClass("drop-down-link")) {
				menuLink = subChild;
			} else if (subChild.nodeName.toLowerCase() == "div" && jQuery(subChild).hasClass("drop-down-window")) {
				menuWindow = subChild;
			}
		}

		var popup = {
			container : parentLi,
			openLink : openCtrl,
			menuLink: menuLink,
			popupWindow: menuWindow,
			//openElementsFunc : function(elementLi) {elementLi.style.display = "block";},
			//closeElementsFunc : function(elementLi) {elementLi.style.display = "none";},
			open : false
		};
		renderPopup(popup);
		lastPopup = popup;
		addDocumentEventHandlers();
		return false;
	}
	
	function applayPopupBodyElements(popup, applayElementsFunc) {
		var mainUl = popup.popupContainer.parentNode;
		var canApplay = false;
		for ( var x1 = 0; mainUl.childNodes[x1]; x1++) {
			var childLi = mainUl.childNodes[x1];
			var isSubChildMenuItem = jQuery(childLi).hasClass("sub-child-menu-item") && (childLi.nodeName.toLowerCase() == "li");
			if (isSubChildMenuItem) {
				if (canApplay) {
					applayElementsFunc(childLi);
				}
			} else if (childLi.nodeName.toLowerCase() == "li") {
				if (childLi==popup.popupContainer) {
					// т.к. далее должны идти элементы дочернего меню
					canApplay = true;
				} else  {
					// элементы дочернего меню закончились
					canApplay = false;
				}
			}
			
		}
	}
	
	function closePopup(popup) {
		//popup.menuLink.style.display = "block";
		//popup.openLink.style.display = "block";
		popup.popupWindow.style.display = "none";
		jQuery(popup.container).removeClass("opened-node");
		popup.open = false;
	}

	function renderPopup(popup) {
		if (lastPopup) {
			if (lastPopup.container == popup.container && lastPopup.open) {
				// закрыть и выйти
				closePopup(lastPopup);
				return;
			}
			
			// просто закрыть
			closePopup(lastPopup);
		}
		
		popup.popupWindow.style.display = "block";
		jQuery(popup.container).addClass("opened-node");
		popup.open = true;
	}
	

	function mouseClickByDocument(event) {
		if (lastPopup==null || !lastPopup.open) {
	    	return;
	    }
		
		var clickOnPopupBody = false;
		/*applayPopupBodyElements(lastPopup, function(elementLi) {
			if (event.originalTarget == elementLi || event.srcElement == elementLi) {
				clickOnPopupBody = true;	
		    }
		});*/
		
		if (clickOnPopupBody) {
			utils.stopEventPropagation(event);
	    	return;
		}
		
		
		
		closePopup(lastPopup);

		// (3)
		removeDocumentEventHandlers();
	}

	function addDocumentEventHandlers() {
		document.onclick = mouseClickByDocument;
		document.ondragstart = document.body.onselectstart = emptyFalseFn;
	}
	function removeDocumentEventHandlers() {
		document.onclick = document.ondragstart = document.body.onselectstart = null;
	}

	return {

		openPopupMenu : function(event, openControl) {
			utils.stopEventPropagation(event);
			showPopup(openControl);
		},
	
		closePopupMenu : function(event) {
			utils.stopEventPropagation(event);
			closePopup(lastPopup);
		}
	};
}());