package pl.edu.agh.ki.iosr.mockservices.control;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.concurrent.atomic.AtomicInteger;

public class OffsetProvider implements MessageListener {

    private static AtomicInteger offset = new AtomicInteger(0);

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;

        try {
            offset.set(Integer.parseInt(textMessage.getText()));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static int getOffset() {
        return offset.get();
    }
}
