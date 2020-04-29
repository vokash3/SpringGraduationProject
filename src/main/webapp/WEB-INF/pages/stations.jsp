<%--
  ~     Copyright © Kashin Vladimir Olegovich (Wain Key), 2020.
  ~     This project is a part of a graduation qualification work of Bauman Moscow State Technical University (BMSTU) student. 
  ~     If You use this project or a part of it for your own purposes please make sure that You indicated authorship. 
  --%>

<%--
  Created by IntelliJ IDEA.
  User: Владимир
  Date: 13.04.2020
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>STATIONS DATA</title>
    <link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>

<h2>Stations Data</h2>
<%--<div>Загрузка данных в формате JSON </div>--%>
<%--<form method="POST" action="uploadFile" enctype="multipart/form-data">--%>
<%--    File to upload: <input type="file" name="file"><br />--%>

<%--    <input type="submit" value="Upload">--%>
<%--    Press here to upload the file!--%>
<%--</form>--%>
<center><table>
    <tr>
        <th>№ Записи</th>
        <th>Station №</th>
        <th>Date</th>
        <th>Power</th>
    </tr>
    <c:choose>
        <c:when test="${stationDataList.size() != 0}">
            <c:forEach var="stations" items="${stationDataList}">
                <tr>
                    <td>${stations.number}</td>
                    <td>${stations.station}</td>
                    <td>${stations.date}</td>
                    <td>${stations.power}</td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td style="text-align: center" colspan="4">Нет данных</td>
            </tr>
        </c:otherwise>
    </c:choose>
    <br/>
</table></center>
</body>
</html>
