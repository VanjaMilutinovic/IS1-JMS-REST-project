/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vanja
 */
public class ClientMain {
    //1
    public static void createCity(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter city name: ");
        String city = in.nextLine();
        try {

            URL url = new URL("http://localhost:8080/CentralServer/resources/actions/1/" + city);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            Scanner scanner = new Scanner(con.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            System.out.println(response);
            scanner.close();

        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //2
    public static void createUser(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter name and surname: ");
        String nameSurame = in.nextLine();
        System.out.println("Enter username: ");
        String username = in.nextLine();
        System.out.println("Enter password: ");
        String password = in.nextLine();
        System.out.println("Enter address: ");
        String address = in.nextLine();
        //consider that users are made with money = 0
        System.out.println("Enter city name: ");
        String cityName = in.nextLine();
        try {

            URL url = new URL("http://localhost:8080/CentralServer/resources/actions/2/"
                    + nameSurame + "/"
                    + username + "/"
                    + password + "/"
                    + address + "/"
                    + cityName);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            Scanner scanner = new Scanner(con.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            System.out.println(response);
            scanner.close();

        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //3
    public static void addMoney(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter money amount to add: ");
        String money= in.nextLine();
        System.out.println("Enter username: ");
        String usernameMoney= in.nextLine();
    try {

            URL url = new URL("http://localhost:8080/CentralServer/resources/actions/3/"+ money + "/" + usernameMoney);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            Scanner scanner = new Scanner(con.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            System.out.println(response);
            scanner.close();

        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //4
    public static void changeAddress(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter city name: ");
        String cityChange= in.nextLine();
        System.out.println("Enter address: ");
        String addressChange= in.nextLine();
        System.out.println("Enter username: ");
        String usernameAddress= in.nextLine();
    try {

        URL url = new URL("http://localhost:8080/CentralServer/resources/actions/4/"+ 
                cityChange + "/" + 
                addressChange + "/"+
                usernameAddress);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        int responseCode = con.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        Scanner scanner = new Scanner(con.getInputStream());
        String response = scanner.useDelimiter("\\A").next();
        System.out.println(response);
        scanner.close();

    } catch (IOException ex) {
        Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    //5
    public static void createCategory(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter category name: ");
        String category= in.nextLine();
        System.out.println("Enter parent category name or 'none': ");
        String parent= in.nextLine();
        try {

            URL url = new URL("http://localhost:8080/CentralServer/resources/actions/5/"+ category + "/" + parent);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            Scanner scanner = new Scanner(con.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            System.out.println(response);
            scanner.close();

        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //6
    public static void createProduct(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter product name: ");
        String product= in.nextLine();
        System.out.println("Enter description: ");
        String description= in.nextLine();
        System.out.println("Enter discount: ");
        String discount= in.nextLine();
        System.out.println("Enter price: ");
        String price= in.nextLine();
        System.out.println("Enter category name: ");
        String categoryName= in.nextLine();
        System.out.println("Enter seller name: ");
        String sellerName= in.nextLine();
        try {

            URL url = new URL("http://localhost:8080/CentralServer/resources/actions/6/"+ 
                    product + "/"+
                    description + "/"+
                    discount + "/"+
                    price + "/"+
                    categoryName + "/"+
                    sellerName + "/");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            Scanner scanner = new Scanner(con.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            System.out.println(response);
            scanner.close();

        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //7
    public static void changeItemPrice(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter price: ");
        String price= in.nextLine();
        System.out.println("Enter product name: ");
        String product= in.nextLine();
    try {

            URL url = new URL("http://localhost:8080/CentralServer/resources/actions/7/"+ price + "/" + product);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            Scanner scanner = new Scanner(con.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            System.out.println(response);
            scanner.close();

        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    //8
    public static void setItemDiscount(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter discount: ");
        String discount= in.nextLine();
        System.out.println("Enter product name: ");
        String product= in.nextLine();
        try {

                URL url = new URL("http://localhost:8080/CentralServer/resources/actions/8/"+ discount + "/" + product);

                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");

                int responseCode = con.getResponseCode();
                System.out.println("Response Code: " + responseCode);

                Scanner scanner = new Scanner(con.getInputStream());
                String response = scanner.useDelimiter("\\A").next();
                System.out.println(response);
                scanner.close();

            } catch (IOException ex) {
                Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
            }        
    }
    
    //9
    public static void addToCart(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter product to add: ");
        String product= in.nextLine();
        System.out.println("Enter username: ");
        String usernameMoney= in.nextLine();
        try {

            URL url = new URL("http://localhost:8080/CentralServer/resources/actions/9/"+ product + "/" + usernameMoney);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            Scanner scanner = new Scanner(con.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            System.out.println(response);
            scanner.close();

        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //10
    public static void removeFromCart(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter product to remove: ");
        String product= in.nextLine();
        System.out.println("Enter username: ");
        String usernameMoney= in.nextLine();
        try {

            URL url = new URL("http://localhost:8080/CentralServer/resources/actions/10/"+ product + "/" + usernameMoney);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            Scanner scanner = new Scanner(con.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            System.out.println(response);
            scanner.close();

        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //11
    public static void pay(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = in.nextLine();
        System.out.println("Enter address: ");
        String address = in.nextLine();
        System.out.println("Enter city: ");
        String city = in.nextLine();
        try {

            URL url = new URL("http://localhost:8080/CentralServer/resources/actions/11/" 
                    + username + "/"
                    + address + "/"
                    + city);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            Scanner scanner = new Scanner(con.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            System.out.println(response);
            scanner.close();

        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //12
    public static void getAllCities(){
        try {
            URL url = new URL("http://localhost:8080/CentralServer/resources/actions/12");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            Scanner scanner = new Scanner(con.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            System.out.println(response);
            scanner.close();

        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //13
    public static void getAllUsers(){
         try {
            URL url = new URL("http://localhost:8080/CentralServer/resources/actions/13");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            Scanner scanner = new Scanner(con.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            System.out.println(response);
            scanner.close();

        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    //14
    public static void getAllCategories(){
        try {
            URL url = new URL("http://localhost:8080/CentralServer/resources/actions/14");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            Scanner scanner = new Scanner(con.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            System.out.println(response);
            scanner.close();

        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    //15
    public static void getItemsSold(){
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter username: ");
            String username = in.nextLine();
            URL url = new URL("http://localhost:8080/CentralServer/resources/actions/15/" + username);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            Scanner scanner = new Scanner(con.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            System.out.println(response);
            scanner.close();

        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    //16
    public static void getCartContents(){
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter username: ");
            String username = in.nextLine();
            URL url = new URL("http://localhost:8080/CentralServer/resources/actions/16/" + username);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            Scanner scanner = new Scanner(con.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            System.out.println(response);
            scanner.close();

        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    //17
    public static void getAllOrdersForUser(){
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter username: ");
            String username = in.nextLine();
            URL url = new URL("http://localhost:8080/CentralServer/resources/actions/17/" + username);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            Scanner scanner = new Scanner(con.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            System.out.println(response);
            scanner.close();

        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    //18
    public static void getAllOrders(){
        try {
            URL url = new URL("http://localhost:8080/CentralServer/resources/actions/18");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            Scanner scanner = new Scanner(con.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            System.out.println(response);
            scanner.close();

        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    //19
    public static void getAllTransactions(){
        try {
            URL url = new URL("http://localhost:8080/CentralServer/resources/actions/19");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            Scanner scanner = new Scanner(con.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            System.out.println(response);
            scanner.close();

        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int choice = 0;

        while (choice != -1) {
            System.out.println("Make a choice:");
            System.out.println("1. Create city");
            System.out.println("2. Create user");
            System.out.println("3. Add money to a user");
            System.out.println("4. Change user's city and address");
            System.out.println("5. Create category");
            System.out.println("6. Create product");
            System.out.println("7. Change product price");
            System.out.println("8. Set product discount");
            System.out.println("9. Add product to cart");
            System.out.println("10. Remove product from cart");
            System.out.println("11. Pay (Create transaction, order and clear cart)");
            System.out.println("12. Find all cities");
            System.out.println("13. Find all users");
            System.out.println("14. Find all categories");
            System.out.println("15. Find products sold by the user who created the query");
            System.out.println("16. Find all products in the cart for the user who created the query");
            System.out.println("17. Find all orders for the user who created the query");
            System.out.println("18. Find all orders");
            System.out.println("19. Find all transactions");
            System.out.println("-1. Quit");

            choice = in.nextInt();

            switch (choice) {
                case 1:
                    createCity();
                    break;
                case 2: 
                    createUser();
                    break;
                case 3:
                    addMoney();
                    break;
                case 4:
                    changeAddress();
                    break;
                case 5:
                    createCategory();
                    break;
                case 6:
                    createProduct();
                    break;
                case 7:
                    changeItemPrice();
                    break;
                case 8:
                    setItemDiscount();
                    break;
                case 9:
                    addToCart();
                    break;
                case 10:
                    removeFromCart();
                    break;
                case 11:
                    pay();
                    break;
                case 12:
                    getAllCities();
                    break;
                case 13:
                    getAllUsers();
                    break;
                case 14:
                    getAllCategories();
                    break;
                case 15:
                    getItemsSold();
                    break;
                case 16:
                    getCartContents();
                    break;
                case 17:
                    getAllOrdersForUser();
                    break;
                case 18:
                    getAllOrders();
                    break;
                case 19:
                    getAllTransactions();
                    break;
                case -1:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
