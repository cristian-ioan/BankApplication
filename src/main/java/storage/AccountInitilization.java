package storage;

import model.Account;
import model.User;

import java.io.IOException;
import java.util.List;

/**
 * Initializes the list of user's accounts.
 *
 * @throws IOException on input error
 *
 * @author Cristian-Lucian IOAN
 * @version 1.0
 * @since   2019-03-21
 */
public interface AccountInitilization {

    List<Account> initializeAccountList(User user) throws IOException;

}