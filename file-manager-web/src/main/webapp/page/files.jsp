<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 

<table cellpadding="0" cellspacing="0">
<thead>
<tr height="25px">
	<th width="400px;"><spring:message code="files.label.name"/></th>
	<th width="200px;"><spring:message code="files.label.modify_date"/></th>
	<th width="100px;"><spring:message code="files.label.type"/></th>
	<th width="100px;"><spring:message code="files.label.size"/></th>
</tr>
</thead>

<tbody>
<c:forEach var="f" items="${manager.filePanelContent}" varStatus="row">
	<c:set var="idx" scope="page" value="${row.count-1}"/>
	
	<c:choose>
          <c:when test="${manager.currentFileItem == f}">
              <c:set var="selectedStyle" scope="page" value="background-color:rgb(196, 240, 240)"/>
          </c:when>
          <c:otherwise>
               <c:set var="selectedStyle" scope="page" value=""/>
          </c:otherwise>
    </c:choose>
	
	<tr height="25px" onclick="top.location.href='${refbase}/tree/openFile/${idx}'" style="${selectedStyle}">	
		<td>
			<spring:message code="${f.iconUrl}" var="imgUrl"/>
		
			<img src="${urlbase}/img/filetype/${imgUrl}" class="filetypeImg"/>
			<c:out value="${f.basename}"/>
		</td>
		<td><fmt:formatDate dateStyle="long" timeStyle="long" value='${f.attributes["attribute.modifyDate"]}'/></td>
		<td><spring:message code="${f.description}"/></td>
		<td><fmt:formatNumber value='${f.attributes["attribute.size"]}'/></td>
	</tr>
</c:forEach>
</tbody>
</table>