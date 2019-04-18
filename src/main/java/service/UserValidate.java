package service;

import model.User;
import storage.UserInitializationImpl;

import java.util.Optional;

/**
 * Searches the user into the map of users.
 *
 * @return Optional.of(user) if is found, otherwise Optional.empty()
 *
 * @author Cristian-Lucian IOAN
 * @version 1.0
 * @since   2019-04-08
 */
public class UserValidate {

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