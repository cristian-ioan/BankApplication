package service;

import file.UserReader;
import model.User;

import java.util.Optional;

public class UserValidate {

    public Optional<User> searchUser(User user) {
        for (String s : UserReader.getInstance().getUserPasswordMap().keySet()) {
            if (s.equals(user.getUserName()) &&
                    UserReader.getInstance().getUserPasswordMap().get(s).equals(user.getUserPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

}