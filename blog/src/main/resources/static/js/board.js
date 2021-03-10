/*오브젝트 이므로 아무일도 발생하지 않는다.*/
let index = {
	/* function 함수를 사용시 let_this = this; 명시 해줘야한다. */
	init: function() {
		/*on 함수는 첫번 째 파라미터는 어떤 이벤트를 실행할지 결정, 클릭이 되면 두번째 파라미터에는 무엇을 할지 결정 */
		/* function(){} 사용하지 않고 , ()=>{} 을 사용하는 이유는 this를 바인딩하기 위해서 tkdyd */
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-delete").on("click", () => {
			this.deleteById();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
	},
	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};
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
			url: "/api/board",
			data: JSON.stringify(data),
			contentTtpe: "application/json; charset=UTF-8",
			dataType: "json"
		}).done(function(resp) {
			alert("글쓰기가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	/*-------------------------------------------------삭제하기------------------------------------------------*/
	deleteById: function() {
		let id = $("#id").text();

		$.ajax({
			headers: {
				"Accept": "application/json",
				"Content-Type": "application/json"
			},
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json"
		}).done(function(resp) {
			alert("삭제가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	/*---------------------------------------------------글수정하기----------------------------------*/
	update: function() {
		
		let id = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};

		$.ajax({
			headers: {
				"Accept": "application/json",
				"Content-Type": "application/json"
			},
			type: "PUT",
			url: "/api/board/" + id,
			data: JSON.stringify(data),
			contentTtpe: "application/json; charset=UTF-8",
			dataType: "json"
		}).done(function(resp) {
			alert("수정 완료되었습니다.");
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