package service;

import model.User;
import storage.UserInitializationImpl;

import java.util.Optional;

public class UserValidate {

    /**
     * Searches the user into map of users.
     *
     * @return Optional.of(user) if is found, otherwise Optional.empty()
     */
    public Optional<User> searchUser(User user) {

        for (String s : UserInitializationImpl.getInstance().getUserPasswordMap().keySet()) {
            if (s.equals(user.getUserName()) &&
                    UserInitializationImpl.getInstance().getUserPasswordMap().get(s).equals(user.getUserPassword())) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

}