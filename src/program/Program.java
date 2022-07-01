package program;

import dataModels.DataStore;
import dataModels.User;
import services.AuthService;
import views.MainFrame;

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
}
