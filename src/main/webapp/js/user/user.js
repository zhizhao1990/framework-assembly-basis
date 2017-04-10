function addUserGet(id) {
	window.location.href = "user.htm?action=add";
}

function postReq() {
	var requestData = "{'TX':'api.ylpt.new.get.cases.detail', 'T':'2', 'V':'1.0.0', 'D':'A00001', 'case_id':'vqLVSx4rWVRcCgumKwQi_A'}";
	var url = "http://127.0.0.1:8083/api/exec/ylpt.htm";
	$.ajax({
		url : url,
		dataType : 'json',
		type : "post",
		async : false,
		data : {
			requestData : requestData
		},
		success : function(data) {
			alert(data);
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

/**
 * 添加或者修改用户信息
 * 
 * @param flag
 *            {0：添加、1：修改}
 * @returns
 */
function addOrUpdateUserPost(flag) {
	var id = $('#id').val();
	var name = $('#name').val();
	var sex = $('#sex').val();
	var age = $('#age').val();
	if ('' == name) {
		alert('姓名不能为空！');
		return false;
	}
	var url;
	if (0 == flag) {
		url = "user.htm?action=add";
	} else if (1 == flag) {
		url = "user.htm?action=update";
	}
	$.ajax({
		url : url,
		dataType : 'json',
		type : "post",
		async : false,
		data : {
			id : id,
			name : name,
			sex : sex,
			age : age
		},
		success : function(data) {
			if (1 == data.code) {
				if (0 == flag) {
					window.location.href = "user.htm?action=list";
				} else if (1 == flag) {
					location.reload();
				}
			} else if (0 == data.code) {
				if (0 == flag) {
					alert('添加用户失败 ，请重新尝试！');
				} else if (1 == flag) {
					alert('修改用户失败 ，请重新尝试！');
				}
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

function getUser(id) {
	window.location.href = "user.htm?action=get&id=" + id;
}

function deleteUser(id) {
	if (confirm('您确定要删除该条记录吗？')) {
		$.ajax({
			url : 'user.htm?action=delete',
			dataType : 'json',
			type : "post",
			async : false,
			data : {
				id : id
			},
			success : function(data) {
				if (1 == data.code) {
					location.reload();
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
}

function prePage(pageNo, pageCount) {
	if (pageNo == 1) {
		alert('已经是第一页啦！');
	} else {
		pageNo = parseInt(pageNo) - 1;
		window.location.href = "user.htm?action=list&pageNo=" + pageNo;
	}
}

function nextPage(pageNo, pageCount) {
	if (pageNo >= pageCount) {
		alert('没有更多啦！');
	} else {
		pageNo = parseInt(pageNo) + 1;
		window.location.href = "user.htm?action=list&pageNo=" + pageNo;
	}
}

function upload() {
	var ava = $('#avatar').val();
	if ('' == ava) {
		alert('请选择文件！');
		return;
	} else {
		$.ajaxFileUpload({
			url : 'upload.htm?action=upload',
			secureuri : false,
			fileElementId : 'avatar',
			dataType : "json",
			success : function(data, status) {
				alert('上传成功！');
			},
			error : function(data, status, e) {
				alert(e);
			}
		});
	}
}

function logout() {
	if (confirm('您确定退出系统吗？')) {
		$.ajax({
			url : 'logout.htm?action=logout',
			dataType : 'json',
			type : "post",
			async : false,
			data : {},
			success : function(data) {
				if (1 == data.code) {
					window.location.href = "login.htm?action=login";
				} else {
					alert('退出失败，请重新尝试！');
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
}

function getSampleUser() {
	$.ajax({
		url : 'user.htm?action=getSampleUser',
		dataType : 'json',
		type : "post",
		async : false,
		data : {},
		success : function(data) {
			alert('success~');
			alert(data.id);
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

/**
 * 数据校验：前端校验
 * 
 * @author Thomas Lee
 * @since 2016年12月22日 上午10:23:26
 */
function existsTheUser() {
	var name = $("#name").val();
	if ('' == name) {
		alert("前填写用户姓名！");
		return false;
	}
}