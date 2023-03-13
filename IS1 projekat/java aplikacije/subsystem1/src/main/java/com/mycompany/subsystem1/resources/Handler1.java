 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.subsystem1.resources;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import sub1entities.City;
import sub1entities.User;

/**
 *
 * @author Vanja
 */
@Path("actions")
@Stateless
@Transactional
public class Handler1 {
    @PersistenceContext
    EntityManager em;
    
    @POST //create city
    @Path("/1/{name}")
    @Transactional
    public Response action1(@PathParam("name") String name){ 
        List<City> cities = em.createNamedQuery("City.findByName",City.class).setParameter("name", name).getResultList();
        if(!cities.isEmpty()){
            return Response
                .ok("city already exists :( ")
                .build(); 
        }  
        City c = new City();
        c.setName(name);
        em.persist(c);
        em.flush();
        return Response.ok("city added successfully :) ").build();
    }
    
    
    @POST  //create user
    @Path("/2/{nameSurame}/{username}/{password}/{address}/{cityName}")
    @Transactional
    public Response action2(@PathParam("nameSurame") String nameSurame,
                            @PathParam("username") String username,
                            @PathParam("password") String password,
                            @PathParam("address") String address,
                            @PathParam("cityName") String cityName){
        List<User> users = em.createNamedQuery("User.findByUsername",User.class).setParameter("username", username).getResultList();
        if(!users.isEmpty()){
            return Response
                .ok("user already exists :( ")
                .build(); 
        }
        List<City> cities = em.createNamedQuery("City.findByName",City.class).setParameter("name", cityName).getResultList();
        if(cities.isEmpty()){
            return Response
                .ok("city doesnt exist :( ")
                .build(); 
        } 
        User u = new User();
        u.setAddress(address);
        u.setCity(cities.get(0));
        u.setMoney(0);
        u.setNameSurname(nameSurame);
        u.setPassword(password);
        u.setUsername(username);
        em.persist(u);
        em.flush();
        return Response.ok("user added successfully :) ").build(); 
    }

    
    @POST //add money
    @Path("/3/{money}/{username}")
    @Transactional
    public Response action3(@PathParam("money") float  money,
                            @PathParam("username") String username){ 
        List<User> users = em.createNamedQuery("User.findByUsername",User.class).setParameter("username", username).getResultList();
        if(users.isEmpty()){
            return Response
                .ok("user doesnt exist :( ")
                .build(); 
        } 
        User u = users.get(0);
        u.setMoney(u.getMoney() + money);
        em.persist(u);
        em.flush();
        return Response.ok("added money successfully :) ").build();
    }

    
    @POST //change address
    @Path("/4/{city}/{address}/{username}")
    @Transactional
    public Response action4(@PathParam("city") String city,
                            @PathParam("address") String address,
                            @PathParam("username") String username){
        List<User> users = em.createNamedQuery("User.findByUsername",User.class).setParameter("username", username).getResultList();
        if(users.isEmpty()){
            return Response
                .ok("user doesnt exist :( ")
                .build(); 
        } 
        List<City> cities = em.createNamedQuery("City.findByName",City.class).setParameter("name", city).getResultList();
        if(cities.isEmpty()){
            return Response
                .ok("city doesnt exist :( ")
                .build(); 
        } 
        User u = users.get(0);
        u.setCity(cities.get(0));
        u.setAddress(address);
        em.persist(u);
        em.flush();
        return Response.ok("address and city successflly changed").build();
    }
    

    
    @GET
    @Path("/12")
    @Transactional
    public Response action12(){ //get all cities
        List<Object[]> allcities = em.createNativeQuery("Select * from City").getResultList();
        if(allcities.isEmpty()){
            return Response.ok("there are no cities :( ").build(); 
        } 
        return Response.status(200).entity(allcities).build();
                
    }
    
    @GET
    @Path("/13")
    @Transactional
    public Response action13(){ //get all users
        List<Object[]> allusers = em.createNativeQuery("Select * from User").getResultList();
        if(allusers.isEmpty()){
            return Response.ok("there are no users :( ").build(); 
        } 
        return Response.status(200).entity(allusers).build();
                
    }
}