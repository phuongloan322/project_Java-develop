$(function () {
    $('[data-toggle="tooltip"]').tooltip();
    $('#datepicker1').datepicker({format: "yyyy/mm/dd",language: "ja",autoclose: true,orientation:'bottom left'});
    $('#datepicker2').datepicker({format: "yyyy/mm/dd",language: "ja",autoclose: true,orientation:'bottom left'});
})