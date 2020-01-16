<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery.ajax-cross-origin.min.js"></script>
<script type="text/javascript" src="/js/snowfall.jquery.js"></script>

<script src='https://unpkg.com/magic-snowflakes/dist/snowflakes.min.js'></script>
<link rel="stylesheet" href="/css/sakura.css">
<script src="/js/sakura.js" text="text/javascript"></script>


<style>
    .drop {
        position: absolute;
        filter: flipV(), flipH();
    }
</style>
<script src="http://dallvit.com.ne.kr/jsfile/skyrain.js"></script>
<script src="http://dallvit.com.ne.kr/jsfile/rain-skyblue.js"></script>
<script type="text/javascript">
    $(function () {
        var wheather = "";


        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(successCallback, errorCallback);

        } else {
            alert("좌표를 찾을수 없습니다.");
        }

        function successCallback(position) {
            var wheather = "https://samples.openweathermap.org/data/2.5/weather?lat=" + position.coords.latitude + "&lon=" + position.coords.longitude + "&appid=16cc01d3837e13da9db7e6bdf5fc6c8c";
            $.ajax({
                url: "${pageContext.request.contextPath}/weather",
                dataType: "json",
                data: "lat=" + position.coords.latitude + "&lon=" + position.coords.longitude,
                success: function (result) {
                    console.log("result.dt:" + result.dt);
                    console.log("result:" + result.weather);
                    $.each(result.weather, function (index, item) {
                        var randValNode = document.getElementById("rand-val");//style='display:none'
                        randValNode.innerHTML = "<iframe src='clearSky' allow='autoplay' id='audio' style='display:none'></iframe>";

                        if (item.description == "clear sky") {
                            const sakura = new Sakura('body');

                        } else {
                            var sf = new Snowflakes({
                                color: "#ffffff", // 색상
                                count: 75, // 갯수
                                minOpacity: 0.2, // 최소 투명도 0: 투명 | 1: 불투명
                                maxOpacity: 0.6 // 최대 투명도
                            });
                        }
                    })
                }
            });

        }

        function errorCallback(error) {
            alert(error.message);

        }


    })


</script>
<head>
    <meta charset="UTF-8">

</head>
<body>
<div id="rand-val">날씨

</div>


</body>
</html>