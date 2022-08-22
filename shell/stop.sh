#!/bin/bash
appName="wms-admin-1.0.jar"

PID=$(ps -ef | grep "${appName} --wms-admin-profile=prod" | grep -v grep | awk '{ print  }')

if [ ! $PID ]; then
    echo "$appName stopped!!!"
else
    echo "wms-admin stopping $appName pid: ${PID}"

    kill -9 ${PID}

    echo "$profile stop success"
fi
