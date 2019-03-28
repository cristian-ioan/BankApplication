package service;

import java.util.Scanner;

public class IOService {

    private Scanner option;

    public IOService() {
        option = new Scanner(System.in);
    }

    public int readInteger() {
        return option.nextInt();
    }

    public String readLine() {
        return option.next();
    }

}