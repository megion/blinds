<%@ include file="../includes/webTaglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<jsp:include page="../includes/pageHeader.jsp"></jsp:include>
</head>
<body>
	<div>
		<div id="cnt">
			<div id="appbar"></div>
			<div class="mw">
				<div id="rcnt" style="clear: both; position: relative;">
					<div>
						<div id="leftnavc">
							<div style="position: absolute; top: 1px; width: 132px;"
								id="leftnav">
								<div id="ms">
									<jsp:include page="../includes/topMenu.jsp"></jsp:include>
								</div>
							</div>
						</div>
					</div>
					<div id="center_col" style="visibility: visible;">
						<div id="main">
							<cms:area name="tableCenterArea"
								components="standard-templating-kit:components/features/stkImageGallery,megion-site:components/geoMap,megion-site:components/fulltextSearch,megion-site:components/contactForm,standard-templating-kit:components/content/stkTextImage" />
							<cms:area name="columnsCenterArea"
								components="standard-templating-kit:components/features/stkImageGallery,megion-site:components/geoMap,megion-site:components/fulltextSearch,megion-site:components/contactForm,standard-templating-kit:components/content/stkTextImage" />
						</div>
					</div>
				</div>
				<div id="foot" class="tsf-p"></div>
			</div>
		</div>
	</div>
</body>
</html>
