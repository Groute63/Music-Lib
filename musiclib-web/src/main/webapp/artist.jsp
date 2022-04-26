<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Driver" %>
<%@ page import="company.storage.dao.database.databasestorage.DBStorage" %>
<%@ page import="company.storage.dao.DaoType" %>
<%@ page import="company.entityclass.EntityClassMarker" %>
<%@ page import="java.util.Collection" %>
<%@ page import="company.entityclass.Artist" %>
<%@ page import="company.entityclass.Album" %>
<%@ page import="java.util.List" %>
<%@ page import="company.entityclass.Song" %><%--
  Created by IntelliJ IDEA.
  User: саша
  Date: 16.03.2022
  Time: 7:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="artistMenu.css">
    <script></script>
</head>
<body>
<%
    DriverManager.registerDriver((Driver) Class.forName("org.postgresql.Driver").newInstance());
    DBStorage db = DBStorage.getInstance();
    Collection<? extends EntityClassMarker> col = db.get(DaoType.ARTIST).values();
%>
<br>
<table>
    <tr>
        <th> Артисты</th>
        <th> Альбомы</th>
        <th> Треки</th>
    </tr>
    <%
        int name = 0;
        for (EntityClassMarker art : col) {
            List<Album> alb = ((Artist) art).getAlbums();
    %>
    <tr>
        <td><%=((Artist) art).getName()%>
        </td>
            <%
            for (int i = 0; i < alb.size(); i++) {
                List<Song> song = alb.get(i).getSongs();
                if (i == 0) {
        %>
        <td><%=alb.get(i).getName()%>
        </td>
            <%} else {%>
    <tr>
        <td></td>
        <td><%=alb.get(i).getName()%>
        </td>
        <%}%>
        <%
            for (int j = 0; j < song.size(); j++) {
                if (j == 0) {
        %>
        <td>
            <button onclick="getSongInfo(<%=name%>)" type="button"><%=song.get(j).getName()%>
            </button>
        </td>
    </tr>
    <%} else {%>
    <tr>
        <td></td>
        <td></td>
        <td>
            <button onclick="getSongInfo(<%=name%>)" type="button" id ><%=song.get(j).getName()%>
            </button>
        </td>
    </tr>
    <%}%>
    <div class="info" id="<%=name%>">Длительность - <%= song.get(j).getLength()%> тэги - <%= song.get(j).getTags()%> </div>
    <%name++;
            }%>
    <%}%>
    <%}%>
</table>
<div id="song"></div>
<script type="text/javascript" src="test.js"></script>
</body>
</html>
