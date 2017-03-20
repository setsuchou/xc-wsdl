# xc-wsdl
<tfoot>  
<tr>  
    <td colspan="9" align="center" class="p">  
	<c:if test="${pageNum != 1}">  
	    <a href="list.html?query=a&page=${pageNum - 1}">上一页</a>  
	</c:if>  
	<c:if test="${pages != 1}">  
	    <c:forEach var="pageIndex" begin="1" end="${pages}">  
		<c:choose>  
		    <c:when test="${pageNum == pageIndex}">  
			<a>${pageIndex}</a>  
		    </c:when>  
		    <c:otherwise>  
			<a href="list.html?query=a&page=${pageIndex}">${pageIndex}</a>  
		    </c:otherwise>  
		</c:choose>  
	    </c:forEach>  
	</c:if>  
	<c:if test="${pageNum != pages}">  
	    <a href="list.html?query=a&page=${pageNum+1}">下一页</a>  
	</c:if>  
    </td>  
</tr>  
</tfoot> 
