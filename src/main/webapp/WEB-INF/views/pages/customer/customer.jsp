<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="classic-table">
	<tr>
		<th>customerId</th>
		<th>firstName</th>
		<th>lastName</th>
		<th>role</th>
		<th>birthDay</th>
		<th>email</th>
		<th>password</th>
	</tr>
	<c:forEach var="customer" items="${customerAll}">
		<tr>
			<td>${customer.customerId}</td>
			<td>${customer.firstName}</td>
			<td>${customer.lastName}</td>
			<td>${customer.role}</td>
			<td>${customer.birthDay}</td>
			<td>${customer.email}</td>
			<td>${customer.password}</td>
		</tr>
	</c:forEach>
</table>
<form action="${pageContext.request.contextPath}/customer-pdf" method="POST">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
Open file as PDF<input type="submit" value="ok">
</form>

