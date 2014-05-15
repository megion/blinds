#!/bin/bash

app_name="vblinds" 
data_dir=$(rhc ssh $app_name 'echo $OPENSHIFT_DATA_DIR')
echo "Openshift data dir: $data_dir"

echo "Build WAR file for deploy manual deploy to openshift"
mvn -Denv.OPENSHIFT_DATA_DIR=$data_dir clean package -Popenshift,tomcat

#echo "Stoping app"
#rhc app stop -a $app_name

#rhc scp $app_name upload deployments/ROOT.war app-root/runtime/dependencies/jbossews/webapps/ROOT.war

echo "Upload war"
rhc scp $app_name upload megion-blindsweb/target/ROOT.war app-root/data/ROOT.war

#echo "Starting app"
#rhc app start -a $app_name
