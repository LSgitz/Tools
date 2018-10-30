#!/bin/bash
count=0
>1.xml
touch 1.xml
while read line;do
((count++))
{
	echo $line >>1.xml
	find path "*$line*" -exec
	if [ $count -eq 1 ] && [ $((count%1)) -eq 0 ];then
		echo "1 line is reached current count is :"$count
		count=0
	fi
}
done < $1
echo "finshed"