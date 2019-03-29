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

    public int readInteger() {
        return new Scanner(System.in).nextInt();
    }

    public String readLine() {
        return new Scanner(System.in).next();
    }

}