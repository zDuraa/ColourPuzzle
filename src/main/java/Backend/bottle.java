package Backend;

public class bottle {

    int[] bot = new int[4];

    public bottle(int layer1, int layer2, int layer3, int layer4)
    {
        bot[0] = layer1;
        bot[1] = layer2;
        bot[2] = layer3;
        bot[3] = layer4;
    }

    public int checkTop() //returns array pos of highest filled layer in bottle. if bottle is empty returns -1
    {
        int ret = -1;
        for (int i = 0; i < 4; i++)
        {
            if (bot[i] != 0)
            {
                ret++;
            }
        }
        return ret;
    }

    public int checkColourSize(int layer)   // returns size of colour of the given layer
    {
        int ret = 1;
        for (int i = 1; (layer-i) > -1; i++)
        {
            if (bot[layer] != bot[layer-i])
            {
                break;
            }
            ret++;
        }
        return ret;
    }


    public void setColourId(int layer, int id)
    {
        bot[layer] = id;
    }

    public int getColourId(int layer)
    {
        return bot[layer];
    }


}
