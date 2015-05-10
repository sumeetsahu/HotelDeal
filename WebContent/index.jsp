<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Best Hotel Deal</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" />
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<style type="text/css"> 
.centerDiv {   
	border:solid 0px #cccccc;
background:#FCFAFA;
width:850px;
margin:95px auto 0px;
box-shadow:1px 2px 6px rgba(0,0,0,0.5);
-moz-box-shadow:1px 2px 6px rgba(0,0,0,0.5);
-webkit-box-shadow:1px 2px 6px rgba(0,0,0,0.5);
-moz-border-radius:15px;
-webkit-border-radius:15px;
border-radius:15px;
padding:15px 10px 15px 10px;
text-align:center;
     } 
    
</style> 
<script>
  $(function() {
    $("input[type=submit]" )
      .button()
      .click(function( event ) {
        window.location.assign(location.protocol + '//' + location.host + location.pathname+"Deal.jsp?city="+$("#city").val())
      });
    $("#city").keydown(function (e) {
    	  if (e.keyCode == 13) {
    	    $("input[type=submit").click();
    	  }
    	});
  });
  </script>
</head>
<body>

<h1 align="center">Search Best Hotel Deal</h1>

<div class="centerDiv">
	<input type="text" style="height: 30px" size="60" align="middle" id="city" placeholder="Type and search a city to get best deal...."/>
	<input type="submit" value="Search Deal" id="search"/>
</div>

</body>
</html>