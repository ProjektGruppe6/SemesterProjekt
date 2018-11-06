package worldofzuul;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

public class Room {

    private String description;
    private HashMap<String, Room> exits;
    
    ArrayList<Item> items = new ArrayList<Item>();
    ArrayList<Task> tasks = new ArrayList<Task>();

    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }
    
    public void addTask(Task task){
        tasks.add(task);
    }

    public String getShortDescription() {
        return description;
    }

    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();

    }

    private String getExitString() {
        String returnString = "Exits:";
        Set keys = exits.keySet();
        for (Iterator iter  = keys.iterator(); iter.hasNext();)
            returnString += " " + iter.next();
         returnString += "\nItems in the Room";   
         returnString += getRoomItems();
         return returnString;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
        
        
    }

    //Get items from the room 
    public Item getItem(int index) {

        return items.get(index);
    }

    //Set a particular Item
    public void setItem(Item newItem) {
       items.add(newItem);
    }

    public String getRoomItems()

    {

        String output = ("");
        for (int i = 0; i < items.size(); i++) {
            output += items.get(i).getDescription() + " ";
        }
        return output;

    }
}