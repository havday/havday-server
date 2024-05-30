.PHONY: run

REPOSITORY ?= wan2da

run:
	@echo "gradlew docker build"
	@./gradlew docker -PdockerRepository=${REPOSITORY}

	@echo "gradlew docker push"
	@./gradlew dockerPush