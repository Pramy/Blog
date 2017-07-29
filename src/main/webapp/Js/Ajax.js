
	function getXmlHttpObject() {
		var xmlHttp = null;
		try {
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		return xmlHttp;
	}
	
	function getServerInfo() {
		http = getXmlHttpObject();
		var url = "getmsg?time=" + Math.random();
		http.onreadystatechange = getInfoBack;
		http.open("GET", url, true);
		http.send(null);
	}
	
	function getInfoBack() {
		if (http.readyState == 4) {
			if (http.status == 200) {
				var response = http.responseText;
				document.getElementById("chatbox").innerHTML += response
						+ "\r\n";
				getServerInfo();
			}
		}
	}
	
	function sendok(o,form) {
		form.submit();
		document.getElementById(o).value = "";
		var txt = document.getElementById("chatbox");
		txt.scrollTop = txt.scrollHeight;
	}