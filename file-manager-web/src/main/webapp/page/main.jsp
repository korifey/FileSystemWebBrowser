<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 

<%-- Base path for including static resources --%>
<c:set var="urlbase" scope="application" value='<%=request.getContextPath()+"/page"%>'/>

<%-- Base path for invoking controllers --%>
<c:set var="refbase" scope="application" value='<%=request.getContextPath()+"/manager"%>'/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Web File Browser</title>
  <link rel="stylesheet" type="text/css" href="${urlbase}/css/style.css">

  <script type="text/javascript">
	<c:choose>
		<c:when test="${empty errorMessage}"/>
		<c:otherwise>
  			alert("${errorMessage}");
		</c:otherwise>
	</c:choose>
	
	function changeNextButton(param) {
		if (param == 'down') {
			document.getElementById("nextButton").src="${urlbase}/img/next_pressed.png";
		} else if (param == 'up') {
			document.getElementById("nextButton").src="${urlbase}/img/next.png";
			alert('Yeah! Cool, isn\'t it? Click me again slowly if you want to see these changing pictures ;) ')
		} else {
			return alert('Unsupported param for function changeNextButton():'+param)
		}
	}
	
	function scrollTo() {
		document.getElementById('selectedTreeId').scrollIntoView(true);
		document.getElementById('selectedFileId').scrollIntoView(true);
	}
  </script>
</head>

	<body onload="scrollTo();">
		<div id="mainPanel">			
			<div id="navPanel">
				 <jsp:include page="nav.jsp"/>
			</div>			
			<div id="contentPanel">
				<div id="menuPanel">
					<img class="gradient" src="${urlbase}/img/gradient_white2blue.png"></img>
				</div>
				<div id="treePanel">
				  	<jsp:include page="tree.jsp"/>
				</div>
				<div id="filePanel">
				  	<jsp:include page="files.jsp"/>
				</div>
				<div id="infoPanel">
					<img class="gradient" src="${urlbase}/img/gradient_blue2white.png"></img>
					<jsp:include page="info.jsp"/>
				</div>
			</div>
			<img src="/resources/folder.png"/>
		</div>
		
		<div style="position:fixed; bottom: 5px; left:10px; color:blue; font-size: 10pt;">
			<spring:message code="project.name"/>
			<spring:message code="project.version"/>
		</div>
		
		<div style="position:fixed; bottom: 25px; right:200px; color:rgb(100, 100, 100); font-size: 20pt; font-family: verdana">
			Best viewed with <img src="${urlbase}/img/firefox.png" height="80px;" style="top:30px;"/>
		</div>
	</body>
</html>