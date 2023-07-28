# HRWorks Showcase

This project serves as a showcase for demonstrating the synchronization of times between Clockify and HRWorks using Camunda 7 and following the principles of the Hexagonal Architecture.

## Run the Stack
To run the Camunda Platform with the history plugin and a PostgreSQL database, follow these steps:

### Step 1: Build the Docker Image

First, build the custom Camunda Docker image that includes the history plugin:

```bash
docker build -t camunda-7-with-history-plugin -f Dockerfile.camunda .
```

### Step 2: Start the Stack

Once the Docker image is built, use `docker-compose` to start the Camunda Platform along with the PostgreSQL database:

```bash
docker-compose up
```
The Camunda Platform and the PostgreSQL database will now be up and running in a containerized environment.

### Accessing Camunda Web Applications

After starting the stack, you can access the Camunda Web Applications using the following URLs:

Camunda Tasklist: http://localhost:8080/camunda/app/tasklist/default/#/?searchQuery=%5B%5D
Camunda Cockpit: http://localhost:8080/camunda/app/cockpit/default/#/dashboard
Camunda Admin: http://localhost:8080/camunda/app/admin/default/#/

## Run Tests

To run the tests for this showcase, execute the following command:

```bash
mvn clean test
```

## License

This showcase is licensed under the [MIT License](LICENSE).
