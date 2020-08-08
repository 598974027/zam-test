<%--
  @description: new jsp
  @author: zhangaomin
  @time: 2020/7/13 17:00
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fn" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>${message}</h1>

<%
    System.out.println("This is printed by System.out.println.");
    out.println("This is printed by out.println.");
%>

</body>
</html>
