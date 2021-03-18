function solve ()
{ const formatter = new Intl.NumberFormat('en-US', {minimumFractionDigits: 0,
maximumFractionDigits:2
});/*makes max decimal places as 2 and min as 0
this scary looking thing is used to write currencies in minimum and maximum decimal places specified this also converts a number value to a string and appends a currency symbol before it if specified by the code*/
  var flag=true;
  var a = document.getElementById("a").value;
  var b = document.getElementById("b").value;
  var c = document.getElementById("c").value;
 if(a=="")
 {a=1;
  document.getElementById("a").value=1;}
 
 if(b=="")
 {b=1;
 document.getElementById("b").value=1;}
 
 if(c=="")
 {c=1;
 document.getElementById("c").value=1;}
let sign=[];
sign=document.getElementsByClassName("sign");
if(sign[0].value="")
sign[0]="+";

for(var i =1;i<sign.length;i++) 
if(sign[i].value==""||(sign[i].value!="+" && sign[i].value!="-")||!$.isNumeric(a)||!$.isNumeric(b)||!$.isNumeric(c))
{
flag = false;
}
if(!flag)
swal.fire("Invalid","recheck your equation","error");
else
{ if(sign[0].value=="-")
  a=-a;
  if(sign[1].value=="-")
  b=-b;
  if(sign[2].value=="-")
  c=-c;
  var D= (b*b) - (4*a*c);
var r1,r2,R1,R2;

if (D>0)
{
 r1 = formatter.format(-(b - Math.sqrt(D))/(2*a));
 r2 = formatter.format(-(b + Math.sqrt(D))/(2*a));
 R1=-r1;
 R2=-r2;
if (R1>=0)
R1="+"+R1;
if (R2>=0)
R2="+"+R2;
 swal.fire({
     title: "Roots : "+r1+" & "+r2,
     icon:"success",
     html:"<u>Factors :</u> <br \> (x"+R1+")(x"+R2+")"
 });
 
}
else if (D==0)
{ 
 r1=r2=formatter.format((-b + Math.sqrt(D))/(2*a));
 R1=-r1;
 R2=-r2;
if (R1>=0)
R1="+"+R1;
 swal.fire({
     title: "Root : "+r1,
     html:"<u>Reduced form :</u> <br \> (x"+R1+")²",
     icon:"success"
 });
}
else
{ let r=formatter.format(-b/(2*a)) ;
 r1 =r +"+"+(formatter.format(Math.sqrt(-D)/(2*a))+"i");
 r2 =r +"-"+(formatter.format(Math.sqrt(-D)/(2*a))+"i");
 // now take time to get the logic !
R1=-r + "+"+(formatter.format(Math.sqrt(-D)/(2*a))+"i");
R2=-r + "-"+(formatter.format(Math.sqrt(-D)/(2*a))+"i");
if(r<0)
{ r=-r;
R1="+"+r+"+"+(formatter.format(Math.sqrt(-D)/(2*a))+"i");
 R2="+"+r+"-"+(formatter.format(Math.sqrt(-D)/(2*a))+"i");
}
 swal.fire({
     title: "Roots : "+r1+" & "+r2,
     html:"<u>Factors :</u> <br \> (x"+R1+")(x"+R2+") <br \> *Where i stands for iota",
     icon:"success"
 });
}
}}

window.onload=function(){
$('.sign').keyup(function(){
if($(this).val().length==$(this).attr("maxlength")){
            $(this).next().focus();
        }
    });
$("#submit").on("click",function(){
    solve();
});
$("#clear").on("click",function(){
 document.getElementsByTagName("form")[0].reset();});
 
$("#features").on("click", function (){
swal.fire({
        title:"Features :",
   html: "<ul><li>Simplifies the equation.</li><li>Can calculate IMAGINARY roots !</li><li>Auto-shifts the sign fields.</li><li>SMART-fill feature !<br \>(if number fields are left empty, they're assumed 1 and the first sign field is assumed '+')</li></ul>"+"<br \> More are on the way !",
   icon: "info"
 });
});
}