<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page import="com.model2.mvc.service.domain.Product"  %>
<%@ page import="com.model2.mvc.service.domain.User" %> 
<%@ page import="com.model2.mvc.service.domain.Purchase" %>
<html>
<head>
<title>ȸ������</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<!-- <script type="text/javascript">
function fncAddUser() {
	// Form ��ȿ�� ����
	var id=document.detailForm.userId.value;
	var pw=document.detailForm.password.value;
	var pw_confirm=document.detailForm.password2.value;
	var name=document.detailForm.userName.value;
	
	if(id == null || id.length <1){
		alert("���̵�� �ݵ�� �Է��ϼž� �մϴ�.");
		return;
	}
	if(pw == null || pw.length <1){
		alert("�н������  �ݵ�� �Է��ϼž� �մϴ�.");
		return;
	}
	if(pw_confirm == null || pw_confirm.length <1){
		alert("�н����� Ȯ����  �ݵ�� �Է��ϼž� �մϴ�.");
		return;
	}
	if(name == null || name.length <1){
		alert("�̸���  �ݵ�� �Է��ϼž� �մϴ�.");
		return;
	}
	
	if(document.detailForm.password.value != document.detailForm.password2.value) {
		alert("��й�ȣ Ȯ���� ��ġ���� �ʽ��ϴ�.");
		document.detailForm.password2.focus();
		return;
	}
		
	if(document.detailForm.phone2.value != "" && document.detailForm.phone2.value != "") {
		document.detailForm.phone.value = document.detailForm.phone1.value + "-" + document.detailForm.phone2.value + "-" + document.detailForm.phone3.value;
	} else {
		document.detailForm.phone.value = "";
	}
		
	document.detailForm.action='/addProduct.do';
	document.detailForm.submit();
}


function resetData() {
	document.detailForm.reset();
}

</script> -->
</head>

<body bgcolor="#ffffff" text="#000000">
<!-- ��ȿ�� chek -->
<form name="detailForm"  method="post" action="/addProduct.do">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">��ǰ���</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:13px;">
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			��ǰ�� <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="105">
						<input type="text" name="prodName" class="ct_input_bg" style="width:100px; height:19px"  maxLength="20" >
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
			��ǰ������ <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="prodDetail" class="ct_input_g" 
							style="width:500px; height:50px"  maxLength="25" minLength="20"  />
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
		 	���� ����<img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input type="date" name="manuDate" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="10" minLength="6"  />
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			����<img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="price" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="50" >��
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">��ǰ����</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="file" name="fileName" class="ct_input_g" value="ã�ƺ���"
							style="width:100px; height:19px" onChange="javascript:checkSsn();"  maxLength="13" >
		
		</td>
	</tr>

	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<input type="submit" value="���">
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td> 
					<td width="30"></td>					
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:resetData();">���</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

</form>

<script type="text/javascript">
document.getElementById("btnCmfID").focus();
</script>

</body>
</html>
