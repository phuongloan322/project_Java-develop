$(document).ready(function() {

    /* ManhourDay ---------------------------------*/

    // Sum value hour day and round 1 decimal when on change input
    $('.value-hour').change(function() {

        totalHourOfDay();
        $(this).val(parseFloat($(this).val()).toFixed(1));
    })

    // Save CSV
    $('.btn-save-csv').click(function saveCSV() {

        $.fileDownload("/ManhourInput/CSV?processingDate=" + $(".container-date").val() + "&userNo=" + $(".userNo").val(), {
            httpMethod: "GET"
        });
    })

    // Change theme
    $('.btn-change-Theme').click(function btnChange() {

        let themeNo = $(this).closest('tr').find('.theme-no').text();
        let themeName = $(this).closest('tr').find('.theme-name1').text();
        let workContent = $(this).closest('tr').find('.work-content-code').text();

        $('.theme-no-input').val(themeNo);
        $('.theme-name-input').val(themeName);
        $('.work-content-input').val(workContent); 
        if (confirm('Bạn có muốn thay đổi theme dòng đang chọn không?')) {
        	
            $('#modal3').modal('show');  
        }        
    });

    clickDatepicker();
    totalHourOfDay(); 

    // event click button delete show
    var deletedThemeArr = [];
    $('.btn-delete-Theme').click(function btnDelete() {

        if (confirm('Bạn có muốn xóa dòng đang chọn không?')) {

            deletedThemeArr.push([
                { name: "year", value: $(this).closest('tr').find('.year').val() },
                { name: "month", value: $(this).closest('tr').find('.month').val() },
                { name: "userNo", value: $(this).closest('tr').find('.userNo').val() },
                { name: "themeNo", value: $(this).closest('tr').find('.theme-no').text() },
                { name: "workContentsClass", value: $(this).closest('tr').find('.work-contents-class').text() },
                { name: "workContentsCode", value: $(this).closest('tr').find('.work-contents-code1').text() },
                { name: "workContentsDetail", value: $(this).closest('tr').find('.work-contents-detail').text() }
            ]);
            $(this).closest('tr').remove();
            totalHourOfDay();
        }
    });



    // event click select theme
    $('.btn-select-theme').click(function() {

        if (confirm('Bạn có muốn thay đổi theme dòng đang chọn không?')) {

            $('#modal1').modal('show');
        } 
    });

    /* ----------------- Save/Update/Delete ManhourInput-------------*/
    $('.btn-save-ManhourDay').click(function() {
        $('.form-ManhourDay').trigger("submit");
        $('.form-ManhourWeek').trigger("submit");
    });

    // Form ManhourInput Day
    $('.form-ManhourDay').on("submit", function(e) {
        e.preventDefault();
        var elementNumber = 10;
        var data = $(this).serializeArray();
        var tempArr = [];

        //deleted list object 
        var deletedArr = [];
        for (let i = 0; i < deletedThemeArr.length; i++) {
            let values = {};
            $.each(deletedThemeArr[i], function(i, field) {
                values[field.name] = field.value;
            });
            deletedArr.push(values);
        }

        // saved object list
        var loop = data.length / elementNumber;
        for (let i = 0; i < loop; i++) {
            tempArr.push(data.splice(0, elementNumber));
        }
        var savedArr = [];
        for (let i = 0; i < loop; i++) {
            let values = {};
            $.each(tempArr[i], function(i, field) {
                values[field.name] = field.value;
            });
            savedArr.push(values);
        }
        var parrentArr = [];
        parrentArr.push(deletedArr);
        parrentArr.push(savedArr);
        console.log(parrentArr);
        $.ajax({
            type: "POST",
            url: "/ManhourInputDay/Save?getDay=" + $(".getDay").val(),
            dataType: "json",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(parrentArr)
        });
    });
    
   

    // function total manHourDay
    function totalHourOfDay() {

        var hourArr = $('.value-hour');
        var total = 0;
        for (var i = 0; i < hourArr.length; i++) {

            if (parseFloat(hourArr[i].value)) {

                total += parseFloat(hourArr[i].value);
            }
        }
        if (total == 8) {
            $('.total-Hour').text(parseFloat(total).toFixed(1));
            $('.exist-Hour').text(parseFloat(8 - total).toFixed(1));
            $('.alert-manhourDay').removeClass('alert-warning');
            $('.alert-manhourDay').html("");
        } else {
            var notifHour = '<i class="fas fa-exclamation-circle text-warning" data-toggle="tooltip" title="合計工数が8h未満です"></i>' + total;
            $('.total-Hour').html(notifHour);
            $('.exist-Hour').text(parseFloat(8 - total).toFixed(1));
            $('.alert-manhourDay').addClass('alert-warning');
            $('.alert-manhourDay').html('<strong>アラート</strong> - 合計工数が8h未満です！');
        }
    } 

    // function date picker
    function clickDatepicker() {

        // init datepicker
        $('#datepicker1').datepicker({
            format: "yyyy/mm/dd",
            language: "ja",
            autoclose: true,
            orientation: 'bottom right'
        });

        // event click button datepicker
        $("#datepicker1").click(function() {
            $(".day").on("click", function() {
                let date = new Date($(this).data("date"));
                var dateStringWithTime = new Date(date).toISOString().slice(0, 19).replace(/-/g, '/').replace(/T/, ' ')
                $('.container-date').val(dateStringWithTime);
                $('.container-date-week').val(dateStringWithTime);
                $('.update-present-day').val('selectedDate');
                $('.update-present-week').val('selectedDateOfWeek');
                $('#formDay').trigger("submit");
                $('#formWeek').trigger("submit"); 
            });
        });
    }

    /*---------------- Button change Day/Week/Month--------------- */
    $('.btn_day').click(function btnDay() {

        $('.change-date').val('Day');
        $('#formWeek').hide();
        $('#formMonth').hide();
    });

    $('.btn_week').click(function btnWeek() {

        $('.change-date').val('Week');
        $('#formDay').hide();
        $('#formMonth').hide();
    });

    $('.btn_month').click(function btnMonth() {

        $('.change-date').val('Month');
        $('#formDay').hide();
        $('#formWeek').hide();
    }); 
    
    $('.ManhourInput-Day').each(function() {
		$('.btn_day').addClass('active');
		$('.btn_week').removeClass('active');
		$('.btn_month').removeClass('active');
		$('.alert-manhourWeek').remove();
	})
	$('.ManhourInput-Week').each(function() {
		$('.btn_week').addClass('active');
		$('.btn_month').removeClass('active');
		$('.btn_day').removeClass('active');
		$('.alert-manhourDay').remove();
	})
	$('.ManhourInput-Month').each(function() {
		$('.btn_month').addClass('active');
		$('.btn_week').removeClass('active');
		$('.btn_day').removeClass('active');
	})

    /*---------------- Button change tomorrow/present/yesterday--------------- */
    $('.btn-previous-day').click(function() {

        $('.update-present-day').val('previousDate');
    });

    $('.btn-next-day').click(function() {

        $('.update-present-day').val('nextDate');
    }); 

    /*------------------------------------End ManhourDay ---------------------------------*/

    /*------------------------------------ ManhourWeek ---------------------------------*/


    // sum total column manHourWeek 
    $('table.week-table thead th').each(function(i) {
        calculateColumnWeek(i);
    });

    totalHourOfWeek();

    // sum value manHourWeek input
    function calculateColumnWeek(columnId) {
        var total = 0;
        var textDanger = '<i class="fas fa-exclamation-circle text-danger" data-toggle="tooltip" title="工数が未入力です"></i>' + parseFloat(total).toFixed(1); 
        $('table.week-table tr').each(function() {
            var value = parseFloat($('td input.value-hour-week', this).eq(columnId).val());
            if (!isNaN(value)) {
                total += value;
            }
        }); 
        if(total == 0){ 
        	$('table tfoot td.value-total-week').eq(columnId).html(textDanger); 
            $('.alert-manhourWeek').addClass('alert-warning');
            $('.alert-manhourWeek').html('<strong>アラート</strong> - 未入力のデータが存在します！'); 
        } else {
        	if(total == 8){
            	$('table tfoot td.value-total-week').eq(columnId).text(parseFloat(total).toFixed(1));
            	$('table tfoot td.rest-hour-week').eq(columnId).text(parseFloat(8 - total).toFixed(1));
                $('.alert-manhourWeek').removeClass('alert-warning');
                $('.alert-manhourWeek').html("");
            } else {
            	var notifHour = '<i class="fas fa-exclamation-circle text-warning" data-toggle="tooltip" title="合計工数が8h未満です"></i>' + parseFloat(total).toFixed(1);
                $('table tfoot td.value-total-week').eq(columnId).html(notifHour);
            	$('table tfoot td.rest-hour-week').eq(columnId).text(parseFloat(8 - total).toFixed(1));   
                $('.alert-manhourWeek').html('<strong>アラート</strong> - 合計工数が8h未満です！');
            }
        }
    }

    // sum total manHourWeek
    function totalHourOfWeek() {

        var hourArr = $('tbody tr td.total-hour-week');
        var total = 0;
        for (i = 0; i < hourArr.length; i++) { 
        	
                total += parseFloat(hourArr.eq(i).text()); 
        }
        $('tfoot tr td.total-hour-week').text(parseFloat(total).toFixed(1));
    }
    
    var startDateOfWeek;
    var endDateOfWeek;
    $('.check-day').each(function(i,r){
    	if(i==0){
    		startDateOfWeek = $(this).data("day");
    	}
    	endDateOfWeek = $(this).data("day");
    }) 
    
  //add class table-info into current day in manHourWeek
    var currentDay = $("#currentDay").val();
    $("input[name='day"+currentDay+"']").closest('td').addClass("table-info");
    $(".missing_col"+currentDay).addClass("table-info");
    $(".totalcol"+currentDay).closest('td').addClass("table-info");  

    /*---------------- Button change previous week/present week/ next week--------------- */

    $('.btn-previous-week').click(function() {

        $('.update-present-week').val('previousWeek'); 
        $('.container-date-week').val(startDateOfWeek);
    });

    $('.btn-next-week').click(function() {

        $('.update-present-week').val('nextWeek');
        $('.container-date-week').val(endDateOfWeek);
    });  
    
    $('.text-format-week').text(startDateOfWeek.substr(0,10) + '-' + endDateOfWeek.substr(8,2)); 

 // Form ManhourInput Week
    $('.form-ManhourWeek').on("submit", function(e) {
        e.preventDefault();
        var tdCount = $('tbody.week-tbody').children('tr').children('td').children('input.value-hour-week').length; // count td input in manhourWeek
        var trCount = $('tbody.week-tbody').children('tr').length; 
        var getColInput = tdCount/trCount;
        console.log(getColInput)
        var elementNumber = 9 + getColInput;
        var data = $(this).serializeArray();
        var tempArr = [];

        //deleted list object 
        var deletedArr = [];
        for (let i = 0; i < deletedThemeArr.length; i++) {
            let values = {};
            $.each(deletedThemeArr[i], function(i, field) {
                values[field.name] = field.value;
            });
            deletedArr.push(values);
        }
        // saved object list
        var loop = data.length / elementNumber ;
        for (let i = 0; i < loop; i++) {
            tempArr.push(data.splice(0, elementNumber));
        }
        var savedArr = [];
        for (let i = 0; i < loop; i++) {
            let values = {};
            $.each(tempArr[i], function(i, field) {
                values[field.name] = field.value;
            });
            values['startDate'] = startDateOfWeek;
            values['endDate'] = endDateOfWeek;
            savedArr.push(values);
        }
        var parrentArr = []; 
        
        parrentArr.push(deletedArr);
        parrentArr.push(savedArr);  
        $.ajax({
            type: "POST",
            url: "/ManhourInputWeek/Save",
            dataType: "json",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data:  JSON.stringify(parrentArr)  
        });
    });
    
    /*------------------------------------End ManhourWeek ---------------------------------*/


});