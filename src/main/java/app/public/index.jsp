<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Lab work 2</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="styles/style.css">
</head>
<body>
<table id="site-header" border="1" cellpadding="0" cellspacing="0" width="100%">
    <tr>
        <th colspan=2 class="site-header">
            <p>Author: Bekmukhametov Vladislav</p>
            <p>Group: P3119</p>
            <p>ISU Number: 335150</p>
        </th>
    </tr>
    <tr>
        <th width=50%><h2 class="form-name">Entry Form</h2></th>
        <th><h2 class="table-name">Table</h2></th>
    </tr>
    <tr>
        <td>
            <form class="form">
                <div>
                    <p>Radius</p>
                    <input type="radio" id="1Radius"
                           name="radius" value="1" oninput="setInput('1Radius')">

                    <label for="1Radius">1</label>
                    <input type="radio" id="2Radius"
                           name="radius" value="2" oninput="setInput('2Radius')">
                    <label for="2Radius">2</label>
                    <input type="radio" id="3Radius"
                           name="radius" value="3" checked="checked" oninput="setInput('3Radius')">
                    <label for="3Radius">3</label>
                    <input type="radio" id="4Radius"
                           name="radius" value="4" oninput="setInput('4Radius')">
                    <label for="4Radius">4</label>
                    <input type="radio" id="5Radius"
                           name="radius" value="5" oninput="setInput('5Radius')">
                    <label for="5Radius">5</label>
                </div>
                <p>X coordinate</p>
                <input type="checkbox" id="-x5" name="checkX" value="-5">
                <label for="-x5">-5</label>
                <input type="checkbox" id="-x4" name="checkX" value="-4">
                <label for="-x4">-4</label>
                <input type="checkbox" id="-x3" name="checkX" value="-3">
                <label for="-x3">-3</label>
                <input type="checkbox" id="-x2" name="checkX" value="-2">
                <label for="-x2">-2</label>
                <input type="checkbox" id="-x1" name="checkX" value="-1">
                <label for="-x1">-1</label>
                <input type="checkbox" id="x0" name="checkX" value="0">
                <label for="x0">0</label>
                <input type="checkbox" id="x1" name="checkX" value="1">
                <label for="x1">1</label>
                <input type="checkbox" id="x2" name="checkX" value="2">
                <label for="x2">2</label>
                <input type="checkbox" id="x3" name="checkX" value="3">
                <label for="x3">3</label>
                <p>
                    <label>
                        <input type="text" name="y" placeholder="Y coordinate" maxlength=10>
                    </label>
                </p>
                <button class="check-button" type="submit">CHECK</button>
                <br>
                <br>
                Input parameters status:
                <text class="validation-message">waiting for input</text>
            </form>
        </td>
        <td>
            <div class="table-container">
                <table class="table">
                    <tr>
                        <th>R</th>
                        <th>X coordinate</th>
                        <th>Y coordinate</th>
                        <th width=40%>Check status</th>
                    </tr>
                    <%--@elvariable id="list" type="java.util.List"--%>
                    <c:forEach items="${list}" var="d">
                        <tr>
                            <td>${d.r}</td>
                            <td>${d.x}</td>
                            <td>${d.y}</td>
                            <td><c:choose>
                                <c:when test="${d.status=='true'}">
                                    Got it.
                                </c:when>
                                <c:otherwise>
                                    Miss
                                </c:otherwise>
                            </c:choose></td>
                        </tr>
                    </c:forEach>
                </table>
                <br>
                <button type="button" class="clear-button">Clear the table</button>
                <input
                        type="checkbox"
                        id="uploadCheckBox"
                        name="subscribe"
                class="uploadCheckBox"/>
                <label for="uploadCheckBox">Redirect to result</label>
            </div>

        </td>
    </tr>
    <tr>
        <td colspan=2>
            <h2 class="task">Task</h2>
        </td>
    </tr>
    <tr>
        <td colspan=2>
            <canvas width="400" height="400" id="image"></canvas>
        </td>
    </tr>
    <tr>
        <td colspan=2>
            <footer class="site-footer">
                <div class="container">
                    <p>&copy;Yuiko, 2022</p>
                    <p>Web-Programming</p>
                </div>
            </footer>
        </td>
    </tr>
</table>
</body>
<script src="libs/jquery-3.6.0.js"></script>
<script src="scripts/script.js"></script>
<script>draw()</script>
<script>
    $(document).ready(function () {
        $('input:checkbox').click(function () {
            $('input:checkbox').not(this).prop('checked', false);
        });
    });
</script>
<script>setInput('3Radius')</script>
</html>