#!/bin/bash

# Zookeeper 서비스 배포
kubectl apply -f zookeeper-svc.yaml

# Kafka 서비스 배포
kubectl apply -f kafka-svc.yaml

echo "Zookeeper와 Kafka 서비스가 성공적으로 배포되었습니다."