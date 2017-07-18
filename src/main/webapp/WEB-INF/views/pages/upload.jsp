 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>

<body>
	<h2>Upload Form</h2>

	<form:form method="POST" commandName="fileUploadForm"
		enctype="multipart/form-data">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
		<form:errors path="*" cssClass="errorblock" element="div" />

		Please select a 'users.json' file to upload : <input type="file" name="users" />
		Please select a 'events.json' file to upload : <input type="file" name="events" />
		<input type="submit" value="upload" />
		<span><form:errors path="file" cssClass="error" />
		</span>

	</form:form>

</body>
</html>