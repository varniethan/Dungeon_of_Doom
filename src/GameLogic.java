import javax.print.DocFlavor;
import java.io.*;
import java.util.*;

/**
 * Contains the main logic part of the game, as it processes.
 *
 */
public class GameLogic {

    /* Reference to the map being used */
    protected Map map;

    protected HumanPlayer player;
    protected Bot bot;
//    private int[] playerPosition;
    public char previousChar;

    /**
     * Default constructor
     */
    public GameLogic() {
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

    /**
     * Get file choice
     *
     * @return : integer choice of the file.
     */
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
        int option;
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

    public String userInput()
    {
        Scanner userInput = new Scanner(System.in);
        String command = userInput.nextLine().trim().toUpperCase();
        return command;
    }

    /**
     * Set up the game
     *
     * @return : true if all set up correctly.
     */
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
        player = new HumanPlayer(map.randomPlayerPosition('P'));
        bot = new Bot(map.randomPlayerPosition('B'), map.getBotMap());
        player.setCurrentPosition(map.getCharPosition('P'));
        bot.setCurrentPosition(map.getCharPosition('B'));
    }


    public boolean moveCommand(char playerType, char direction) {
        int[] newPlayerPosition = player.move(direction, this.previousChar, this.map);
        if(map.isValidMove(newPlayerPosition))
        {
            this.movePlayer(newPlayerPosition);
            return true;
        }
        else
        {
            return false;
        }
    }

    public int pickupCommand(char playerType)
    {
        int newGold = player.pickup(this.previousChar);
        if(newGold >= 0)
        {
            this.previousChar = '.';
            return newGold;
        }
        else
        {
            return -1;
        }
    }

    public void movePlayer(int[] newPosition)
    {
        int[] currentPlayerPosition = map.getCharPosition('P');
        char replaceChar = '.';
        if(this.previousChar == 'G' || this.previousChar == 'E')
        {
            replaceChar = this.previousChar;
        }
        this.previousChar = map.getChatAtPosition(newPosition[0], newPosition[1]);
        map.updateMap(newPosition, currentPlayerPosition, replaceChar);
    }

    public boolean checkWinningCondition()
    {
        return player.getGoldOwned() == map.getGoldRequired();
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

//
//    public static void main(String[] args) {
//        GameLogic logic = new GameLogic();
//    }
}

