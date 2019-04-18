package utils;

/**
 * Class of constants.
 *
 * @param FILE_PATH_USERS the path of users file
 * @param FILE_PATH_ACCOUNT the path of accounts file
 * @param SPLIT_BY_SPACE unit of space
 * @param NUMBER_OF_COLUMNS_ACCOUNT numbers of columns from accounts file
 * @param NUMBER_OF_COLUMNS_USERS numbers of columns from users file
 *
 * @author  Cristian-Lucian IOAN
 * @version 1.0
 * @since   2019-03-21
 */

public final class FileUtils {

    public static final String FILE_PATH_USERS = "src/main/resources/users.txt";
    public static final String FILE_PATH_ACCOUNTS = "src/main/resources/accounts.txt";
    public static final String SPLIT_BY_SPACE = "\\s";
    public static final Integer NUMBER_OF_COLUMNS_ACCOUNTS = 4;
    public static final Integer NUMBER_OF_COLUMNS_USERS = 2;

    private FileUtils(){}

}
