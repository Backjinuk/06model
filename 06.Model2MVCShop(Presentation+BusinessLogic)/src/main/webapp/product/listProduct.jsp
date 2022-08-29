<%@ page language="java" contentType="text/html;charset=euc-kr"
	pageEncoding="euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>��ǰ �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">

	function fncGetList(currentPage) {
		//document.getElementById("currentPage").value = currentPage;
		$("#currentPage").val(currentPage);
		//document.detailForm.submit();
		$("form").attr("action", "/product/listProduct").attr("method", "post").submit();
	}
	
	$(function() {
		$(".Search_btn01").on("click", function() {
			fncGetList(1);
		})
		
		$(".Search_btn02").on("click", function() {
			fncGetList(1);
		})
	})
	
	$(".ct_list_pop.purchase").find("intpu[name='purchaseuUpdate]").on("click", function() {
		$(this).parent().parent().find("intpu[name='purchaseuUpdate]")
	})

</script>
</head>

<body bgcolor="#ffffff" text="#000000">

	<div style="width: 98%; margin-left: 10px;"> 

		<form>

			<table width="100%" height="37" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"
						width="15" height="37" /></td>
					<td background="/images/ct_ttl_img02.gif" width="100%"
						style="padding-left: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="93%" class="ct_ttl01">��ǰ ����</td>
							</tr>
						</table>
					</td>
					<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"
						width="12" height="37" /></td>
				</tr>
			</table>


			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>

					<!-- 		 � Ű����� �˻��Ҳ��� �˻� value������ DB�� ��� -->
				<td align="right">
				<select name="searchCondition"	class="ct_input_g" style="width: 80px">	
					<option selected disabled="disabled">�����ϼ���</option>
					<option value="0" ${! empty  searchVO.searchCondition && searchVO.searchCondition == "0" ? "selected" 	: "" }>��ǰ��ȣID</option>
					<option value="1" ${! empty  searchVO.searchCondition && searchVO.searchCondition == "1" ? "selected" 	: "" }>��ǰ�̸�</option>
					<option value="2" ${! empty  searchVO.searchCondition && searchVO.searchCondition == "2" ? "selected" 	: "" }>����</option>			
			</select>
					<input type="text" name="searchKeyword" value="${searchVO.searchKeyword }"
						 class="ct_input_g"
						style="width: 200px; height: 19px" /></td>
					<td align="right" width="70">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="17" height="23"><img
									src="/images/ct_btnbg01.gif" width="17" height="23"></td>
								<td background="/images/ct_btnbg02.gif" class="ct_btn01"
									style="padding-top: 3px;">
									<a class="Search_btn01">�˻�</a></td>
								<td width="14" height="23"><img
									src="/images/ct_btnbg03.gif" width="14" height="23"></td>
							</tr>
						</table>
					</td>				
				</tr>
				<tr>
				<td style="float: right; margin-top: 20px; ">
				<select name="searchPrice"	class="ct_input_g" style="width: 80px">	
						<option selected disabled="disabled">�����ϼ���</option>
						<option value="3" ${! empty  searchVO.searchPrice && searchVO.searchPrice == "3" ? "selected" 	: "" }>������ ������</option>
						<option value="4" ${! empty  searchVO.searchPrice && searchVO.searchPrice == "4" ? "selected" 	: "" }>������ ������</option>					
				</select>
						<input type="text" name="startPrice" value="${searchVO.startPrice}">~
						<input type="text" name="endPrice" value="${searchVO.endPrice}">
						<a  class="Search_btn02">�˻�</a>
					</td>
				</tr>
			</table>


			<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
				<tr>
					<td colspan="11">
					��ü  ${resultPage.totalCount } �Ǽ�, ���� ${resultPage.currentPage } ������</td>
				</tr>
				
				<tr>
					<td class="ct_list_b" width="100">No</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">��ǰ��</td>
					<td class="ct_line02"></td> 
					<td class="ct_list_b" width="150">����</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">�����</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">�������</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">����</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="808285" height="1"></td>
				</tr>
					<c:set var="i" value="0"/>
						<c:forEach var="productVO" items="${list }">
							<c:set var="i" value="${i+1 }"/>
								<tr class="ct_list_pop.purchase">
						 <c:if test="${! empty productVO.productValue || productVO.productValue eq '0'}"> 
							<td align="center">${i }</td>
							<td></td>
							<td align="left">
								<a href="/product/getProduct?prodNo=${productVO.prodNo }">${productVO.prodName }</a></td>					
							<td></td>
							<td align="left">${productVO.price }</td>
							<td></td>
							<td align="left">${productVO.regDate }</td>
							<td></td>
							<td align="left">
							<c:choose>
								<c:when test="${user.userId eq 'admin' }">
									<c:choose>						
											<c:when test="${ productVO.proTranCode eq 'null'  }">
												�Ǹ���						
											</c:when>								
										 <c:when test="${! empty productVO.proTranCode && productVO.proTranCode eq '���ſϷ�' }">
										 	<input type="button" name="purchaseuUpdate" value="����ϱ�">
												<a href="/purchase/updateTranCodeByProd?prodNo=${productVO.prodNo }&tranCode=${purchaseVO.tranCode}">
													����ϱ� </a> 	
										</c:when> 
										<c:otherwise>							
												${productVO.proTranCode }
																	
										</c:otherwise>	
									</c:choose>																			
								</c:when>
							</c:choose>						
						</td>					
					<td></td>
				<td align="left">${productVO.productValue }</td>
				</c:if> 
				</tr>
				<tr>
					<td colspan="11" bgcolor="D6D7D6" height="1"></td>
				</tr>
				</c:forEach>
				
			</table>		
		
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
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