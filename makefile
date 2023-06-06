build:
	mvn clean install
test:
	mvn test
run:
	mvn spring-boot:run
build-run:
	make build
	make run