<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 


<input id="prevButton" type="image" style="left: 15px;"; src="${urlbase}/img/prev.png"  onclick="alert('If you see this alert I forgot to implement this feature. LoL 8)')"/>
<input id="nextButton" type="image" style="left: 20px;" src="${urlbase}/img/next.png" onmousedown="changeNextButton('down')" onmouseup="changeNextButton('up')"/>
<input id="pathInput" type="text" disabled="disabled" value="${manager.currentTreeItem.folder.fullname}"/>
<input id="synchronizeButton" type="image" style="left:60px;" src="${urlbase}/img/sync.png" onclick="alert('It\'s just for fun, don\'t take this button too seriously 8)')"/>