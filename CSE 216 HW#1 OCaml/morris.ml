let rec morris_helper lst counter =
    match lst with
    | [] -> lst
    | h::[] -> counter::h::morris_helper [] 0
    | h::t -> if h == List.hd t then morris_helper t (1 + counter) else counter::h::morris_helper t 1;;
let morris lst = 
    morris_helper lst 1;;
let rec revomventh_helper lst n counter = 
    match lst with
    | [] -> lst
    | h::[] -> if n == counter then revomventh_helper [] n (counter + 1) else h::revomventh_helper [] n (counter + 1)
    | h::t -> if n == counter then revomventh_helper t n (counter + 1) else h::revomventh_helper t n (counter + 1);;
let removenth lst n = 
    revomventh_helper lst n 0;;

let rec lastelem lst = 
    match lst with 
    | [] -> -1
    | h::[] -> h
    | h::t -> lastelem t;;

let rec rev_helper lst acc = 
    match lst with 
    | [] -> acc
    | h::t -> rev_helper t (h::acc);;
let reverse lst = 
    rev_helper lst [];;
let rec isPalindrome lst lst2 = 
    match lst with
    | [] -> true
    | h::t -> if(h == List.hd lst2) then isPalindrome t (List.tl lst2) else false;;
let palindrome lst =
    isPalindrome lst (reverse lst);;
 
    