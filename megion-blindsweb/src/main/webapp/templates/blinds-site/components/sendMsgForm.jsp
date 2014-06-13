<%@ include file="../includes/webTaglibs.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String localUuid = "" +  System.currentTimeMillis();
%>

<c:if test="${errorMessage!=null}">
	<div class="errorMessage">${errorMessage}</div>
</c:if>

<div class="sendMsgForm" id="formContainer-<%=localUuid%>">
	<form:form commandName="formData" method="POST" action="?">
		<blossom:pecid-input />
		<div class="control-row">
			<div class="control-label">
				<label class="control-label">Имя</label> <span
					class="required-marker">*</span>
			</div>

			<div class="control-input">
				<form:input path="name" />
			</div>

			<div class="control-error">
				<form:errors path="name" />
			</div>
		</div>

		<div class="control-row">
			<div class="control-label">
				<label class="control-label">Телефон</label> <span
					class="required-marker">*</span>
			</div>

			<div class="control-input">
				+7
				<form:input path="phone" cssClass="phone-input" />
			</div>

			<div class="control-error">
				<form:errors path="phone" />
			</div>
		</div>

		<div class="control-row">
			<div class="control-label">
				<label class="control-label">Текст сообщения</label> <span
					class="required-marker">*</span>
			</div>

			<div class="control-input">
				<form:textarea path="message" cols="60" rows="5" />
			</div>

			<div class="control-error">
				<form:errors path="message" />
			</div>
		</div>

		<div class="control-row">
			<div class="captcha-label">Введите символы:</div>
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

			<div class="control-error">
				<form:errors path="captchaText" />
			</div>
		</div>

		<button type="submit" class="submit-button">Отправить</button>

	</form:form>
</div>

<script type="text/javascript">
    var containerId = "#formContainer-" + "<%=localUuid%>";
	var form = jQuery(containerId).find("form");
	var phone = form.find("input[name='phone']");
	phone.maskedInput('(999) 999-99-99', {
		definitions : {
			'~' : '[+-]'
		}
	});
</script>
