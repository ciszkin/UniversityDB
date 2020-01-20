package by.tms.UniversityDB.util;


import java.util.Scanner;

public class ConsoleReader implements Reader {
    Scanner scan = new Scanner(System.in);

    @Override
    public String read() {
        return scan.nextLine();
    }

}
