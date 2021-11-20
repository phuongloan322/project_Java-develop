
$(document).ready(function(){
	
	$(document).find(".group:first-child").find(".group-delete").remove();
	if($(document).find(".theme:first-child").html() == undefined){
		$(document).find(".theme").find(".theme-delete").remove();
	}else{
		$(document).find(".theme:first-child").find(".theme-delete").remove();
	}
	
	$(document).find(".group").find(".user-child:first-child").find(".user-delete").remove();
	
	
	$(".save-name-had").on("change",function(){
		window.location.replace("manhourReport?saveName="+$(this).val());
//		$(".save-name-input").val($(this).val());
	})
	
	var groupOptionOld = "";
	$(document).on("click", ".group-select", function(){
		if($(this).val() != ""){
			var text = $(this).find("option[value="+$(this).val()+"]").html();
			groupOptionOld = '<option value="'+$(this).val()+'">'+text+'</option>';
		}else{
			groupOptionOld = "";
		}
	})
	$(document).on("change",".group-select", function(){
		var groupCode = $(this).val();
		var this_group = $(this);
		var groupSelectAll = $(".group-select").not(this);
		groupSelectAll.prepend(groupOptionOld);
		groupSelectAll.find("option[value="+this_group.val()+"]").remove();
		
		$.ajax({
            url : "userByGroup",
            type : "get",
            dataType:"json",
            data : {
                 groupCode : groupCode
            },
            success : function (userItemList){
            	var selectUser = this_group.closest(".row").find(".select-user");
            	selectUser.html('<option value=""></option>');
        		$.each(userItemList, function(j, userItem){
            		var option = '<option value="'+userItem.userNo+'">'+userItem.userName+'</option>';
            		selectUser.append(option);
            	})
            }
        });
	})
	
	var userOptionOld = "";
	
	//get option old
	$(document).on("click", ".user-select", function(){
		if($(this).val() != ""){
			var text = $(this).find("option[value="+$(this).val()+"]").html();
			userOptionOld = '<option value="'+$(this).val()+'">'+text+'</option>';
		}else{
			userOptionOld = "";
		}
	})
	
	//change user
	$(document).on("change",".user-select", function(){
		var userCode = $(this).val();
		var userSelectAll = $(this).closest(".row").find(".user-select").not(this);
		userSelectAll.prepend(userOptionOld);
		userSelectAll.find("option[value="+$(this).val()+"]").remove();
	})
	
	//add user element
	$(document).on("click", ".add-user", function(){
		var userFirst = $(this).closest(".group").find(".user-parent .user-child:first-child");
		var userClone = userFirst.clone();
		if(userFirst.find(".user-select").val()!=""){
			userClone.find("option[value="+userFirst.find(".user-select").val()+"]").remove();
		}
		var deleteElement = '<button class="user-delete"><i class="far fa-trash-alt"></i></button>';
		userClone.find(".user-select").after(deleteElement);
		userClone.find(".user-select").val("");
		$(this).closest(".group").find(".user-parent").append(userClone);
	})
	
	//add group element
	$(document).on("click",".add-group",function(){
		var groupClone = $(this).closest(".form-group").prev("div").find(".group:first-child").clone();
		if(groupClone.find(".group-select").val()!=""){
			groupClone.find(".group-select").find("option[value="+groupClone.find(".group-select").val()+"]").remove();
		}
		var selectElement = '<div class="input-group pl-0 user-child"><select class="form-control form-control-sm select-user user-select"><option value=""></option></select></div>';
		groupClone.find(".user-parent").html("").append(selectElement);
		groupClone.find(".group-select").val("");
		
		var deleteElement = '<button class="group-delete"><i class="far fa-trash-alt"></i></button>';
		groupClone.find(".group-select").after(deleteElement);
		$(this).closest(".form-group").prev("div").append(groupClone);
	})
	
	//add theme element
	$(".add-theme").on("click", function(){
		var themeClone = $(".theme").first().clone();
		themeClone.find(".theme-child").find("input").val("");
		themeClone.find(".work-content").find("select").html("");
		themeClone.find(".work-content-detail").find("input").val("");
		var deleteElement = '<button class="theme-delete"><i class="far fa-trash-alt"></i></button>';
		themeClone.find(".work-content-detail").after(deleteElement);
		$(this).closest(".form-group").before(themeClone);
	})
	
	//move option to show
	$(".show-change").on("click", function(){
		var optionFix = ["全体合計","月別合計","日別合計"];
		var selectHide = $(".select-hide").find(":selected").removeClass("hide-option").addClass("show-option");
		$.each(selectHide,function(){
			if(optionFix.indexOf($(this).html()) == -1 ){
				$(".select-show").find(".show-option:first-child").before($(this));
			}else{
				$(".select-show").append($(this));
			}
		})
	})
	
	//move option to hide
	$(".hide-change").on("click", function(){
		var selectShow = $(".select-show").find(":selected");
		$(".select-hide").append(selectShow);
	})
	
	//move option show up
	$(".change-prev button").on("click",function(){
		var optionFix = ["全体合計","月別合計","日別合計"];
		var selectShow = $(".select-show").find(":selected");
		var prev = selectShow.prev("option");
		if(optionFix.indexOf(selectShow.html()) == -1 || optionFix.indexOf(prev.html()) != -1){
			prev.before(selectShow);
		}
	})
	
	//move option show down
	$(".change-next button").on("click",function(){
		var optionFix = ["全体合計","月別合計","日別合計"];
		var selectShow = $(".select-show").find(":selected");
		var next = selectShow.next("option");
		if(optionFix.indexOf(next.html()) == -1 || optionFix.indexOf(selectShow.html()) != -1){
			next.after(selectShow);
		}
	})
	
	//delete user element
	$(document).on("click",".user-delete",function(){
		var value = $(this).prev().val();
		if(value != ""){
			var text = $(this).prev().find("option[value="+value+"]").html();
			userOptionOld = '<option value="'+value+'">'+text+'</option>';
			var userSelectAll = $(this).closest(".row").find(".user-select").not(this);
			userSelectAll.prepend(userOptionOld);
		}
		$(this).closest(".user-child").remove();
		
	})
	//delete group element
	$(document).on("click",".group-delete",function(){
		if($(this).prev().val() != ""){
			var text = $(this).prev().find("option[value="+$(this).prev().val()+"]").html();
			groupOptionOld = '<option value="'+$(this).prev().val()+'">'+text+'</option>';
			var groupSelectAll = $(".group-select").not(this);
			groupSelectAll.prepend(groupOptionOld);
		}
		
		$(this).closest(".group").remove();
	})
	//delete theme element
	$(document).on("click",".theme-delete",function(){
		$(this).closest(".theme").remove();
	})
	
	$(".save-screen").on("click",function(){
		var saveObject = createSaveObject();
		$.ajax({
            url : "manhourReport",
            type : "post",
            dataType:"json",
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            data : JSON.stringify(saveObject),
            success : function (result){
            	if(result.result == true){
            		alert(result.message);
            		var saveName = $(document).find(".save-name-had").find("option[value="+saveObject.saveName+"]").html();
            		if(saveName == undefined){
            			var option = "<option value='"+saveObject.saveName+"'>"+saveObject.saveName+"</option>";
                		$(".save-name-had").append(option);
                		$(document).find(".save-name-had").val(saveObject.saveName).trigger('change');
            		}
            	}else{
            		$("main").prepend("<div class='alert alert-danger'>"+result.message+"</div>");
            	}
            },
            error: function (){
            	alert("error");
            }
        });
	})
	
	$(".download-csv").on("click",function(){
		var saveObject = createSaveObject();
		$.ajax({
            url : "/manhourReport/check",
            type : "POST",
            dataType:"json",
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            data : JSON.stringify(saveObject),
            success : function (result){
            	if(result.result == true){
            		$.fileDownload("/manhourReport/search", {
             	        httpMethod: "POST",
            	 	    headers: { 
            	           'Accept': 'application/x-www-form-urlencoded',
            	 	    },
             	        contentType:"application/x-www-form-urlencoded",
             	        data: saveObject,
             	    });
            	}else{
            		$("main").prepend("<div class='alert alert-danger'>"+result.message+"</div>");
            	}
            }
        });
	})
	
	$(".remove-save-name").on("click", function(){
		var saveName = $(".save-name-had").val();
		$.ajax({
            url : "deleteReport",
            type : "POST",
            dataType:"json",
            data : { saveName : saveName},
            success : function (userItemList){
            	window.location.replace("manhourReport");
            }
        });
	})
	
	$(".tab-search").on("click", function(){
		var saveObject = createSaveObject();
		var newTab = $(".new-tab-search").clone().show();
		$.ajax({
            url : "/manhourReport/check",
            type : "POST",
            dataType:"json",
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            data : JSON.stringify(saveObject),
            success : function (result){
            	if(result.result == true){
            		$.ajax({
                        url : "/manhourReport/page",
                        type : "POST",
                        dataType:"json",
                        headers: { 
                            'Accept': 'application/json',
                            'Content-Type': 'application/json' 
                        },
                        data : JSON.stringify(saveObject),
                        success : function (result){
                        	var length = 0;
                        	$.each(result, function(i, row){
                        		var row;
                        		if(i==0){
                        			length = row.length;
                        			rowHtml = newTab.find("thead tr");
                        			$.each(row, function(j, record){
                        				rowHtml.append("<td style='white-space: nowrap; border-bottom: 1px solid; border-right: 1px solid; background-color: cadetblue;'>"+record+"</td>");
                            		})
                        		}else{
                        			rowHtml = "<tr>";
                        			if(result.length == 2){
                        				rowHtml += "<td style='white-space: nowrap; border-bottom: 1px solid; border-right: 1px solid;'> 合計 </td>";
                        				for(i = 1; i<length; i++){
                            				rowHtml += "<td style='white-space: nowrap; border-bottom: 1px solid; border-right: 1px solid;'></td>";
                                		}
                        			}else{
                        				$.each(row, function(j, record){
                            				rowHtml += "<td style='white-space: nowrap; border-bottom: 1px solid; border-right: 1px solid;'>"+record+"</td>";
                                		})
                        			}
                            		rowHtml += "</tr>";
                        			newTab.find("tbody").append(rowHtml);
                        		}
                        		
                        	})
                        	var myWindow = window.open("");
                        	myWindow.document.write(newTab.html());
                        }
                    });
            	}else{
            		$("main").prepend("<div class='alert alert-danger'>"+result.message+"</div>");
            	}
            }
        })
	})
	
	function createSaveObject(){
		var saveName = $(".save-name-input").val();
		var startDate = $(".start-date-input").val();
		var endDate = $(".end-date-input").val();
		var groupElement = $(".group-select");
		var userElement = $(".user-select");
		var themeElement = $(".appendThemeSelect");
		var workContentElement = $(".work-content-select");
		var workContentDetailElement = $(".work-content-detail-input");
		var hideElement = $(document).find(".hide-select option");
		var showElement = $(document).find(".show-select option");
		var total = $(".total-input:checked").val();
		var comma = $(".comma-input:checked").val();
		var apostrophe = $(".apostrophe-input:checked").val();
		
		//get list group
		var groupCodeList = [];
		$.each(groupElement, function(){
			groupCodeList.push($(this).val());
		})
		
		//get list user
		var userCodeList = [];
		$.each(userElement, function(){
			userCodeList.push($(this).val());
		})
		
		//get list theme
		var themeCodeList = [];
		$.each(themeElement, function(){
			var theme = $(this).val().split("/")[0];
			themeCodeList.push(theme);
		})
		
		//get list workContent
		var workContentCodeList = [];
		$.each(workContentElement, function(){
			workContentCodeList.push($(this).val());
		})
		
		//get list workContentDetail
		var workContentDetailList = [];
		$.each(workContentDetailElement, function(){
			workContentDetailList.push($(this).val());
		})
		
		//get list show
		var showList = [];
		$.each(showElement, function(){
			showList.push($(this).val());
		})
		
		//get list hide
		var hideList = [];
		$.each(hideElement, function(){
			hideList.push($(this).val());
		})
		
		//create object 
		var saveObject = {};
		saveObject["saveName"] = saveName;
		saveObject["startDate"] = startDate;
		saveObject["endDate"] = endDate;
		saveObject["groupList"] = groupCodeList;
		saveObject["userList"] = userCodeList;
		saveObject["themeList"] = themeCodeList;
		saveObject["workContentList"] = workContentCodeList;
		saveObject["workContentDetailList"] = workContentDetailList;
		saveObject["hideList"] = hideList;
		saveObject["showList"] = showList;
		saveObject["total"] = total;
		saveObject["comma"] = comma;
		saveObject["apostrophe"] = apostrophe;
		
		return saveObject;
	}
}) 
