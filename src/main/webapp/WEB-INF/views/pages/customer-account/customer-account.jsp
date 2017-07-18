<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="classic-table">
	<tr>
		<th>customerAccountId</th>
		<th>customerId</th>
		<th>customerMoney</th>
	</tr>
	<c:forEach var="customerAccount" items="${customerAccounts}">
		<tr>
			<td>${customerAccount.customerAccountId}</td>
			<td>${customerAccount.customerId}</td>
			<td>${customerAccount.customerMoney}</td>

		</tr>
	</c:forEach>
</table>