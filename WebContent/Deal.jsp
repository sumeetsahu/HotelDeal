<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Deal</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" />
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
</head>
<body>

<table id="deal_table"></table>

<script type="text/javascript">

url = "API/deal?city=chicago&start=0&end=11";
var keys_map = { 
		name : {IsEnabled:true,FriendlyName: "Hotel Name"} , 
		streetAddress : {IsEnabled:false,FriendlyName: "Street Address"}, 
		city : {IsEnabled:true,FriendlyName: "City"}, 
		baseRate: {IsEnabled:true,FriendlyName: "Price"} };

$.getJSON(url , function(data) {

    var tbl_body = "";
    var head_row;
    $.each(keys_map,function(k,v){
    	if(v.IsEnabled)
    		head_row += "<th>"+v.FriendlyName+"</th>";
    		
    })
    tbl_body += "<tr>"+head_row +"</tr>";
    
    var odd_even = false;
    $.each(data, function() {

        var tbl_row = "";
        $.each(this, function(k , v) {

        	if ( ( k in keys_map ) && keys_map[k].IsEnabled ) {
            	tbl_row += "<td>"+v+"</td>";
        	}
        })
        tbl_body += "<tr class=\""+( odd_even ? "odd" : "even")+"\">"+tbl_row+"</tr>";
        odd_even = !odd_even;               
    })
    
    $("#deal_table").html(tbl_body);
});

</script>
</body>
</html>