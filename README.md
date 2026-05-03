# Healthcare Notification Service

Microservice responsible for consuming appointment events from Apache Kafka
and sending confirmation notifications to patients.

Part of the Healthcare Appointment System built for SEZG583 - Scalable Services assignment.

## Tech Stack
- Java 17
- Spring Boot 3.5.13
- Spring for Apache Kafka
- Maven

## Port
Runs on **port 8083**

## Responsibilities
- Listens to `appointment-events` Kafka topic
- Sends booking confirmation when event type = `BOOKED`
- Sends cancellation notice when event type = `CANCELLED`

## Kafka Consumer Configuration

| Property | Value |
|----------|-------|
| Topic | `appointment-events` |
| Group ID | `notification-group` |
| Auto offset reset | `earliest` |

## How it works
```
Appointment Service
      │
      │ publishes event to Kafka
      ▼
 [appointment-events topic]
      │
      │ @KafkaListener consumes event
      ▼
Notification Service
      │
      │ logs confirmation / cancellation
      ▼
  Patient notified ✓
```

## Sample Console Output
```
===========================================
>>> NOTIFICATION SERVICE - Event received!
>>> Event type   : BOOKED
>>> Appointment  : 1
>>> Patient ID   : 1
>>> Date & Time  : 2026-04-15 at 10:30
>>> ACTION: Sending booking confirmation to patient 1
===========================================
```

## Running Locally

### Prerequisites
- Java 17
- Maven 3.9+
- Apache Kafka running on port 9092

### Start Kafka
```bash
docker run -d --name kafka -p 9092:9092 apache/kafka:3.7.0
```

### Run the service
```bash
mvn clean package -DskipTests
java -jar target/notification-service-0.0.1-SNAPSHOT.jar
```

## Running via Docker
```bash
docker build -t notification-service:1.0 .
docker run -p 8083:8083 notification-service:1.0
```

## Project Structure
```
src/main/java/com/healthcare/notification/
├── AppointmentBookedEvent.java       # Kafka message payload (deserialization)
├── NotificationService.java          # @KafkaListener - event handler
├── KafkaConsumerConfig.java          # Kafka consumer configuration
└── NotificationServiceApplication.java
```

## Group Members
| Name | ID |
|------|----|
| Member 1 | ID |
| Member 2 | ID |
| Member 3 | ID |
| Member 4 | ID |
