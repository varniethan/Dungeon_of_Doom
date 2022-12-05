import java.io.*;
import java.util.*;

/**
 * Contains the main logic part of the game, as it processes.
 *
 */
public class GameLogic {

    /* Reference to the map being used */
    private Map map;
    private HumanPlayer player;
    private Bot bot;
    private int[] botCoords;
    private int[] playerCoords;
    public char previousChar;
    private List<String> validCommands;

    /**
     * Default constructor
     */
    public GameLogic()
    {
        this.validCommands = Arrays.asList("HELLO", "LOOK", "MOVE N", "MOVE S", "MOVE E", "MOVE W", "PICKUP", "QUIT");
        setUpGame();

    }

    /**
     * Checks if the game is running
     *
     * @return if the game is running.
     */
    public boolean gameRunning()
    {
        return false;
    }

    /**
     * Returns the gold required to win.
     *
     * @return : Gold required to win.
     */
    public String hello()
    {
        return null;
    }

    public int getFileChoice(String fileList[])
    {
        System.out.println("Select the number of the map you want to play. Enter 0 to load the default map :> ");
        int i = 0;
        for (String file : fileList)
        {
            i++;
            System.out.print(i+".");
            System.out.println(file);
        }
        Scanner userInput = new Scanner(System.in);
        int option=-1;
        while (true)
        {
            if (userInput.hasNextInt())
            {
                option = userInput.nextInt();
                if(option >= 0 && option <= i)
                {
                    break;
                }
                else
                {
                    System.out.println("Error, Your input out of range. Please enter an integer in range 0-"+(i));
                    userInput.nextLine();
                }

            }
            else
            {
                System.out.println("Error, You input isn't recognised. Please enter an integer between 0-"+(i));
                userInput.nextLine();
            }
        }
        return option;
    }

    /**
     * Get all the files avaialbe in the maps folder
     *
     * @return : list of files.
     */
    public String[] getListFiles(String filePath)
    {
        File directoryPath = new File(filePath);
        String fileList[] = directoryPath.list();
        return fileList;
    }


    public void setUpGame()
    {
        String[] fileList = getListFiles("./src/maps");
        int option = getFileChoice(fileList);
        if (option == 0)
        {
            map = new Map();
        }
        else
        {
            map = new Map(fileList[option-1]);
        }
        player = new HumanPlayer(map.randomPlayerPosition('P'), this.map);
        bot = new Bot(map.randomPlayerPosition('B'), this.map);
        player.setCurrentPosition(map.getCharPosition('P'));
        bot.setCurrentPosition(map.getCharPosition('B'));
    }

    public String userInput()
    {
        Scanner userInput = new Scanner(System.in);
        return userInput.nextLine().toUpperCase();
    }

    public boolean checkWinningCondition()
    {
        if (player.getGoldOwned() == map.getGoldRequired())
        {
            return true;
        }
        return  false;
    }
    public void play()
    {
        String choice;
        while (true)
        {
            choice = this.userInput();
            if (this.validCommands.contains(choice))
            {
                String[] arr = choice.split(" ", 2);
                String command = arr[0];
                switch (command) {
                    case "HELLO":
                        System.out.println("Gold to win: "+ map.getGoldRequired());
                        break;
                    case "GOLD":
                        System.out.println("Gold owned: "+player.getGoldOwned());
                        break;
                    case "PICKUP":
                        int newGoldOwned = player.pickup();
                        if (newGoldOwned != -1)
                        {
                            System.out.println("SUCCESS: Gold owned " + newGoldOwned);
                        }
                        else
                        {
                            System.out.println("FAIL");
                        }
                        break;
                    case "MOVE":
                        if(player.move(choice.charAt(5)))
                        {
                            System.out.println("SUCCESS");
                        }
                       else
                        {
                            System.out.println("FAIL");
                        }
                        break;
                    case "LOOK":
                        for(char[] x : player.look('P'))
                        {
                            for (char y : x)
                            {
                                System.out.print(y);
                            }
                            System.out.println();
                        }
                        break;
                    case "QUIT":
                        if(this.checkWinningCondition())
                        {
                            System.out.println("WIN");
                        }
                        else
                        {
                            System.out.println("LOST");
                        }
                        System.exit(0);
                        break;
                }
            }
            else
            {
                System.out.println("Command FAILED.");
            }

            choice = bot.getNextAction();
            String[] arr = choice.split(" ", 2);
            String command = arr[0];
            System.out.println(command);
            System.out.println(choice);
            if(command.equals("MOVE"))
            {
                System.out.println("BOT MOVE ");
                bot.move(choice.charAt(5));
            }
            else
            {
                bot.setLastMapMemory(bot.look('B'));
            }

            printMap();
        }
    }

    public void printMap()
    {
        for (char[] x : map.getMap())
        {
            for (char y : x)
            {
                System.out.print(y);
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        System.out.println("Welcome to the Dungeon of Doom!");
        GameLogic game = new GameLogic();
        System.out.println("Here are the game protocol commands:");
        System.out.println("|---------|-------------------------------------------------|");
        System.out.println("| Command | Action                                          |");
        System.out.println("|---------|-------------------------------------------------|");
        System.out.println("| HELLO   | Total amount of gold required to win            |");
        System.out.println("| GOLD    | Displays current gold owned                     |");
        System.out.println("| PICKUP  | Picks up the gold in given location             |");
        System.out.println("| MOVE    | Move <direction>. Direction can be N, S, E or W.|");
        System.out.println("| LOOK    | Show the map around the player                  |");
        System.out.println("| QUIT    | Ends the game                                   |");
        System.out.println("|---------|-------------------------------------------------|");
        System.out.println("Enter above commands to play the game :>");
        game.play();
    }
}