#!/bin/sh
mvn archetype:create -DarchetypeGroupId=org.richfaces.cdk -DarchetypeArtifactId=maven-archetype-jsfwebapp \
         -DarchetypeVersion=3.3.1.SP3 -Dversion=3.3.1.SP3 -DgroupId=org.richfaces.samples -DartifactId=$1
