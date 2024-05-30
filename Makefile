.PHONY: run

run:
	@echo	"Running Dockerfile"
	@docker build -t api-gateway:1.0.0 ./api-gateway
	@docker build -t delivery-service:1.0.0 ./delivery-service
	@docker build -t member-service:1.0.0 ./member-service
	@docker build -t order-service:1.0.0 ./order-service
	@docker build -t product-service:1.0.0 ./product-service
	@docker build -t review-service:1.0.0 ./review-service
