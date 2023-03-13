/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.subsystem3.resources;

import java.util.ArrayList;
import java.util.Date;
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
import sub3entities.Cart;
import sub3entities.Order1_1;
import sub3entities.Orderitem;
import sub3entities.OrderitemPK;
import sub3entities.Product;
import sub3entities.Productsincart;
import sub3entities.ProductsincartPK;
import sub3entities.Transaction;
import sub3entities.User;

/**
 *
 * @author Vanja
 */
@Path("actions")
@Stateless
@Transactional
public class Handler3 {

    @PersistenceContext
    EntityManager em;
    //11,17,18,19

    @POST  //create user
    @Path("/2/{nameSurame}/{username}/{password}/{address}")
    @Transactional
    public Response action2(@PathParam("nameSurame") String nameSurame,
            @PathParam("username") String username,
            @PathParam("password") String password,
            @PathParam("address") String address) {
        List<User> users = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username).getResultList();
        if (!users.isEmpty()) {
            return Response
                    .ok("user already exists :( ")
                    .build();
        }
        User u = new User();
        u.setAddress(address);
        u.setMoney(0);
        u.setName(nameSurame);
        u.setPassword(password);
        u.setUsername(username);
        em.persist(u);
        em.flush();
        Cart cart = new Cart();
        cart.setTotalPrice(0);
        cart.setUserId(u);
        em.persist(cart);
        em.flush();

        return Response.ok("user added successfully :) ").build();
    }

    @POST //add money
    @Path("/3/{money}/{username}")
    @Transactional
    public Response action3(@PathParam("money") float money,
            @PathParam("username") String username) {
        List<User> users = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username).getResultList();
        if (users.isEmpty()) {
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
    @Path("/4/{address}/{username}")
    @Transactional
    public Response action4(@PathParam("address") String address,
            @PathParam("username") String username) {
        List<User> users = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username).getResultList();
        if (users.isEmpty()) {
            return Response
                    .ok("user doesnt exist :( ")
                    .build();
        }
        User u = users.get(0);
        u.setAddress(address);
        em.persist(u);
        em.flush();
        return Response.ok("address successflly changed").build();
    }

    @POST //create Product
    @Path("/6/{product}/{description}/{discount}/{price}/{sellerName}")
    @Transactional
    public Response action6(@PathParam("product") String product,
            @PathParam("description") String description,
            @PathParam("discount") float discount,
            @PathParam("price") float price,
            @PathParam("sellerName") String sellerName) {
        List<Product> products = em.createNamedQuery("Product.findByName", Product.class).setParameter("name", product).getResultList();
        if (!products.isEmpty()) {
            return Response
                    .ok("product already exists :( ")
                    .build();
        }

        List<User> users = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", sellerName).getResultList();
        if (users.isEmpty()) {
            return Response
                    .ok("user doesnt exist :( ")
                    .build();
        }
        Product p = new Product();
        p.setDescription(description);
        p.setDiscount(discount);
        p.setName(product);
        p.setPrice(price);
        p.setSellerId(users.get(0));
        em.persist(p);
        em.flush();
        return Response.ok("product created successfully :) ").build();
    }

    @POST //changeItemPrice
    @Path("/7/{price}/{product}")
    @Transactional
    public Response action7(@PathParam("price") float price,
            @PathParam("product") String product) {

        List<Product> products = em.createNamedQuery("Product.findByName", Product.class).setParameter("name", product).getResultList();
        if (products.isEmpty()) {
            return Response
                    .ok("product doesnt exist :( ")
                    .build();
        }
        Product p = products.get(0);
        double old = p.getPrice();

        //update all carts with this product
        List<Productsincart> productincart = em.createNativeQuery("SELECT * FROM productsincart WHERE idProduct = " + products.get(0).getId()).getResultList();
        for (Productsincart obj : productincart) {
            Cart cart = obj.getCart();
            int quantity = obj.getQuantity();

            cart.setTotalPrice(cart.getTotalPrice() - quantity * old * (100 - p.getDiscount()) / 100);
            cart.setTotalPrice(cart.getTotalPrice() + quantity * price * (100 - p.getDiscount()) / 100);
            em.persist(cart);
            em.flush();
        }
        p.setPrice(price);
        em.persist(p);
        em.flush();
        return Response.ok("price changed successfully :) ").build();
    }

    @POST //setItemDiscount
    @Path("/8/{discount}/{product}")
    @Transactional
    public Response action8(@PathParam("discount") float discount,
            @PathParam("product") String product) {

        List<Product> products = em.createNamedQuery("Product.findByName", Product.class).setParameter("name", product).getResultList();
        if (products.isEmpty()) {
            return Response
                    .ok("product doesnt exist :( ")
                    .build();
        }
        Product p = products.get(0);
        double old = p.getPrice();

        //update all carts with this product
        List<Productsincart> productincart = em.createNamedQuery("Productsincart.findByIdProduct", Productsincart.class).setParameter("idProduct", p.getId()).getResultList();
        for (Productsincart obj : productincart) {
            Cart cart = obj.getCart();
            int quantity = obj.getQuantity();

            cart.setTotalPrice(cart.getTotalPrice() - quantity * old * (100 - p.getDiscount()) / 100);
            cart.setTotalPrice(cart.getTotalPrice() + quantity * old * (100 - discount) / 100);
            em.persist(cart);
            em.flush();
        }
        p.setDiscount(discount);
        em.persist(p);
        em.flush();
        return Response.ok("discount changed successfully :) ").build();
    }

    @POST //addToCart
    @Path("/9/{product}/{username}")
    @Transactional
    public Response action9(@PathParam("product") String product,
            @PathParam("username") String username) {

        List<Product> products = em.createNamedQuery("Product.findByName", Product.class).setParameter("name", product).getResultList();
        if (products.isEmpty()) {
            return Response
                    .ok("product doesnt exist :( ")
                    .build();
        }
        List<User> users = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username).getResultList();
        if (users.isEmpty()) {
            return Response
                    .ok("user doesnt exist :( ")
                    .build();
        }

        if (users.get(0).getCartList() == null) {
            return Response.ok("error: user doesnt have a cart :( ").build();
        }
        List<Cart> carts = em.createNamedQuery("Cart.findById", Cart.class).setParameter("id", users.get(0).getId()).getResultList();
        Cart cart = null;
        if (carts.isEmpty() == false) {
            cart = carts.get(0);
        }
        //dodaj PIC
        List<Productsincart> pics = em.createNamedQuery("Productsincart.findByIdCartAndIdProduct", Productsincart.class)
                .setParameter("idCart", cart.getId()).setParameter("idProduct", products.get(0).getId()).getResultList();
        if (pics.isEmpty()) {
            Productsincart pic = new Productsincart();
            pic.setCart(cart);
            pic.setProduct(products.get(0));
            pic.setProductsincartPK(new ProductsincartPK(products.get(0).getId(), cart.getId()));
            pic.setQuantity(1);
            em.persist(pic);
            em.flush();
        } else {
            pics.get(0).setQuantity(pics.get(0).getQuantity() + 1);

            em.persist(pics.get(0));
            em.flush();
        }
        cart.setTotalPrice(cart.getTotalPrice() + products.get(0).getPrice() * (100 - products.get(0).getDiscount()) / 100);
        em.persist(cart);
        em.flush();
        return Response.ok("product added to cart successfully :) ").build();
    }

    @POST //removeFromCart
    @Path("/10/{product}/{username}")
    @Transactional
    public Response action10(@PathParam("product") String product,
            @PathParam("username") String username) {

        List<Product> products = em.createNamedQuery("Product.findByName", Product.class).setParameter("name", product).getResultList();
        if (products.isEmpty()) {
            return Response
                    .ok("product doesnt exist :( ")
                    .build();
        }
        List<User> users = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username).getResultList();
        if (users.isEmpty()) {
            return Response
                    .ok("user doesnt exist :( ")
                    .build();
        }

        if (users.get(0).getCartList() == null) {
            return Response.ok("error: user doesnt have a cart :( ").build();
        }
        List<Cart> carts = em.createNamedQuery("Cart.findById", Cart.class).setParameter("id", users.get(0).getId()).getResultList();
        Cart cart = null;
        if (carts.isEmpty() == false) {
            cart = carts.get(0);
        }

        List<Productsincart> pics = em.createNamedQuery("Productsincart.findByIdCartAndIdProduct", Productsincart.class)
                .setParameter("idCart", cart.getId()).setParameter("idProduct", products.get(0).getId()).getResultList();
        if (pics.isEmpty()) {
            return Response.ok("error: this product isnt in cart :( ").build();
        } else {
            if (pics.get(0).getQuantity() != 0) {
                pics.get(0).setQuantity(pics.get(0).getQuantity() - 1);
                if (pics.get(0).getQuantity() == 0) {
                    em.remove(pics.get(0));
                }
                em.persist(pics.get(0));
                em.flush();

                cart.setTotalPrice(cart.getTotalPrice() - products.get(0).getPrice() * (100 - products.get(0).getDiscount()) / 100);
                em.persist(cart);
                em.flush();
                return Response.ok("product removed from cart successfully :) ").build();
            }
        }
        return Response.ok("this product isnt in cart :( ").build();
    }

    @POST  //pay
    @Path("/11/{username}/{address}/{city}")
    @Transactional
    public Response action11(@PathParam("username") String username,
                             @PathParam("address") String address,
                             @PathParam("city") String city) {

        List<User> users = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username).getResultList();
        if (users.isEmpty()) {
            return Response
                    .ok("user doesnt exist :( ")
                    .build();
        }
        User user = users.get(0);
        List<Cart> carts = em.createNamedQuery("Cart.findById", Cart.class).setParameter("id", user.getId()).getResultList();
        Cart cart = null;
        if (carts.isEmpty() == false) {
            cart = carts.get(0);
        }
        if (cart.getTotalPrice() == 0) {
            return Response.ok("Your cart is empty :( ").build();
        }

        double totalPrice = cart.getTotalPrice();
        if (user.getMoney() < totalPrice) {
            return Response.ok("You don't have enough money :( ").build();
        }

        // remove money
        user.setMoney(user.getMoney() - totalPrice);
        em.persist(user);
        em.flush();

        //remove from cart
        cart.setTotalPrice(0);
        em.persist(cart);
        em.flush();

        //make order
        Order1_1 order = new Order1_1();
        order.setAddress(address);
        order.setCity(city);
        order.setDateTime(new Date() + "");
        order.setTotalPrice(totalPrice);
        em.persist(order);
        em.flush();

        //make transaction
        Transaction trans = new Transaction();
        trans.setBuyer(user);
        trans.setAmount(totalPrice);
        trans.setOrderId(order);
        trans.setDateTime(new Date() + "");
        em.persist(trans);
        em.flush();

        //remove from cart and pay the seller
        
        List<Object[]> cartItems = em.createNativeQuery("SELECT * FROM Productsincart p WHERE p.idCart  =" + cart.getId() + " AND p.quantity > 0").getResultList();
        for (Object[] array : cartItems) {
            int quantity = (int) array[2];
            int idProduct = (int) array[0];
            List<Product> item = em.createNamedQuery("Product.findById", Product.class).setParameter("id", idProduct).getResultList();
            double price = item.get(0).getPrice();
            double discount = item.get(0).getDiscount();
            User seller = item.get(0).getSellerId();
            seller.setMoney(seller.getMoney()+ (price * (1 - discount / 100)) * quantity);
            em.persist(seller);
            em.flush();
            em.createNativeQuery("UPDATE Productsincart SET quantity=0 WHERE idProduct=" + idProduct + " and idCart=" + cart.getId()).executeUpdate();

            Orderitem oi = new Orderitem();
            oi.setPriceForOne(price * (1 - discount / 100));
            oi.setOrder1(order);
            oi.setOrderitemPK(new OrderitemPK(order.getId(), idProduct));
            oi.setProduct(item.get(0));
            oi.setQuantity(quantity);
            em.persist(oi);
            em.flush();
        }

        return Response.ok("paid successfully :) ").build();
    }

    
    @GET //getAllOrdersForUser
    @Path("/17/{username}")
    @Transactional
    public Response action17(@PathParam("username") String username) {
        List<User> users = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username).getResultList();
        if (users.isEmpty()) {
            return Response
                    .ok("user doesnt exist :( ")
                    .build();
        }

        User user = users.get(0);
        List<Transaction> transactionList = user.getTransactionList();
        if (transactionList.isEmpty()) {
            return Response.ok("You haven't made any orders :(").build();
        }
        
        List<Order1_1> resultList = 
           em.createNativeQuery("SELECT o.* FROM Transaction t, Order1 o WHERE t.order_id=o.id AND t.buyer = " + user.getId()).getResultList();
      
        return Response.status(200).entity(resultList).build();
    }

    @GET //getAllOrders
    @Path("/18")
    @Transactional
    public Response action18() {
        List<Object[]> allorders = em.createNativeQuery("SELECT * FROM `order1`").getResultList();
        if (allorders.isEmpty()) {
            return Response.ok("there are no orders :( ").build();
        }
        return Response.status(200).entity(allorders).build();
    }

    @GET //getAllTransactions
    @Path("/19")
    @Transactional
    public Response action19(@PathParam("username") String username) {
        List<Object[]> allTransactions = em.createNativeQuery("Select * from Transaction").getResultList();
        if (allTransactions.isEmpty()) {
            return Response.ok("there are no transactions :( ").build();
        }
        return Response.status(200).entity(allTransactions).build();
    }
}
