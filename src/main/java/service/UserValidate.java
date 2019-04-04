package service;

import model.User;
import model.UserAccountInitialization;

import java.util.Optional;

public class UserValidate {

    public Optional<User> searchUser(User user) {

        for (String s : UserAccountInitialization.getInstance().getUserPasswordMap().keySet()) {
            if (s.equals(user.getUserName()) &&
                    UserAccountInitialization.getInstance().getUserPasswordMap().get(s).equals(user.getUserPassword())) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

}