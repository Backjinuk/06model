<%@ page language="java" contentType="text/html;charset=euc-kr"
	pageEncoding="euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
		document.detailForm.submit();
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/purchase/listPurchase" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
	<td colspan="11" >
		전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage } 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">전화번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">정보수정</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<c:set  var="i" value="0"/>
	<c:forEach var="purchaseVO" items="${list }">
		<c:set var="i" value="${i+1 }"/>
			<tr class="ct_list_pop">
		<td align="center">
			<a href="/getPurchase.do?tranNo=${purchaseVO.tranNo}">${purchaseVO.tranNo}</a>
		</td>
		<td></td>
		<td align="left">
			<a href="/getUser.do?userId=${userVO.userId}">${purchaseVO.buyer.userId}</a>
		</td>
		<td></td>
		<td align="left">
			<a href="/getPurchase.do?tranNo=${purchaseVO.tranNo}">${purchaseVO.purchaseProd.prodName }</a>
		</td>
		<td></td>
		<td align="left">${purchaseVO.receiverPhone }</td>
		<td></td>
		<td align="left">
				${! empty  purchaseVO.tranCode && purchaseVO.tranCode == "구매완료" ? "현재 구매완료 상태입니다" 	: "" }
				${! empty  purchaseVO.tranCode && purchaseVO.tranCode == "배송완료" ? "현재 배송중 상태 입니다" 	: "" }
				${! empty  purchaseVO.tranCode && purchaseVO.tranCode == "물건배송" ? "현재 배송완료된 상태입니다" 	: "" }
				
			</td>
		<td></td>
		<td align="left">
		<c:if test="${purchaseVO.tranCode == '배송완료'}">
			<a	href="updateTranCode.do?prodNo=${purchaseVO.purchaseProd.prodNo }&tranCode=${purchaseVO.tranCode}">
					물건도착 </a> 
		</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach>
</table>
		
	



<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
				 <input type="hidden" id="currentPage" name="currentPage" value=""/>
						<jsp:include page="../common/pageNavigator.jsp"/>	
		</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>