# HRWorks Showcase

This project serves as a showcase for demonstrating the synchronization of times between Clockify and HRWorks using Camunda 7 and following the principles of the Hexagonal Architecture.

## The Process

<img alt="hrworks-bpmn-diagram" src="images/hrworks-timesync.png">

This BPMN diagram represents the **HRworks TimeSync Process** using the Camunda Modeler (exporter version 5.13.0).

The process involves synchronizing time-related data within the HRworks system. It consists of the following key steps:

1. **Start Event**: The process begins with a start event. This should be changed to a timer start event to trigger automatically at a specific time.

2. **Load Users Service Task**: This external service task named "Load Users" is executed asynchronously. It loads user data and related information. The loaded data includes users, employees, and workspace information.

3. **Subprocess - Sync Time Entries for Users**:
    - This subprocess is a multi-instance subprocess, executed asynchronously for each user.
    - It contains several tasks:
        - **Start Event**: This event initiates the subprocess for each user.
        - **Load Time Entries Service Task**: This external service task loads time entry data for the user.
        - **Sync Time Service Task**: Another external service task syncs the time entries for the user.
        - **Notify User Task**: This task notifies the user about the process status. There is one error triggered by purpose in the code to demonstrate the notification user task.

4. **End Events**:
    - The subprocess can end with two types of end events:
        - **"times synced"**: If the time synchronization process completes successfully.
        - **"error processed"**: If there was an error during the synchronization process.

### Flow of Execution

1. The process starts with a **Start Event** triggering the **Load Users Service Task**.
2. The loaded user data triggers the subprocess for each user.
3. In the subprocess:
    - **Load Time Entries** service task fetches time entries data for the user.
    - **Sync Time** service task synchronizes the time entries.
    - **Notify** user task informs the user about an error in the process.
4. Depending on the outcome, the subprocess concludes with either a successful `times synced` end event or an `error processed` end event.

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
<img src="images/deployment.png" alt="deployment" width=180>
3. Click on `Deploy`

## Run Tests

To run the tests for this showcase, execute the following command:

```bash
mvn clean test
```

## License

This showcase is licensed under the [MIT License](LICENSE).
