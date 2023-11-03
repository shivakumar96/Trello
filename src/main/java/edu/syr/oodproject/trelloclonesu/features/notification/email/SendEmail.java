package edu.syr.oodproject.trelloclonesu.features.notification.email;

import edu.syr.oodproject.trelloclonesu.common.logs.ApplicationLogger;
import edu.syr.oodproject.trelloclonesu.features.notification.notifiactionAPI.NotificationAPI;


public class SendEmail implements NotificationAPI {
    private String message;
    private String email;

    public SendEmail(String email, String message){
        this.email = email;
        this.message = message;
    }
    @Override
    public void sendNotification() {
        ApplicationLogger logger = ApplicationLogger.getApplicationLogger();
        logger.getLogger().info("Sent Notification to "+this.email+ " : "+message);
    }
}
