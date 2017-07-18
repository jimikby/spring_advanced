<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="classic-table">
	<tr>
		<th>counterId</th>
		<th>typeName</th>
		<th>typeId</th>
		<th>counterName</th>
		<th>counterValue</th>
	</tr>
	<c:forEach var="counter" items="${counterAll}">
		<tr>
			<td>${counter.counterId}</td>
			<td>${counter.typeName}</td>
			<td>${counter.typeId}</td>
			<td>${counter.counterName}</td>
			<td>${counter.counterValue}</td>
		</tr>
	</c:forEach>
</table>
<form action="${pageContext.request.contextPath}/counter-pdf"
	method="POST">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" /> Open file as PDF<input type="submit"
		value="ok">
</form>