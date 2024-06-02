.PHONY: run

REPOSITORY ?= wan2da

run:
	@echo "gradlew daemon stop"
	@ ./gradlew --stop

	@echo "gradlew docker build"
	@./gradlew docker -PdockerRepository=${REPOSITORY}

	@echo "gradlew docker push"
	@./gradlew dockerPush