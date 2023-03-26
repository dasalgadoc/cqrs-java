# cqrs-java
Demo CQRS project using Java

```
mvn compile && mvn package
mvn clean install -DskipTests
docker-compose up --build
docker-compose up -d mysql-container
docker stop cqrs-java-mysql-container-1
docker exec -it cqrs-java-mysql-container-1 bash
```
