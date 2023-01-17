public class Game
{
    private Room currentRoom;

    public Game()
    {
        public static void main(String[] args)
        {
            Game game = new Game();
            game.createRoom();
            game.play();
        }
    }

    public static void createRooms()
    {
        Room Laboratory = new Room("You look around, you see many mechanisms, trinkets, and tubes filled with experiments");
        Room AcidicSewer = new Room("The foul smell of the sewers makes you want to leave immediately");
        Room Library = new Room("Books upon books are filled around you.");
        Room StorageRoom = new Room("Many used items are laying around.");
        Room MasterBedroom = new Room("You see countless papers and trinkets laying around with a singular light that illuminates the room.");
        Room Outside = new Room("You reach the outside of the palace, ");
    }

    public void play()
    {
        printWelcome();

        boolean finished = false;

        while(!finsihed)
        {

        }
        System.out.println("Thanks for playing!");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to my text adventure game!");
        System.out.println("You will find yourself in the Ark Palace, your mission is to escape and live.");
        System.out.println("Type \"help\" if you need assistence");
        System.out.println();
        System.out.println("Print long description here");
    }
}
