<%--@elvariable id="timestamp" type="long"--%>
<%--@elvariable id="numberOfSessions" type="int"--%>
<%--@elvariable id="sessionList" type="java.util.List<javax.servlet.http.HttpSession>"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jsun" uri="http://www.jsun.com/jsp/tld/jsun" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message code="title.sessionList" var="sessionTitle" />
<template:basic htmlTitle="${sessionTitle}" bodyTitle="${sessionTitle}">
    <spring:message code="message.sessionList.instruction">
        <spring:argument value="${numberOfSessions}" />
    </spring:message><br /><br />
    <c:forEach items="${sessionList}" var="s">
        <c:out value="${s.id} - ${s.getAttribute('com.jsun.user.principal')}" />
        <c:if test="${s.id == pageContext.session.id}">
            (<spring:message code="message.sessionList.you" />)</c:if>
        - <spring:message code="message.sessionList.lastActive" />
        ${jsun:timeIntervalToString(timestamp - s.lastAccessedTime)} ago<br />
    </c:forEach>
</template:basic>
