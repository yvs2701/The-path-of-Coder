$(function() {
    $("input").on("keyup", function(k){
        var key= k.which;
        if(key==13)
            $("#add").click();
    });
    $("#clean").on("click", function(){
    $("#mylist").slideUp();
    setTimeout(function(){
    $("#mylist").empty();
    $("#mylist").slideDown();
    },1000); 
    });
    $("#add").on("click", function() {
        var val = $("input").val();
//using val.trim() so that the input contains no extra spaces and input can't be whitespaces        
        if(val.trim() !== '') {
            var elem = $("<li></li>").text(val.trim());
            $(elem).append("<button class='del'>X</button>").append("<button class= 'tick'>✓</tick>");
            $(elem).children().eq(1).hide();
            $("#mylist").append(elem);
            $("input").val("");}
//the cross button :-
/*font-weight was not working with chrome so i used opacity in its place to produce the same effect*/
  $(".del").on("click", function() {
  $(this).parent().css({textDecoration:"line-through", /*fontWeight:"lighter",*/ color:"rgba(255,0,0,0.4)"});
 $(this).next().show();
  $(this).hide();});
//the tick button :-  
$(".tick").on("click",function(){
$(this).parent().css( {textDecoration:"none", color:"rgba(255,0,0,1)"/*,fontWeight:"normal"*/});
    $(this).prev().show();
    $(this).hide();
    });
        
    });
});