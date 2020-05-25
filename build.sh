#!/usr/bin/env bash

./mvnw clean

./mvnw install -DskipTests=true -Dmaven.javadoc.skip=true -B -V

./mvnw test -B

./mvnw spring-boot:build-image
