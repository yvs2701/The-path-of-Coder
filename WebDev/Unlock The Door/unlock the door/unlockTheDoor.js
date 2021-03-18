// add intro and timer and a max moves as footer,show results on last screen
//add an animation on the win page.
$(function(){
play();
$(".win").hide();
$(".rules").on("click", function(){
    alert("Unlock the Lift Door. There are six buttons on the lock. Pressing a button will change the buttons surrounding it (but not the one you pressed) from red to green or vice-versa.\nTo unlock the door you must make all the buttons green.\nAll the best for your escape.");
});//rules
function ranGen()
{var nums = [];
var ran=parseInt((Math.random()*1000));
if(ran/2 ==0)
for(var i = 0; i < 2 ; i++)
{   
    var num =Math.floor(Math.random() *6)+1;
    /* num gets any random number of Int type math.random is multiplied by 6 because — The math. random() function returns a floating-point, pseudo-random number between range 0,1, 0 (inclusive) and 1 (exclusive). so multiplies by 6 gives you a range of 0,6 and +1 make the range from 1,7 but by math floor only Int value are stored.*/
    nums.push(num);
}
else
for(var i=0;i<3;i++)
{
   var num=Math.floor(Math.random()*6)+1;
   nums.push(num);
}
return nums;
}
function play(){
var nums= [];
nums=ranGen();
var n;
for( n of nums){
$(".btn").eq(n).addClass("red");/* toggleClass is not used because when ranGen send array of two number in which both are same then toggle will first add and then remove the red class which would be a bug*/
}}
function end(){
if($("button").eq(0).hasClass("red")==false && $("button").eq(1).hasClass("red")==false && $("button").eq(2).hasClass("red")==false && $("button").eq(3).hasClass("red")==false && $("button").eq(4).hasClass("red")==false && $("button").eq(5).hasClass("red")==false)
{$(".wrapper").css({borderColor:"green"});
 setTimeout(function (){
 $("game").hide();
 $(".win").show();
 $("body").css({backgroundImage:"none", backgroundColor:"black"});
 },1200)
 }
}
//controls
$("body").on("keyup", function(k){
    var key=k.which;
    if(key==49||key==97)
    $(".btn").eq(0).click();
    else if(key==50||key==98)
    $(".btn").eq(1).click();
    else if(key==51||key==99)
    $(".btn").eq(2).click();
    else if(key==52||key==100)
    $(".btn").eq(3).click();
    else if(key==53||key==101)
    $(".btn").eq(4).click();
    else if(key==54||key==102)
    $(".btn").eq(5).click();
});
$(".btn").eq(0).on("click",function(){
    $(".btn").eq(1).toggleClass("red")
    $(".btn").eq(2).toggleClass("red")
    end()
});
$(".btn").eq(1).on("click",function(){
    $(".btn").eq(0).toggleClass("red")
    $(".btn").eq(3).toggleClass("red")
    end()
});
$(".btn").eq(2).on("click",function(){
    $(".btn").eq(0).toggleClass("red")
    $(".btn").eq(3).toggleClass("red")
    $(".btn").eq(4).toggleClass("red")
    end()
});
$(".btn").eq(3).on("click",function(){
    $(".btn").eq(1).toggleClass("red")
    $(".btn").eq(2).toggleClass("red")
    $(".btn").eq(5).toggleClass("red")
    end()
});
$(".btn").eq(4).on("click",function(){
    $(".btn").eq(2).toggleClass("red")
    $(".btn").eq(5).toggleClass("red")
    end()
});
$(".btn").eq(5).on("click",function(){
    $(".btn").eq(4).toggleClass("red")
    $(".btn").eq(3).toggleClass("red")
    end()
});
});
/*
Hints and Rules :-
The starting pattern of lights is random, but here are some possible combinations (out of many others). 
The button arrangement: 
1 2 
3 4 
5 6
Start: 2 and 6 are red the  Press 1, 5 
Start: 4, 5 and 6 are red then Press 6, 3, 1
 Start: 2, 3 and 6 are red then Press 5, 3, 5 Start: 2, 5 and 6 are red Press 3, 2, 1, 5 
Start: 6 is red then Press 4, 1 
Every button toggles the colour of surrounding buttons (not the diagonals) but not itself.
1 toggles 2 and 3
2 toggles 1 and 4
3 toggles 1,4 and 5
4 toggles 2,3 and 6
5 toggles 3 and 6
6 toggles 4 and 5
*/