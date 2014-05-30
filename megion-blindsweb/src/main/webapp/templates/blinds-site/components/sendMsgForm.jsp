<%@ include file="../includes/webTaglibs.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String localUuid = "" +  System.currentTimeMillis();
%>

<c:if test="${errorMessage!=null}">
	<div class="errorMessage">${errorMessage}</div>
</c:if>

<div class="sendMsgForm">
	<form:form commandName="formData" method="POST" action="?">
		<blossom:pecid-input />
		<div class="form-row">
			<label class="control-label">Имя</label> <span
				class="required-marker">*</span>

			<form:input path="name" />
			&nbsp;<span class="form-input-error"><form:errors path="name" /></span>
		</div>

		<div class="form-row">
			<label class="control-label">Телефон</label> <span
				class="required-marker">*</span>

			<form:input path="phone"
				id="phone-<%=localUuid%>" />
			&nbsp;<span class="form-input-error"><form:errors path="phone" /></span>
		</div>

		<div class="form-row">
			<label class="control-label">Текст</label> <span
				class="required-marker">*</span> &nbsp;<span
				class="form-input-error"><form:errors path="message" /></span>
			<form:textarea path="message" cols="60" rows="5" cssClass="textinput" />
		</div>

		<div class="captcha-label">Введите символы:</div>
		<div style="margin: 0 0 0.5em">
			<span class="form-input-error"><form:errors path="captchaText" />&nbsp;</span>
		</div>
		<div class="captcha-content">
			<div class="captcha-element">
				<c:if test="${getCaptchaResult!=null}">
					<form:input path="captchaKey" type="hidden"
						value="${getCaptchaResult.captcha}" />
					<img src="${getCaptchaResult.url}" />
				</c:if>
			</div>
			<div class="captcha-element" style="width: 10px;"></div>
			<div class="captcha-element">
				<form:input path="captchaText" cssClass="captcha-input"
					autocomplete="off" />
				<c:if test="${checkSpamResult!=null}">
					<form:input path="checkSpamId" type="hidden"
						value="${checkSpamResult.id}" />
				</c:if>
			</div>

		</div>
		
		<button type="submit">Отправить</button>

	</form:form>
</div>

<script type="text/javascript">
    var phoneId = "#phone-" + "<%=localUuid%>";
	//jQuery().mask.definitions['~']='[+-]';
	jQuery(phoneId).mask('(999) 999-9999');
</script>
