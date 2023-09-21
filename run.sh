#!/bin/bash

echo "ACTION DEPLOY START"

sudo fuser -k 8080/tcp || echo "No process is using port 8080"

sleep 3

sudo nohup java -jar ~/cicd/lookiz-0.0.1-SNAPSHOT.jar > output.log 2> nohup.err < /dev/null &
