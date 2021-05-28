import java.io.IOException;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import javax.jms.*;
import javax.naming.*;

import com.mongodb.*;
import com.mongodb.client.model.Filters.*;

import org.apache.activemq.broker.BrokerService;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.apache.activemq.*;

/** Handler servlet for ActiveMQ Artemis server connections from all clients, including ESP8266 modules.
 * @author Anna Irma Elizabeth Kennedy, el17ak@leeds.ac.uk
 * @version 1.0
 * */
@WebServlet("/message")
public class MessagingServlet implements MessageListener {

    private Session session;

    /**
     * Already set up to load on startup in 'web.xml', and to connect to the ActiveMQ Artemis
     * service as a client, consuming the data collection topic. For this purpose, the initialisation of the servlet
     * is delegated to a 'setup' function.
     */
    public MessagingServlet() {
        this.setupMessageConsumer();
    }

    /**
     * Establishes TCP connection with ActiveMQ Artemis server, creates session and "general" topic, and finally
     * creates a consumer object to handle incoming messages to the topic.
     */
    private void setupMessageConsumer() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = null;
        try {
            InitialContext context = new InitialContext();
            connection = connectionFactory.createConnection();
            connection.start();
            this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination generalTopic = this.session.createTopic("general");

            MessageConsumer consumer = this.session.createConsumer(generalTopic);
            consumer.setMessageListener(this);
        } catch (JMSException e) {
            //Handle the exception appropriately
        } catch (NamingException e) {

        }
    }

    /**
     * Listener method for all incoming messages to the ActiveMQ Artemis server.
     *
     * @param message the binary message being received by the messageConsumer
     */
    public void onMessage(javax.jms.Message message) {
        try {
            // JMS messages will be an instance of TextMessage.
            if (message instanceof TextMessage) {
                String textMessage = ((TextMessage) message).getText();
                String[] parameters = partitionMessage(textMessage);
                if (parameters == null) {
                    return;
                }
                Integer[] intParameters = transformMessage(parameters);
                int result = uploadRequest(intParameters[0], intParameters[1], intParameters[2], intParameters[3]);
            }
            // MQTT messages are an instance of BytesMessage and require additional parsing.
            else if (message instanceof BytesMessage) {
                BytesMessage bytesMsg = (BytesMessage) message;
                byte[] byteData = null;
                byteData = new byte[(int) bytesMsg.getBodyLength()];
                bytesMsg.readBytes(byteData);
                bytesMsg.reset();
                String messageText = new String(byteData);
                String[] parameters = partitionMessage(messageText);
                if (parameters == null) {
                    return;
                }
                Integer[] intParameters = transformMessage(parameters);
                int result = uploadRequest(intParameters[0], intParameters[1], intParameters[2], intParameters[3]);
            }

            //Send the response to the Destination specified by the JMSReplyTo field of the received message,
            //this is presumably a temporary queue created by the client
            //this.replyProducer.send(message.getJMSReplyTo(), response);
        } catch (JMSException e) {
            //Handle the exception appropriately
        }
    }

    /**
     * Method for automatic data uploads based on messages received on the ActiveMQ Artemis server.
     *
     * @param train_id    the unique identifier for the train in the composition database.
     * @param coach_id    the unique identifier for the carriage.
     * @param seat_id     the carriage-specific seat identifier.
     * @param seat_status the data obtained from sensors.
     */
    private int uploadRequest(int train_id, int coach_id, int seat_id, int seat_status) {
        try {
            MongoClient mc = new MongoClient("localhost", 27017);
            DB db = mc.getDB("collected_data");
            DBObject query = new QueryBuilder().start().put("seat_id").is(seat_id).get();
            DBCollection collection = db.getCollection(Integer.toString(coach_id));

            collection.remove(query);
            collection.insert(new BasicDBObject().append("train_id", train_id).append("coach_id",
                    coach_id).append("seat_id", seat_id).append("status",
                    seat_status).append("timestamp", new java.util.Date()));
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Verifies the length and nature of substrings sent to the ActiveMQ Artemis server.
     *
     * @param parameters the strings sent to the topic
     * @return true if message is legal, false otherwise
     */
    private Boolean isLegalMessage(String[] parameters) {
        try {
            if (parameters.length != 4) {
                return false;
            }
            for (String parameter : parameters) {
                Integer.parseInt(parameter);
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Splits sent messages to the ActiveMQ Artemis server according to commas.
     *
     * @param message the received message.
     * @return an array of parameters as strings.
     */
    private String[] partitionMessage(String message) {
        String[] parameters = message.split(",");
        if (!isLegalMessage(parameters)) {
            return null;
        }
        return parameters;
    }

    /**
     * Transforms a String array into an Integer array.
     *
     * @param parameters the array of String parameters.
     * @return an array of Integer parameters.
     */
    private Integer[] transformMessage(String[] parameters) {
        try {
            Integer[] integerParameters = new Integer[4];
            int i = 0;
            for (String parameter : parameters) {
                integerParameters[i] = Integer.parseInt(parameter);
                i++;
            }
            return integerParameters;
        } catch (Exception e) {
            return null;
        }
    }
}