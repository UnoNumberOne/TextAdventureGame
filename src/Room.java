import java.util.HashMap;

public class Room
{
    private String description;
    private HashMap<String, Room>exits;

    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    public String getShortDescription()
    {
        return description;
    }
}
