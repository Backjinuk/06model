<%@ page language="java" contentType="text/html;charset=euc-kr"
	pageEncoding="euc-kr"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>장바구니 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	function fncGetList(currentPage) {
		//document.getElementById("currentPage").value = currentPage;
		$("#currentPage").val(currentPage);
		//document.detailForm.submit();
		$("form").attr("acton", "/cart/getCartList").attr("method" , "post").submit;
	}
	
	$( function() {
		
		$(".bnt01").on("click", function() {
			
			var product =$(this).parents(".ct_list_pop").find("#productValue").val();
			
			var result = $(this).parents(".ct_list_pop").find("#result");
			
			var number = result.text().trim();
			
				
			
			if( $(this).val() == '+' && parseInt(number) < parseInt(product)){
				number = parseInt(number) + 1;
				console.log(number);
			}else if( $(this).val() == '-' && number > 0){
				number = parseInt(number) -1;
				console.log(number);
			}
			
			result.text(number); 
			
		});
		
		$("input[name='purchase']").on("click", function(){
			alert("구매창으로 이동합니다");
			var result = $("#result");			
			var chk_arr = $("input[type='checkbox']");
			
			var chk_data = [];
			var chk_valueData = [];
			
			$.each(chk_arr, function(index, item){
				if( $( chk_arr[index] ).is(":checked") == true ) {					
			    	chk_data.push(item.value );
			    	chk_valueData.push( $( $("tr.ct_list_pop").find("#result")[index]).text() );
			    
			    }  
			})
			
			console.log(chk_data);
	    	console.log(chk_valueData);
	    	self.location =  '/purchase/addCartPurchaseView?prodNo='+chk_data+'&cartValue='+chk_valueData;
			
		});

	
	});



</script>

<script type="text/javascript">

</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form>

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">장바구니 목록조회</td>
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
		<td class="ct_list_b">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">수량</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b"></td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr> 
	<c:set  var="i" value="0"/>
	<c:forEach var="cart" items="${list }">
		<c:set var="i" value="${i+1 }"/>
	<tr class="ct_list_pop">
		<td class="product" align="center">
			${cart.cartNo}
		
			<input type="hidden" name="cartNo" id="cartNo" value="${cart.cartNo}">
		
			<input type="hidden" id="productValue" value="${cart.prodNo.productValue }">
		
		</td>
		<td></td>
		<td align="left">${cart.buyerId.userId}</td>
		<td></td>
		<td align="left">
			<a href="/product/getProduct?prodNo=${cart.prodNo.prodNo}">${cart.prodNo.prodName}</a>			 
		</td>
		<td></td>
		<td class="cart" align="left">
			<div id="result">${cart.cartValue }</div>
			<input id="cartValue" type="hidden" name="cartValue" value="${cart.cartValue }">			
			
			<input type="button" class="bnt01" name="plus" value="+">
			<input type="button" class="bnt01" name="minus" value="-">
		
		</td>			
		<td></td>
		<td align="left">
			<input type="checkbox" value="${cart.prodNo.prodNo }" name="chekList" />
			<input type="button" 
				onclick="location.href='/cart/deleteCart?cartNo=${cart.cartNo}'" value="삭제"/>
			<input type="button" 
					onclick="location.href='/purchase/addCartPurchaseView?prodNo=${cart.prodNo.prodNo}&cartValue=${cart.cartValue }'"  value="구매" />
		</td>
		<td></td>
	</tr>
		
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach>
</table>

<input type="button" name="purchase" value="전체구매" style="float: right ;"/>	




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