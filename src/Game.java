public class Game
{
    private Room currentRoom;

    private Parser parser;

    private Player player;

    Item poisonPotion = new Item();
    Item unknownPotion = new Item();
    Item bookRead = new Item();
    Item dullSword = new Item();
    Item steelArmor = new Item();
    Item masterKey = new Item();
    Item enchantedSword = new Item();
    Item enchantedArmor = new Item();

    public Game()
    {
        parser = new Parser();
        player = new Player();
    }

    public static void main(String[] args)
    {
        Game game = new Game();
        game.createRooms();
        game.play();
    }

    public void createRooms()
    {
        Room Laboratory = new Room("You look around, you see many mechanisms, trinkets, and tubes filled with experiments", "There are 2 potions, one that seems to contain poison and the other is unknown. To the west is a door that seems to go to a library. To the east is an open door that leads to a sewer.");
        Room AcidicSewer = new Room("The foul smell of the sewers makes you want to leave immediately. ", "You take a small glance around the room and see a disposal that leads to somewhere unknown, you seem to be able to go in but the sewers is filled with acid. ");
        Room Library = new Room("Books upon books are filled around you.", "There is a small case that seems to have no lock and books shelves with books you may read. As you look to the north, a chill goes down your spine, a locked door that leads to what looks like the owners room. ");
        Room StorageRoom = new Room("Many used items are laying around.", "Only a suit of armor and a key seems to be of use. ");
        Room MasterRoom = new Room("You see countless papers and trinkets laying around with a singular light that illuminates the room.", "It is indeed the owners room. There is a man who stands in the room who turns toward your direction. The man is enraged and looks as if he's going to attack you. ");
        Room Outside = new Room("You reach the outside of the palace, ", "Outside Long");


        Laboratory.setExit("east", AcidicSewer);
        Laboratory.setExit("west", Library);

        AcidicSewer.setExit("west", Laboratory);
        AcidicSewer.setExit("east", Outside);

        Library.setExit("east", Laboratory);
        Library.setExit("south", StorageRoom);
        Library.setExit("north", MasterRoom);

        StorageRoom.setExit("north", Library);

        MasterRoom.setExit("east", Outside);


        Laboratory.setItem("PoisonPotion", poisonPotion);
        Laboratory.setItem("UnknownPotion", unknownPotion);

        Library.setItem("Book", bookRead );
        Library.setItem("DullSword", dullSword);

        StorageRoom.setItem("SteelArmor", steelArmor);
        StorageRoom.setItem("MasterKey", masterKey);




        currentRoom = Laboratory;
    }

    public void play()
    {
        printWelcome();

        boolean finished = false;

        while(!finished)
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thanks for playing!");
    }

    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;
        System.out.println(command.getCommandWord());
        CommandWord commandWord = command.getCommandWord();

        switch(commandWord)
        {
            case UNKNOWN:
                System.out.println("I don't know what you mean. ");
                break;

            case HELP:
                printHelp();

                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = true;
                break;

            case LOOK:
                look(command);
                break;

            case DROP:
                dropItem(command);
                break;

            case GRAB:
                grabItem(command);
                break;

            case READ:
                readItem(command);
                break;

            case APPLY:
                applyItem(command);
                break;

        }
        return wantToQuit;
    }


    private  void printHelp()
    {
        System.out.println("You are currently in the " + currentRoom);
        System.out.println("");
        System.out.println("Your command words are: ");
        parser.showCommands();
    }
    private void look(Command command)
    {
        if(command.hasSecondWord())
        {
            System.out.println("You can't look at that. " + command.getSecondWord());
            return;
        }
        System.out.println(currentRoom.getLongDescription());
        System.out.println(player.getItemString());
    }
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Go where? ");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if(nextRoom == null)
        {
            System.out.println("There is no entrance! ");
        }

        else
        {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getShortDescription());
        }
    }

    private void grabItem(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Grab what? ");
            return;
        }

        String item = command.getSecondWord();
        Item itemGrabbed = currentRoom.getItem(item);

        if(itemGrabbed == null)
        {
            System.out.println("You can't grab " + command.getSecondWord());
        }
        else
        {
            player.setItem(item, itemGrabbed);
        }
        System.out.println("You have grabbed " + command.getSecondWord());
    }
    private void dropItem(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Drop what? ");
            return;
        }

        String item = command.getSecondWord();
        Item itemDropped = player.getItem(item);

        if(itemDropped == null)
        {
            System.out.println("You can't drop " + command.getSecondWord());
        }
        else
        {
            currentRoom.setItem(item, itemDropped);
        }
        System.out.println("You have dropped " + command.getSecondWord());
    }

    private void readItem(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Read what? ");
            return;
        }

        String readCommand = command.getSecondWord();
        Item thingToRead = player.getItem(readCommand);

        if(!thingToRead.equals(bookRead))
        {
            System.out.println("You can't read " + command.getSecondWord());
        }
        else
        {
            System.out.println("You have read the book. ");
            System.out.println("Information about the potions read: ");
            System.out.println("The Poison Potion is highly dangerous and could be applied onto something. ");
            System.out.println("The Unknown Potion is a Protective Potion and could be applied onto something. ");
        }
    }

    private void applyItem(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Apply what? ");
            return;
        }

        String applyCommand = command.getSecondWord();
        Item appliedItem = player.getItem(applyCommand);


        if(!appliedItem.equals(dullSword))
        {
            System.out.println("You can't apply " + command.getSecondWord());
        }
        if(appliedItem.equals(dullSword))
        {
            System.out.println("What would you like to apply it with? ");
        }
        if(appliedItem.equals(poisonPotion))
        {
            dullSword.equals(enchantedSword);
        }
        else
        {
            System.out.println("Your " + appliedItem + "has been enchanted");
        }
    }


    private boolean quit(Command command)
    {
        if(command.hasSecondWord())
        {
            System.out.println("You can't quit " + command.getSecondWord());
            return false;
        }
        else
        {
            return true;
        }
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to my text adventure game!");
        System.out.println("You will find yourself in the Ark Palace, your mission is to escape and live.");
        System.out.println("Type \"help\" if you need assistance");
        System.out.println();
        System.out.println("You look around, you see many mechanisms, trinkets, and tubes filled with experiments. ");
    }
}
