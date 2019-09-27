#!/bin/bash

## valid_square function checks if a square coordinate consisting of one letter and one number is a valid san chess square
## Only accepts one argument
## test function tests all valid inputs and a few invalid

## USAGE: valid_square [test_square_here]
## USAGE: test

## Personal note:  REGULAR EXPRESSION GEM [[ ^[a-h]{1}[1-8]{1}$ ]]

valid_square()
{
if ! [ "$#" = 1 ]
then
	printf "Invalid input: only enter one argument.\n"
	return
elif ! [ ${#1} = 2 ]
then
	printf "Invalid input: only enter two characters (one number followed by one letter).\n"
	return
fi

if [[ $1 =~ [a-h][1-8] ]]
	then
	printf "YES \"%s\" IS a valid square according to standard algebraic chess notation.\n" "$1"
	else
	printf "NO \"%s\" is NOT a valid square according to standard algebraic chess notation.\n" "$1"
fi
}

test()
{
declare -a letterArr
letterArr=(a b c d e f g h i)
declare -a numberArr
numberArr=(1 2 3 4 5 6 7 8 9)

for i in {0..8}
do
	for j in {0..8}
	do
		square="${letterArr[$i]}${numberArr[$j]}"
		valid_square $square
	done
done

printf "valid_square A1\n"
valid_square A1

printf "valid_square a1 b2\n"
valid_square a1 b2

printf "valid_square a12\n"
valid_square a12
}