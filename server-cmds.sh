#!/user/bin/env bash

# $1 is for first parameter passed to script in Jenkinsfile
# def shellCmd = "bash ./server-cmds.sh ${IMAGE_NAME}"
#  ${IMAGE_NAME} <-- first parameter
export IMAGE=$1
docker-compose -f docker-compose.yaml up --detach
echo "success"