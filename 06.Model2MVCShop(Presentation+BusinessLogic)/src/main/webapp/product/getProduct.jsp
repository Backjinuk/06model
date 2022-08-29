<%@ page language="java" contentType="text/html; charset=euc-kr"
    pageEncoding="euc-kr"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	$(function() {
		
		$(".ct_btn01:contains('수정')").on("click", function() {
			alert("수정페이지로 이동합니다 : " + ${productVO.prodNo });
			location.href="/product/updateProductView?prodNo=${productVO.prodNo }";
		});
		
		$(".ct_btn01:contains('장바구니')").on("click", function() {
			alert("장바구니로 이동합니다");
			self.location="/cart/addCartView?prodNo=${productVO.prodNo }";
		});
		
		$(".ct_btn01:contains('구매')").on("click", function() {
			alert("구매페이지로 이동합니다");
			self.location="/purchase/addPurchaseView?prodNo=${productVO.prodNo }";
		});
		
		$(".ct_btn01:contains('목록')").on("click", function() {
			alert("목록으로 이동합니다");
			self.location="/product/listProduct?menu=manage";
		});
		
		$($(".commentText").find("input[type=button]" )[0] ).on("click", function() { 
		//	if( ${user.userId} != null){
				alert("댓글 등록 이동");
				$("form").attr("action", "/comment/addComment").attr("method", "POST").submit();	
			//}else{
			//	alert("로그인이 필요한 기능입니다.");
			//}
		});
		
		$(".comment").find("input[name='update']" ).on("click", function() {
			var text = $(this).parent().parent("div").find("input[name='comment']").val();
			var text2 = $($(this).parent().parent("div").find("input[name='updateComment']").val(text)).val();
			var commentNo = $(this).parent().parent("div").find("input[name='commentNo']").val();
			
			alert("commentNo" + commentNo);
			alert("text2 : " + text2);
			alert("댓글 수정 이동");
			location.href="/comment/updateComment?commentNo="+commentNo+"&commentTitle="+text2+"&prodNoo=${productVO.prodNo}";
		});
		
		$(".comment").find("input[name='delete']" ).on("click", function() {
			alert("댓글 삭제 이동");
			var commentNo = $(this).parent().parent("div").find("input[name='commentNo']").val();
			
			self.location = "/comment/delectComment?commentNo="+commentNo+"&prodNoo=${productVO.prodNo}";
		});
		
		$(".recomment").find("input[name='recommentUpdate']").on("click", function() {
			alert("대댓글 수정하기!!");
			var recomment = $(this).parent().parent().find("input[name='recommentTitle']").val();
			var recommentNo = $(this).parent().parent().find("input[name=recommentNo]").val();
			console.log(recommentNo);
			alert("recomment: " + recomment);
			
			location.href="/recomment/updateReComment?recommentTitlee="+recomment +"&recommentNoo="+recommentNo+"&prodNoo=${productVO.prodNo}";
			
		});
		
		$(".recomment").find("input[name='recomentDelete']").on("click", function() {
			alert("대댓글 삭제 하기");
			var recommentNo = $(this).parent().parent().find("input[name=recommentNo]").val();
			console.log(recommentNo); 
			
			location.href="/recomment/deleteReComment?recommentNoo="+recommentNo+"&prodNoo=${productVO.prodNo}";
		});
		
		$(".addReComment").find("input[name='showComment']").on("click", function() {
			$(this).parent().parent().parent().parent().find("#reComent").show();
		});
		
		$(".hideComment").find("input[name='hideComment']").on("click", function() {
			//alert("댓글 숨기기");
			$(this).parent().parent().parent().parent().find("#reComent").hide();
				var recommentTitle= $(this).parent().parent().parent().find("input[name='recommentTitleHidden']").val();
				var recommentNo = $(this).parent().parent().parent().parent().find("input[name='commentNo']").val();
				
			console.log(recommentTitle);
			console.log(recommentNo);
			location.href = "/recomment/addReComment?recommentTitlee="+recommentTitle+"&recommentNoo="+recommentNo+"&prodNoo=${productVO.prodNo}";
			});
	});
</script>

<title>Insert title here</title>
</head>

<body bgcolor="#ffffff" text="#000000">

<form>
<input type="hidden" value="${user.userId }" class="userId">
<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
		<img src="/images/ct_ttl_img01.gif"	width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">상품 상세보기</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif"  width="12" height="37"/>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 13px;">
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			상품번호 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="105">${productVO.prodNo }
					<input type="hidden" name="productNo" value="${productVO.prodNo }">
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			상품명 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${productVO.prodName }</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			상품이미지 <img 	src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<img src = "/images/uploadFiles/${productVO.fileName }"/>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			상품상세정보 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${productVO.prodDetail }</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">제조일자</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${productVO.manuDate }</td>
	</tr>
	<tr> 
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">가격</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${productVO.price }</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">등록일자</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${productVO.regDate }</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>

	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
</table>
	<h3 style="text-align: center;">댓글달기</h3>
	
	<div style=" margin-top: 20px; text-align: -webkit-center;">
	
		<div  style=" width: 600px; height: 65px; text-align: center; border: 3px solid #000;">
	
			<input type="text" name="commentTitle" style=" width: 80%; height: 65px; border: none;">
	
				<div class="commentText" style="float: right;">
	
					<input type="button" value="등록" style=" width:70px; height: 65px; border: none; outline: 0;">

					
				</div>
		</div>
	</div>
	
	<h3 style="text-align: center;">댓글창</h3>
		<c:set var="i" value="0"/>
			<c:forEach var="comment" items="${list }">
			<div>
				<div style=" margin-top: 5px; text-align: -webkit-center;">
					<div  style="width: 700px; height: 40px; text-align: center; border: 3px solid #000;">
							<div style=" width: 100px;  height: 38;  float: left;   border-right: 1px solid; font-weight: bold;">
								${comment.comment.userId.userId }
								<input type="hidden" name="commentNo" value="${comment.comment.commentNo }">
							</div>
							
							<input type="text" name="comment" value="${comment.comment.commentTitle }" 
								style=" width: 50%; height: 40px; border: none; margin-left: 100px;">
							<input type="hidden" name="updateComment" value="${comment.comment.commentTitle }">
							<div class="comment" style="float: right;">					
								<c:if test="${user.userId eq comment.comment.userId.userId  }">
									<input type="button" name="update" value="수정" style=" width:70px; border: none; outline: 0;">	
										<div> </div>	
									<input type="button" name="delete" value="삭제" style=" width:70px; border: none; outline: 0;">
								</c:if>	
							</div>
							<div class="addReComment" style="float:right;">
								<input type="button" name="showComment" value="등록" style=" width:70px; height: 40px; border: none; outline: 0;">
							</div>
					</div>
				</div>
								
				<div id="reComent" style="margin-top: 10px; text-align: -webkit-center; display: none;">						
					<div style=" border: solid; width: 500px; height: 40px; margin-left: 160;">

						<input type="text" name="recommentTitleHidden" style="width: 200px; height: 40; border: none">	
							<div class="hideComment" style="float:right;">
								<input type="button" name="hideComment" value="등록" style=" width:50px; height: 41px; border: none; outline: 0;">
							</div>
					</div>
				</div>
				
					<!-- 대댓글 시작 -->		
			
			<c:forEach var="recomment" items="${comment.recomment.list }">			
					<div class="recomment" style=" margin-top: 5px; text-align: -webkit-center;">
						<div  style="width: 500px; height: 40px; text-align: center; border: 3px solid #000;">
								<div style=" width: 100px;  height: 38;  float: left;   border-right: 1px solid; font-weight: bold;">
									${recomment.userId.userId }
									<input type="hidden"  name="recommentNo" value="${recomment.recommentNo }">
								</div>							 
								<input type="text"  name="recommentTitle" value="${recomment.recommentTitle }"  
									style=" width: 30%; height: 40px; border: none; margin-left: 100px;">
							 	<input type="hidden" name="recommentTitleHidden" value="${recomment.recommentTitle }"> 
								<div  style="float: right;">					
								 	<c:if test="${user.userId eq recomment.userId.userId}"> 
										<input type="button"  name="recommentUpdate" value="수정" style=" width:70px; border: none; outline: 0;">	
											<div> </div>	
										<input type="button"  name="recomentDelete" value="삭제" style=" width:70px; border: none; outline: 0;">
									</c:if>
								</div>
							 	<div  style="float:right;">
									<input type="button"  value="등록" style=" width:70px; height: 40px; border: none; outline: 0;">
								</div>
						</div>
					</div>				
		<!-- 		<div id="reComent" style="margin-top: 10px; text-align: -webkit-center; display: none;">						
					<div style=" border: solid; width: 500px; height: 40px; margin-left: 160;">

						<input type="text" name="recommentTitle" style="width: 200px; height: 40; border: none">	
							<div class="hideComment" style="float:right;">
								<input type="button"  value="등록" style=" width:50px; height: 41px; border: none; outline: 0;">
							</div>
					</div>
				</div> -->
				
					
	</c:forEach>
				
			</div>		
	</c:forEach>
				
<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 30px;">
	<tr>
		<td width="53%"></td>
		<td align="right">

		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
		
				
				<c:if test="${user.userId eq 'admin'}">
				<td width="17" height="23">
					<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
				</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
						수정
					</td>
				<td width="14" height="23">
					<img src="/images/ct_btnbg03.gif" width="14" height="23">
				</td>
				</c:if>
				<c:if test="${user.userId ne 'admin' }">
				<td width="17" height="23">
					<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
				</td>
				<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
						장바구니
				</td>
				<td width="14" height="23">
					<img src="/images/ct_btnbg03.gif" width="14" height="23">
				</td>					
					
				<td width="14" height="23">
				</td>
					
				<td width="17" height="23">
					<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
				</td>
				<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
						구매
				</td>
				<td width="14" height="23">
					<img src="/images/ct_btnbg03.gif" width="14" height="23">
				</td>
				</c:if>				 
			
				<td width="30"></td>
		
				<td width="17" height="23">
					<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
				</td>
				<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
					목록으로
				</td>
				<td width="14" height="23">
					<img src="/images/ct_btnbg03.gif" width="14" height="23">
				</td>
			</tr>
		</table>

		</td>
	</tr>
</table>
</from>

</body>
</html>