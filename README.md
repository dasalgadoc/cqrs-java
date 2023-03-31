<h1 align="center">
  🚀 🍮 CQRS (Async) Java implementation
</h1>

<p align="center">
  Basic example of CQRS implementation in a Java 11 SpringBoot rest api. It uses MySql as DB and RabbitMQ as AMQP to deal with Commands, Queries and Domain events.
</p>

## 🧲 Environment Setup

### 🛠️ Needed tools

1. Java 11
2. Maven
3. Docker and Docker compose (I use Docker version 23.01.1 and docker-compose v2.17.0)

### 🏃🏻 Application execution

1. Make sure to download all __Needed tools__
2. Clone the repository
```bash
git clone https://github.com/dasalgadoc/cqrs-java.git
```
3. Build up maven project
```bash
mvn dependency:resolve
```
4. Compile the project
````bash
mvn compile && mvn package
````

If you have errors using the previous command try this:
````bash
mvn clean install -DskipTests
````
5. Once target file and compile .jar were generated, you can run the docker environment.
_Note_: If you change the project name, make sure to change the Dockerfile as well as follows.
   
````dockerfile
ARG JAR_FILE=./target/<COMPILED NAME>.jar

ADD ${JAR_ILE} <COMPILED NAME>.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/<COMPILED NAME>jar"]  
````

Run the environment use

````bash
docker-compose up --build
````

### 🤳🏻 Util commands

You can run any docker from compose using:
````bash
docker-compose up -d <CONTAINER NAME>
  
docker-compose up -d mysql-container
````

Also, you can access to any docker using:
````bash
docker exec -it cqrs-java-<CONTAINER NAME>-1 bash

docker exec -it cqrs-java-mysql-container-1 bash
````

## 🧳 Project use

If everything went ok, you can test the ping endpoint, notice that docker forwarded the 8080 docker port to 8085 localhost.
You can change this in the docker-compose file

```bash
curl --request GET \
  --url http://localhost:8085/ping
```

Expected results:
```html
  Pong
```

Others, API endpoints are:

#### 🙋🏻‍ Command (Async RabbitMQ)

Create a blog
````bash
curl --request POST \
  --url http://localhost:8085/blog \
  --header 'Content-Type: application/json' \
  --data '{
	"id": "a9771f2c-7e23-4473-ba7d-ae1db3313d96",
	"title": "My title",
	"type": "TUTORIAL",
	"brief": "My brief",
	"url": "http://www.example.com"
}'
````

Beware! Body structure is important to this endpoint. Async commands and CQRS it's not about validation! So

```json
{
  "id": "<UUID STRING>",
  "title": "<STRING WITH 140 CHARACTERS OR LESS>",
  "type": "<CHOICE ONE IN ALL CAPS (TUTORIAL/NEWS/OPINION/RANT)>",
  "brief": "<STRING WITH 400 CHARACTERS OR LESS>",
  "url": "<STRING THAT MATCHES WITH THIS ^(http|https)://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$ REGEX>"
}
```

#### 💁🏻 Query (Async RabbitMQ)

Find blog by ID
```bash
curl --request GET \
  --url http://localhost:8085/blog/a9771f2c-7e23-4473-ba7d-ae1db3313d96
```

```bash
curl --request GET \
  --url http://localhost:8085/blog/<UUID>
```

#### 💁🏻 Query (Sync in memory)

````bash
curl --request GET \
  --url 'http://localhost:8085/count?entity_type=Blogs'
````
