#!/bin/bash

# USAGE: transpose rows columns

transpose()
{

row=${1:?}
col=${2:?}
elem=$(($col * $row))

declare -a grid

for ((i=0;i<elem;i++))
do
	grid[$i]=$[RANDOM%100+1]
done

printf "[1] This is the original grid:\n"
for ((j=0;j<row;j++))
do
	for ((k=0;k<col;k++))
	do						# translate 2d array position into 1d array index:
		idx=$(($col * j + k))			# col * j + k = totalColumns * row + column
		printf "%s\t" "${grid[idx]}"
	done
	printf "\n"
done

printf "\n[2] This is the transposed grid:\n"		# just reverse order: print columns first as rows, and rows second as columns
for ((k=0;k<col;k++))
do
	for ((j=0;j<row;j++))
	do
		idx=$(($col * j + k))
		printf "%s\t" "${grid[idx]}"
	done
	printf "\n"
done

}