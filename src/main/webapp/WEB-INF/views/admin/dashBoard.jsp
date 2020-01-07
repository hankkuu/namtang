<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>

  </head>
<body>
    <div id="chart_div"></div>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'DataName');
        data.addColumn('number', 'V1');
        data.addColumn('number', 'V2');

		
		for(var i=0; i<=${list.size()}-1; i++){
			//data.addRow(['${list.get(i).getStatIdx()}', ${list.get(i).getSalePrice()}, ${list.get(i).getUserCount()}]);
			alert(i);
	        alert('${list.get(i).getStatIdx()}'+i);
			alert(${list.get(i).getSalePrice()});
			alert(${list.get(i).getUserCount()});
		}


        var options = {'title':'TEST BARCHART',
                       'width':400,
                       'height':300};

       var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
           chart.draw(data, options);    
     }         
    </script>
  </body>
</html>