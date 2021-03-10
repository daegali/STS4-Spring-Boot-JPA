/*오브젝트 이므로 아무일도 발생하지 않는다.*/
let index = {
	/* function 함수를 사용시 let_this = this; 명시 해줘야한다. */
	init: function() {
		/*on 함수는 첫번 째 파라미터는 어떤 이벤트를 실행할지 결정, 클릭이 되면 두번째 파라미터에는 무엇을 할지 결정 */
		/* function(){} 사용하지 않고 , ()=>{} 을 사용하는 이유는 this를 바인딩하기 위해서 tkdyd */
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
		/*	$("#btn-login").on("click",() =>{
				this.login();	
		
			});*/
	},
//	-------------------------------------------------------회원가입---------------------------------------------
	save: function() {
		// alert("user의 save함수 호출");
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()

		};
		// console.log(data); /*값을 제대로 불러오는지 확인*/

		/*Ajax 요청*/
		// 회원가입 수행 요청 성공시 done 요청 실패시 fail요청
		// ajax호출시 default가 비동기 호출
		// ajax가 통신을 성공하고  서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해준다.
		$.ajax({
			headers: {
				"Accept": "application/json",
				"Content-Type": "application/json"
			},
			type: "POST",
			url: "/auth/joinProc", // 앞에 api 붙인다. 보통 join은 잘 적지 않는다.
			data: JSON.stringify(data), // json으로 변경 , http body 데이터
			contentTtpe: "application/json; charset=UTF-8", // body데이터가 어떤 타입인지(MINE)
			dataType: "json" // 요청에 대한 응답이 왔을 때 기본적으로 모든 것이 String(생긴게 json이라면) =>javascript오브젝트로 변경해준다. 
		}).done(function(resp) {
			alert("회원가입이 완료되었습니다.");
			//console.log(resp)
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});  // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!!
	},
//--------------------------------------------------------user 수정------------------------------------	
	update: function() {
		// alert("user의 save함수 호출");
		let data = {
			id: $("#id").val(),
			password: $("#password").val(),
			email: $("#email").val()

		};
		$.ajax({
			headers: {
				"Accept": "application/json",
				"Content-Type": "application/json"
			},
			type: "PUT",
			url: "/user", 
			data: JSON.stringify(data), 
			contentTtpe: "application/json; charset=UTF-8", 
			dataType: "json"
		}).done(function(resp) {
			alert("회원수정이 완료되었습니다.");
			//console.log(resp)
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});  
	},


}
index.init();

/*--------------------------------------------------로그인-----------------------------------------------*/
/*	login: function() {
		let data = {
			username:$("#username").val(),
			password:$("#password").val(),
		};
		$.ajax({
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp){
			alert("로그인이 완료되었습니다.");
			//console.log(resp);
			location.href = "/"
		}).fail(function(){
				alert(JSON.stringify(error));
		});
	}*/