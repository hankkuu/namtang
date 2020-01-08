<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
google.charts.load('current', {'packages':['bar']});
google.charts.setOnLoadCallback(drawStuff);
//	var arr = "[['Year', 'Sales', 'Expenses', 'Profit'],['2014', 1000, 400, 200],['2015', 1170, 460, 250],['2016', 660, 1120, 300],['2017', 1030, 540, 350]]";
/*
	for (var i=0;i<=${list.size()};i++) {
		arrstr += ",['1500',1500,1500,1500]";
	}
	*/
//	alert(arrstr);
//	alert(arr);
    function drawStuff() {
    	var dataArray = [['날짜', '유저수', '매출액']];
    	var str = "";
    	<c:forEach  items="${list}" var="liststr">
				//alert("'${liststr.saleDate}'"+ '${liststr.salePrice}' + '${liststr.userCount}');
				dataArray.push(['${liststr.saleDate}','${liststr.salePrice}','${liststr.userCount}']);
    	</c:forEach>
    	/*
    	for (var n=0;n<=${list.size()};n++){
    		alert(n);
    		//str += '2019',${list.get(n).getSalePrice()},${list.get(n).getUserCount()};
    		//str = "'2019',"+${list.get(n).getSalePrice()},${list.get(n).getUserCount()}+"";
    	    dataArray.push(['2019',${list.get(n).getSalePrice()},${list.get(n).getUserCount()}]);
    	}
    	*/
     // var arr = "[['Year', 'Sales', 'Expenses', 'Profit'],['2014', 1000, 400, 200],['2015', 1170, 460, 250],['2016', 660, 1120, 300],['2017', 1030, 540, 350]]";
      var data = new google.visualization.arrayToDataTable(dataArray);
      var options = {
        width: 1200,
        chart: {
          title: '매출액 이용자수',
          subtitle: '날짜별 이용자, 매출액'
        },
        bars: 'horizontal', // Required for Material Bar Charts.
        series: {
          0: { axis: 'distance' }, // Bind series 0 to an axis named 'distance'.
          1: { axis: 'brightness' } // Bind series 1 to an axis named 'brightness'.
        },
        axes: {
          x: {
            distance: {label: ''}, // Bottom x-axis.
            brightness: {side: 'top', label: ''} // Top x-axis.
          }
        }
      };

    var chart = new google.charts.Bar(document.getElementById('dual_x_div'));
    chart.draw(data, options);
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
  <a href="종합 통계" class="w3-bar-item w3-button">Link 1</a>
  <a href="상세 통계" class="w3-bar-item w3-button">Link 2</a>
  <a href="회원 상품 통계" class="w3-bar-item w3-button">Link 3</a>
</div>

<div class="w3-main" style="margin-left:11%">
<div class="w3-teal">
  <button class="w3-button w3-teal w3-xlarge w3-hide-large" onclick="w3_open()">&#9776;</button>
  <div class="w3-container">
    <h1>종합 통계</h1>
    <div id="dual_x_div" style="width: 200px; height: 250px;"></div>
    <div id="dual_x_div" style="width: 200px%; height: 250px;"></div>
  </div>
</div>

  </body>
</html>