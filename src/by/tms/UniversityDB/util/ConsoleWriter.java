package by.tms.UniversityDB.util;


public class ConsoleWriter implements Writer {

    @Override
    public void write(Object message) {
        System.out.println(message.toString());
    }
}
