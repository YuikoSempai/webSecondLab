<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP Application</title>
    <link rel="stylesheet" href="styles/style.css">
</head>
<body>
<p>X: ${x}</p>
<p>Y: ${y}</p>
<p>R: ${r}</p>
<p>Status: ${status}</p>

<div class="table-container">
    <table class="table">
        <tr>
            <th>R</th>
            <th>X coordinate</th>
            <th>Y coordinate</th>
            <th>Check status</th>
        </tr>
        <%--@elvariable id="list" type="java.util.List"--%>
        <td>${r}</td>
        <td>${x}</td>
        <td>${y}</td>
        <td>
            <c:choose>
                <c:when test="${status=='true'}">
                    Got it.
                </c:when>
                <c:otherwise>
                    Miss
                </c:otherwise>
            </c:choose>
        </td>
    </table>
    <br>
    <button type="button" class="redirection-button">Home page</button>
</div>
</body>
<script>
    document.querySelector('.redirection-button').onclick = function () {
        window.location.replace("./..");
    }
</script>