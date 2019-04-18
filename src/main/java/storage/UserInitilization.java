package storage;

import java.io.IOException;

/**
 * Initializes the map of users.
 *
 * @throws IOException on input error
 *
 * @author Cristian-Lucian IOAN
 * @version 1.0
 * @since   2019-03-21
 */
public interface UserInitilization {

    void initializeMapUserPassword() throws IOException;

}
