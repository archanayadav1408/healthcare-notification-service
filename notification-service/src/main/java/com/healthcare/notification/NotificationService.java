package com.healthcare.notification;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @KafkaListener(
            topics = "appointment-events",
            groupId = "notification-group"
    )
    public void handleAppointmentEvent(AppointmentBookedEvent event) {
        System.out.println("===========================================");
        System.out.println(">>> NOTIFICATION SERVICE - Event received!");
        System.out.println(">>> Event type   : " + event.getEventType());
        System.out.println(">>> Appointment  : " + event.getAppointmentId());
        System.out.println(">>> Patient ID   : " + event.getPatientId());
        System.out.println(">>> Date & Time  : " + event.getAppointmentDate()
                + " at " + event.getAppointmentTime());

        if ("BOOKED".equals(event.getEventType())) {
            System.out.println(">>> ACTION: Sending booking confirmation to patient "
                    + event.getPatientId());
        } else if ("CANCELLED".equals(event.getEventType())) {
            System.out.println(">>> ACTION: Sending cancellation notice to patient "
                    + event.getPatientId());
        }
        System.out.println("===========================================");
    }
}