let isPrime (n : int) : bool =
  let rec noDivisors (m : int) : bool =
    m*m > n || (n mod m != 0 && noDivisors (m + 1))
  in
    n >= 2 && noDivisors 2

let rec goldbachHelper (a,b) = 
  match (a,b) with 
  | (a,b) -> if (isPrime a) && (isPrime b)then (a,b) else goldbachHelper((a+1),(b-1));;

let goldbachpair a = goldbachHelper(2,a-2);;