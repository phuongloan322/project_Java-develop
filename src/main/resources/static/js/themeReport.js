var this_theme;
$(document).ready(function () {
// Select Theme
	$(".container-fluid").on("click",".selectTheme", function(e){
		this_theme = $(this);
		e.preventDefault();
		$('#dataThemeSearch').html(" ");
		$.ajax({
			type: "GET",
            url: "/SelectTheme",
            dataType : "json",
	        success : function(data){ 
	        	$(".accountingGroups").html('');
	        	 $(".accountingGroups").append('<option value="">部門選択...</option>');
                $.each(data.groupList,function(key, value)
                {
                    $(".accountingGroups").append('<option value=' + value.accountingGroupCode + '>' + value.accountingGroupCode + ' '+ value.accountingGroupName + '</option>');
                });
                
                $(".salesObjects").html('');
                $(".salesObjects").append('<option value="">売上科目...</option>');
                $.each(data.salesObjectList,function(key, value)
                {
                    $(".salesObjects").append('<option value=' + value.salesObjectCode + '>' + value.salesObjectCode + ' '+ value.salesObjectName + '</option>');
                });
                
                $('.accountingGroups option[value="'+data.inputSelectTheme.accountingGroupCode+'"]').prop('selected', true);
                $('.salesObjects option[value="'+data.inputSelectTheme.salesObjectCode+'"]').prop('selected', true);
                $('#selectThemeNo').val(data.inputSelectTheme.themeNo);
                $('#selectThemeName').val(data.inputSelectTheme.themeName1);
                $("input[name=soldFlg][value="+ data.inputSelectTheme.soldFlg +"]").prop('checked', true);
                
                //show modal
                
	        },
			error: function() {
				console.log("エラー");
             }

		});
	});
// Search Theme
	$(".searchInfoTheme").on("click", function(e){
		e.preventDefault();
		var dataForm = $('.formSearchTheme').serializeArray();
		let inputSelectTheme = {};
		$.each(dataForm, function(i, field) {
			inputSelectTheme[field.name] = field.value;
		});
		$.ajax({
			type: "POST",
            url: "/SelectTheme/Search",
            dataType : "json",
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            data : JSON.stringify(inputSelectTheme),
	        success : function(data){
	        	if(data.checkError == 1){
	        		$("#dataThemeSearch").html('');
		        	$.each(data.themeList, function(i, theme) {
		        		var soldFlg = theme.soldFlg == false ? ' <td>未売上</td>' : ' <td>売上済</td>';
		        		var valueCheckbox = theme.themeNo + "/" + theme.themeName1 + "/" + theme.workContentsClass + "/" + theme.soldFlg;
		        		$("#dataThemeSearch").append('<tr>'
			                    +' <td><div class="form-check text-center"><input class="form-check-input position-static" type="radio" name="themeRadio" id="Radio" value="'+valueCheckbox+'" aria-label="..."></div></td>'
			                    +' <td>'+theme.themeNo+'</td>'
			                    +' <td>'+theme.themeName1+'</td>'
			                    +  soldFlg
			                    +' </tr>');
					})
	        	}else{
	        		alert(data.mes);
	        	}
	        	
	        },
			error: function() {
				console.log("エラー");
             }

		});
	});
//AddOn Theme
	$('.addOnThemeCheckBox').on('click', function(e){
		e.preventDefault();
		var infoTheme = $("input[name='themeRadio']:checked").val();
		var infoThemeArr = infoTheme.split("/");
		if(infoThemeArr[3] == "false"){
			this_theme.closest(".theme-child").find(".appendThemeSelect").val(infoTheme);
			this_theme.closest(".theme-child").find(".appendNameThemeSelect").val(infoThemeArr[0] + " " + infoThemeArr[1]);
			$('#modal1').modal('toggle');
			getWorkContents(infoThemeArr);
		}else{
			if(confirm("売上済のテーマです。テーマを追加しますか？")){
				this_theme.closest(".theme-child").find(".appendThemeSelect").val(infoTheme);
				this_theme.closest(".theme-child").find(".appendNameThemeSelect").val(infoThemeArr[0] + " " + infoThemeArr[1]);
				$('#modal1').modal('toggle');
				getWorkContents(infoThemeArr);
			}
		}
	});
});

function getWorkContents(infoThemeArr) {
	$.ajax({
		type: "GET",
        url: "/ManhourUpdate/GetWorkContents",
        dataType : "json",
        data : {
        	"workContentsClass" : infoThemeArr[2],
        },
        success : function(data){ 
        	this_theme.closest(".form-group").find(".workContentsCode").html("");
        	this_theme.closest(".form-group").find(".workContentsCode").append('<option value = "">内容選択...</option>');
        	$.each(data, function(i,r){
        		var option = '<option value="'+r.workContentsCode+'">'+r.workContentsCode+' ['+r.workContentsCodeName+']'+'</option>';
        		this_theme.closest(".form-group").find(".workContentsCode").append(option);
        	}) 
        },
		error: function() {
			console.log("エラー");
         }

	});
}