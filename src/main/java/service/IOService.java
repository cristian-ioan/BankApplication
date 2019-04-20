package service;

import java.util.Scanner;

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