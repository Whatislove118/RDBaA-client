package parser;

import exception.CommandException;
import http.HttpConnection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class CommandParser {

    private static CommandParser instance;
    public static ArrayList<ArrayList<String>> commands = new ArrayList<>();


    private CommandParser(Properties properties){
        String[] string_commands = properties.get("allowed-commands").toString().split("&");
        for (String command: string_commands){
            commands.add(new ArrayList(Arrays.asList(command.split(" "))));
        }
        commands.forEach(System.out::println);
    }


    public void parseCommand(String command) throws CommandException {
        String[] parts_of_command = command.split(" ");
        String general_command = parts_of_command[0];
        if (general_command.equals("add")){
            if (parts_of_command.length != 1){
                String book_name = parts_of_command[1];
                HttpConnection.sendAddCommand(book_name);
            }else{
                throw new CommandException("Команда add требует аргумент: имя книги");
            }
        }else if(general_command.equals("search")){
            if(parts_of_command.length == 3){
                String flag = parts_of_command[1];
                switch (flag){
                    case "-n":
                        HttpConnection.sendSearchCommand(parts_of_command[2], "name");
                        break;
                    case "-k":
                        HttpConnection.sendSearchCommand(parts_of_command[2], "keyword");
                        break;
                    case "-a":
                        HttpConnection.sendSearchCommand(parts_of_command[2], "author");
                        break;
                    default:
                        throw new CommandException("Неверные флаги команды search");
                }
            }else {
                throw new CommandException("Неверный синтаксис команды search");
            }
        }else if(general_command.equals("exit")){
            System.out.println("Досвидос");
            System.exit(0);
        }else{
            throw new CommandException(general_command + " не является командой");
        }


    }


    public static CommandParser getInstance(Properties properties){
        if (instance == null){
            instance = new CommandParser(properties);
        }
        return instance;
    }
}
