package console;

import exception.CommandException;
import parser.CommandParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class ConsoleReader {

    private static ConsoleReader instance;
    private Properties properties;
    private CommandParser parser;

    private ConsoleReader(){
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")){
            properties = new Properties();
            properties.load(input);
            parser = CommandParser.getInstance(properties);
        }catch (IOException e){
            System.err.println("Ошибка старта программы. Отсутствует файл конфигурации");
            System.exit(0);
        }
    }

    public void read(){
        System.out.println("Welcome to BookSearcher!");

            while (true) {
                System.out.println(this.info());
                Scanner in = new Scanner(System.in);
                System.out.println("Enter command:");
                try {
                    this.parser.parseCommand(in.nextLine());
                }catch (CommandException e){
                    System.err.println(e.getMessage());
                    continue;
                }
            }


    }


    private String info(){
        return properties.getProperty("help-message");
    }


    public static ConsoleReader getInstance(){
        if (instance == null){
            instance = new ConsoleReader();
        }
        return instance;
    }







}
