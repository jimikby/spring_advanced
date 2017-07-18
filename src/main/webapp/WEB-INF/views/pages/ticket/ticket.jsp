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
