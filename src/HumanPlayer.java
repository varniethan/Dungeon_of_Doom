public class HumanPlayer extends Player
{
    protected int goldOwned;
    protected char previousChar;
    public HumanPlayer(int[] currentPosition, Map map)
    {
        super(currentPosition, map);
        this.goldOwned = 0;
    }

    public int getGoldOwned()
    {
        return this.goldOwned;
    }

    public void setGoldOwned()
    {
        this.goldOwned += 1;
    }
    public int pickup()
    {
//        int[] position = this.getCurrentPosition();
        if(this.previousChar == 'G')
        {
            this.setGoldOwned();
            this.previousChar = '.';
            return this.getGoldOwned();
        }
        else
        {
            return -1;
        }
    }

    public boolean move(char direction)
    {
        this.setCurrentPosition(map.getCharPosition('P'));
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
            int[] currentPlayerPosition = map.getCharPosition('P');
            char replaceChar = '.';
            if(this.previousChar == 'G' || this.previousChar == 'E')
            {
                replaceChar = this.previousChar;
            }
            this.previousChar = map.getCharAtPosition(newPlayerPosition[0], newPlayerPosition[1]);
            System.out.println(this.previousChar);
            map.updateMap(newPlayerPosition, currentPlayerPosition, replaceChar, 'P');
            return true;
        }
        else
        {
            return false;
        }
    }
}
