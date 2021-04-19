<%--
- form.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.dashboard.form.title.general-indicators"/>
	</caption>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-public-tasks"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPublicTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-private-tasks"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPrivateTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-finished-tasks"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfFinishedTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-non-finished-tasks"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfNonFinishedTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-number-task-execution-period"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfTaskExecutionPeriods}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-number-task-execution-period"/>
		</th>
		<td>
			<acme:print value="${deviationNumberOfTaskExecutionPeriods}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-number-task-execution-period"/>
		</th>
		<td>
			<acme:print value="${minimumNumberOfTaskExecutionPeriods}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-number-task-execution-period"/>
		</th>
		<td>
			<acme:print value="${maximumNumberOfTaskExecutionPeriods}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-number-task-workloads"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfTaskWorkloads}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-number-task-workloads"/>
		</th>
		<td>
			<acme:print value="${deviationNumberOfTaskWorkloads}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-number-task-workloads"/>
		</th>
		<td>
			<acme:print value="${minimumNumberOfTaskWorkloads}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-number-task-workloads"/>
		</th>
		<td>
			<acme:print value="${maximumNumberOfTaskWorkloads}"/>
		</td>
	</tr>
	
</table>


