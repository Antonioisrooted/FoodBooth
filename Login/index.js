$(document).ready(function(){
$('#text1').hide()
$("#login").click(function() {
$("#text1").show();
$("#login").hide();
});
}
);
$(document).ready(function(){
$("#text1").click(function(){
  $("#text1").hide();
  $("#login").show()
});
});

