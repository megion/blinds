<%@ include file="../includes/webTaglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<jsp:include page="../includes/pageHeader.jsp"></jsp:include>
</head>
<body>
    
    <jsp:include page="../includes/topLayout.jsp"></jsp:include>

	<div class="content">
		<div id="main">
			<div class="left-area">
				<div class="menu-block">
					<jsp:include page="../includes/topMenu.jsp"></jsp:include>
				</div>
				<div class="promo-block">
					<cms:area name="promosArea"
						components="megion-site:components/textPromo,megion-site:components/promo,megion-site:components/imageOverlay,standard-templating-kit:components/content/stkTextImage" />
				</div>
			</div>
			<div class="center-area">
				<!--  
				<h1>${content.title}</h1>
				-->
				<cms:area name="centerArea"
					components="standard-templating-kit:components/features/stkImageGallery,standard-templating-kit:components/content/stkTextImage,blinds-site:components/sendMsg,megion-site:components/geoMap,megion-site:components/imageOverlay" />
			</div>
		</div>
	</div>
	
	<jsp:include page="../includes/footer.jsp"></jsp:include>

</body>
</html>
