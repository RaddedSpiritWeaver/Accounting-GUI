package services;

import dataModels.DataStore;
import dataModels.User;
import program.Program;

public class AuthService {

    private DataStore datastore;
    private Program program;

    public AuthService(DataStore datastore, Program program) {
        this.datastore = datastore;
        this.program = program;
    }

    public boolean login(String username, String password) {
        boolean successfulAction = false;

        // first check if users exists
        User u = this.datastore.getUserByName(username);
        if (u == null) {
            System.out.println("loginsService: (login) user not found!");
            return successfulAction;
        }

        // check password
        if (u.getPassword().equals(password)) {
            successfulAction = true;
            this.program.setCurrentUser(u);
            System.out.println("loginsService: (login) successful login");
        }
        else {
            System.out.println("loginsService: (login) password incorrect!");
        }

        return successfulAction;
    }

    public boolean createUser(String username, String password) {
        boolean successfulAction = false;

        // check if username is not taken;
        User u = this.datastore.getUserByName(username);
        if (u == null) {
            // create a new user
            u = new User(username, password);
            this.datastore.addUser(u);
            System.out.println("loginsService: (createUser) new user crated");
            successfulAction = true;
        }
        else {
            System.out.println("loginsService: (createUser) username already taken!");
        }

        return successfulAction;
    }
}
