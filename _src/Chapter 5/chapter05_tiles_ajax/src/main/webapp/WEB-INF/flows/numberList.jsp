<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<div id="numberDiv">
    <c:forEach items="${numberList}" var="item">
    <li>
        <c:out value="${item}" />
    </li>
    </c:forEach>
</div>
