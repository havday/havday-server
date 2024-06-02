#!/bin/bash

kubectl delete -f zookeeper-svc.yaml

kubectl delete -f kafka-svc.yaml

echo "Zookeeper와 Kafka 서비스가 성공적으로 제거되었습니다."