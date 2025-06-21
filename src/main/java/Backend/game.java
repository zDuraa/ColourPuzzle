package Backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            int tarTop = target.checkTop();
            if (size < 4 - tarTop )
            {
                for (int i = 0; i < size; i++)
                {
                    target.setColourId(tarTop+1+i, source.getColourId(src-i));
                    source.setColourId(src-i, 0);
                }
            }
        }

    }



    public void initialize ()
    {
        List<Integer> number = new ArrayList<>();
        for (int i = 1 ; i < num+1; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                number.add(i);
            }
        }
        Collections.shuffle(number);
        int q = 0;
        for (int i = 0 ; i < num; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                jug[i].setColourId(j,number.get(q));
                q++;
            }
        }
        //check if already solved(win condition)
    }

    public void checkJug()
    {
        for (int j = 3; j > -1 ; j--)
        {
            for (int i = 0; i < num+1 ; i++ )
            {
                System.out.print("|"+jug[i].bot[j]+"| ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public boolean winCon() {
        int fullCount = 0;

        for (bottle bottle : jug) {
            int b0 = bottle.bot[0];
            int b1 = bottle.bot[1];
            int b2 = bottle.bot[2];

            if (b0 == b1 && b0 == b2) {
                fullCount++;
            } else {
                // Glas ist weder leer noch korrekt gefüllt → kein Sieg möglich
                return false;
            }
        }

        return fullCount == num+1 ;
    }

}
