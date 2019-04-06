package storage;

import model.Account;
import model.User;

import java.io.IOException;
import java.util.List;

public interface AccountInitilization {

    List<Account> initializeAccountList(User user) throws IOException;

}