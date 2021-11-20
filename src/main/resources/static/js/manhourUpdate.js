$(document).ready(function () {
    	
//Tooltip.
	 $('#datepicker1').datepicker({format: "yyyy/mm",language: "ja",autoclose: true,minViewMode: 'months',orientation:'bottom left'});
     $('[data-toggle="tooltip"]').tooltip();
	
//Get Group and user follow group
	$(".groupCode").on("change",function(e){
		e.preventDefault();
		var groupCode = $(".groupCode").val();
		$.ajax({
			type: "GET",
            url: "/ManhourUpdate/Search/UserFollowGroup",
            data: {
            	"groupCode" : groupCode,
            },
            dataType : "json",
	        success : function(result){ 
	        	$(".userList").html("");
	        	$.each(result, function(i,r){
	        		var option = '<option value="'+r.userNo+'">'+r.userNo+' ['+r.userName+']'+'</option>';
	        		$(".userList").append(option);
	        	})       	
             },
			error: function() {
				console.log("エラー");
             }

		});

	});
	
//Update manhour when change hour on client
	$(".container-fluid").on("click", ".table-input", function(){
		$(this).select();
	});
	
	$(".container-fluid").on("change", ".table-input", function(){
	//$(".table-input").on("change", function(){
		var valueChange = parseFloat($(this).val());
		if(isNaN(valueChange)){
			valueChange = 0.0;
		}
		$(this).val(valueChange.toFixed(1));
		
		var addressRow;
		var addressCol;
		var sumValueRow = 0;
		var sumValueCol = 0;
		var sumValueMonth = 0;
		
		var classAll = $(this).attr('class').split(' ');
		// find address column and row.
		$.each(classAll,function(i,className){
			if(className.indexOf('row')!=-1){
				addressRow = className;
			}
			if(className.indexOf('col')!=-1){
				addressCol = className;
			}
		});
		// calculator sum value row and column.
		$('.'+addressRow).each(function(){
			sumValueRow += parseFloat($(this).val());
		});
		$('.total'+addressRow).html(sumValueRow.toFixed(1));
		$('.totalR'+addressRow).val(sumValueRow.toFixed(1));
		// change value follow column you choose and change sumValueMonth
		changeValueByColumn(addressCol, sumValueCol, sumValueMonth);
		
	});
	
//add class table-info into current day
	var currentDay = $("#currentDay").val();
	$("input[name='day"+currentDay+"']").closest('td').addClass("table-info");
	$(".missing_col"+currentDay).addClass("table-info");
	$(".totalcol"+currentDay).closest('td').addClass("table-info");
	
	// display notification when previous day have 合計= 0
	var previousDay = parseInt(currentDay)-1;
	if(parseFloat($(".totalcol"+previousDay).text()) == 0){
		var notification = '<i class="fas fa-exclamation-circle text-danger" data-toggle="tooltip" title="" data-original-title="工数が未入力です"></i> ';
		$(".totalcol"+previousDay).closest("td").find('span').before(notification);
	}

//Delete theme in client.
	var deletedThemeArr = [];
	$(".delete-Theme").click(function(){
		if (confirm('選択した行を削除しますか？')) {
            
			deletedThemeArr.push([
                {name: "year", value: $(this).closest('tr').find('input[name="year"]').val()},
                {name: "month", value: $(this).closest('tr').find('input[name="month"]').val()},
                {name: "userNo", value: $(this).closest('tr').find('input[name="userNo"]').val()},
                {name: "themeNo", value: $(this).closest('tr').find('input[name="themeNo"]').val()},
               	{name: "workContentsClass", value: $(this).closest('tr').find('input[name="workContentsClass"]').val()},
                {name: "workContentsCode", value: $(this).closest('tr').find('input[name="workContentsCode"]').val()},
               	{name: "workContentsDetail", value: $(this).closest('tr').find('input[name="workContentsDetail"]').val()}
            ]);
            $(this).closest('tr').remove();
            
         // change all value when delete row
            for(let i = 0; i <= 31; i++){
        		let addressCol = "col"+i;
        		let sumValueCol = 0;
        		let sumValueMonth = 0;
        		changeValueByColumn(addressCol, sumValueCol, sumValueMonth);
            }
        }
	});
	
	
// Change Theme in client
	
	$('.changeTheme').on("click", function(){
		var changeThemeRow = $(this).closest('tr');
		var themeNo = changeThemeRow.find('.themeNo').val();
		var themeName1 = changeThemeRow.find('.themeName1').html();
		var workContentsCode = changeThemeRow.find('.workContentsCode').val();
		var workContentsClass = changeThemeRow.find('.workContentsClass').val();
		var workContentsDetail = changeThemeRow.find('.workContentsDetail').val();
		var workContentsCodeName = changeThemeRow.find('.workContentsCodeName').text();
		
		$('.appendNameThemeSelect').val("");
		$('.themeNoPresent').val(themeNo);
		$('.themeNamePresent').val(themeName1);
		$('.workContentsCodePresent').val(workContentsCode + "[" + workContentsCodeName + "]");
		$('.workContentsDetailPresent').val(workContentsDetail);
		
		$('.submitChangeTheme').on("click", function(){
			let infoTheme = $('.modal-body .appendThemeSelect').val();
			let infoThemeArr;
			if(infoTheme != undefined){
				infoThemeArr = infoTheme.split("/");
			}
			let workContentsCodeModal = $('.modal-body .workContentsCode').val();
			let workContentsDetailModal = $('.modal-body .workContentsDetail').val();
			if(workContentsCodeModal == ""
					|| workContentsDetailModal == ""
					|| infoThemeArr[2] == ""
					|| infoThemeArr[0] == ""){
				
				alert("{0}の対象を選択してください");
			}else{
				if(workContentsCodeModal == workContentsCode
						&& workContentsDetailModal == workContentsDetail
						&& infoThemeArr[2] == workContentsClass
						&& infoThemeArr[0] == themeNo){
					alert("重複トピック");
					$('#modal3').modal('toggle');
				}else{
					
					
					deletedThemeArr.push([
		                {name: "year", value: changeThemeRow.find('input[name="year"]').val()},
		                {name: "month", value: changeThemeRow.find('input[name="month"]').val()},
		                {name: "userNo", value: changeThemeRow.find('input[name="userNo"]').val()},
		                {name: "themeNo", value: changeThemeRow.find('input[name="themeNo"]').val()},
		               	{name: "workContentsClass", value: changeThemeRow.find('input[name="workContentsClass"]').val()},
		                {name: "workContentsCode", value: changeThemeRow.find('input[name="workContentsCode"]').val()},
		               	{name: "workContentsDetail", value: changeThemeRow.find('input[name="workContentsDetail"]').val()}
		            ]);
					console.log(deletedThemeArr);
					
					changeThemeRow.find('.themeNo').val(infoThemeArr[0]);
					changeThemeRow.find('.themeNotd').html(infoThemeArr[0]);
					changeThemeRow.find('.themeName1').html(infoThemeArr[1]);
					changeThemeRow.find('.workContentsCode').val(workContentsCodeModal);
					changeThemeRow.find('.workContentsCodetd').html(workContentsCodeModal);
					changeThemeRow.find('.workContentsClass').val(infoThemeArr[2]);
					changeThemeRow.find('.workContentsDetail').val(workContentsDetailModal);
					changeThemeRow.find('.workContentsDetailtd').html(workContentsDetailModal);
					
					$('#modal3').modal('toggle');
				}
			}
		})
	});
	
// Save/Update/Delete ManhourUpdate.
	$(".saveManhourUpdate").on("click",function(){
		let checkDay24h = false;
		for(let i = 1; i <= 31; i++){
			if( parseFloat($(".totalcol"+i).html()) > 24.0){
				checkDay24h = true;
				break;
			}
		}
		
		if(checkDay24h){
			alert("労働時間の合計が24hを超えることはできません。");
		}else{
			$(".formManhourUpdate").trigger("submit");
		}
	});
	
	$(".formManhourUpdate").on("submit",function(e){
		e.preventDefault();
		
		//list theme delete
		var deletedThemeArrJson = [];
		for(let i =0; i<deletedThemeArr.length; i++){
			let values = {};
			$.each(deletedThemeArr[i], function(i, field) {
			    values[field.name] = field.value;
			});
			deletedThemeArrJson.push(values);
		}
		
		//list object update insert
		var themeSize = 43;
		var data = $(this).serializeArray();
		var themeArrToSave = [];
		var loop = data.length/themeSize;
		for(let i=0;i<loop;i++){
			themeArrToSave.push(data.splice(0,themeSize));
		}
		var themeArrJsonToSave = [];
		for(let i =0; i<loop; i++){
			let values = {};
			$.each(themeArrToSave[i], function(i, field) {
			    values[field.name] = field.value;
			});
			themeArrJsonToSave.push(values);
		}
		
		// list include list update insert and list delete
		var changedThemeArrJson = [];
		changedThemeArrJson.push(deletedThemeArrJson);
		changedThemeArrJson.push(themeArrJsonToSave);
		
    	$.ajax({
			type: "POST",
            url: "/ManhourUpdate/Save",
            dataType : "json",
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            data : JSON.stringify(changedThemeArrJson),
	        success : function(result){ 
	        	alert("セーブに成功");    	
             },
			error: function() {
				alert("エラー");
             }
		});
    	
	});
	
//Add Theme into Manhour 
	$(".addThemeManhour").on("click", function(e){
		e.preventDefault();
		var workContentsCode = $(".workContentsCode").val();
		var themeSelect = $(".appendThemeSelect").val();
		var workContentsDetail = $(".workContentsDetail").val();
		if(workContentsCode == "" || themeSelect == "" || workContentsDetail == ""){
			alert("{0}の対象を選択してください");
		}else{
			var Theme = themeSelect.split("/");
			var checkTheme = false;
			$('.tr_tbody').each(function(){
				if(Theme[0] == $(this).find('.themeNo').val() 
						&& Theme[2] == $(this).find('.workContentsClass').val() 
						&& workContentsCode == $(this).find('.workContentsCode').val() 
						&& workContentsDetail == $(this).find('.workContentsDetail').val()){
					
					checkTheme = true;
					return false;
				}
			});
			if(checkTheme){
				$('.mes-warning').html("テーマはすでに存在します。");
			}else{
				var td_day;
				var rowCount = $('.tbody tr').length + 1;
				
				for(let day = 1; day <= 31; day ++){
					
					let classHoliday = " ";
					let classInMissing = $('.missing_col'+day).attr('class').split(' ');
					$.each(classInMissing,function(i,className){
						if(className.indexOf('table-danger')!=-1){
							classHoliday = className;
						}
					});
					
					td_day += ' <td class="'+classHoliday+'">'
               	 			+' <input type="number" min=0 maxlength=4 oninput="this.value=this.value.slice(0,this.maxLength||0);this.value=(this.value < 0) ? (0) : this.value;" value="0.0" class="form-control table-input col'+day+' row'+rowCount+'" data-day="day'+day+'" name="day'+day+'">'
               	 			+' </td>';
                }
				let userNoSearch = $("#userNoSearch").val();
				let groupCodeSearch = $("#groupCodeSearch").val();
				let siteCodeSearch = $("#siteCodeSearch").val();
				var currentMonthAndYear = $('.currentMonthAndYear').val().split("/");
				$(".tbody").append('<tr class="tr_tbody">'
		                 + '<td><div class="text-center"><i class="fas fa-thumbtack" style="color: #D3D3D3;"></div></td>'
		                 + '<td style="display:none">'
		                 + '<input type="hidden" name="year" value="'+currentMonthAndYear[0]+'">'
		                 + '<input type="hidden" name="month" value="'+currentMonthAndYear[1]+'">'
		                 + '<input type="hidden" name="userNo" value="'+userNoSearch+'" class="userNo">'
		                 + '<input type="hidden" name="groupCode" value="'+groupCodeSearch+'">'
		                 + '<input type="hidden" name="siteCode" value="'+siteCodeSearch+'">'
		                 + '<input type="hidden" name="themeNo" value="'+Theme[0]+'" class="themeNo">'
		                 + '<input type="hidden" name="workContentsClass" value="'+Theme[2]+'" class="workContentsClass">'
		                 + '<input type="hidden" name="workContentsCode" value="'+workContentsCode+'" class="workContentsCode">'
		                 + '<input type="hidden" name="workContentsDetail" value="'+workContentsDetail+'" class="workContentsDetail">'
		                 + '<input type="hidden" name="pinFlg" value="false">'
		                 + '<input type="hidden" name="total" value="0.0" class="totalRrow'+rowCount+'">'
		                 + '<input type="hidden" name="fixDate" value="'+currentMonthAndYear[0]+'">'
		                 + '</td>'
		                 + '<td>'+Theme[0]+'</td>'
		                 + '<td>'+Theme[1]+'</td>   '                                  
		                 + '<td>'+workContentsCode+'</td>'
		                 + '<td>'+workContentsDetail+'</td>'
		                 + '<td class="totalrow'+rowCount+'">0.0</td>'
		                 + td_day
		                 +' <td><div class="text-center"><i class="fas fa-exchange-alt" data-toggle="modal" data-target="#modal3"></i></div></td>'
                         +' <td><div class="text-center delete-Theme"><i class="far fa-trash-alt"></i></div></td>'
		                 + '</tr>');
				$("input[name='day"+currentDay+"']").closest('td').addClass("table-info");
				
			}
		}
	});
	
	
// Download file CSV
	$(".dowloadCsv").on("click", function() {
		$.fileDownload("/dowloadCsvManhourUpdate", {
	        httpMethod: "GET"
	    });
	});
	
// Import file CSV
	$("#fileCsvImport").on("change", function(){
		var formData = new FormData();
		formData.append('file', document.getElementById("fileCsvImport").files[0]);
    	$.ajax({
			type: "POST",
            url: "/ManhourUpdate/ImportCsv",
            data : formData,
			async : false,
			cache : false,
			contentType : false,
			enctype : 'multipart/form-data',
			processData : false,
	        success : function(result){
	        	location.reload(true);
	        	$('.mes-warning').html("CSVアップロードが正常終了しました");
             },
			error: function() {
				alert("エラーインポートファイル");
             }
		});
	});
});

$(document).on('show.bs.modal', '.modal', function () {
    var zIndex = 1040 + (10 * $('.modal:visible').length);
    $(this).css('z-index', zIndex);
    setTimeout(function () {
        $('.modal-backdrop').not('.modal-stack').css('z-index', zIndex - 1).addClass('modal-stack');
    }, 0);
});

//funtion change manhour one colum
function changeValueByColumn(addressCol, sumValueCol, sumValueMonth) {
	$('.'+addressCol).each(function(){
		sumValueCol += parseFloat($(this).val());
	});
	$('.total'+addressCol).html(sumValueCol.toFixed(1));
	
	for(let i = 1; i<=31; i++){
		sumValueMonth += parseFloat($('.totalcol'+i).html()); 
	}
	$('.totalMonth').html(sumValueMonth.toFixed(1));
	
	//missing manhour 1 date check manhour > 0 && manhour < 8
	if($(".total"+addressCol).closest("td").hasClass("table-danger") == false){
		if(sumValueCol > 0 && sumValueCol <8  ){
			$(".missing_"+addressCol).html((8 - sumValueCol).toFixed(1));
			
			var checkTagIRange0To8 = $(".total"+addressCol).closest("td").find("i.hour-min");
			if(checkTagIRange0To8.html() == undefined){
				var i = '<i class="fas fa-exclamation-circle text-warning hour-min" data-toggle="tooltip" title="合計工数が8h未満です"></i> ';
				$(".total"+addressCol).closest("td").find('span').before(i);
			}else{
				checkTagIRange0To8.show();
			}
			
		}else{
			$(".missing_"+addressCol).html('');
			$(".total"+addressCol).closest("td").find("i").hide();
		}
	}
	
	//check manhour > 24
	if(sumValueCol > 24){
		var checkTagIGreater24 = $(".total"+addressCol).closest("td").find("i.hour-max");
		if(checkTagIGreater24.html() == undefined){
			var i = '<i class="fas fa-exclamation-circle text-warning hour-max" data-toggle="tooltip" title="労働時間の合計が24hを超えることはできません。"></i> ';
			$(".total"+addressCol).closest("td").find('span').before(i);
		}else{
			checkTagIGreater24.show();
		}
	}else{
		$(".total"+addressCol).closest("td").find("i.hour-max").hide();
	}
}
