<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<p>
Hello! Please click the button below to switch to the next page!
<form:form method="post">
	<input type="submit" name="_eventId_next" value="Next" />
</form:form>
</p>
