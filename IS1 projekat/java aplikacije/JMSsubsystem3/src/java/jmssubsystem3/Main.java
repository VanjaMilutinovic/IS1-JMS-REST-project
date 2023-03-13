/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jmssubsystem3;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.TextMessage;

/**
 *
 * @author Vanja
 */
public class Main {

    @Resource(lookup = "myConnectionFactory")
    private static ConnectionFactory cf;
    @Resource(lookup = "receiveSub3")
    private static Queue receive;
    @Resource(lookup = "respondSub3")
    private static Queue respond;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JMSContext context = cf.createContext();
        JMSConsumer consumer = context.createConsumer(receive);
        JMSProducer jMSProducer = context.createProducer();

        while (true) {
            Message m = consumer.receive();
            if (m instanceof TextMessage) {
                try {
                    TextMessage textMessage = (TextMessage) m;
                    URL url = new URL(textMessage.getText());

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod(textMessage.getStringProperty("method"));

                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    try ( Scanner scanner = new Scanner(con.getInputStream())) {
                        String response = scanner.useDelimiter("\\A").next();
                        TextMessage message = context.createTextMessage();
                        message.setText(response);
                        jMSProducer.send(respond, message);
                    }
                } catch (JMSException | IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
