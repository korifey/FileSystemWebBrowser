<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 

	
	<c:choose>
          <c:when test="${not empty manager.currentFileItem}">
              <c:set var="item" scope="page" value="${manager.currentFileItem}"/>
          </c:when>
          <c:otherwise>
          <c:choose>
          	   <c:when test="${not empty manager.currentTreeItem}">
                  <c:set var="item" scope="page" value="${manager.currentTreeItem.folder}"/>
               </c:when>
          </c:choose>     
          </c:otherwise>
    </c:choose>
	
	<c:choose>
	<c:when test="${not empty item}">
     	<spring:message code="${item.iconUrl}" var="imgUrl"/>
     	<img src="${urlbase}/img/filetype/${imgUrl}" class="filetypeImg" style="height:64px; width:64px; position:relative;"/>
     	
     	<span style="top:-26px;">
     		<spring:message code="files.label.name"/> : <span class="attrValue"><c:out value="${item.name}"/></span>
     	</span>
     	<span style="top:-26px; left:20px;">
     		<spring:message code="files.label.type"/> : <span class="attrValue"><spring:message code="${item.description}"/></span>
     	</span>
     	
     	<c:forEach items="${item.attributes}" var="attr" varStatus="row">
     		 <c:set var="lft" value="left:${(1+row.count)*20}px;"/>
     	    <span style="top:-26px; ${lft}">
     			<spring:message code="${attr.key}"/> : <span class="attrValue"><c:out value="${attr.value}"/></span>
     		</span>
     	</c:forEach>
     	
     	
    </c:when>
    </c:choose>

</table>