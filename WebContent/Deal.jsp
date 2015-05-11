<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>

table a:link {
	color: #666;
	font-weight: bold;
	text-decoration:none;
}
table a:visited {
	color: #999999;
	font-weight:bold;
	text-decoration:none;
}
table a:active,
table a:hover {
	color: #bd5a35;
	text-decoration:underline;
}
table {
	font-family:Arial, Helvetica, sans-serif;
	color:#666;
	font-size:12px;
	text-shadow: 1px 1px 0px #fff;
	background:#eaebec;
	margin:20px;
	border:#ccc 1px solid;

	-moz-border-radius:3px;
	-webkit-border-radius:3px;
	border-radius:3px;

	-moz-box-shadow: 0 1px 2px #d1d1d1;
	-webkit-box-shadow: 0 1px 2px #d1d1d1;
	box-shadow: 0 1px 2px #d1d1d1;
}
table th {
	padding:21px 25px 22px 25px;
	border-top:1px solid #fafafa;
	border-bottom:1px solid #e0e0e0;

	background: #ededed;
	background: -webkit-gradient(linear, left top, left bottom, from(#ededed), to(#ebebeb));
	background: -moz-linear-gradient(top,  #ededed,  #ebebeb);
}
table th:first-child {
	text-align: left;
	padding-left:20px;
}
table tr:first-child th:first-child {
	-moz-border-radius-topleft:3px;
	-webkit-border-top-left-radius:3px;
	border-top-left-radius:3px;
}
table tr:first-child th:last-child {
	-moz-border-radius-topright:3px;
	-webkit-border-top-right-radius:3px;
	border-top-right-radius:3px;
}
table tr {
	text-align: center;
	padding-left:20px;
}
table td:first-child {
	text-align: left;
	padding-left:20px;
	border-left: 0;
}
table td {
	padding:18px;
	border-top: 1px solid #ffffff;
	border-bottom:1px solid #e0e0e0;
	border-left: 1px solid #e0e0e0;

	background: #fafafa;
	background: -webkit-gradient(linear, left top, left bottom, from(#fbfbfb), to(#fafafa));
	background: -moz-linear-gradient(top,  #fbfbfb,  #fafafa);
}
table tr.even td {
	background: #f6f6f6;
	background: -webkit-gradient(linear, left top, left bottom, from(#f8f8f8), to(#f6f6f6));
	background: -moz-linear-gradient(top,  #f8f8f8,  #f6f6f6);
}
table tr:last-child td {
	border-bottom:0;
}
table tr:last-child td:first-child {
	-moz-border-radius-bottomleft:3px;
	-webkit-border-bottom-left-radius:3px;
	border-bottom-left-radius:3px;
}
table tr:last-child td:last-child {
	-moz-border-radius-bottomright:3px;
	-webkit-border-bottom-right-radius:3px;
	border-bottom-right-radius:3px;
}
table tr:hover td {
	background: #f2f2f2;
	background: -webkit-gradient(linear, left top, left bottom, from(#f2f2f2), to(#f0f0f0));
	background: -moz-linear-gradient(top,  #f2f2f2,  #f0f0f0);	
}

</style>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Best Hotel Deal</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" />
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script>
  $(function() {
    $("input[type=submit]" )
      .button()
      .click(function( event ) {
        window.location.assign(location.protocol + '//' + location.host + location.pathname+"?city="+$("#city").val())
      });
    $("#city").keydown(function (e) {
    	  if (e.keyCode == 13) {
    	    $("input[type=submit").click();
    	  }
    	});
	

url = "API/deal"+location.search;

var keys_map = { 
		name : {IsEnabled:true,FriendlyName: "Hotel Name"} , 
		streetAddress : {IsEnabled:true,FriendlyName: "Street Address"}, 
		city : {IsEnabled:true,FriendlyName: "City"},
		starRating: {IsEnabled:true,FriendlyName: "Star Rating", IsSortable:true}, 
		guestRating: {IsEnabled:true,FriendlyName: "Guest Rating", IsSortable:true},		
		pricePerNight: {IsEnabled:true,FriendlyName: "Rate per Night", IsSortable:true},
		percentSavings: {IsEnabled:true,FriendlyName: "Discount", IsSortable:true}};

$.getJSON(url , function(data) {

    var tbl_body = "";
    var head_row;
    $.each(keys_map,function(k,v){
    	if(v.IsEnabled)
    		{
    			if(v.IsSortable)
    				{
    					if(window.location.href.search("sort")<0)
    						if(window.location.href.search("\\?")>=0)
    							redirect = window.location.href.replace("?", "?sort="+k+"&");
    						else
    							redirect = window.location.href+"?sort="+k;
    					else
    						redirect = window.location.href.replace(/sort=.*&/g,"sort="+k+"&");
    	    			head_row += "<th><a href='"+redirect+"'>"+v.FriendlyName+"</a></th>";
    				}
    			else
    				{
    					head_row += "<th>"+v.FriendlyName+"</th>";
    				}
    			
    		}
    		
    		
    })
    tbl_body += "<tr>"+head_row +"</tr>";
    
    var odd_even = false;
    $.each(data, function() {

        var tbl_row = "";
        $.each(this, function(k , v) {

        	alert(k);
        	if ( ( k in keys_map ) && keys_map[k].IsEnabled ) {
            	tbl_row += "<td>"+v+"</td>";
        	}
        })
        tbl_body += "<tr class=\""+( odd_even ? "odd" : "even")+"\">"+tbl_row+"</tr>";
        odd_even = !odd_even;               
    })
    
    $("#deal_table").html(tbl_body);
});	
		
		
  });
  </script>
</head>
<body>

<div style="width:1200px; margin:0 auto;">
<div style="width:800px; margin:0 auto;">
<input type="text" style="height: 30px" size="60" align="middle" id="city" placeholder="Type and search a city to get best deal...."/>
<input type="submit" value="Search Deal" id="search"/>
</div>
<table id="deal_table"></table>
</div>


</body>
</html>