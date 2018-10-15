package worldofzuul;

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        

    public Game() 
    {
        createRooms();
        parser = new Parser();
    }


private void createRooms()
    {
        Room outside, semesterProject, objectOriented, softwareEngeneering, computersystem,projektlokal, onlineCourse, bar, library, cafe, secondSemester ;
 
        outside = new Room("outside the main entrance of the university");
        onlineCourse = new Room("in online course class");
        semesterProject = new Room("in Group local rooms with your group members");
        objectOriented = new Room("in Object oriented programming");
        softwareEngeneering = new Room("in the Introduction to software engineering ");
        computersystem = new Room("in the Computer System Room");
        bar = new Room("in the bar");
        library = new Room("in the library");
        cafe = new Room("in the cafe");
        secondSemester = new Room ("in the entrance to second semester");
        projektlokal = new Room ("in the projekt lokal room");
        
        outside.setExit("east", computersystem);
        outside.setExit("south", secondSemester);
        outside.setExit("west", objectOriented);
        outside.setExit("north", softwareEngeneering);
        
        softwareEngeneering.setExit("north", library); 
        softwareEngeneering.setExit("south",outside);
        softwareEngeneering.setExit("east", onlineCourse);
        softwareEngeneering.setExit("west", semesterProject);
       
        
        semesterProject.setExit("north", library);
        semesterProject.setExit("south", objectOriented);
        semesterProject.setExit("east", softwareEngeneering);
        semesterProject.setExit("west", projektlokal);
        
        
        objectOriented.setExit("north", semesterProject);
        objectOriented.setExit("south", cafe);
        objectOriented.setExit("east", outside);
        objectOriented.setExit("west", library);
        
        onlineCourse.setExit("north", bar);
        onlineCourse.setExit("south", computersystem);
        onlineCourse.setExit("east", library);
        onlineCourse.setExit("west", softwareEngeneering);
        
        computersystem.setExit("north", onlineCourse);
        computersystem.setExit("south", cafe);
        computersystem.setExit("east", library);
        computersystem.setExit("west", outside);
        

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
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
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
}
