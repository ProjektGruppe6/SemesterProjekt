package worldofzuul;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {

    private Parser parser;
    private Room currentRoom;
    Room outside, semesterProject, objectOriented, softwareEngineering, computersystem, SemesterProject, onlineCourse, bar, library, cafe, secondSemester, Task;
    ArrayList<Item> material = new ArrayList<Item>();

    public Game() {
        createRooms();
        parser = new Parser();
    }

    private void createTask() {

        try {
            BufferedReader br = new BufferedReader(new FileReader(" "));
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                String[] temp = line.split(";");
                if (temp[0].equals("OP")) {
                    objectOriented.addTask(new Task(temp[1], temp[3], temp[2]));
                }

            }

        } catch (Exception ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void createRooms() {
        // Here we have 11 Rooms

        outside = new Room("outside the main entrance of the university\n");
        onlineCourse = new Room("in online course class\n");
        semesterProject = new Room("in Group local rooms with your group members\n");
        objectOriented = new Room("in Object oriented programming\n");
        softwareEngineering = new Room("in the Introduction to software engineering\n");
        computersystem = new Room("in the Computer System Room\n");
        bar = new Room("in the bar\n");
        library = new Room("in the library\n");
        cafe = new Room("in the cafe");
        secondSemester = new Room("in the entrance to second semester\n");
        semesterProject = new Room("in the semester project room\n");

        // Exits from outside 
        outside.setExit("east", computersystem);
        outside.setExit("south", secondSemester);
        outside.setExit("west", objectOriented);
        outside.setExit("north", softwareEngineering);

        // Exits from Software engineering Room
        softwareEngineering.setExit("north", library);
        softwareEngineering.setExit("south", outside);
        softwareEngineering.setExit("east", onlineCourse);
        softwareEngineering.setExit("west", semesterProject);

        // Exits from library
        library.setExit("east", secondSemester);
        library.setExit("north", objectOriented);

        // Exits from cafe
        cafe.setExit("north", computersystem);
        cafe.setExit("west", secondSemester);

        // Exits form Second semester Room
        secondSemester.setExit("north", outside);
        secondSemester.setExit("east", cafe);
        secondSemester.setExit("west", library);
        semesterProject.setExit("south", objectOriented);
        semesterProject.setExit("east", softwareEngineering);

        // Exits from object oriented Room
        objectOriented.setExit("north", semesterProject);
        objectOriented.setExit("south", library);
        objectOriented.setExit("east", outside);

        // Task 
        Task task = null;
        objectOriented.addTask(task);

        // Exits from online course
        onlineCourse.setExit("south", computersystem);
        onlineCourse.setExit("west", softwareEngineering);

        // Exits from Computer System
        computersystem.setExit("north", onlineCourse);
        computersystem.setExit("south", cafe);
        computersystem.setExit("west", outside);

        // Materials added when u start the course
        material.add(new Item("Student ID"));
        material.add(new Item("Laptop"));
        material.add(new Item("BlackBoard UserName and Password"));

        library.setItem(new Item("Books"));
        library.setItem(new Item("Dictionary"));

        cafe.setItem(new Item("Koffee"));
        cafe.setItem(new Item("Pepsi cola"));
        cafe.setItem(new Item("Sandwich"));
        cafe.setItem(new Item("Øl"));

        // Materials added in the Rooms laptop and books
        // Items --- books
        computersystem.setItem(new Item(" CS book"));
        // computersystem.setItem(new Item(" Key"));
        objectOriented.setItem(new Item(" Java Book"));
        softwareEngineering.setItem(new Item(": Software Book\n"));
        //objectOriented.setItem(new Item(" Key"));
        //softwareEngineering.setItem(new Item(" Key"));

        currentRoom = outside;

    }

    public void play() throws IOException {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() {

        System.out.println();
        System.out.println("****************************************************************");
        System.out.println("Welcome to the FreshMan Game!");
        System.out.println("A Program designed for first semester student at SDU");

        //System.out.println(currentRoom.getLongDescription());
        System.out.println("Type '" + CommandWord.HELP + "'");
        System.out.println("****************************************************************");

    }

    private boolean processCommand(Command command) throws IOException {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (null != commandWord) {
            switch (commandWord) {
                case HELP:
                    printHelp();
                    break;
                case GO:
                    goRoom(command);
                    break;
                case QUIT:
                    wantToQuit = quit(command);
                    break;
                case MATERIALS:
                    printInventory();
                    break;
                case TEST:
                    printTest();
                    break;
                case NOTES:
                    printNotes();
                    break;
                case COURSEINFO:
                    printcourseInfo();
                    break;
                case INSTRUCTOR:
                    printInstructor();

                default:
                    break;
            }
        }
        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("\nWelcome to SDU you are about to start Software Engineering \nThis game will give you a view for first semester\nYou have 3 Course and 1 online course and 1 Semester project \n ");
        System.out.println("Choose one of the commands:");
        System.out.println("");
        System.out.println(CommandWord.GO + " east, north, west or south");
        System.out.println(CommandWord.HELP);
        System.out.println(CommandWord.QUIT);
        System.out.println("");
        //  parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            System.out.println("");
            System.out.println("Choose commands in the class rooms:");
            System.out.println("");
            System.out.println(CommandWord.NOTES + "'");
            System.out.println(CommandWord.INSTRUCTOR + "'");
            System.out.println(CommandWord.TEST + "'");
            System.out.println(CommandWord.COURSEINFO + "'");
            System.out.println("");

        }
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }

    private void printInventory() {
        String output = ("0");
        for (int i = 0; i < material.size(); i++) {
            output += material.get(i).getDescription() + " ";
        }

        System.out.println("Now You Have :");
        System.out.println(output);
    }

    private void printTest() {
        System.out.println("printTest");
    }

    private void printcourseInfo() {
        System.out.println("print Course info");
    }

    private void printNotes() {
        System.out.println("print notes");
    }

    private void printInstructor() throws FileNotFoundException, IOException {

        Scanner instuctor = new Scanner(System.in);
        System.out.println("Hellow My Name is Lone");

        System.out.println("Welcom to " + currentRoom.getShortDescription());

        BufferedReader reader = new BufferedReader(new FileReader(
                "cvsFile.csv"));

        // read file line by line
        String line = null;
        Scanner scanner = null;
        int index = 0;
        List<cvsFile> empList = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            cvsFile emp = new cvsFile();
            scanner = new Scanner(line);
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                String data = scanner.next();
                if (index == 0) {
                    emp.setId(Integer.parseInt(data));
                } else if (index == 1) {
                    emp.setSubject(data);
                } else if (index == 2) {
                    emp.setQuestion(data);
                } else if (index == 3) {
                    emp.setChoice(data);
                } else if (index == 4) {
                    emp.setAnswer(data);
                } else {
                    System.out.println("invalid data::" + data);
                }

                index++;
            }

            index = 0;
            empList.add(emp);

        }

        //close reader
        reader.close();

        System.out.println(empList);
    }
}
