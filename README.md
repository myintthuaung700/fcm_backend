# FCM Backend Testing

This project is a Spring Boot application designed for testing Firebase Cloud Messaging (FCM). It provides REST endpoints to manage and send FCM notifications.

## Prerequisites

- Java 11 or higher
- Maven 3.6.x or higher
- A Firebase project with Cloud Messaging enabled
- Firebase Admin SDK service account key file (`firebase-service-account.json`)

## Setup

1. **Clone the Repository**

   ```bash
   git clone https://github.com/myintthuaung700/fcm_backend.git
   cd fcm_backend
    ```

2. **Configure Firebase**

Place your firebase-service-account.json file in the src/main/resources directory. This file is necessary for authenticating with Firebase services.

3. **Build the Project**

Use Maven to build the project:
```bash
    mvn clean install
```

4. **Run the Application**
```bash
    mvn spring-boot:run
```

## Usage
API Endpoints
Send Notification
Send a notification to a specific device or topic.

Endpoint: POST /notifications/send
Request Body:
```json
{
  "title": "Notification Title",
  "body": "Notification Body",
  "topic": "news_updates",
  "token": "device_token_here"
}
```
