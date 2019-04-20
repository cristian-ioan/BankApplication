package storage;

import model.Account;
import model.User;

import java.io.IOException;
import java.util.List;

public interface AccountInitilization {

    /**
     * Initializes the list of user's accounts.
     *
     * @throws IOException on input error
     */
    List<Account> initializeAccountList(User user) throws IOException;

}