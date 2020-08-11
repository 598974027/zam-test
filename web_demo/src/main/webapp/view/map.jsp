<%--
  @description: new jsp
  @author: zhangaomin
  @time: 2020/8/8 14:54
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <style type="text/css">
        body, html, #allMap {
            width: 100%;
            height: 100%;
            overflow: hidden;
            margin: 0;
            font-family: "微软雅黑";
        }
    </style>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=10efKHRRF5oSAdBRhWajUzTKXBGaUv2S"></script>
    <title>百度地图API</title>
</head>
<body>
<div id="allMap"></div>
</body>
</html>
<script type="text/javascript">
    //百度地图API功能
    var map = new BMap.Map("allMap");//创建Map实例
    map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);//初始化地图,设置中心点坐标和地图级别
    //添加地图类型控件
    map.addControl(new BMap.MapTypeControl({
        mapTypes: [
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]
    }));
    map.setCurrentCity("北京");//设置地图显示的城市 此项是必须设置的
    map.enableScrollWheelZoom(true);//开启鼠标滚轮缩放
</script>

