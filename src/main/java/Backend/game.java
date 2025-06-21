package Backend;

public class game {
    int num;
    bottle[] jug;

    public game(int num){
        this.num = num;
        this.jug = new bottle[num+1];

    }

    public void fillJug()
    {
        for (int i = 0; i < num+1;i++)
        {
            this.jug[i] = new bottle(0, 0, 0, 0);
        }
    }

    public bottle[] getJug() {
        return jug;
    }

    public void moveColour(bottle source, bottle target)
    {
        int src = source.checkTop();
        if (src != -1)
        {
            int size = source.checkColourSize(src);
            int fill = target.checkTop();
            if (size <= 4-fill )
            {
                for (int i = 0; i < size; i++)
                {
                    target.setColourId(fill+1+i, source.getColourId(src-i));
                    source.setColourId(src-i, 0);
                }
            }
        }
    }

    public void setJug(bottle[] jug) {
        this.jug = jug;
    }

    public void checkJug()
    {
        for (int j = 3; j > -1 ; j--)
        {
            for (int i = 0; i < num+1 ; i++ )
            {
                System.out.print("|"+jug[i].bot[j]+"| ");
            }
            System.out.println("");
        }
    }
}
