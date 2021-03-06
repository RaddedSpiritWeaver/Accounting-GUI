package dataModels;

import dataModels.enums.ProductType;

import java.util.LinkedList;

public class DataStore {

    private LinkedList<Product> products;
    private LinkedList<User> users;
    private Cart inventory;

    public DataStore() {
        this.products = new LinkedList<>();
        this.users = new LinkedList<>();
        this.inventory = new Cart(null);
        initUsers();
        initProducts();
        initInventory();
    }

    private void initUsers() {
        for(int i = 1; i < 11; i++)
            this.users.add(new User(String.format("user%d", i), "12345678"));
    }

    private void initProducts() {
        this.products.add(new Product("laptop", ProductType.Electronics, 1000));
        this.products.add(new Product("laptop 2", ProductType.Electronics, 2000));
        this.products.add(new Product("laptop 3", ProductType.Electronics, 2300));
        this.products.add(new Product("laptop 4", ProductType.Electronics, 2300));
        this.products.add(new Product("laptop 5", ProductType.Electronics, 2300));
        this.products.add(new Product("laptop 6", ProductType.Electronics, 2300));
        this.products.add(new Product("laptop 7", ProductType.Electronics, 2300));

        this.products.add(new Product("pill", ProductType.Pharmaceutical, 100));
        this.products.add(new Product("pill2", ProductType.Pharmaceutical, 200));
        this.products.add(new Product("pill3", ProductType.Pharmaceutical, 300));
        this.products.add(new Product("pill laptop", ProductType.Pharmaceutical, 100));
        this.products.add(new Product("pill2 laptop", ProductType.Pharmaceutical, 200));
        this.products.add(new Product("pill3 laptop", ProductType.Pharmaceutical, 300));

        this.products.add(new Product("chips", ProductType.Household, 50));
        this.products.add(new Product("chips2", ProductType.Household, 75));
        this.products.add(new Product("chips3", ProductType.Household, 100));
    }

    private void initInventory() {
        int i = 1;
        for (Product p : this.products) {
            i++;
            this.inventory.addItem(p, i);
        }
    }

    public User getUserByName(String username) {
        // todo: improve search
        // run simple search for user
        for(User u : this.users)
            if(u.getUsername().equals(username))
                return u;

        // return null on fail
        return null;
    }

    public void addUser(User u){
        this.users.add(u);
    }

    public Cart getInventory() {
        return this.inventory;
    }

    public Product getFirst() {
        return this.products.getFirst();
    }

    public LinkedList<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        this.inventory.show();
        return "DataStore{" +
                "products=" + products +
                "\n users=" + users +
                '}';
    }
}
