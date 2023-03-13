/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.centralserver.resources;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Vanja
 */
@Path("actions")
public class DoTheDo {

    @Resource(lookup = "myConnectionFactory")
    ConnectionFactory cf;
    @Resource(lookup = "receiveSub1")
    Queue receive1;
    @Resource(lookup = "respondSub1")
    Queue respond1;
    @Resource(lookup = "receiveSub2")
    Queue receive2;
    @Resource(lookup = "respondSub2")
    Queue respond2;
    @Resource(lookup = "receiveSub3")
    Queue receive3;
    @Resource(lookup = "respondSub3")
    Queue respond3;

    @POST
    @Path("/1/{city}")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response action1(@PathParam("city") String city) {
        try {
            JMSContext context = cf.createContext();
            JMSConsumer consumer = context.createConsumer(respond1);
            JMSProducer jMSProducer = context.createProducer();

            TextMessage message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem1/resources/actions/1/" + city);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive1, message);

            Message m = consumer.receive(2000);
            if (m instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m;
                System.out.println("Action 1: " + textMessage.getText());
                return Response.ok(textMessage.getText()).build();
            }
        } catch (JMSException ex) {
            Logger.getLogger(DoTheDo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    @POST
    @Path("/2/{nameSurame}/{username}/{password}/{address}/{cityName}")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response action2(@PathParam("nameSurame") String nameSurame,
            @PathParam("username") String username,
            @PathParam("password") String password,
            @PathParam("address") String address,
            @PathParam("cityName") String cityName) {

        try {
            JMSContext context = cf.createContext();
            JMSConsumer consumer1 = context.createConsumer(respond1);
            JMSConsumer consumer2 = context.createConsumer(respond2);
            JMSConsumer consumer3 = context.createConsumer(respond3);
            JMSProducer jMSProducer = context.createProducer();

            TextMessage message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem1/resources/actions/2/"
                    + nameSurame + "/"
                    + username + "/"
                    + password + "/"
                    + address + "/"
                    + cityName);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive1, message);

            message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem2/resources/actions/2/"
                    + nameSurame + "/"
                    + username + "/"
                    + password + "/"
                    + address);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive2, message);

            message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem3/resources/actions/2/"
                    + nameSurame + "/"
                    + username + "/"
                    + password + "/"
                    + address);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive3, message);

            String returnMes = "";
            Message m1 = consumer1.receive(2000);
            if (m1 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m1;
                System.out.println("Action 1: " + textMessage.getText());
                returnMes += "Action 1: " + textMessage.getText() + "\n";
            }
            Message m2 = consumer2.receive(2000);
            if (m2 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m2;
                System.out.println("Action 2: " + textMessage.getText());
                returnMes += "Action 2: " + textMessage.getText() + "\n";
            }
            Message m3 = consumer3.receive(2000);
            if (m3 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m3;
                System.out.println("Action 3: " + textMessage.getText());
                returnMes += "Action 3: " + textMessage.getText() + "\n";
            }
            return Response.ok(returnMes).build();

        } catch (JMSException ex) {
            Logger.getLogger(DoTheDo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    @POST
    @Path("/3/{money}/{username}")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response action3(@PathParam("money") double money,
            @PathParam("username") String username) {

        try {
            JMSContext context = cf.createContext();
            JMSConsumer consumer1 = context.createConsumer(respond1);
            JMSConsumer consumer2 = context.createConsumer(respond2);
            JMSConsumer consumer3 = context.createConsumer(respond3);
            JMSProducer jMSProducer = context.createProducer();

            TextMessage message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem1/resources/actions/3/"
                    + money + "/"
                    + username);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive1, message);

            message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem2/resources/actions/3/"
                    + money + "/"
                    + username);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive2, message);

            message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem3/resources/actions/3/"
                    + money + "/"
                    + username);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive3, message);

            String returnMes = "";
            Message m1 = consumer1.receive(2000);
            if (m1 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m1;
                System.out.println("Action 1: " + textMessage.getText());
                returnMes += "Action 1: " + textMessage.getText() + "\n";
            }
            Message m2 = consumer2.receive(2000);
            if (m2 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m2;
                System.out.println("Action 2: " + textMessage.getText());
                returnMes += "Action 2: " + textMessage.getText() + "\n";
            }
            Message m3 = consumer3.receive(2000);
            if (m3 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m3;
                System.out.println("Action 3: " + textMessage.getText());
                returnMes += "Action 3: " + textMessage.getText() + "\n";
            }

            return Response.ok(returnMes).build();

        } catch (JMSException ex) {
            Logger.getLogger(DoTheDo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    @POST
    @Path("/4/{cityChange}/{addressChange}/{username}")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response action4(@PathParam("cityChange") String cityChange,
            @PathParam("addressChange") String addressChange,
            @PathParam("username") String username) {

        try {
            JMSContext context = cf.createContext();
            JMSConsumer consumer1 = context.createConsumer(respond1);
            JMSConsumer consumer2 = context.createConsumer(respond2);
            JMSConsumer consumer3 = context.createConsumer(respond3);
            JMSProducer jMSProducer = context.createProducer();

            TextMessage message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem1/resources/actions/4/"
                    + cityChange + "/"
                    + addressChange + "/"
                    + username);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive1, message);

            message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem2/resources/actions/4/"
                    + addressChange + "/"
                    + username);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive2, message);

            message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem3/resources/actions/4/"
                    + addressChange + "/"
                    + username);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive3, message);

            String returnMes = "";
            Message m1 = consumer1.receive(2000);
            if (m1 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m1;
                System.out.println("Action 1: " + textMessage.getText());
                returnMes += "Action 1: " + textMessage.getText() + "\n";
            }
            Message m2 = consumer2.receive(2000);
            if (m2 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m2;
                System.out.println("Action 2: " + textMessage.getText());
                returnMes += "Action 2: " + textMessage.getText() + "\n";
            }
            Message m3 = consumer3.receive(2000);
            if (m3 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m3;
                System.out.println("Action 3: " + textMessage.getText());
                returnMes += "Action 3: " + textMessage.getText() + "\n";
            }

            return Response.ok(returnMes).build();

        } catch (JMSException ex) {
            Logger.getLogger(DoTheDo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    @POST
    @Path("/5/{name}/{parentname}")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response action5(@PathParam("name") String name,
            @PathParam("parentname") String parentname) {
        try {
            JMSContext context = cf.createContext();
            JMSConsumer consumer = context.createConsumer(respond2);
            JMSProducer jMSProducer = context.createProducer();

            TextMessage message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem2/resources/actions/5/" + name + "/" + parentname);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive2, message);

            Message m = consumer.receive(2000);
            if (m instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m;
                System.out.println("Action 2.: " + textMessage.getText());
                return Response.ok(textMessage.getText()).build();
            }
        } catch (JMSException ex) {
            Logger.getLogger(DoTheDo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    @POST
    @Path("/6/{product}/{description}/{discount}/{price}/{categoryName}/{sellerName}")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response action6(@PathParam("product") String product,
            @PathParam("description") String description,
            @PathParam("discount") float discount,
            @PathParam("price") float price,
            @PathParam("categoryName") String categoryName,
            @PathParam("sellerName") String sellerName) {

        try {
            JMSContext context = cf.createContext();
            JMSConsumer consumer2 = context.createConsumer(respond2);
            JMSConsumer consumer3 = context.createConsumer(respond3);
            JMSProducer jMSProducer = context.createProducer();

            TextMessage message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem2/resources/actions/6/"+ 
                    product + "/"+
                    description + "/"+
                    discount + "/"+
                    price + "/"+
                    categoryName + "/"+
                    sellerName);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive2, message);

            message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem3/resources/actions/6/"+ 
                    product + "/"+
                    description + "/"+
                    discount + "/"+
                    price + "/"+
                    sellerName);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive3, message);

            String returnMes = "";
        
            Message m2 = consumer2.receive(2000);
            if (m2 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m2;
                System.out.println("Action 2: " + textMessage.getText());
                returnMes += "Action 2: " + textMessage.getText() + "\n";
            }
            Message m3 = consumer3.receive(2000);
            if (m3 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m3;
                System.out.println("Action 3: " + textMessage.getText());
                returnMes += "Action 3: " + textMessage.getText() + "\n";
            }
            return Response.ok(returnMes).build();

        } catch (JMSException ex) {
            Logger.getLogger(DoTheDo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }
    
    
    @POST
    @Path("/7/{price}/{product}")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response action7(@PathParam("price") float price,
                            @PathParam("product") String product) {

        try {
            JMSContext context = cf.createContext();
            JMSConsumer consumer2 = context.createConsumer(respond2);
            JMSConsumer consumer3 = context.createConsumer(respond3);
            JMSProducer jMSProducer = context.createProducer();

            TextMessage message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem2/resources/actions/7/"+ price + "/" + product);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive2, message);

            message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem3/resources/actions/7/"+ price + "/" + product);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive3, message);

            String returnMes = "";
        
            Message m2 = consumer2.receive(2000);
            if (m2 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m2;
                System.out.println("Action 2: " + textMessage.getText());
                returnMes += "Action 2: " + textMessage.getText() + "\n";
            }
            Message m3 = consumer3.receive(2000);
            if (m3 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m3;
                System.out.println("Action 3: " + textMessage.getText());
                returnMes += "Action 3: " + textMessage.getText() + "\n";
            }
            return Response.ok(returnMes).build();

        } catch (JMSException ex) {
            Logger.getLogger(DoTheDo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }
    
    @POST
    @Path("/8/{discount}/{product}")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response action8(@PathParam("discount") float discount,
                            @PathParam("product") String product){

        try {
            JMSContext context = cf.createContext();
            JMSConsumer consumer2 = context.createConsumer(respond2);
            JMSConsumer consumer3 = context.createConsumer(respond3);
            JMSProducer jMSProducer = context.createProducer();

            TextMessage message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem2/resources/actions/8/"+ discount + "/" + product);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive2, message);

            message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem3/resources/actions/8/"+ discount + "/" + product);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive3, message);

            String returnMes = "";
        
            Message m2 = consumer2.receive(2000);
            if (m2 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m2;
                System.out.println("Action 2: " + textMessage.getText());
                returnMes += "Action 2: " + textMessage.getText() + "\n";
            }
            Message m3 = consumer3.receive(2000);
            if (m3 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m3;
                System.out.println("Action 3: " + textMessage.getText());
                returnMes += "Action 3: " + textMessage.getText() + "\n";
            }
            return Response.ok(returnMes).build();

        } catch (JMSException ex) {
            Logger.getLogger(DoTheDo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }


    @POST
    @Path("/9/{product}/{username}")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response action9(@PathParam("product") String product,
                            @PathParam("username") String username){

        try {
            JMSContext context = cf.createContext();
            JMSConsumer consumer2 = context.createConsumer(respond2);
            JMSConsumer consumer3 = context.createConsumer(respond3);
            JMSProducer jMSProducer = context.createProducer();

            TextMessage message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem2/resources/actions/9/"+ product + "/" + username);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive2, message);

            message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem3/resources/actions/9/"+ product + "/" + username);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive3, message);

            String returnMes = "";
        
            Message m2 = consumer2.receive(2000);
            if (m2 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m2;
                System.out.println("Action 2: " + textMessage.getText());
                returnMes += "Action 2: " + textMessage.getText() + "\n";
            }
            Message m3 = consumer3.receive(2000);
            if (m3 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m3;
                System.out.println("Action 3: " + textMessage.getText());
                returnMes += "Action 3: " + textMessage.getText() + "\n";
            }
            return Response.ok(returnMes).build();

        } catch (JMSException ex) {
            Logger.getLogger(DoTheDo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }
    
    
    @POST
    @Path("/10/{product}/{username}")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response action10(@PathParam("product") String product,
                            @PathParam("username") String username){

        try {
            JMSContext context = cf.createContext();
            JMSConsumer consumer2 = context.createConsumer(respond2);
            JMSConsumer consumer3 = context.createConsumer(respond3);
            JMSProducer jMSProducer = context.createProducer();

            TextMessage message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem2/resources/actions/10/"+ product + "/" + username);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive2, message);

            message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem3/resources/actions/10/"+ product + "/" + username);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive3, message);

            String returnMes = "";
        
            Message m2 = consumer2.receive(2000);
            if (m2 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m2;
                System.out.println("Action 2: " + textMessage.getText());
                returnMes += "Action 2: " + textMessage.getText() + "\n";
            }
            Message m3 = consumer3.receive(2000);
            if (m3 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m3;
                System.out.println("Action 3: " + textMessage.getText());
                returnMes += "Action 3: " + textMessage.getText() + "\n";
            }
            return Response.ok(returnMes).build();

        } catch (JMSException ex) {
            Logger.getLogger(DoTheDo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    
    @POST
    @Path("/11/{username}/{address}/{city}")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response action11(@PathParam("username") String username,
            @PathParam("address") String address,
            @PathParam("city") String city) {

        try {
            JMSContext context = cf.createContext();
            JMSConsumer consumer2 = context.createConsumer(respond2);
            JMSConsumer consumer3 = context.createConsumer(respond3);
            JMSProducer jMSProducer = context.createProducer();

            TextMessage message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem2/resources/actions/11/"
                    + username + "/"
                    + address + "/"
                    + city);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive2, message);

            message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem3/resources/actions/11/"
                    + username + "/"
                    + address + "/"
                    + city);
            message.setStringProperty("method", "POST");
            jMSProducer.send(receive3, message);

            String returnMes = "";
        
            Message m2 = consumer2.receive(2000);
            if (m2 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m2;
                System.out.println("Action 2: " + textMessage.getText());
                returnMes += "Action 2: " + textMessage.getText() + "\n";
            }
            Message m3 = consumer3.receive(2000);
            if (m3 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m3;
                System.out.println("Action 3: " + textMessage.getText());
                returnMes += "Action 3: " + textMessage.getText() + "\n";
            }
            return Response.ok(returnMes).build();

        } catch (JMSException ex) {
            Logger.getLogger(DoTheDo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }
    
    
    @GET
    @Path("/12")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response action12() {
        try {
            JMSContext context = cf.createContext();
            JMSConsumer consumer = context.createConsumer(respond1);
            JMSProducer jMSProducer = context.createProducer();

            TextMessage message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem1/resources/actions/12");
            message.setStringProperty("method", "GET");
            jMSProducer.send(receive1, message);

            Message m = consumer.receive(2000);
            if (m instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m;
                System.out.println("Action 2: " + textMessage.getText());
                return Response.ok(textMessage.getText()).build();
            }
        } catch (JMSException ex) {
            Logger.getLogger(DoTheDo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }
    
    @GET
    @Path("/13")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response action13() {
        try {
            JMSContext context = cf.createContext();
            JMSConsumer consumer = context.createConsumer(respond1);
            JMSProducer jMSProducer = context.createProducer();

            TextMessage message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem1/resources/actions/13");
            message.setStringProperty("method", "GET");
            jMSProducer.send(receive1, message);

            Message m = consumer.receive(2000);
            if (m instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m;
                System.out.println("Action 2: " + textMessage.getText());
                return Response.ok(textMessage.getText()).build();
            }
        } catch (JMSException ex) {
            Logger.getLogger(DoTheDo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }


    @GET
    @Path("/14")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response action14() {
        try {
            JMSContext context = cf.createContext();
            JMSConsumer consumer = context.createConsumer(respond2);
            JMSProducer jMSProducer = context.createProducer();

            TextMessage message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem2/resources/actions/14");
            message.setStringProperty("method", "GET");
            jMSProducer.send(receive2, message);

            Message m = consumer.receive(2000);
            if (m instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m;
                System.out.println("Action 2: " + textMessage.getText());
                return Response.ok(textMessage.getText()).build();
            }
        } catch (JMSException ex) {
            Logger.getLogger(DoTheDo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }
    
    
    @GET
    @Path("/15/{username}")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response action15(@PathParam("username") String username) {
        try {
            JMSContext context = cf.createContext();
            JMSConsumer consumer = context.createConsumer(respond2);
            JMSProducer jMSProducer = context.createProducer();

            TextMessage message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem2/resources/actions/15/"+username);
            message.setStringProperty("method", "GET");
            jMSProducer.send(receive2, message);

            Message m = consumer.receive(2000);
            if (m instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m;
                System.out.println("Action 2: " + textMessage.getText());
                return Response.ok(textMessage.getText()).build();
            }
        } catch (JMSException ex) {
            Logger.getLogger(DoTheDo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    
    
    @GET
    @Path("/16/{username}")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response action16(@PathParam("username") String username) {
        try {
            JMSContext context = cf.createContext();
            JMSConsumer consumer = context.createConsumer(respond2);
            JMSProducer jMSProducer = context.createProducer();

            TextMessage message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem2/resources/actions/16/"+username);
            message.setStringProperty("method", "GET");
            jMSProducer.send(receive2, message);

            Message m = consumer.receive(2000);
            if (m instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m;
                System.out.println("Action 2: " + textMessage.getText());
                return Response.ok(textMessage.getText()).build();
            }
        } catch (JMSException ex) {
            Logger.getLogger(DoTheDo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }
    
    
    @GET
    @Path("/17/{username}")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response action17(@PathParam("username") String username) {
        try {
            JMSContext context = cf.createContext();
            JMSConsumer consumer = context.createConsumer(respond3);
            JMSProducer jMSProducer = context.createProducer();

            TextMessage message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem3/resources/actions/17/"+username);
            message.setStringProperty("method", "GET");
            jMSProducer.send(receive3, message);

            Message m = consumer.receive(2000);
            if (m instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m;
                System.out.println("Action 3: " + textMessage.getText());
                return Response.ok(textMessage.getText()).build();
            }
        } catch (JMSException ex) {
            Logger.getLogger(DoTheDo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    
    @GET
    @Path("/18")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response action18() {
        try {
            JMSContext context = cf.createContext();
            JMSConsumer consumer = context.createConsumer(respond3);
            JMSProducer jMSProducer = context.createProducer();

            TextMessage message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem3/resources/actions/18");
            message.setStringProperty("method", "GET");
            jMSProducer.send(receive3, message);

            Message m = consumer.receive(2000);
            if (m instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m;
                System.out.println("Action 3: " + textMessage.getText());
                return Response.ok(textMessage.getText()).build();
            }
        } catch (JMSException ex) {
            Logger.getLogger(DoTheDo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }
    
    
    @GET
    @Path("/19")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response action19() {
        try {
            JMSContext context = cf.createContext();
            JMSConsumer consumer = context.createConsumer(respond3);
            JMSProducer jMSProducer = context.createProducer();

            TextMessage message = context.createTextMessage();
            message.setText("http://localhost:8080/subsystem3/resources/actions/19");
            message.setStringProperty("method", "GET");
            jMSProducer.send(receive3, message);

            Message m = consumer.receive(2000);
            if (m instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) m;
                System.out.println("Action 3: " + textMessage.getText());
                return Response.ok(textMessage.getText()).build();
            }
        } catch (JMSException ex) {
            Logger.getLogger(DoTheDo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }
}
