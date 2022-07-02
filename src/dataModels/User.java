package dataModels;

public class User {

    private String username;
    private String password; // todo: maybe add hashing and stuff
    private Cart cart;
    private long debt;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.cart = new Cart(this);
        this.debt = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public long getDebt() {
        return debt;
    }

    public void setDebt(long debt) {
        this.debt = debt;
    }

    public boolean hasEmptyCart() {
        return this.cart.getProducts().isEmpty();
    }

    @Override
    public String toString() {
        this.cart.show();
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", cart=" + cart +
                ", debt=" + debt +
                '}';
    }
}
