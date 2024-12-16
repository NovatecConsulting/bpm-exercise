# Chapter3: Camunda 8

## Solution: Camunda 8 Application 'exam-registration'

If you want to try the solution, add the following properties in the `application.properties` file under
`src/main/resources` or generate a `application-local.properties` file for your local development:

- `zeebe.client.cloud.region`
- `zeebe.client.cloud.clusterId`
- `zeebe.client.cloud.clientId`
- `zeebe.client.cloud.clientSecret`

The `Application` will deploy the process (the BPMN) and DMN into your Camunda 8 SaaS.
You can start the process in the Camunda Tasklist.