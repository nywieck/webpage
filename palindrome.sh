#!/bin/bash

palindrome() {
var=$*
var1=$(echo $var | awk '{print tolower($var)}' | tr -cd [:alnum:])
length=${#var1}
half=$((length/2))
flag=0

for (( i=0; i<$half; i++ ))
	do
	start=${var1:i:1}
	end=${var1:$length-i-1:1}
	if [ $start != $end ]
		then
		printf "No this is not a palindrome. Grade: F+, day 1 recycle.\n"
		flag=1
		break
	fi
	done

if [ $flag = 0 ]
	then
	printf "Yes this is a palindrome! You are my role-model, thank you for your service - free Golden Corral for a year.\n"
fi
}