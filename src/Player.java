public class Player {

    protected int[] currentPosition;


    protected Map map;
    public Player(int[] currentPosition, Map map)
    {

        this.currentPosition = currentPosition;
        this.map = map;

    }

    public int[] getCurrentPosition()
    {
        return this.currentPosition;
    }

    public void setCurrentPosition(int[] newPosition)
    {
        this.currentPosition = newPosition;
    }





    public char[][] look(char charToLook)
    {
        char[][] lookMap = new char[5][5];
        this.setCurrentPosition(map.getCharPosition(charToLook));
//        System.out.println(map.getPlayerPosition()[0]);
//        System.out.println(map.getPlayerPosition()[1]);
        int x = map.getCharPosition(charToLook)[1];
        int y = map.getCharPosition(charToLook)[0];
        if (x-2 < 0)
        {
            x = 2;
        }
        if (x+2 > (map.getLengthHorizontal()-1))
        {
            x = (map.getLengthHorizontal()-1) -2;
        }
        if (y-2 < 0)
        {
            y = 2;
        }
        if (y+2 > (map.getLengthVertical()-1))
        {
            y = (map.getLengthVertical()-1) -2;
        }
        int row = 0;
        int column = 0;
        for (int i = (y-2); i <= (y+2); i++)
        {
            column = 0;
            for (int j = (x-2); j <= (x+2); j++)
            {
                lookMap[row][column] = map.getChatAtPosition(i,j);
                column += 1;
            }
            row += 1;
        }
        return lookMap;
    }
}
