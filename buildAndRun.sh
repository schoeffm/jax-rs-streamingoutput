#!/bin/sh
mvn clean package && docker build -t schoeffm/streamoutput .
docker rm -f streamoutput || true && docker run -d -p 8080:8080 -p 4848:4848 --name streamoutput schoeffm/streamoutput 
