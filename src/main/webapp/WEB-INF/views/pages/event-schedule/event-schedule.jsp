<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="classic-table">
	<tr>
		<th>eventScheudleId</th>
		<th>eventDate</th>
		<th>eventId</th>
		<th>auditoriumId</th>
		<th>Select Event Schedule</th>
	</tr>
	<c:forEach var="eventSchedule" items="${eventScheduleAll}">
		<tr>
			<td>${eventSchedule.eventScheduleId}</td>
			<td>${eventSchedule.eventDate}</td>
			<td>${eventSchedule.eventId}</td>
			<td>${eventSchedule.auditoriumId}</td>
			<td><input type="button" value="select"
				onclick="return location.href = '${pageContext.request.contextPath}/ticket/event-schedule/${eventSchedule.eventScheduleId}'"></td>
		</tr>
	</c:forEach>
</table>
<form action="${pageContext.request.contextPath}/event-schedule-pdf" method="POST">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
Open file as PDF<input type="submit" value="ok">
</form>