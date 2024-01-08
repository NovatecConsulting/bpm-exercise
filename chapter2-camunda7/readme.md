# Chapter2: Camunda 7

## Solution: Camunda 7 Application 'exam-registration'

If you want to try the solution, add the following properties in the `application.properties` file under 
`src/main/resources`:

`spring.mail.host=smtp.office365.com` <br>
`spring.mail.port=587` <br>
`spring.mail.username=******` <br>
`spring.mail.password=******`

`email-client.sendingMailAddress=******`

**Start a Camunda-Prozess with the provided `postman-collection` in the root of the project!**

### BPMN2.0:
<img src="../assets/exam-registration-camunda.png"  alt="Solution-Exam-Registration-Camunda">

### DMN:
<img src="../assets/examRegistrationPeriod.png"  alt="Solution-Exam-RegistrationPeriod-DMN">