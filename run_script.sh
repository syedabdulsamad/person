#!/bin/bash

docker-compose down --rmi local --remove-orphans
mvn clean install -DskipTests
docker-compose up

