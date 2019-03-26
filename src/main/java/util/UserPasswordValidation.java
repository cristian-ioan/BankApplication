package util;

import file.ReadFromFileUsers;
import model.User;

import java.util.Optional;

public class UserPasswordValidation {

    public Optional<User> verifyLogin(User user) {
        for (String s : ReadFromFileUsers.getInstance().getUserPasswordMap().keySet()) {
            if (s.equals(user.getUsername()) &&
                    ReadFromFileUsers.getInstance().getUserPasswordMap().get(s).equals(user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

}
