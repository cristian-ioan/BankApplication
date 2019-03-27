package util;

import file.ReadFromUsersFile;
import model.User;

import java.util.Optional;

public class UserPasswordValidation {

    public Optional<User> verifyLogin(User user) {
        for (String s : ReadFromUsersFile.getInstance().getUserPasswordMap().keySet()) {
            if (s.equals(user.getUserName()) &&
                    ReadFromUsersFile.getInstance().getUserPasswordMap().get(s).equals(user.getUserPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

}
