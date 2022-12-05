import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Reads and contains in memory the map of the game.
 *
 */
/*
Important:
in 2D array: [0] is row and [1] is column
in x-y plane. x is always horizontal and y is always vertical
 */
public class Map {

    /* Representation of the map */
    private char[][] map;

    /* Map name */
    private String mapName;

    /* Gold required for the human player to win */
    private int goldRequired;

    private int lengthVertical;

    private int lengthHorizontal;


    /**
     * Default constructor, creates the default map "Very small Labyrinth of doom".
     */
    public Map() {
        this.mapName = "Very small Labyrinth of Doom";
        this.goldRequired = 2;
        this.map = new char[][]{
                {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
                {'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
                {'#','.','.','.','.','.','.','G','.','.','.','.','.','.','.','.','.','E','.','#'},
                {'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
                {'#','.','.','E','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
                {'#','.','.','.','.','.','.','.','.','.','.','.','G','.','.','.','.','.','.','#'},
                {'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
                {'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
                {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}
        };
        this.lengthVertical = map.length;
        this.lengthHorizontal = map[0].length;
    }

    /**
     * Constructor that accepts a map to read in from.
     *
     * @param : The filename of the map file.
     */
    public Map(String fileName) {
        readMap(fileName);
    }


    /**
     * Reads the map from file.
     *
     * @param : Name of the map's file.
     */
    public void readMap(String fileName) {
        try
        {
            String line;
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader("./src/maps/"+fileName));
                this.mapName = (br.readLine()).substring(5);
                this.goldRequired = Integer.parseInt(String.valueOf(br.readLine().charAt(4)));
//                System.out.println( this.mapName);
//                System.out.println(this.goldRequired);
                ArrayList<String> fileLines = new ArrayList<>();
                this.lengthVertical = 0;
                this.lengthHorizontal = 0;
                while ((line = br.readLine()) != null)
                {
                    lengthVertical++;
                    lengthHorizontal = line.length();
                    fileLines.add(line);
                }
                map = new char[lengthVertical][lengthHorizontal];
                for(int i = 0; i < lengthVertical; i++)
                {
                    for(int j = 0; j < lengthHorizontal; j++)
                    {
                        map[i][j] = fileLines.get(i).charAt(j);
                    }
                }
            }
            catch (FileNotFoundException e)
            {
                System.out.println("FileNotFoundException");
            }
            catch (IOException e)
            {
                System.out.println("IOException 1");
            }
            finally {
                if (br != null)
                {
                    br.close();
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("IOException 2");
        }
    }

    public int getGoldRequired()
    {
        return goldRequired;
    }

    public int[] randomPlayerPosition(char playerType)
    {
        int x = ThreadLocalRandom.current().nextInt(0, lengthHorizontal);
        int y = ThreadLocalRandom.current().nextInt(0, lengthVertical);
        int[] randomPlayerPosition =  new int[2];
        while (map[y][x] == 'G' || map[y][x] == 'E'||map[y][x] == '#')
        {
            x = ThreadLocalRandom.current().nextInt(0, lengthHorizontal);
            y = ThreadLocalRandom.current().nextInt(0, lengthVertical);
        }
        if (map[y][x] != 'G' || map[y][x] != 'E'||map[y][x] != '#')
        {
            randomPlayerPosition[0] = y;
            randomPlayerPosition[1] = x;
            map[y][x] = playerType;
            return randomPlayerPosition;
        }
        return null;
    }
    public int[] getCharPosition(char playerType)
    {
        int[] charPosition =  new int[2];
        for(int i = 0; i < lengthVertical; i++)
        {
            for(int j = 0; j < lengthHorizontal; j++)
            {
                if(map[i][j] == playerType)
                {
                    charPosition[0] = i;
                    charPosition[1] = j;
                    return charPosition;
                }
            }
        }
        return null;
    }

    public int[] getBotPosition()
    {
        int[] playerPosition =  new int[2];
        for(int i = 0; i < lengthVertical; i++)
        {
            for(int j = 0; j < lengthHorizontal; j++)
            {
                if(map[i][j] == 'B')
                {
                    playerPosition[0] = i;
                    playerPosition[1] = j;
                    return playerPosition;
                }
            }
        }
        return null;
    }

    public char getChatAtPosition(int y, int x)
    {
        return map[y][x];
    }

    public int getLengthVertical()
    {
        return this.lengthVertical;
    }

    public int getLengthHorizontal()
    {
        return this.lengthHorizontal;
    }

    public int[] updateMap(int[] newPosition, int[] currentPlayerPosition, char replaceChar, char playerType)
    {
        int x = newPosition[1];
        int y = newPosition[0];
        if (map[y][x] != '#')
        {
            map[currentPlayerPosition[0]][currentPlayerPosition[1]] = replaceChar;
            map[y][x] = playerType;
            currentPlayerPosition[0] = y;
            currentPlayerPosition[1] = x;
            return currentPlayerPosition;
        }
        return newPosition;
    }

    public void replaceChar(int[] position, char replaceChar)
    {
        map[position[0]][position[1]] = replaceChar;
    }

    public char getCharAtPosition(int y, int x)
    {
        return map[y][x];
    }


    public boolean isValidMove(int[] newPosition)
    {
        if (map[newPosition[0]][newPosition[1]] == '#')
        {
            return false;
        }
        return true;
    }

    public boolean isGoldPresent(int[] position)
    {
        if (map[position[0]][position[1]] == 'G')
        {
            return true;
        }
        return false;
    }

    public void updateChar(char character, int[] position)
    {
        map[position[0]][position[1]] = character;
    }

    public char[][] getMap() {
        return map;
    }

    public char[][] getBotMap()
    {
        char[][] lookMap = new char[5][5];
        int x = this.getCharPosition('B')[1];
        int y = this.getCharPosition('B')[0];
        if (x-2 < 0)
        {
            x = 2;
        }
        if (x+2 > (this.getLengthHorizontal()-1))
        {
            x = (this.getLengthHorizontal()-1) -2;
        }
        if (y-2 < 0)
        {
            y = 2;
        }
        if (y+2 > (this.getLengthVertical()-1))
        {
            y = (this.getLengthVertical()-1) -2;
        }
        int row = 0;
        int column = 0;
        for (int i = (y-2); i <= (y+2); i++)
        {
            column = 0;
            for (int j = (x-2); j <= (x+2); j++)
            {
                lookMap[row][column] = this.getChatAtPosition(i,j);
                column += 1;
            }
            row += 1;
        }
        return lookMap;
    }
}
