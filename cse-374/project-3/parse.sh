#!/bin/bash
# Zach Willson - HW3 Problem 2
if [ $# -lt 2 ]
then
    echo Not enough arguments >&2 && exit 1
fi
if [ -e $1 ]
then
    # Make output file and set up temp files
    touch $2
    temp1="$(mktemp)"
    temp2="$(mktemp)"
    temp3="$(mktemp)"
    temp4="$(mktemp)"
    # Use several different sed and grep commands to remove unwanted lines
    sed -E -e 's/.*h/h/g' $1 > $temp1
    sed -E -e 's/>.*/>/g' $temp1 > $temp2
    sed -E -e 's/\/">/\//g' $temp2 > $temp3
    sed -E -e 's/">//g' $temp3 > $temp4
    grep -E -e "http://" $temp4 > $2
    # Manually remove the first two lines which are form the html file
    sed -i -e '1,2d' $2
    rm $temp1 $temp2 $temp3 $temp4
    # Clean up temp files
else 
    echo HTML does not exist >&2 && exit 1
fi
exit 0
