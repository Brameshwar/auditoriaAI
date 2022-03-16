# This Project is for auditoriaAI Assignment

### Tech Stack is as below
- Java
- Spring Boot
- Swagger
- Maven

### Steps to Run
1. Open the project in any IDE
2. Run command mvn clean install
3. Navigate to [Application](main/src/main/java/com/Application.java) file and Run
4. Open Any brower and open swagger [URL](http://localhost:9090/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/send-email-controller/sendEmail)
5. And trigger the api/vi/sendEmail API else you can run below curl.
   curl -X POST "http://localhost:9090/api/v1/sendEmail" -H "accept: application/json" -H "Content-Type: application/json" -d "{\"fromEmailId\":\"bramesh.srit@gmail.com\",\"toEmailId\":\"bramesh.srit@gmail.com\",\"password\":\"***********\",\"emailSubject\":\"Assignment\",\"emailContent\":\"Hello Team,\n\nPlease find the attached updated file and zipped code.\n\nThanks\"}"


### Note: 
We are required to generate App password using [https://support.google.com/accounts/answer/185833](https://support.google.com/accounts/answer/185833)
