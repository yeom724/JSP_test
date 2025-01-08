<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import ="DTO.Member" %>
<html>
<head>
<link rel="stylesheet" href="resources/css/bootstrap.min.css" />
<%
    session = request.getSession(false);
	Member mb = (Member)session.getAttribute("member");
	if(mb != null){
		System.out.println("세션을 성공적으로 가져왔습니다. ");
	}
	String id = mb.getId();
	String password = mb.getPassword();
	String name = mb.getName();
	String gender = mb.getGender();
	String birthyy = mb.getBirth().split("-")[0]; 
	System.out.println("년도"+birthyy);
	String birthmm = mb.getBirth().split("-")[1]; 
	System.out.println("월 "+birthmm);
	String birthdd = mb.getBirth().split("-")[2]; 
	System.out.println("일 "+birthdd);
	String mail1 = mb.getMail().split("@")[0];
	String mail2 = mb.getMail().split("@")[1];
	String phone = mb.getPhone();
	String address = mb.getAddress();

%>
	
<title>회원 수정</title>
</head>
<body onload="init()">

<div class="container py-4">
   <jsp:include page="/menu.jsp" />

 <div class="p-5 mb-4 bg-body-tertiary rounded-3">
      <div class="container-fluid py-5">
        <h1 class="display-5 fw-bold">회원 수정</h1>
        <p class="col-md-8 fs-4">Membership Updating</p>      
      </div>
    </div>
	<div class="container">
		<form name="newMember"action="updateMember" method="post" onsubmit="return checkForm()">
				<div class="mb-3 row">
				<label class="col-sm-2 ">아이디</label>
				<div class="col-sm-3">
					<input name="id" type="text" class="form-control" placeholder="id" value="<%=id%>" >
				</div>
			</div>
				<div class="mb-3 row">
				<label class="col-sm-2">비밀번호</label>
				<div class="col-sm-3">
					<input name="password" type="text" class="form-control" placeholder="password" value="<%=password%>" >
				</div>
			</div>
				<div class="mb-3 row">
				<label class="col-sm-2">비밀번호확인</label>
				<div class="col-sm-3">
					<input name="password_confirm" type="text" class="form-control" placeholder="password_confirm" >
				</div>
			</div>
				<div class="mb-3 row">
				<label class="col-sm-2">성명</label>
				<div class="col-sm-3">
					<input name="name" type="text" class="form-control" placeholder="name" value="<%=name%>" >
				</div>
			</div>
				<div class="mb-3 row">
				<label class="col-sm-2">성별</label>
				<div class="col-sm-10">
					<input name="gender" type="radio" value="남"	 <%if(gender.equals("남")){ out.println("checked");}%> >남 
					<input name="gender" type="radio" value="여"	 <%if(gender.equals("여")){ out.println("checked");}%> >여
				</div>
			</div>
		    <div class="mb-3 row">
				<label class="col-sm-2">생일</label>
				<div class="col-sm-10  ">
				  <div class="row">
					<div class="col-sm-2">
					<input type="text" name="birthyy"  maxlength="4"  class="form-control" placeholder="년(4자)" 	size="6" value="<%=birthyy%>"> 
					</div>
					<div class="col-sm-2">
						<select name="birthmm"	id="birthmm" class="form-select">
						<option value="">월</option>
						<option value="01"<%if(birthmm.equals("01")){ out.println("selected='selected'");}%>>1</option>
						<option value="02"<%if(birthmm.equals("02")){ out.println("selected='selected'");}%>>2</option>
						<option value="03"<%if(birthmm.equals("03")){ out.println("selected='selected'");}%>>3</option>
						<option value="04"<%if(birthmm.equals("04")){ out.println("selected='selected'");}%>>4</option>
						<option value="05"<%if(birthmm.equals("05")){ out.println("selected='selected'");}%>>5</option>
						<option value="06"<%if(birthmm.equals("06")){ out.println("selected='selected'");}%>>6</option>
						<option value="07"<%if(birthmm.equals("07")){ out.println("selected='selected'");}%>>7</option>
						<option value="08"<%if(birthmm.equals("08")){ out.println("selected='selected'");}%>>8</option>
						<option value="09"<%if(birthmm.equals("09")){ out.println("selected='selected'");}%>>9</option>
						<option value="10"<%if(birthmm.equals("10")){ out.println("selected='selected'");}%>>10</option>
						<option value="11"<%if(birthmm.equals("11")){ out.println("selected='selected'");}%>>11</option>
						<option value="12"<%if(birthmm.equals("12")){ out.println("selected='selected'");}%>>12</option>
					</select> 
					</div>
					<div class="col-sm-2">
						<input type="text" name="birthdd" maxlength="2" class="form-control" placeholder="일" size="4" value="<%=birthdd%>">
					</div>
				  </div>
			    </div>
			</div>
			<div class="mb-3 row">
				<label class="col-sm-2">이메일</label>
				<div class="col-sm-10">
				  <div class="row">
				     <div class="col-sm-4">
					    <input type="text" name="mail1" maxlength="50" value="<%=mail1 %>" class="form-control">
				     </div>@
				  	 <div class="col-sm-3" >
						<select name="mail2" id="mail2" class="form-select">
							<option <%if(mail2.equals("naver.com")){ out.println("selected");}%>>naver.com</option>
							<option <%if(mail2.equals("daum.net")){ out.println("selected");}%>>daum.net</option>
							<option <%if(mail2.equals("gmail.com")){ out.println("selected");}%>>gmail.com</option>
							<option <%if(mail2.equals("nate.com")){ out.println("selected");}%>>nate.com</option>
						</select>
				   	 </div>
				   </div>
			     </div>
			</div>     
			<div class="mb-3 row">
				<label class="col-sm-2">전화번호</label>
				<div class="col-sm-3">
					<input name="phone" type="text" class="form-control" placeholder="phone" value="<%=phone%>" >
				</div>
			</div>

				<div class="mb-3 row">
				<label class="col-sm-2 ">주소</label>
				<div class="col-sm-5">
					<input name="address" type="text" class="form-control" placeholder="address" value="<%=address%>">
				</div>
			</div>
				<div class="mb-3 row">
				<div class="col-sm-offset-2 col-sm-10 ">
					<input type="submit" class="btn btn-primary" value="회원수정 "> 
					<a href="deleteMember?id=<%=id%>" class="btn btn-primary">회원탈퇴</a>
				</div>
			</div>
		</form>	
	</div>
		   <jsp:include page="/footer.jsp" />  
  </div>	
</body>
</html>
<script type="text/javascript">
	function init() {
		setComboMailValue("${mail2}");
		setComboBirthValue("${month}");
	}

	function setComboMailValue(val) {
		var selectMail = document.getElementById('mail2');
		for (i = 0, j = selectMail.length; i < j; i++) {
			if (selectMail.options[i].value == val) {
				selectMail.options[i].selected = true; 
				break;
			}
		}
	}
	function setComboBirthValue(val) {
		var selectBirth = document.getElementById('birthmm'); 
		for (i = 0, j = selectBirth.length; i < j; i++){
			if (selectBirth.options[i].value == val){
				selectBirth.options[i].selected = true; 
				break;
			}
		}
	}
	function checkForm() {
		if (!document.newMember.id.value) {
			alert("아이디를 입력하세요.");
			return false;
		}
		if (!document.newMember.password.value) {
			alert("비밀번호를 입력하세요.");
			return false;
		}
		if (document.newMember.password.value != document.newMember.password_confirm.value) {
			alert("비밀번호를 동일하게 입력하세요.");
			return false;
		}
	}
</script>