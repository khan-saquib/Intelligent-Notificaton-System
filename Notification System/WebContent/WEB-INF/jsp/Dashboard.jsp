<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>INTELLIGENT NOTIFICATIONS SYSTEM</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/DahBoardMenu.css" media="screen" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/EditPreferences.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
 
<script type="text/javascript">
	function editPreferences()
	{
	 	$.post("ViewPreferences",{},function(data){
			if(data != "")
			{
				$("#MainDisplayArea").html(data);
				  var htmlCode = "<div id='MainDisplayAreaContent'></div>";
				  $("#MainDisplayArea").append(htmlCode);
				  return true;
			}
		});
	 	return false;
	}
	
	function viewSelectedPreference()
	{
	 	var selectedPrefID = $("#UserPreferenceID").val();
	 	$.post( "EditPreference",{userPreferenceID:selectedPrefID}, function( data ) {
	  		if(data!="")
	 		{
	  			$("#MainDisplayAreaContent").html(data);
	  			$("#MainDisplayAreaContent").find("script").each(function(){
	  			     eval($(this).text());
	  			   });
	  			return true;
	 		}
		});
	 	return false;
	}
</script>
</head>

	
<body>

<header style="display:block; margin:0 20px 10px; padding:20px 30px 10px; position:relative;text-align:center;text-shadow:rgba(0, 0, 0, 0.2) 1px 1px 1px;">
	<h1 style="color:rgba(26, 89, 120, 0.901961);font-family:BebasNeueRegular, 'Arial Narrow', Arial, sans-serif !important;
	font-size:35px;font-weight:400;line-height:35px;padding:0 0 5px;position:relative;
	text-shadow:rgba(0, 0, 0, 0.0980392) 1px 1px 1px;">
	<span style="color:#7CBCD6;text-shadow:rgba(255, 255, 255, 0.8) 0 1px 1px;">INTELLIGENT</span> 
	NOTIFICATION SYSTEM
	</h1>
</header>
            
<div style="width:100%;">
<div style="float:left;width:20%; padding-left: 20px" class="container">
  <ul>
    <li class="dropdown">
      <a href="#" data-toggle="dropdown">My Events <i class="icon-arrow"></i></a>
      <ul class="dropdown-menu">
        <li><a href="Dashboard">Registered Events</a></li>
        <li><a href="Dashboard">Recommended Events</a></li>
      </ul>
    </li>
    <li class="dropdown">
      <a href="#" data-toggle="dropdown">My Preferences <i class="icon-arrow"></i></a>
      <ul class="dropdown-menu">
        <li><a href='javascript:editPreferences()'>Edit Preferences</a></li>
      </ul>
    </li>
    <li class="dropdown">
      <a href="#" data-toggle="dropdown">My Profile <i class="icon-arrow"></i></a>
      <ul class="dropdown-menu">
        <li><a href="#">Edit Profile</a></li>
      </ul>
    </li>
  </ul>
</div>


<div id="MainDisplayArea" style="float:left; width:70%; padding-top:25px; padding-left:30px;">
	<h1>DASHBOARD PAGE</h1>
	<h4>UNDER CONSTRUCTION</h4>
	<h6><a href="Logout">CLICK HERE TO LOG OUT</a></h6>
</div>
</div>
</body>

<script src="js/index.js"></script>
</html>