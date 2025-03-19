#!/bin/bash

ssh dracofeu@172.31.253.27 'if lsof -i tcp:3306; then kill $(lsof -i tcp:3306 | grep -o [[:digit:]]* | head -1)| echo "killed proccess"; else echo "not running";fi; exec java -jar xmart-city-backend.jar >logBackend.log 2>&1 </dev/null &'

