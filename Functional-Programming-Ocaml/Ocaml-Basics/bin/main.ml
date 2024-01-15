let rec length lst = match lst with
  |[] -> 0
  |h::t -> 1+length t;;
(* Problem 1 *)
let rec pow x n =
  if n == 0 then
    1
  else 
    x * pow x (n-1);;
let rec float_pow x n =
  if n == 0 then
    1.0
  else 
   float_pow x (n-1) *. x;;
(* Problem 2 *)
let rec compress x = match x with
  |[] -> []
  |[h] -> [h]
  |h::(x :: _ as t) -> if h = x then compress t else h::compress t;;
(* Problem 3 *)
let rec remove_if list f = match list with
  |[] -> []
  |h::t -> if (f h) then remove_if t f else h::remove_if t f;;
(* Problem 4 *)
let rec slice_tmp list left right tmp = match list with
    |[] -> tmp
    |h::t -> if left == 0 then 
      slice_tmp list (left-1) right tmp
    else if left > 0 then 
      slice_tmp t (left-1) (right-1) tmp
    else if right == 0 then 
        tmp
    else slice_tmp t left (right-1) (h::tmp);;
  let reverse list =
    let rec aux acc = function
    | [] -> acc
    | h::t -> aux (h::acc) t in aux [] list;;
let slice list left right =
  if left > right then [] else 
    if right > (length list) then 
      reverse (slice_tmp list left ((length list)) []) else
  reverse (slice_tmp list left right [])
(* Problem 5 *)
let rec equivs_helper f lst = match lst with
| [] -> []
| h :: t -> if (f h) then h :: (equivs_helper f t)
else (equivs_helper f t);;
let rec modlist f e = match f with
|[] -> []
|h::t -> if h = e then modlist t e else h:: (modlist t e)
let rec compare lst1 lst2 = match lst1 with
|[] -> lst2
| h :: t -> compare t (modlist lst2 h)
let rec equivs f lst = match lst with
| [] -> [[]]
| h :: t -> match (compare (equivs_helper (f h) lst) t) with
| [] -> [(equivs_helper (f h) lst)]
| h1::t1 -> (equivs_helper (f h) lst) :: (equivs f (compare (equivs_helper (f h) lst) t));;
(* Problem 6 *)
let rec tmp a i = match a with
  |2 -> true
  |n when n < 2 -> false
  |n when n mod i = 0 -> false
  |n when n < i*i -> true
  |_ -> tmp a (i+1);;
let checkPrime a = tmp a 2;;
let rec goldbachpair_helper (a,b) = match (a,b) with
  |(a,b) -> if (checkPrime a) && (checkPrime b) then (a,b) else
    (goldbachpair_helper ((a+1),(b-1)));;
let goldbachpair a = goldbachpair_helper (2,a-2);;
(* Problem 7 *)
let rec equiv_on f g lst = match lst with
  |[] -> true
  |h::t -> if ((f h) = (g h)) then equiv_on f g t else false;;
(* Problem 8 *)
let rec pairwisefilter_helper cmp lst = match lst with
  |[] -> []
  |[x] -> x::pairwisefilter_helper cmp []
  |a:: (b::_ as t) -> (cmp a b)::pairwisefilter_helper cmp (List.tl t);;
let rec pairwisefilter cmp lst = match (pairwisefilter_helper cmp lst) with t ->
  if (length t mod 2 == 0) then 
    pairwisefilter cmp t
else t
(* Problem 9 *)
let rec polynomial lst x = match lst with
  |[] -> 0
  |(a,b)::t -> a * (pow x b) + polynomial t x
(* Problem 10 *)
  let rec append_list lst a = match lst with 
    |[] -> [] 
    |h::t -> (a::h) :: append_list t a 
  let rec powerset lst = match lst with
    |[] -> [[]]
    |h::t -> (powerset t) @ (append_list (powerset t) h)



