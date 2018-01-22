package pl.edu.agh.ki.iosr.mockservices.control;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class ActiveMQClient implements ExceptionListener, ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        start();
    }

    private void start() {
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://iosr-active-mq:61616");

            Connection connection = connectionFactory.createConnection();
            connection.start();

            connection.setExceptionListener(this);

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("iosr");

            MessageConsumer consumer = session.createConsumer(destination);

            consumer.setMessageListener(new OffsetProvider());

        } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }

    @Override
    public void onException(JMSException e) {
        System.err.println(e.getErrorCode());
    }
}
