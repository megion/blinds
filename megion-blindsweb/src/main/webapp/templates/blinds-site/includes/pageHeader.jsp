<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../includes/webTaglibs.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<cms:init />

<c:if test="${not empty content.metaDescription}"><meta name="description" content="${content.metaDescription}" /></c:if>
<c:if test="${not empty content.metaKeywords}"><meta name="keywords" content="${content.metaKeywords}" /></c:if>
<c:if test="${not empty content.metaCopyright}"><meta name="copyright" content="${content.metaCopyright}" /></c:if>
<c:choose>
	<c:when test="${not empty content.metaRobots}">
		<meta name="robots" content="${content.metaRobots}">
	</c:when>
	<c:otherwise>
		<meta name="robots" content="all">
	</c:otherwise>
</c:choose>

<!--[if IE]>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=IE7" />
<![endif]-->
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<c:choose>
	<c:when test="${pageHeader!=null}">
		<title>${pageHeader}</title>
	</c:when>
	<c:otherwise>
		<title>${content.title}</title>
	</c:otherwise>
</c:choose>

<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/docroot/blinds-site/favicon.ico" />

<c:forEach items="${pageModel.theme.cssFiles}" var="cssFile">
    <c:choose>
		<c:when test="${not empty cssFile.conditionalComment}">
			<!--[if ${cssFile.conditionalComment}]>
		    <link rel="stylesheet" type="text/css" href="${cssFile.link}" media="${cssFile.media}" />
		    <![endif]-->
		</c:when>
		<c:otherwise>
			<link rel="stylesheet" type="text/css" href="${cssFile.link}"
				media="${cssFile.media}" />
		</c:otherwise>
	</c:choose>
</c:forEach>

<!--[if ! lte IE 6]><!-->
<script>
	//global information for the javascripts
	window.magnoliaFrontendData = {
		themePath : "${pageContext.request.contextPath}/resources/templating-kit/",
		contextPath : "${pageContext.request.contextPath}",
		isEdit : true,
		texts : {
			lightbox : {
				close : "close lightbox",
				next : "next picture"
			},
			close : "close"
		},
		cfg : {

		}
	};
</script>	
<c:forEach items="${pageModel.theme.jsFiles}" var="item">
    <script type="text/javascript" src="${item.link}"></script>
</c:forEach>
<!--<![endif]-->


<!-- /cms:area -->

<!-- megion-site resources -->
<link
	href="${pageContext.request.contextPath}/docroot/megion-site-core/css/components.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/docroot/megion-site-core/css/tags.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/docroot/megion-site-core/css/areas.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/docroot/blinds-site/css/page.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/docroot/blinds-site/css/components.css"
	rel="stylesheet" type="text/css" />


<script type="text/javascript"
	src="${pageContext.request.contextPath}/docroot/blinds-site/js/general-utils.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/docroot/megion-site-core/js/jquery.maskedinput.js"></script>
