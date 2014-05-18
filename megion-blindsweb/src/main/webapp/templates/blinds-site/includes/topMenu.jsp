<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../includes/webTaglibs.jsp"%>

<!-- Рисуем один уровень вложенности верхнего меню -->
<ul class="navigation">
    <%
			List<MenuItem> menus = (List<MenuItem>) request.getAttribute("topMenus");
			int counter = 0;
		%>
	<c:forEach var="menu" items="${topMenus}" varStatus="status">
	<%
				String menuCssClass = "main-item";
					if (counter == 0) {
						menuCssClass = menuCssClass + " first-item";
					}

					if ((counter + 1) >= menus.size()) {
						menuCssClass = menuCssClass + " last-item";
					}
			%>
			<c:if test="${menu.selected}">
				<%
					menuCssClass = menuCssClass + " selected";
				%>
			</c:if>
		<li class="<%=menuCssClass%>">
		    <c:choose>
				<c:when test="${not empty menu.replacementURL}">
					<a href="<c:url value="${menu.replacementURL}" />">${menu.name}</a>
				</c:when>
				<c:otherwise>
					<a href="<c:url value="${menu.URL}" />">${menu.name}</a>
				</c:otherwise>
			</c:choose>
		</li>
			
		<%
			counter++;
		%>
	</c:forEach>
</ul>


