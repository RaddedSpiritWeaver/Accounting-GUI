package services;

import dataModels.Cart;
import dataModels.Product;
import program.Program;

public class CartServices {

    private Cart cart;
    private Cart inventory;
    private Program program;

    public CartServices(Cart cart, Cart inventory, Program program) {
        this.cart = cart;
        this.inventory = inventory;
        this.program = program;
    }

    public int getInventoryCount(Product p) {
        return this.inventory.getCount(p);
    }

    public int getCartCount(Product p) {
        return this.cart.getCount(p);
    }

    public void addToCart(Product p) {
        int inventoryCount = this.inventory.getCount(p);

        if (inventoryCount <= 0) {
            System.out.println("cartService - (addToCart) : cannot add to cart, not enough resources");
            return;
        }
        this.inventory.removeItem(p);
        this.cart.addItem(p);

        // refresh screen
        this.program.signalScreenRefresh();
    }

    public void removeFromCart(Product p) {
        int cartCount = this.cart.getCount(p);

        if (cartCount <= 0) {
            System.out.println("cartService - (removeFromCart) : cannot remove from cart, item not exists");
            return;
        }
        this.cart.removeItem(p);
        this.inventory.addItem(p);

        this.program.signalScreenRefresh();
    }
}
