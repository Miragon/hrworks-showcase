# HRWorks Showcase

This project serves as a showcase for demonstrating the synchronization of times between Clockify and HRWorks using Camunda 7 and following the principles of the Hexagonal Architecture.

## Run the Stack

To run the Camunda Platform with the history plugin and a PostgreSQL database, follow these steps:

### Step 1: Build the Docker Image

First navigate to the hrworks-showcase-c7 directory:

```bash
cd stack/hrworks-showcase-c7
```

Then build the Docker image using the following command:

```bash
docker compose build
```

### Step 2: Start the Stack

Once the Docker image is built, use `docker-compose` to start the Camunda Platform along with the PostgreSQL database:

```bash
docker compose up
```
The Camunda Platform and the PostgreSQL database will now be up and running in a containerized environment.

### Accessing Camunda Web Applications

After starting the stack, you can access the Camunda Web Applications using the following URLs:

Camunda Tasklist: http://localhost:8080/camunda/app/tasklist/default/#/?searchQuery=%5B%5D
Camunda Cockpit: http://localhost:8080/camunda/app/cockpit/default/#/dashboard
Camunda Admin: http://localhost:8080/camunda/app/admin/default/#/

## Project Setup

To run this project, you need to have a running instance of Camunda Platform 7.
Set the base URL to it in the `application.yml` of the `timesync-camunda7` module:

```yaml
# Camunda Platform 7
camunda:
  bpm:
    client:
      base-url: http://localhost:8080/engine-rest
```

Additionally, you must set the Clockify API key in the `application.yml`:

```yaml
# Clockify
clockify:
  apiKey: "<API-KEY>"
```

The Clockify API key can be found on the [Clockify website under "User Settings"](https://app.clockify.me/user/settings).
For convenience, we have set up two test accounts, and the credentials can be found in 1Password.

Moreover, you need to set the HRWorks `accessKey` and `secretAccessKey` inside the `application.yml`:

```yaml
# HRWorks
hrworks:
  accessKey: "<ACCESS-KEY>"
  secretAccessKey: "<SECRET-ACCESS-KEY>"
```
The HRWorks keys can be generated on the HRWorks admin page at https://ssl1.hrworks.de/demo-a03/HrwScStartView under
"Grundlagen" > "Integration" > "HRWorks-API".

Please ensure that you have obtained the necessary credentials and correctly configured the `application.yml` before
running the project.

## Running the Project

After following the [Project Setup](#project-setup) you should be able to simply start the `TimeSyncCamunda7Application`.

## Deploy artifacts

You can use the [Camunda Modeler](https://camunda.com/de/download/modeler/) to deploy the process.
1. Open the [bpmn file](./timesync/timesync-camunda7/src/main/resources/bpmn/hrworks-timesync.bpmn)
2. Add the [form](./timesync/timesync-camunda7/src/main/resources/.camunda/forms/error.form) to your deployment  
<img src="https://github.com/Miragon/hrworks-showcase/blob/55510cff5ce7f3765623d516480853c411d44112/images/deployment.png?raw=true" alt="deployment" width=180>
3. Click on `Deploy`

## Run Tests

To run the tests for this showcase, execute the following command:

```bash
mvn clean test
```

## License

This showcase is licensed under the [MIT License](LICENSE).
