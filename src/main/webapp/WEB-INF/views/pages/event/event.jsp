<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="classic-table">
	<tr>
		<th>eventId</th>
		<th>eventName</th>
		<th>rating</th>
		<th>basePrice</th>
		<th>Select Event</th>
	</tr>
	<c:forEach var="event" items="${eventAll}">
		<tr>
			<td>${event.eventId}</td>
			<td>${event.eventName}</td>
			<td>${event.rating}</td>
			<td>${event.basePrice}</td>
			<td><input type="button" value="select"
				onclick="return location.href = '${pageContext.request.contextPath}/event-schedule/event/${event.eventId}'"></td>
		</tr>
	</c:forEach>
</table>
<form action="${pageContext.request.contextPath}/event-pdf" method="POST">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
Open file as PDF<input type="submit" value="ok">
</form>