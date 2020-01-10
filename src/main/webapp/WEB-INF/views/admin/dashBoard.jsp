<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
.left-box {
  background: red;
  float: left;
  width: 50%;
}
.right-box {
  background: blue;
  float: right;
  width: 50%;
}
</style>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
google.charts.load('current', {'packages':['corechart', 'bar']});
google.charts.setOnLoadCallback(drawStuff);

function drawStuff() {
	var dataArray = [['날짜', '유저수', '매출액']];
	<c:forEach  items="${list}" var="liststr">
			dataArray.push(['${liststr.saleDate}',${liststr.salePrice},${liststr.userCount}]);
	</c:forEach>
  var button = document.getElementById('change-chart');
  var chartDiv = document.getElementById('chart_div');

  var data = google.visualization.arrayToDataTable(dataArray);

  var materialOptions = {
    width: 900,
    chart: {
      title: 'Nearby galaxies',
      subtitle: 'distance on the left, brightness on the right'
    },
    series: {
      0: { axis: 'distance' }, // Bind series 0 to an axis named 'distance'.
      1: { axis: 'brightness' } // Bind series 1 to an axis named 'brightness'.
    },
    axes: {
      y: {
        distance: {label: 'parsecs'}, // Left y-axis.
        brightness: {side: 'right', label: 'apparent magnitude'} // Right y-axis.
      }
    }
  };

  var classicOptions = {
    width: 900,
    series: {
      0: {targetAxisIndex: 0},
      1: {targetAxisIndex: 1}
    },
    title: 'Nearby galaxies - distance on the left, brightness on the right',
    vAxes: {
      // Adds titles to each axis.
      0: {title: 'parsecs'},
      1: {title: 'apparent magnitude'}
    }
  };

  function drawMaterialChart() {
    var materialChart = new google.charts.Bar(chartDiv);
    materialChart.draw(data, google.charts.Bar.convertOptions(materialOptions));
    button.innerText = 'Change to Classic';
    button.onclick = drawClassicChart;
  }

  function drawClassicChart() {
    var classicChart = new google.visualization.ColumnChart(chartDiv);
    classicChart.draw(data, classicOptions);
    button.innerText = 'Change to Material';
    button.onclick = drawMaterialChart;
  }

  drawMaterialChart();
};
  <!--사이드바 -->
  function w3_open() {
    document.getElementById("mySidebar").style.display = "block";
  }

  function w3_close() {
    document.getElementById("mySidebar").style.display = "none";
  }
</script>
  </head>
  <body>
  <div class="w3-sidebar w3-bar-block w3-collapse w3-card w3-animate-left" style="width:10%;" id="mySidebar">
  <button class="w3-bar-item w3-button w3-large w3-hide-large" onclick="w3_close()">Close &times;</button>
  <a href="dashBoard" class="w3-bar-item w3-button">종합 통계</a>
  <a href="상세 통계" class="w3-bar-item w3-button">상세 통계</a>
  <a href="회원 상품 통계" class="w3-bar-item w3-button">회원 상품 통계 </div></a>
</div>
<div style="margin-left:10%; background: red;">
현재 관리자 : <br>

</div>
<div style="margin-left:10%; height: 100%">
  <div style="background: blue;">
    <h1>종합 통계</h1>
    <button id="change-chart">Change to Classic</button>
  <div id="chart_div" style="width: 50%; height: 20%;"></div>
</div>


</div>
  </body>
</html>