package com.example.user_activity_tracking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserActivityTrackingService {

    private static final Logger logger = LoggerFactory.getLogger(UserActivityTrackingService.class);

    public void logActivity(String username, String action, String description) {
        // Create a log message with the user's activity
        String message = String.format("User: %s performed action: %s. Description: %s", username, action, description);

        // Log the message
        logger.info(message);
    }
}
