#!/bin/bash

echo 'build admin image and push to aliyun'

docker login --username=liangyali@1628222615223597 registry.cn-beijing.aliyuncs.com

docker build --no-cache -t  registry.cn-beijing.aliyuncs.com/jingtitong/door-sdk-server ./
docker push registry.cn-beijing.aliyuncs.com/jingtitong/door-sdk-server

ssh shuxiangjingyuan-server 'mkdir -p /opt/heg-door'
scp ./docker-compose.yml shuxiangjingyuan-server:/opt/heg-door
ssh shuxiangjingyuan-server 'docker pull registry-vpc.cn-beijing.aliyuncs.com/jingtitong/door-sdk-server'
ssh shuxiangjingyuan-server 'cd /opt/heg-door && docker compose down && docker compose up -d --build'



