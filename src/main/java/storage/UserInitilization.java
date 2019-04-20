package storage;

import java.io.IOException;

public interface UserInitilization {

    /**
     * Initializes the map of users.
     *
     * @throws IOException on input error
     *
     */
    void initializeMapUserPassword() throws IOException;

}
