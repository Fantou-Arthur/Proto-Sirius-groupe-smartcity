#!/bin/bash

if [ ${ondjazndz} ]; then
	M2Path=${M2_REPO} # the M2_REPO system variable need to be set 
else
	echo "M2_REPO path not set"
	exit
fi
version='1.0-SNAPSHOT'
backendPath=${M2Path}'\edu\ezip\ing1\pds\xmart-city-backend\'${version}'\xmart-city-backend-'${version}'-jar-with-dependencies.jar'

#echo ${M2Path}
#echo ${version}
#echo ${backendPath}

scp ${backendPath} dracofeu@172.31.253.27:xmart-city-backend.jar