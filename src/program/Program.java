package program;

import dataModels.DataStore;
import dataModels.Product;
import dataModels.User;
import services.AuthService;
import services.CartServices;
import views.MainFrame;

import java.util.LinkedList;

public class Program {

    private DataStore dataStore;
    private User currentUser = null;
    private MainFrame mainFrame;

    // services
    private AuthService authService;

    public Program() {
        this.dataStore = new DataStore();
        System.out.println(this.dataStore.toString());
        this.mainFrame = new MainFrame("Shopping Cart", this);

        // init services
        this.authService = new AuthService(this.dataStore, this);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public AuthService getAuthService() {
        return authService;
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public CartServices getCartService() {
        return new CartServices(this.currentUser.getCart(), this.dataStore.getInventory(), this);
    }

    public void signalScreenRefresh(){
        this.mainFrame.resetVisibility();
    }

    public Product getRandomProduct() {
        return this.dataStore.getFirst();
    }

    public LinkedList<Product> getProducts() {
        return dataStore.getProducts();
    }
}
