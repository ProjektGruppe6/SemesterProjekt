
// Dialog
package worldofzuul;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Game 
{
    private Parser parser;
    private Room currentRoom;
    Room outside, semesterProject, objectOriented, softwareEngineering, computersystem,SemesterProject, onlineCourse, bar, library, cafe, secondSemester, Task ;
    ArrayList<Item> material = new ArrayList<Item>();
    
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    
private void createTask(){
    
        try {
            BufferedReader br = new BufferedReader(new FileReader(" "));
                String line;
                while((line = br.readLine())!=null) 
                {
                // process the line.
                    String[] temp = line.split(";");
                    if(temp[0].equals("OP"))
                    {
                        objectOriented.addTask(new Task(temp[1],temp[3],temp[2]));
                    }
                        
                }
                    
                    
                    
                    
        } catch (Exception ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    
   

}    
    
    

private void createRooms()
    {
       // Here we have 11 Rooms
        
        outside = new Room("outside the main entrance of the university");
        onlineCourse = new Room("in online course class");
        semesterProject = new Room("in Group local rooms with your group members");
        objectOriented = new Room("in Object oriented programming");
        softwareEngineering = new Room("in the Introduction to software engineering ");
        computersystem = new Room("in the Computer System Room");
        bar = new Room("in the bar");
        library = new Room("in the library");
        cafe = new Room("in the cafe");
        secondSemester = new Room ("in the entrance to second semester");
        semesterProject = new Room ("in the semester project room");
        
        // Exits from outside 
        
        outside.setExit("east", computersystem);
        outside.setExit("south", secondSemester);
        outside.setExit("west", objectOriented);
        outside.setExit("north", softwareEngineering);
        
        // Exits from Software engineering Room
        
        softwareEngineering.setExit("north", library); 
        softwareEngineering.setExit("south",outside);
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
        
        material.add(new Item(" Student ID"));
        material.add(new Item(" Computer / Laptop"));
        material.add(new Item(" BlackBoard UserName and Password"));

        
        // Materials added in the Rooms laptop and books
        
        // Items laptop
        onlineCourse.setItem(new Item(" Laptop"));
        softwareEngineering.setItem(new Item(" Laptop"));
        objectOriented.setItem(new Item(" Laptop"));
        
        // Items --- books
        computersystem.setItem(new Item(" Computer Science"));
        objectOriented.setItem(new Item(" Introduction to Java Programmering"));
        softwareEngineering.setItem(new Item(" Introduction to Software Engineering"));
         
        currentRoom = outside;
        
    }

    public void play() 
    {            
        printWelcome();

                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        
        System.out.println();
        System.out.println("****************************************************************");
        System.out.println("Welcome to the Fresh Man Game!");
        System.out.println("You are about to start Software Engennering at SDU.");
        System.out.println("Type '" + CommandWord.HELP + "'");
        System.out.println("Collect some material for the study");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        System.out.println("****************************************************************");
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (null != commandWord) switch (commandWord) {
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
            
                
            default:
                break;
        }
        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);
        Dialog d = new Dialog();
        d.showText("COSdialog.txt");

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }

    private void printInventory() {
        String output = ("");
        for (int i = 0; i < material.size(); i++) {
          output += material.get(i).getDescription() + " " ;
        }
        /* logic to ask what kind of inventory he or she would like to have ?
         System.out.println("would u like to have computer ?");
        */
        
        System.out.println("Now You Have :");
        System.out.println(output);
    }
}


