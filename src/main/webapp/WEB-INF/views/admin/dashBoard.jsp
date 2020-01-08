<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
    	var dataArray = [['Year', '유저수', '매출액']];
    	var str = "";
    	<c:forEach  items="${list}" var="liststr">
				//alert("'${liststr.saleDate}'"+ '${liststr.salePrice}' + '${liststr.userCount}');
				dataArray.push(['${liststr.saleDate}',${liststr.salePrice},${liststr.userCount}]);
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
          title: 'Nearby galaxies',
          subtitle: 'distance on the left, brightness on the right'
        },
        bars: 'horizontal', // Required for Material Bar Charts.
        series: {
          0: { axis: 'distance' }, // Bind series 0 to an axis named 'distance'.
          1: { axis: 'brightness' } // Bind series 1 to an axis named 'brightness'.
        },
        axes: {
          x: {
            distance: {label: 'parsecs'}, // Bottom x-axis.
            brightness: {side: 'top', label: 'apparent magnitude'} // Top x-axis.
          }
        }
      };

    var chart = new google.charts.Bar(document.getElementById('dual_x_div'));
    chart.draw(data, options);
  };
</script>
  </head>
  <body>
    <div id="dual_x_div" style="width: 900px; height: 500px;"></div>
  </body>
</html>