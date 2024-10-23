MAVEN := ./mvnw

run-docker: build-docker
	@docker compose --env-file=.env up

build-docker: build-maven
	@docker compose build

build-maven:
	@${MAVEN} clean package
