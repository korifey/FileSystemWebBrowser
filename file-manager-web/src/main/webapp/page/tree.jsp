<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:forEach var="f" items="${manager.treePanelContent}" varStatus="row">
	<c:set var="idx" scope="page" value="${row.count-1}"/>
	
	<c:choose>
          <c:when test="${manager.currentTreeItem == f}">
              <c:set var="selectedStyle" scope="page" value="background-color:rgb(196, 240, 240)"/>
          </c:when>
          <c:otherwise>
               <c:set var="selectedStyle" scope="page" value=""/>
          </c:otherwise>
    </c:choose>
        
	<div class="treeItem" style="top:${idx * 27}px; ${selectedStyle}">  
       <c:choose>
          <c:when test="${f.opened}">
            <c:set var="imgUrl" scope="page" value="${urlbase}/img/expanded.png"/>
          </c:when>
          <c:otherwise>
            <c:set var="imgUrl" scope="page" value="${urlbase}/img/collapsed.png"/>
          </c:otherwise>
        </c:choose>
        
        <c:set var="lft" value="left:${10*(1+f.offset)}px;"/>
		<img src="${imgUrl}" class="expandImg"; style="${lft}" onclick="top.location.href='${refbase}/tree/expand/${idx}'">
		
		<spring:message code="${f.folder.iconUrl}" var="imgUrl"/>
		
		<img src="${urlbase}/img/filetype/${imgUrl}" class="filetypeImg"; style="${lft}" onclick="top.location.href='${refbase}/tree/open/${idx}'">
		<span class="folderName" style="${lft}" onclick="top.location.href='${refbase}/tree/open/${idx}'"><c:out value="${f.folder.basename}"/></span>	
	</div>
</c:forEach>