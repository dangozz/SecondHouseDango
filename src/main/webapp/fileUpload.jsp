<%--
  Created by IntelliJ IDEA.
  User: Dango
  Date: 2018/8/22
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<script src="<%=basePath%>/jquery-3.3.1.min.js"></script>
<html>
<head>
    <title>文件上传测试</title>
</head>
<body>
<%--<form action="${pageContext.request.contextPath}/test/file-upload.do" method="post" enctype="multipart/form-data">--%>
<form action="<%=basePath%>/file/test1.do" method="post" enctype="multipart/form-data">
    <input type="file" name="file1">
    <input type="file" name="file2">
    <input type="file" name="file3">
    <input type="text" name="test">
    <input type="submit" value="dango">
</form>

<form id="from2">
    <input type="file" name="from2_1" multiple="multiple">
    <input type="text" name="test2">
    <input type="button" value="dango2" onclick="from2_test();">
</form>


</body>

<script>
    function from2_test() {
        var form = new FormData(document.getElementById("from2"));
        $.ajax({
            url: "<%=basePath%>/file/test2.do",
            type: "post",
            data: form,
            processData: false,
            contentType: false,
            success: function (data) {
                console.log("success" + data.message);
            },
            error: function (e) {
                console.log("err");

            }
        });
    }


</script>

</html>
