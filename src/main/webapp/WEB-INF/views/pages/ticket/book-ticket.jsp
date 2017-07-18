<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="classic-table">
	<tr>
	<th>ticketId</th>
	<th>ticketCost</th>
	<th>eventScheduleId</th>
	<th>seat</th>
	<th>userId</th>
	<th>discount</th>
	</tr>
	<c:forEach var="ticket" items="${tickets}">
		<tr>
			<td>${ticket.ticketId}</td>
			<td>${ticket.ticketCost}</td>
			<td>${ticket.eventScheduleId}</td>
			<td>${ticket.seat}</td>
			<td>${ticket.userId}</td>
			<td>${ticket.discount}</td>
		</tr>
	</c:forEach>
</table>
<form action="${pageContext.request.contextPath}/booking" method="POST">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
<input name="ticketNumbers" type="text" value="">
<input name="eventScheduleId" type="hidden" value="${eventScheduleId}">

<security:authorize access="isAuthenticated()">
<input name="email" type="hidden" value="<security:authentication property="principal.username" />">
</security:authorize>
Book tickets (comma separated)<input type="submit" value="ok">
</form>