.PHONY: run

REGISTRY ?= wan2da
IMAGETAG ?= latest

run:
	@echo "gradle build"
	@./gradlew clean build

	@echo	"Running Dockerfile"
	@docker build -t ${REGISTRY}/api-gateway:${IMAGETAG} ./api-gateway
	@docker build -t ${REGISTRY}/delivery-service:${IMAGETAG} ./delivery-service
	@docker build -t ${REGISTRY}/member-service:${IMAGETAG} ./member-service
	@docker build -t ${REGISTRY}/order-service:${IMAGETAG} ./order-service
	@docker build -t ${REGISTRY}/product-service:${IMAGETAG} ./product-service
	@docker build -t ${REGISTRY}/review-service:${IMAGETAG} ./review-service

	@echo "push docker images to registry"
	@docker push ${REGISTRY}/api-gateway:${IMAGETAG}
	@docker push ${REGISTRY}/delivery-service:${IMAGETAG}
	@docker push ${REGISTRY}/member-service:${IMAGETAG}
	@docker push ${REGISTRY}/order-service:${IMAGETAG}
	@docker push ${REGISTRY}/product-service:${IMAGETAG}
	@docker push ${REGISTRY}/review-service:${IMAGETAG}