#!/bin/bash
# Zach Willson - HW3 Problem 1
if [ $# -lt 1 ] 
then
    echo Need more arguments >&2 && exit 1

fi
output="$(mktemp)"
page="$(mktemp)"
# These below are my temp files to help suppress output to the console
# The line below calls wget on the page lassed in $1
wget --tries=5 --timeout=3 -o $output -O $page $1
# The lin below separates the file size from the rest of the information
filesize=$(stat -c%s "$page")
echo $filesize
rm $output $page
# exit successfully after cleaning my temp files
exit 0
