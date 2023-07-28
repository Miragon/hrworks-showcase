# HRWorks Showcase

This project serves as a showcase for demonstrating the synchronization of times between Clockify and HRWorks using Camunda 7 and following the principles of the Hexagonal Architecture.

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

## Run Tests

To run the tests for this showcase, execute the following command:

```bash
mvn clean test
```

## License

This showcase is licensed under the [MIT License](LICENSE).
