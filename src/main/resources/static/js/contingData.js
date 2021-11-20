$(document).ready(function(){
	$(document).on("submit", "form", function (e) {
		var year = $(this).find(".year").val();
		var month = $(this).find(".month").val();
		if(month == "" || isNaN(month) || year == "" || isNaN(year)){
			var messege = "";
			messege += month != "" ? "" : messege == "" ? "month is empty" : " | month is empty";
			messege += isNaN(month) == false ? "" :  messege == "" ? "month is not a number" : " | month is not a number";
			messege += year != "" ? "" :  messege == "" ? "year is empty" : " | year is empty";
			messege += isNaN(year) == false ?  "" : messege == "" ? "year is not a number" : " | year is not a number";
			$(".error").show().html(messege);
		}else{
			$.ajax({
                url : "/contingDataOutputApi/check?year="+year+"&month="+month,
                type : "get",
                dataType:"json",
                success : function (result){
                	if(result){
                		$(".error").hide();
	    				$.fileDownload("/contingDataOutputApi?year="+$(".year").val()+"&month="+$(".month").val(), {
		 	    	        httpMethod: "GET"
		 	    	    });
	    			}else{
	    				$(".error").show().html("該当データが存在しません");
	    			}
                }
            });
			
		}
	    e.preventDefault();
	});
})