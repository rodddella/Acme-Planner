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

<acme:form readonly="true">
	<acme:form-textbox code="manager.task.form.label.title" path="title"/>
	<acme:form-textarea code="manager.task.form.label.description" path="description"/>
	<acme:form-double code="manager.task.form.label.workload" path="workload"/>
	<acme:form-textbox code="manager.task.form.label.start_period" path="startPeriod"/>
	<acme:form-textbox code="manager.task.form.label.end_period" path="endPeriod"/>
	<acme:form-select code="manager.task.form.label.visibility" path="visibility">
		<acme:form-option code="PUBLIC" value="PUBLIC" selected= "${visibility == 'PUBLIC'}"/>
		<acme:form-option code="PRIVATE" value="PRIVATE" selected= "${visibility == 'PRIVATE'}"/>
	</acme:form-select>
	
  	<acme:form-return code="manager.task.form.button.return"/>
</acme:form>
