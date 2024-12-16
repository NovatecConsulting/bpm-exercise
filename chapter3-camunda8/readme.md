# Chapter3: Camunda 8

## 1. Camunda 8 Application 'exam-registration-c8'

### Business Context:
As part of the end-of-life of Camunda 7, your stakeholder has asked you to
migrate the Camunda 7 application to a Camunda 8 application. To get fast
results, you should use the camunda-7-adapter and not refactor every
Java-code (Java Delegates).

### Technical Context:
Use the Camunda community project https://github.com/camunda-community-hub/camunda-7-to-8-migration
as guidance. Implement the camunda-7-adapter: https://github.com/camunda-community-hub/camunda-7-to-8-migration/tree/main/camunda-7-adapter.

Note the following:
- No refactoring of Java Delegates is necessary.
- Insert the camunda-7-adapter into the application.
- Please note the differences between Camunda 7 and Camunda 8 BPMN: https://docs.camunda.io/docs/guides/migrating-from-camunda-7/adjusting-bpmn-models/
- The 'possible_solution' branch contains a possible solution.