package gdp.client;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client implements MessageListener {
    private static int ackMode;
    private static String clientQueueName;
    private final Collection<Runnable> clients = new ArrayList<Runnable>();

    private boolean transacted = false;
    private MessageProducer producer;

    static {
        ackMode = Session.AUTO_ACKNOWLEDGE;
    }

    public Client() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://77.68.95.246:61616");
        Connection connection;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(transacted, ackMode);
            Destination adminQueue = session.createTopic("general");

            //Setup a message producer to send message to the queue the server is consuming from
            this.producer = session.createProducer(adminQueue);
            this.producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            //Now create the actual message you want to send
            Timestamp ts3 = new Timestamp(System.currentTimeMillis());
            System.out.println("ts3" + ts3);
            TextMessage txtMessage = session.createTextMessage();
            txtMessage.setText("17,15,13,1");

            String correlationId = this.createRandomString();
            txtMessage.setJMSCorrelationID(correlationId);
            this.producer.send(txtMessage);
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void add(Runnable r) {
        clients.add(r);
    }

    private String createRandomString() {
        Random random = new Random(System.currentTimeMillis());
        long randomLong = random.nextLong();
        return Long.toHexString(randomLong);
    }

    public void onMessage(Message message) {
        String messageText = null;
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                messageText = textMessage.getText();
                System.out.println("messageText = " + messageText);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Client c = new Client();
    }
}
