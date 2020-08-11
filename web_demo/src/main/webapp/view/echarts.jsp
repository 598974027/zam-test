<%--
  @description: new jsp
  @author: zhangaomin
  @time: 2020/8/8 14:54
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <script src="https://cdn.staticfile.org/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
</head>
<body>
<%
    //指定允许其他域名访问
    response.setHeader("Access-Control-Allow-Origin", "*");
    //响应类型
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, OPTIONS, DELETE");
    //响应头设置
    response.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, X-Custom-Header, HaiYi-Access-Token");
%>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    //基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    myChart.showLoading();//开启loading效果
    // $.get('https://www.runoob.com/static/js/echarts_test_data.json', function (data) {
    $.get('data.txt', function (data) {
        myChart.hideLoading();//隐藏loading效果
        myChart.setOption({
            series: [
                {
                    name: '访问来源',
                    type: 'pie',//设置图表类型为饼图
                    radius: '55%',//饼图的半径，外半径为可视区尺寸（容器高宽中较小一项）的 55% 长度。
                    data: data.data_pie
                }
            ]
        })
    }, 'json');
</script>
</body>
