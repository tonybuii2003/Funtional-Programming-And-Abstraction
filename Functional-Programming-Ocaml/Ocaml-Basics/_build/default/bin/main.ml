open Printf
let rec pow x n =
  if n == 0 then
    1
  else 
    x * pow x (n-1);;
let a = pow 2 3;;
printf "pow 2 3 = %d\n" a;;
let rec float_pow x n =
  if n == 0 then
    1.0
  else 
   float_pow x (n-1) *. x;;

float_pow 2. 3;;  



