#!/bin/bash
if [ $1 ];
then
    if [ $1 = "debug" ];
    then
        java -server -Xms1024m -Xmx2048m -XX:MetaspaceSize=1024m -XX:MaxMetaspaceSize=2048m -XX:MaxHeapSize=2048m -XX:+AlwaysPreTouch -XX:+UseG1GC -XX:+ScavengeBeforeFullGC -XX:+DisableExplicitGC -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=1045 -jar target/checkout.jar
    fi;
else
    java -server -Xms1024m -Xmx2048m -XX:MetaspaceSize=1024m -XX:MaxMetaspaceSize=2048m -XX:MaxHeapSize=2048m -XX:+AlwaysPreTouch -XX:+UseG1GC -XX:+ScavengeBeforeFullGC -XX:+DisableExplicitGC -jar target/checkout.jar
fi;
