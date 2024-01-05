# Chapter2: Camunda 7

## 1. Camunda 7 Learning Videos

Please study this free courses by camunda and complete the exercises shown in the videos. These will give you a basic 
understanding of how to use the Camunda Platform 7 and how to develop with Camunda 7.

- [ ] [Camunda 7 - Platform Foundation](https://academy.camunda.com/c7-platform-foundation)
- [ ] [Camunda 7 - Platform for Java Developers](https://academy.camunda.com/c7-platform-java)
- [ ] [Camunda 7 - Platform & Microservices](https://academy.camunda.com/c7-platform-microservices)
- [ ] [Camunda 7 - Platform for DevOps](https://academy.camunda.com/c7-platform-devops)

## 2. Camunda 7 Application 'exam-registration'
At this point, you already know the business context. You and your team have decided to specify this a little further 
so that it is clear how you want to develop the process in camunda.

Please read the business context again and adapt your modeled BPMN2.0 model according to the technical specifications. 
It is then your task to implement the BPMN2.0 model using Camunda 7 and SpringBoot.
Remember: Everyone models and implements things differently. It is important that you fulfill the requirements 
accordingly.

### Business Context:
<u>In concrete terms, the following should be implemented with camunda:</u>

*A student begins the process by entering their e-mail address and the course they wish to register for. Next, an 
external system checks whether this student exists. If not, the process is terminated. If the student exists and the 
application is submitted within the official examination registration deadline of the examination office, the student 
is registered for the examination and informed accordingly. If the examination registration deadline is not met, the 
student will be informed that he/she can submit an application in which he/she justifies why his/her late registration 
should be accepted. If the student does not submit this application, the procedure will be discontinued after 7 days. 
If the student submits this application with reasons, it will be examined by the Examination Office. If the application 
is accepted, the student will be registered for the examination. If the application is rejected, the student will 
receive a notification that the application has been rejected.* 

### Technical Context:
Use https://start.camunda.com for creating an Initial SpringBoot-Camunda7-Projekt

Note the following:
- Start the process e.g. via postman, curl or write a small frontend if you like to
- Check if the Student exists with a small external service or a mock. 
- The 'possible_solution' branch contains a possible solution.