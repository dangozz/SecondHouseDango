<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html>
<body>
<h2>Hello World!</h2>
<a href="<%=basePath%>/websocketTest1.jsp">1号</a>
<a href="<%=basePath%>/fileUpload.jsp">上传测试</a>
</body>
</html>
