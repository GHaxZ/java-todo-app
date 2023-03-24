import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class ToDo {
    public static void main(String[] args) {
        Path path = Paths.get("data.txt");
        openFile(path);
        mainConsole(path);
    }

    public static void openFile(Path path){
        try{
            Files.createFile(path);
        } catch (IOException e) {}
    }

    public static void mainConsole(Path path){
        while(true){
            System.out.println("Welcome to ToJo!\n");
            printContent(path);
            System.out.println("\nAvailable commands: new, delete, exit");
            System.out.print("Enter your command: ");
            String input = stringInput();

            switch(input.toLowerCase()){
                case "new":
                    System.out.println("\nPlease enter what task you want to add: ");
                    addItem(stringInput(), path);
                    break;
                case "delete":
                    deleteItem(path);
                    break;
                case "exit":
                    System.exit(0);
                default:
                    System.out.println("\"" + input + "\" is not a valid command.");
            }
        }
    }

    public static void printContent(Path path){
        try{
            System.out.println("Current Tasks:");
            List<String> fileContent = Files.readAllLines(path);
            for(String item : Files.readAllLines(path)){
                System.out.println(item);
            }
        } catch(IOException e){}
    }

    public static void addItem(String input, Path path) {
        try {
            if(Files.size(path) == 0){
                Files.writeString(path, input, StandardOpenOption.APPEND);
            } else {
                Files.writeString(path, "\n" + input, StandardOpenOption.APPEND);
            }
        } catch(IOException e){}
    }

    public static void deleteItem(Path path){
        try{
            List<String> fileContent = Files.readAllLines(path);
            for(int x = 1; x <= fileContent.size(); x++){
                System.out.println("[" + x + "] " + fileContent.get(x - 1));
            }

            System.out.print("Please enter the index of the task you want to delete: ");
            int input = intInput();
            Files.writeString(path, "", StandardOpenOption.TRUNCATE_EXISTING);
            for(int x = 0; x < fileContent.size(); x++){

                if(x + 1 == input){
                    continue;
                }
                addItem(fileContent.get(x), path);
            }
        } catch(IOException e){}
    }

    public static String stringInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int intInput(){
        Scanner scanner = new Scanner(System.in);

        while(true){
            if(scanner.hasNextInt()){
                return scanner.nextInt();
            } else {
                System.out.print("\"" + scanner.nextLine() + "\" is not a valid number. Please enter again: ");
            }
        }
    }
}
