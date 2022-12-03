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
}
