// объект пространства имен вспомогательных функций 
var utils = new Object();

var lastPopUpDialog = null;

/**
 * Остановить распространение события далее
 */
utils.stopEventPropagation = function(event) {
	// IE
	if (jQuery.browser.msie) {
		window.event.cancelBubble = true;
	}
	if (event.stopPropagation) {
		event.stopPropagation();
	}
};

utils.onMenuDropDownClick = function(event, link) {
	utils.stopEventPropagation(event);
	utils.closeLastPopUpDialog();
	utils.showDropDownMenu(link);

	return false;
};


jQuery(document).ready(function() {
	/*document.onclick = function (event) {
		if (!lastPopUpDialog) {
	    	return;
	    }
		
		if (event.originalTarget == lastPopUpDialog || event.srcElement == lastPopUpDialog) {
	    	utils.stopEventPropagation(event);
	    	return;
	    }
		
		utils.closeLastPopUpDialog();
    };*/
});

utils.closeLastPopUpDialog = function() {
	if (lastPopUpDialog) {
		lastPopUpDialog.style.display = "none";
		jQuery(lastPopUpDialog.parentNode).removeClass("opened-node");
    }
};

utils.showDropDownMenu = function(link) {
	//var xy = getOffsetRect(link);
	
	//console.info("Li over (x,y,xOffset): " + xy.left + ", " + xy.top + ", " + nodeLi.offsetWidth);
	
	var nodeLi = link.parentNode;
	jQuery(nodeLi).addClass("opened-node");
	for ( var x1 = 0; nodeLi.childNodes[x1]; x1++) {
		var subChild = nodeLi.childNodes[x1];
		if (subChild.nodeName.toLowerCase() == "ul") {
			subChild.style.display="block";
			lastPopUpDialog = subChild;
			//subChild.style.left = xy.left + link.offsetWidth + "px";
			//subChild.style.top = xy.top + "px";
			
			return;
		}
	}
};



/**
 * Обработчик события наведения мыши на пункт меню 
 */
utils.onMenuMouseOver = function(event, nodeLi) {
	utils.stopEventPropagation(event);

	var xy = getOffsetRect(nodeLi);
	
	//console.info("Li over (x,y,xOffset): " + xy.left + ", " + xy.top + ", " + nodeLi.offsetWidth);
	
	for ( var x1 = 0; nodeLi.childNodes[x1]; x1++) {
		var subChild = nodeLi.childNodes[x1];
		if (subChild.nodeName.toLowerCase() == "ul") {
			subChild.style.left = xy.left + Math.round(nodeLi.offsetWidth/2) + "px";
			subChild.style.top = xy.top + Math.round(nodeLi.offsetHeight/2) + "px";
		}
	}

	return false;
};

/**
 * Кросс-браузерный способ
 * получения координат курсора из события в обработчике а так же определение нажатой кнопки мыши.
 * 
 * @param e
 * @returns
 */
function fixEvent(e) {
	// получить объект событие для IE
	e = e || window.event;

	// добавить pageX/pageY для IE
	if ( e.pageX == null && e.clientX != null ) {
		var html = document.documentElement;
		var body = document.body;
		e.pageX = e.clientX + (html && html.scrollLeft || body && body.scrollLeft || 0) - (html.clientLeft || 0);
		e.pageY = e.clientY + (html && html.scrollTop || body && body.scrollTop || 0) - (html.clientTop || 0);
	}

	// добавить which для IE
	if (!e.which && e.button) {
		e.which = e.button & 1 ? 1 : ( e.button & 2 ? 3 : ( e.button & 4 ? 2 : 0 ) );
	}

	return e;
}

function getOffset(elem) {
    if (elem.getBoundingClientRect) {
        return getOffsetRect(elem);
    } else {
        return getOffsetSum(elem);
    }
}

function getOffsetRect(elem) {
    var box = elem.getBoundingClientRect();
 
    var body = document.body;
    var docElem = document.documentElement;
 
    var scrollTop = window.pageYOffset || docElem.scrollTop || body.scrollTop;
    var scrollLeft = window.pageXOffset || docElem.scrollLeft || body.scrollLeft;
    var clientTop = docElem.clientTop || body.clientTop || 0;
    var clientLeft = docElem.clientLeft || body.clientLeft || 0;
    var top  = box.top +  scrollTop - clientTop;
    var left = box.left + scrollLeft - clientLeft;
 
    return { top: Math.round(top), left: Math.round(left) };
}

function getOffsetSum(elem) {
    var top=0, left=0;
    while(elem) {
        top = top + parseInt(elem.offsetTop);
        left = left + parseInt(elem.offsetLeft);
        elem = elem.offsetParent;
    }
 
    return {top: top, left: left};
}

function emptyFalseFn() {
	return false;
}

function testAjax(url) {
	jQuery.ajax({
		url : url,
		dataType : "json",
		data : {
			"nodeKey" : "test"
		},
		success : function(loadedData) {
			alert("success: " + loadedData);
		}
	});
}

