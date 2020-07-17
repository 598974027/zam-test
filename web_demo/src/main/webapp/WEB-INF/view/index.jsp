<%--
  @description: new jsp
  @author: zhangaomin
  @time: 2020/7/13 17:00
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1><% System.out.println(request.getRequestURL().toString()); %></h1>
<h1><% System.out.println(response.getStatus()); %></h1>
<h1>${message}</h1>

</body>
</html>
