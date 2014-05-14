<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../includes/webTaglibs.jsp"%>

<!-- Рисуем один уровень вложенности верхнего меню -->
<ul class="subnavigation">
    <%
			List<MenuItem> menus = (List<MenuItem>) request.getAttribute("subMenus");
			int counter = 0;
		%>
	<c:forEach var="menu" items="${subMenus}" varStatus="status">
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
		<li class="<%=menuCssClass%>"><a
			href="<c:url value="${menu.URL}" />">${menu.name}</a></li>
			
		<%
			counter++;
		%>
	</c:forEach>
</ul>


