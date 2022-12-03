/**
 * Runs the game with a human player and contains code needed to read inputs.
 *
 */
public class HumanPlayer extends Player
{
    protected int goldOwned;
    public HumanPlayer(int[] currentPosition)
    {
        super(currentPosition);
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
    public int[] move(char direction, char previousChar, Map map)
    {
        int[] newPlayerPosition = new int[2];
        int[] oldPlayerPosition = this.getCurrentPosition();
        switch (direction) {
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
        return newPlayerPosition;
    }


    public int pickup(char previousChar)
    {
        int[] position = this.getCurrentPosition();
        if(previousChar == 'G')
        {
            this.setGoldOwned();
            return this.getGoldOwned();
        }
        else
        {
            return -1;
        }
    }
}
