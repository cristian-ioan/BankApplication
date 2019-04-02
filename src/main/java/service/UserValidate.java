package service;

//import file.FileReader;
import model.User;
import model.UserAccountCollections;

import java.util.Optional;

public class UserValidate {

    public Optional<User> searchUser(User user) {
//        for (String s : FileReader.getInstance().getUserPasswordMap().keySet()) {
//            if (s.equals(user.getUserName()) &&
//                    FileReader.getInstance().getUserPasswordMap().get(s).equals(user.getUserPassword())) {
//                return Optional.of(user);
//            }
//        }

        for (String s : UserAccountCollections.getInstance().getUserPasswordMap().keySet()) {
            if (s.equals(user.getUserName()) &&
                    UserAccountCollections.getInstance().getUserPasswordMap().get(s).equals(user.getUserPassword())) {
                return Optional.of(user);
            }
        }


        return Optional.empty();
    }

}