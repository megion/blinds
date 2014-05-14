<%@ include file="../includes/webTaglibs.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:if test="${promoArea.enableCustomization}">
	<div class="promos">
		<c:forEach items="${components}" var="component">
			<div class="promo-block">
				<cms:component content="${component}" />
			</div>
		</c:forEach>
	</div>
</c:if>

<c:if test="${not promoArea.hideStaticPromos}">
	<jsp:include page="../includes/staticPromos.jsp"></jsp:include>
</c:if>


