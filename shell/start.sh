#!/bin/bash

nohup java -jar wms-admin-1.0.jar --wms-admin-profile=prod > /dev/null 2>&1 &

echo "Starting......"
