.PHONY: run

REGISTRY ?= registry
IMAGETAG ?= latest

run:
	@echo	"Running Dockerfile"
	@docker build -t ${REGISTRY}/api-gateway:${IMAGETAG} ./api-gateway
	@docker build -t ${REGISTRY}/delivery-service:${IMAGETAG} ./delivery-service
	@docker build -t ${REGISTRY}/member-service:${IMAGETAG} ./member-service
	@docker build -t ${REGISTRY}/order-service:${IMAGETAG} ./order-service
	@docker build -t ${REGISTRY}/product-service:${IMAGETAG} ./product-service
	@docker build -t ${REGISTRY}/review-service:${IMAGETAG} ./review-service
