function signIn() {
	var name = $('#name').val();
	var password = $('#password').val();
	if ('' == name) {
		alert('用户名不能为空！');
		return false;
	} else if ('' == password) {
		alert('密码不能为空！');
		return false;
	}
	$.ajax({
		url : 'login.htm?action=login',
		dataType : 'json',
		type : "post",
		async : false,
		data : {
			name : name,
			temp1 : password
		},
		success : function(data) {
			if (0 == data.code) {
				location.href = "user.htm?action=list";
			} else if (100 == data.code) {
				alert('用户名不存在！');
			} else if (101 == data.code) {
				alert('密码错误！');
			} else if (199 == data.code) {
				alert('发生未知错误！');
			}
		},
		error : function(xhr, err, ex) {
			if ('timeout' == err) {
				alert('当前网络情况较差，请稍后再试！');
			} else if ('error' == err) {
				alert('请检查您的手机是否联网！');
			}
		}
	});
}

function signUp() {
	var name = $('#name').val();
	var password = $('#password').val();
	if ('' == name) {
		alert('用户名不能为空！');
		return false;
	} else if ('' == password) {
		alert('密码不能为空！');
		return false;
	}
	$.ajax({
		url : 'login.htm?action=signup',
		dataType : 'json',
		type : "post",
		async : false,
		data : {
			name : name,
			temp1 : password
		},
		success : function(data) {
			if (0 == data.code) {
				alert('注册成功，请登录！');
				location.reload();
			} else if (100 == data.code) {
				alert('用户名已存在！');
			} else if (199 == data.code) {
				alert('发生未知错误！');
			}
		},
		error : function(xhr, err, ex) {
			if ('timeout' == err) {
				alert('当前网络情况较差，请稍后再试！');
			} else if ('error' == err) {
				alert('请检查您的手机是否联网！');
			}
		}
	});
}