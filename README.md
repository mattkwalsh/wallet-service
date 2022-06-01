# Wallet Coding Exercise
A Spring Boot REST service, implementing a Wallet service to be called by game clients.

The service supports 4 endpoints
* Create a named wallet
* Retrieve a wallet and all of its attributes and contents
* Add an item to a specific wallet. Items are keyed by specific strings, allowing them to be easily expanded (or ignored
by older versions of a game client) 
* Retrieve a specific item from a particular endpoint.

See the [API documentation](./API.yaml) for specifics

### Required Software
* Java 11
* Docker Compose ([download link](https://docs.docker.com/compose/install/))

### Building
From this directory, run `./gradlew build` which will download the appropriate gradle 
version, build the service, and run the unit tests

### Running
This service requires a running instance of Mongo to work, which can be started via Docker: `docker-compose up -d`  

Once the DB is running, the service can be started via `./gradlew bootRun` and it will accept HTTP requests

### Potential Improvements
* Due to time constraints, this service does not have authentication, but a real production 
version would ensure a game client could only make requests for a particular user based on
the authentication scheme
* Application/functional tests, that actually spin up the service and its dependencies, then 
make HTTP calls, to validate the service functions as a whole
* Tests for the request validators
