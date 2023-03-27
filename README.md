# cqrs-java
Demo CQRS (Full Async) project using Java

```bash
mvn compile && mvn package
mvn clean install -DskipTests
docker-compose up --build
docker-compose up -d mysql-container
docker stop cqrs-java-mysql-container-1
docker exec -it cqrs-java-mysql-container-1 bash
```

Async commands it's not about validation!

```json
{
	"id": "a9571f2c-7e23-4473-ba7d-ae1db3313d96",
	"title": "My title",
	"type": "TUTORIAL",
	"brief": "My brief",
	"url": "http://www.example.com"
}
```