<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>Insert title here</title>
   <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
    
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Year', 'Sales', 'Expenses'],
          [ "${list.get(0).getSaleDate()}",  ${list.get(0).getSalePrice()},      ${list.get(0).getUserCount()}],
          [ "${list.get(1).getSaleDate()}+",  ${list.get(1).getSalePrice()},      ${list.get(1).getUserCount()}],
          ['2006',  660,       1120],
          ['2007',  1030,      540]
        ]);

        var options = {
          title: 'Company Performance',
          curveType: 'function',
          legend: { position: 'bottom' }
        };
        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
        chart.draw(data, options);
      }
    </script>

</head>
<body>
<div class="w3-sidebar w3-light-grey w3-bar-block" style="width:15%">
  <h3 class="w3-bar-item">Menu</h3>
  <a href="dashBoard" class="w3-bar-item w3-button">통합 정보표시</a>
  <a href="adminDOM" class="w3-bar-item w3-button">admin 관리페이지</a>
  <a href="#" class="w3-bar-item w3-button">매출 통계 페이지</a>
</div>
<!-- Page Content -->
<div style="margin-left:25%">

<div id="curve_chart" style="width: 900px; height: 500px"></div>
</div>
</body>
</html>