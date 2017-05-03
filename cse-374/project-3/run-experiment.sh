#!/bin/bash
# Zach Willson - HW3 Problem 3
if [ $# -lt 2 ]
then
    echo Not enough arguments >&2 && exit 1
fi
# count increments for each line
count=0
while read -r line
do
    count=$((count+1))
    name="$line"
    echo Performing measurement on $name
    size=`./perform-measurement.sh $name`
    if [ $size -ne 0 ]
    then
	# prints success to console and output to file $2
	echo ...success
	echo $count $name $size >> $2
    else
	echo ...failed
    fi
done < "$1"

exit 0
