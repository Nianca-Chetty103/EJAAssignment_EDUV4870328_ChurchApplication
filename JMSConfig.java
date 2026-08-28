package com.mycompany.ejaassignment.jms;

import javax.jms.JMSConnectionFactoryDefinition;
import javax.jms.JMSDestinationDefinition;

/**
 * Question 4: JMS configuration for the church platform.
 */
@JMSConnectionFactoryDefinition(
        name = JMSConfig.CONNECTION_FACTORY,
        description = "Connection factory for the City Church notification system",
        interfaceName = "javax.jms.ConnectionFactory",
        clientId = "cityChurchClient"
)
@JMSDestinationDefinition(
        name = JMSConfig.NOTIFICATION_TOPIC,
        description = "Topic where church notifications are published",
        interfaceName = "javax.jms.Topic",
        destinationName = "cityChurchNotificationTopic"
)
public class JMSConfig {

    public static final String CONNECTION_FACTORY = "java:app/jms/NotificationConnectionFactory";
    public static final String NOTIFICATION_TOPIC = "java:app/jms/NotificationTopic";

    private JMSConfig() {
    }
}
