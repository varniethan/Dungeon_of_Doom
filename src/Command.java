public class Command {


    public static void hello(int goldRequired)
    {
        System.out.println("Gold to win: "+ goldRequired);
    }

    public static void gold(int goldOwned)
    {
        System.out.println("Gold owned: "+goldOwned);
    }

    public static void pickup(int newGoldOwned)
    {
        if (newGoldOwned != -1)
        {
            System.out.println("SUCCESS: Gold owned " + newGoldOwned);
        }
        else
        {
            System.out.println("FAIL");
        }
    }

    public static void move(boolean success)
    {
        if(success)
        {
            System.out.println("SUCCESS");
        }
        else
        {
            System.out.println("FAIL");
        }
    }

    public static void look(char[][] map)
    {
        for(char[] x : map)
        {
            for (char y : x)
            {
                System.out.print(y);
            }
            System.out.println();
        }
    }

    public static void quit(boolean win)
    {
        if(win)
        {
            System.out.println("WIN");
        }
        else
        {
            System.out.println("LOST");
        }
        System.exit(1);
    }

    public static void printValidCommands()
    {
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
    }
}
