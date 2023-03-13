/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.subsystem2.resources;

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
import sub2entities.Cart;
import sub2entities.Category;
import sub2entities.Product;
import sub2entities.Productincart;
import sub2entities.ProductincartPK;
import sub2entities.User;

/**
 *
 * @author Vanja
 */
@Path("actions")
@Stateless
@Transactional
public class Handler2 {

    @PersistenceContext
    EntityManager em;

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
        u.setNameSurname(nameSurame);
        u.setPassword(password);
        u.setUsername(username);
        em.persist(u);
        em.flush();
        Cart cart = new Cart();
        cart.setTotalCost(0);
        cart.setIdUser(u);
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

    @POST //create category
    @Path("/5/{name}/{parentname}")
    @Transactional
    public Response action5(@PathParam("name") String name,
            @PathParam("parentname") String parentname) {
        List<Category> categories = em.createNamedQuery("Category.findByName", Category.class).setParameter("name", name).getResultList();
        if (!categories.isEmpty()) {
            return Response
                    .ok("category already exists :( ")
                    .build();
        }
        List<Category> Parcategories = null;
        if (!parentname.equals("none")) {
            Parcategories = em.createNamedQuery("Category.findByName", Category.class).setParameter("name", parentname).getResultList();
            if (Parcategories.isEmpty()) {
                return Response
                        .ok("parent category doesnt exist :( ")
                        .build();
            }
        }

        Category c = new Category();
        c.setName(name);
        if (!parentname.equals("none") && !parentname.equals(name)) {
            c.setParentCategory(Parcategories.get(0));
        }
        em.persist(c);
        em.flush();
        return Response.ok("category created successfully :) ").build();

    }

    @POST //create Product
    @Path("/6/{product}/{description}/{discount}/{price}/{categoryName}/{sellerName}")
    @Transactional
    public Response action6(@PathParam("product") String product,
            @PathParam("description") String description,
            @PathParam("discount") float discount,
            @PathParam("price") float price,
            @PathParam("categoryName") String categoryName,
            @PathParam("sellerName") String sellerName) {

        List<Product> products = em.createNamedQuery("Product.findByName", Product.class).setParameter("name", product).getResultList();
        if (!products.isEmpty()) {
            return Response
                    .ok("product already exists :( ")
                    .build();
        }
        List<Category> categories = em.createNamedQuery("Category.findByName", Category.class).setParameter("name", categoryName).getResultList();
        if (categories.isEmpty()) {
            return Response
                    .ok("category doesnt exist :( ")
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
        p.setSeller(users.get(0));
        p.setIdCategory(categories.get(0));
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
        float old = p.getPrice();

        //update all carts with this product
        List<Productincart> productincart = em.createNativeQuery("SELECT * FROM productincart WHERE idProduct = " + products.get(0).getIdProduct()).getResultList();
        for (Productincart obj : productincart) {
            Cart cart = obj.getCart();
            int quantity = obj.getQuantity();

            cart.setTotalCost(cart.getTotalCost() - quantity * old * (100 - p.getDiscount()) / 100);
            cart.setTotalCost(cart.getTotalCost() + quantity * price * (100 - p.getDiscount()) / 100);
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
        float old = p.getPrice();

        //update all carts with this product
        List<Productincart> productincart = em.createNativeQuery("SELECT * FROM productincart WHERE idProduct = " + products.get(0).getIdProduct()).getResultList();
        for (Productincart obj : productincart) {
            Cart cart = obj.getCart();
            int quantity = obj.getQuantity();

            cart.setTotalCost(cart.getTotalCost() - quantity * old * (100 - p.getDiscount()) / 100);
            cart.setTotalCost(cart.getTotalCost() + quantity * old * (100 - discount) / 100);
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
        Cart cart = em.createNamedQuery("Cart.findByIdCart", Cart.class).setParameter("idCart", users.get(0).getIdUser()).getResultList().get(0);
        //dodaj PIC
        List<Productincart> pics = em.createNamedQuery("Productincart.findByIdCartAndIdProduct", Productincart.class)
                .setParameter("idCart", cart.getIdCart()).setParameter("idProduct", products.get(0).getIdProduct()).getResultList();
        if (pics.isEmpty()) {
            Productincart pic = new Productincart();
            pic.setCart(cart);
            pic.setProduct(products.get(0));
            pic.setProductincartPK(new ProductincartPK(cart.getIdCart(), products.get(0).getIdProduct()));
            pic.setQuantity(1);
            em.persist(pic);
            em.flush();
        } else {
            pics.get(0).setQuantity(pics.get(0).getQuantity() + 1);

            em.persist(pics.get(0));
            em.flush();
        }
        cart.setTotalCost(cart.getTotalCost() + products.get(0).getPrice() * (100 - products.get(0).getDiscount()) / 100);
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
        Cart cart = em.createNamedQuery("Cart.findByIdCart", Cart.class).setParameter("idCart", users.get(0).getIdUser()).getResultList().get(0);

        List<Productincart> pics = em.createNamedQuery("Productincart.findByIdCartAndIdProduct", Productincart.class)
                .setParameter("idCart", cart.getIdCart()).setParameter("idProduct", products.get(0).getIdProduct()).getResultList();
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

                cart.setTotalCost(cart.getTotalCost() - products.get(0).getPrice() * (100 - products.get(0).getDiscount()) / 100);
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
        List<Cart> carts = em.createNamedQuery("Cart.findByIdCart", Cart.class).setParameter("idCart", user.getIdUser()).getResultList();
        Cart cart = null;
        if (carts.isEmpty() == false) {
            cart = carts.get(0);
        }
        else
            return Response.ok("user doesn't have a cart :(").build();
        if (cart.getTotalCost()== 0) {
            return Response.ok("Your cart is empty :( ").build();
        }

        double totalPrice = cart.getTotalCost();
        if (user.getMoney() < totalPrice) {
            return Response.ok("You don't have enough money :( ").build();
        }

        // remove money
        user.setMoney((float) (user.getMoney() - totalPrice));
        em.persist(user);
        em.flush();

        //remove from cart
        cart.setTotalCost(0);
        em.persist(cart);
        em.flush();


        //remove from cart and pay the seller 
        List<Object[]> cartItems = em.createNativeQuery("SELECT * FROM Productincart p WHERE p.idCart  =" + cart.getIdCart()+ " AND p.quantity > 0").getResultList();
        for (Object[] array : cartItems) {
            int quantity = (int) array[2];
            int idProduct = (int) array[1];
            List<Product> item = em.createNamedQuery("Product.findByIdProduct", Product.class).setParameter("idProduct", idProduct).getResultList();
            if (item.isEmpty())
                continue;
            double price = item.get(0).getPrice();
            double discount = item.get(0).getDiscount();
            User seller = item.get(0).getSeller();
            seller.setMoney((float) (seller.getMoney()+ (price * (1 - discount / 100)) * quantity));
            em.persist(seller);
            em.flush();
            em.createNativeQuery("UPDATE Productincart SET quantity=0 WHERE idProduct=" + idProduct + " and idCart=" + cart.getIdCart()).executeUpdate();

        }

        return Response.ok("paid successfully :) ").build();
    }
    
    @GET //getAllCategories
    @Path("/14")
    @Transactional
    public Response action14() {
        List<Object[]> allcities = em.createNativeQuery("Select * from Category").getResultList();
        if (allcities.isEmpty()) {
            return Response.ok("there are no categories :( ").build();
        }
        return Response.status(200).entity(allcities).build();

    }

    @GET //getItemsSold
    @Path("/15/{username}")
    @Transactional
    public Response action15(@PathParam("username") String username) {
        List<User> users = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username).getResultList();
        if (users.isEmpty()) {
            return Response
                    .ok("user doesnt exist :( ")
                    .build();
        }
        int idUser = (int) em.createNativeQuery("Select idUser from User u where u.username = '" + username + "'")
                .setParameter("username", username).getResultList().get(0);
        List<Object[]> allcities
                = em.createNativeQuery("Select * from Product p where p.seller= " + idUser)
                        .setParameter("idUser", idUser).getResultList();
        if (allcities.isEmpty()) {
            return Response.ok("there are no products :( ").build();
        }
        return Response.status(200).entity(allcities).build();

    }

    @GET //getCartContents
    @Path("/16/{username}")
    @Transactional
    public Response action16(@PathParam("username") String username) {
        List<User> users = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username).getResultList();
        if (users.isEmpty()) {
            return Response
                    .ok("user doesnt exist :( ")
                    .build();
        }
        if (users.get(0).getCartList() == null) {
            return Response.ok("user doesnt have a cart :( ").build();
        }
        Cart cart = users.get(0).getCartList().get(0);
        int cartId = cart.getIdCart();

        List<Object[]> contents = em.createNativeQuery("SELECT * FROM productincart WHERE quantity>0 AND idCart = " + cartId).getResultList();
        if (contents.isEmpty())
            return Response.ok("there are no products in your cart :(").build();
        
        return Response.status(200).entity(contents).build();
    }
}
