package service;

import java.util.Scanner;

/**
 * Creates a singleton for input/output services.
 *
 * @param instance of type IOService
 *
 * @author Cristian-Lucian IOAN
 * @version 1.0
 * @since   2019-03-21
 */
public class IOService {

    private static IOService instance;
    private IOService(){}

    public static synchronized IOService getInstance(){
        if (instance == null){
            instance = new IOService();
        }
        return instance;
    }

    /**
     * Reads an integer from console.
     *
     * @return the value (int) read from console
     */
    public int readInteger() {
        return new Scanner(System.in).nextInt();
    }

    /**
     * Reads a string from console.
     *
     * @return the String read from console
     */
    public String readLine() {
        return new Scanner(System.in).next();
    }

}