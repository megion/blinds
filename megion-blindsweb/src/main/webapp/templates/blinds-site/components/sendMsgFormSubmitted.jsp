<%@ include file="../includes/webTaglibs.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:if test="${formDialog.hasSuccessText}">
	${formDialog.successText}
</c:if>
