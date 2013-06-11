<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    	<title>iPlant Collaborative - Login</title>
		<script type="text/javascript">
			function testCookies() {
				var cookieEnabled = navigator.cookieEnabled;
				//if not IE4+ nor NS6+
				if (typeof navigator.cookieEnabled == "undefined" && !cookieEnabled){ 
					document.cookie = "testcookie"
					cookieEnabled = (document.cookie.indexOf("testcookie") != -1);
				}
				if (!cookieEnabled) 
					alert("Cookies don't appear to be enabled in your browser. Please enable them and try again.");
			}
		</script>
		<script>
			(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
			  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
			  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
			})(window,document,'script','//www.google-analytics.com/analytics.js','ga');

			ga('create', 'UA-40021083-1', 'iplantcollaborative.org');
			ga('send', 'pageview');

		</script>
  	</head>
  	<body onLoad="testCookies()">
     	<table height="100%" width="100%">
      		<tbody>
        		<tr>
          			<td valign="middle" align="center"> 
      	    			<img src="/images/iplant_logo_graphic.png">
        	  			<h2>iPlant Collaborative Login</parameter></h2>
        	  			
        	  			<form action="?loginform" method="POST">
             				<input type="hidden" name="original_url" value="<parameter originalurl/>">
              				<table>
           	    				<tbody>
                					<tr>
                  						<td style="text-align: right; width: 40%;">Username:</td><td style="text-align: left;"><input name="username" tabindex="1" type="text"></td>
                					</tr>
                					<tr>
                  						<td style="text-align: right; width: 40%;">Password:</td><td style="text-align: left;"><input name="password" tabindex="2" type="password"></td>
                					</tr>
                					<tr>
                  						<td colspan="1" style="text-align: right; vertical-align: top;"><input value="Login" tabindex="3" type="submit"></td>
                						<td style="vertical-align: top;"><br></td>
                					</tr>
                					<tr>
                  						<td colspan="3" style="vertical-align: top; text-align:center;"><small>Or log in <a href="http://data.iplantcollaborative.org/iplant/home/shared/ibp/IBDBv2/cowpea/ibdbv2_cowpea_DMS_201300604.sql">via HTTP</a>
                  						&nbsp;&nbsp;&nbsp;<a href="http://www.iplantcollaborative.org/support">Need help?</a></small></td>
                					</tr>
              					</tbody>
            				</table>
          				</form>
        			</td>
      			</tr>
			<tr><td align="right"><i>This web interface is powered by <a href="https://projects.arcs.org.au/trac/davis">Davis</a>, a software project by <a href="http://www.arcs.org.au/">ARCS</a></i></td></tr>
    		</tbody>
  		</table>
  	</body>
</html>
