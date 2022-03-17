package com.example.as;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.widget.Toast;

import com.example.as.database.Database;
import com.example.as.model.CartItem;
import com.example.as.model.ERole;
import com.example.as.model.Order;
import com.example.as.model.OrderItem;
import com.example.as.model.Product;
import com.example.as.model.Users;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static Database database;

    public static Database getDd(Context context) {
        if (database != null) {
            return database;
        }
        database = new Database(context, "GhiChu.sqlite", null, 3);
        database.QueryData("Create table if not exists users(id Integer Primary Key Autoincrement," +
                "username nvarchar(200) unique ,password nvarchar(200),role varchar(20))");
        database.QueryData("Create table if not exists products(id Integer Primary Key Autoincrement," +
                "name nvarchar(200), description nvarchar(200), price DOUBLE, quantity Integer)");
        database.QueryData("Create table if not exists carts(id Integer Primary Key Autoincrement," +
                "quantity Integer, productId Integer, userId Integer, " +
                "FOREIGN KEY(productId) REFERENCES products(id), " +
                "FOREIGN KEY(userId) REFERENCES users(id))");
        database.QueryData("Create table if not exists orders(id TEXT Primary Key," +
                "total DOUBLE, userId Integer, orderDate Text, " +
                "FOREIGN KEY(userId) REFERENCES users(id))");
        database.QueryData("Create table if not exists orderDetails(id Integer Primary Key Autoincrement," +
                "quantity Integer, productId Integer, orderId TEXT, price DOUBLE, " +
                "FOREIGN KEY(productId) REFERENCES products(id), " +
                "FOREIGN KEY(orderId) REFERENCES orders(id))");
        if(loadProduct() == null){
            insertNewProduct(new Product(1, "Nike Air One", 100, 100, "Nike Air in Usa"));
            insertNewProduct(new Product(1, "Nike Air Two", 100, 100, "Nike Air in Usa"));
            insertNewProduct(new Product(1, "Jordan Air One", 100, 100, "Nike Air in Usa"));
            insertNewProduct(new Product(1, "Kobe Air One", 100, 100, "Nike Air in Usa"));
            insertNewProduct(new Product(1, "Adidas Air One", 100, 100, "Nike Air in Usa"));
        }
        return database;
    }
    public static void insertNewProduct(Product product){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",product.getName());
        contentValues.put("price", product.getPrice());
        contentValues.put("description", product.getDescription());
        contentValues.put("quantity", product.getQuantity());
        database.getWritableDatabase().insert("products", "id",contentValues);
    }

    public static void updateProduct(int id, Product product) {
        database.QueryData("UPDATE products " +
                "SET name = '" + product.getName() + "', " +
                "description = '" + product.getDescription() + "', " +
                "price = '" + product.getPrice() + "', " +
                "quantity = '" + product.getQuantity() + "' " +
                "WHERE id = " + id);
    }

    public static Product findProductById(int id) {
        Product product = null;
        Cursor cursor = database.GetData("select * from products where id = " + id);
        if (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            double price = cursor.getDouble(3);
            int quantity = cursor.getInt(4);
            product = new Product(cursor.getInt(0), name, price, quantity, description);
        }
        return product;
    }


    public static List<CartItem> getCard(int userId) {
        List<CartItem> items = null;
        Cursor cursor = database.GetData("select carts.id, carts.productId, carts.quantity from carts join users on carts.userId = users.id where carts.userId = " + userId);
        while (cursor.moveToNext()) {
            int itemId = cursor.getInt(0);
            int productId = cursor.getInt(1);
            int quantity = cursor.getInt(2);
            Product product = findProductById(productId);
            CartItem item = new CartItem(itemId, product, quantity);
            if (items == null) {
                items = new ArrayList<>();
            }
            items.add(item);
        }
        return items;
    }

    public static boolean checkoutCart(int userId) {
        List<CartItem> items = null;
        boolean isValid = true;
        Cursor cursor = database.GetData("select carts.id, carts.productId, carts.quantity from carts join users on carts.userId = users.id where carts.userId = " + userId);
        while (cursor.moveToNext()) {
            int itemId = cursor.getInt(0);
            int productId = cursor.getInt(1);
            int quantity = cursor.getInt(2);
            Product product = findProductById(productId);
            CartItem item = new CartItem(itemId, product, quantity);
            if (item.getQuantity() > product.getQuantity()) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    public static Order checkout(int userId) {
        Order order = null;
        List<Product> products = new ArrayList<>();
        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;
        boolean canCheckout = checkoutCart(userId);
        if (canCheckout) {
            List<CartItem> card = getCard(userId);
            if (card != null && !card.isEmpty()) {
                card.stream().forEach(cartItem -> {
                    Product product = findProductById(cartItem.getProduct().getProductId());
                    products.add(product);
                    product.setQuantity(product.getQuantity() - cartItem.getQuantity());
                    OrderItem item = new OrderItem(1, product, product.getPrice(), cartItem.getQuantity());
                    orderItems.add(item);
                });
                total = products.stream().map(product -> product.getPrice())
                        .reduce(0d, (num, num1) -> num + num1);
                order = new Order(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MM yyyy hh:ss")), total, orderItems);
                order.setUsers(new Users());
                insertOrderItem(orderItems, order);
                removeALLCart(userId);
            } else {
                return null;
            }
        } else {
            return null;
        }
        return order;
    }

    public static void insertOrderItem(List<OrderItem> items, Order order) {
        StringBuilder builder = new StringBuilder();
        builder.append("'"+order.getId() +"'"+ ",");
        builder.append(order.getTotals() + ",");
        builder.append(order.getUsers().getId() + ",");
        builder.append("'"+order.getId() + "')");
        database.QueryData("insert into orders(id, total , userId, orderDate) values(" + builder.toString());
        items.stream().forEach(item -> {
            Product product = item.getProduct();
            StringBuilder builderOrderStr = new StringBuilder();
            builderOrderStr.append("null,");
            builderOrderStr.append(item.getQuantity() + ",");
            builderOrderStr.append(item.getPrice() + ",");
            builderOrderStr.append(item.getProduct().getProductId() + ",");
            builderOrderStr.append("'" + order.getId() + "'" + ");");
            //database.QueryData("insert into orderDetails(id, quantity, price , productId, orderId) values(" + builder.toString());

        });
    }

    public static Users login(String username, String password) {
        Users users = null;
        Cursor cursor = database.GetData("select * from users where username = '" + username + "' and password = '" + password + "'");
        if (cursor.moveToNext()) {
            int userid = cursor.getInt(0);
            String role = cursor.getString(3);
            users = new Users();
            users.setId(userid);
            users.setUsername(username);
            users.setPassword(password);
            users.setRole(ERole.fromString(role));
        }
        return users;
    }

    public static Users register(String username, String password) {
        Users users = null;

        Cursor cursor = database.GetData("select * from users where username = '" + username +"'");
        if (cursor.moveToNext()) {
            return null;
        }
        database.QueryData("insert into users values(null,'" + username + "', '" + password + "', '" + ERole.USER.getText() + "')");
        cursor = database.GetData("select * from users where username = '" + username +"'");
        if (cursor.moveToNext()) {
            int userid = cursor.getInt(0);
            String role = cursor.getString(3);
            users = new Users();
            users.setId(userid);
            users.setUsername(username);
            users.setPassword(password);
            users.setRole(ERole.valueOf(role.toUpperCase()));
        }
        return users;
    }

    private static Users registerAdmin(String username, String password) {
        Users users = null;

        Cursor cursor = database.GetData("select * from users where username = '" + username +"'");
        if (cursor.moveToNext()) {
            return null;
        }
        database.QueryData("insert into users values(null,'" + username + "', '" + password + "', '" + ERole.ADMIN.getText() + "')");
        cursor = database.GetData("select * from users where username = '" + username +"'");
        if (cursor.moveToNext()) {
            int userid = cursor.getInt(0);
            String role = cursor.getString(3);
            users = new Users();
            users.setId(userid);
            users.setUsername(username);
            users.setPassword(password);
            users.setRole(ERole.valueOf(role.toUpperCase()));
        }
        return users;
    }

    public static void updateCart(int itemId, int userId, int quantity) {
        StringBuilder builder = new StringBuilder();
        builder.append(" quantity = ").append(quantity);
        builder.append(" where id =  ").append(itemId);
        builder.append(" and userId = ").append(userId);
        database.QueryData("Update carts set " + builder.toString());
    }

    public static void addCard(CartItem item, int userId, int quantity) {
        if (itemExist(item.getProduct().getProductId(), userId, quantity)) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append("null, ");
        builder.append(item.getQuantity() + quantity).append(", ");
        builder.append(item.getProduct().getProductId()).append(", ");
        builder.append(userId).append(")");
        database.QueryData("insert into carts values" + builder.toString());
    }

    public static void removeCart(CartItem item, int userId) {
        database.QueryData("Delete from carts where carts.userId = " + userId + " and carts.id = " + item.getId());
    }

    public static void removeALLCart(int userId) {
        database.QueryData("Delete from carts where carts.userId = " + userId);
    }

    private static boolean itemExist(int productId, int userId, int quantity) {
        StringBuilder builder = new StringBuilder();
        builder.append("userId = ").append(userId).append(" and productId = ").append(productId);
        Cursor cursor = database.GetData("select quantity from carts where " + builder.toString());
        if (cursor.moveToNext()) {
            updateCart(productId, userId, cursor.getInt(0) + quantity);
            return true;
        } else {
            return false;
        }
    }

    public static List<Product> loadProduct(){
        List<Product> products = null;
        Cursor cursor = database.GetData("select * from products");
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            double price = cursor.getDouble(3);
            int quantity = cursor.getInt(4);
            Product product = new Product(id,name,price,quantity,description);
            if(products == null){
                products = new ArrayList<>();
            }
            products.add(product);
        }
        return products;
    }

}
