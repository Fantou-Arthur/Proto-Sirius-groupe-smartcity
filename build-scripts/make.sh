#!/bin/bash

echo "start of make script !"

echo "installing project"

sh ".\compile.sh"

echo "deploying backend on VM"

sh ".\deployBackend.sh"

echo "launching backend"

sh ".\launch_backend.sh"

if [ $# -gt 0 ];then

	for i in $@; do
		case $i in 
			-r|--run)
				echo "launching frontend"

				sh ".\launch_front.sh"
				shift
				;;
		esac
	done
fi

echo "end of make script !"

