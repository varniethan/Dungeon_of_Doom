import java.util.*;
import java.lang.Math;
public class Bot extends Player{

    private char[][] lastMapMemory;
    private int[] botCoordinates;
    private int[] playerCoordinates;
    private int[] goldCoordinates;
    private boolean needToLook;
    private int lookCounter;
    private boolean playerFlag;
    private boolean goldFlag;
    private char[] directions;


    public Bot(int[] currentPosition, Map map)
    {
        super(currentPosition, map);
        this.lastMapMemory = super.look('B');
        this.lookCounter = 0;
        this.directions = new char[]{'N', 'S', 'E', 'W'};
    }

    public char[][] getLastMapMemory() {
        return this.lastMapMemory;
    }

    public void setLastMapMemory(char[][] lastMap)
    {
        this.lastMapMemory = lastMap;
    }

    public String getNextAction()
    {
        if (needToLook || lookCounter >= 5)
        {
            lookCounter = 0;
            return "LOOK";
        }
        else
        {
            lookCounter ++;
            return "MOVE " + getMoveDirection();
        }
    }

    /*
     * Three cases:
     *If it has seen a player, moves towards them
     *continue in the direction it was already going
     *move in a new random direction
     */


    public boolean move(char direction)
    {
        this.setCurrentPosition(map.getCharPosition('B'));
        int[] newPlayerPosition = new int[2];
        int[] oldPlayerPosition = this.getCurrentPosition();
        switch (direction)
        {
            case 'N':
                newPlayerPosition[0] = oldPlayerPosition[0] - 1;
                newPlayerPosition[1] = oldPlayerPosition[1];
                break;
            case 'S':
                newPlayerPosition[0] = oldPlayerPosition[0] + 1;
                newPlayerPosition[1] = oldPlayerPosition[1];
                break;
            case 'E':
                newPlayerPosition[0] = oldPlayerPosition[0];
                newPlayerPosition[1] = oldPlayerPosition[1] + 1;
                break;
            case 'W':
                newPlayerPosition[0] = oldPlayerPosition[0];
                newPlayerPosition[1] = oldPlayerPosition[1] - 1;
                break;
            default:
                newPlayerPosition[0] =  -1;
                newPlayerPosition[1] = -1;
        }
        if(map.isValidMove(newPlayerPosition))
        {
            int[] currentPlayerPosition = map.getCharPosition('B');
            char replaceChar = '.';
            map.updateMap(newPlayerPosition, currentPlayerPosition, replaceChar, 'B');
            return true;
        }
        else
        {
            return false;
        }
    }

    public char getMoveDirection()
    {
        //move wiht no obstacles
        if(playerFlag)
        {
            int verticalDifference = this.botCoordinates[0] - this.playerCoordinates[1];
            int horizontalDifference = this.botCoordinates[0] - this.playerCoordinates[1];
            if(Math.abs(verticalDifference) <= Math.abs(horizontalDifference))
            {
                if(verticalDifference < 0)
                {
                    return 'S';
                }
                else
                {
                    return 'N';
                }
            }
            else
            {
                if(horizontalDifference < 0)
                {
                    return 'E';
                }
                else
                {
                    return 'W';
                }
            }
        }
        else
        {
            Random random = new Random();
            int randomIndex = random.nextInt(this.directions.length);
            return this.directions[randomIndex];
        }

    }


    public void scan()
    {
        for(int y=0; y<= 4; y++)
        {
            for (int x=0; x<=4; x++)
            {
                if(this.lastMapMemory[y][x] == 'P')
                {
                    this.playerFlag = true;
                    this.playerCoordinates[0] = x;
                    this.playerCoordinates[1] = y;
                }
                if(this.lastMapMemory[y][x] == 'G')
                {
                    this.goldFlag = true;
                    this.goldCoordinates[0] = x;
                    this.goldCoordinates[1] = y;
                }
                if(this.lastMapMemory[y][x] == 'B')
                {
                    this.botCoordinates[0] = x;
                    this.botCoordinates[1] = y;
                }
            }
        }
    }

}
