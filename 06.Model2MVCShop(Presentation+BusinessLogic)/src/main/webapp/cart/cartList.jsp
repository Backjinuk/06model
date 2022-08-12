<%@ page language="java" contentType="text/html;charset=euc-kr"
	pageEncoding="euc-kr"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>��ٱ��� �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	
	function fncGetList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
		document.detailForm.submit();
	}
	
	function cartList() {		
		alert("����");		
		
		var chk_arr = document.getElementsByName("chekList");
		
		var chk_data = [];

		for( var i=0; i<chk_arr.length; i++ ) {
		    if( chk_arr[i].checked == true ) {
		        chk_data.push(chk_arr[i].value);
		    
		    }
 	 	   alert(chk_data) 
		    location.href = "/addCartPurchase.do?prodNo="+chk_data;
		} 
		alert("�� ����");
		document.detailForm.action = "/purchase/addPurchase.do?prodNo"+chk_data;
		doucument.detatilFoem.submit();
		
	}


</script>

<script type="text/javascript">

</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/cart/getCartList" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">��ٱ��� �����ȸ</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
	<td colspan="11" >
		��ü  ${resultPage.totalCount } �Ǽ�, ���� ${resultPage.currentPage } ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ��ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">����</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr> 
	<c:set  var="i" value="0"/>
	<c:forEach var="cart" items="${list }">
		<c:set var="i" value="${i+1 }"/>
			<tr class="ct_list_pop">
		<td align="center">${cart.cartNo}</td>
		<td></td>
		<td align="left">${cart.buyerId.userId}</td>
		<td></td>
		<td align="left">
			<a href="/getProduct.do?prodNo=${cart.prodNo.prodNo}">${cart.prodNo.prodName}</a>			
		</td>			
		<td></td>
		<td align="left">
		<input type="checkbox" value="${cart.prodNo.prodNo }" name="chekList" />
			<input type="button" 
				onclick="location.href='/deleteCart.do?cartNo=${cart.cartNo }'"  value="����"/></td>
		<td></td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach>
</table>	
<input type="button" onclick="cartList();"  value="����" style="float: right ;"/>



<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
				 <input type="hidden" id="currentPage" name="currentPage" value=""/>
						<jsp:include page="../common/pageNavigator.jsp"/>	
		</td>
	</tr>
</table>

<!--  ������ Navigator �� -->
</form>

</div>

</body>
</html>