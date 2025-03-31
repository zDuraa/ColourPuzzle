package Backend;

public class bottle {

    colour[] bot = new colour[4];

    public bottle(colour layer1, colour layer2, colour layer3, colour layer4)
    {
        bot[0] = layer1;
        bot[1] = layer2;
        bot[2] = layer3;
        bot[3] = layer4;
    }

    public int moveColour(bottle source, bottle target)
    {
        return 0;
    }

    public int checkSrc(bottle src) //returns array pos of highest filled layer in bottle. if bottle is empty returns -1
    {
        int ret = -1;
        for (int i = 0; i < 4; i++)
        {
            if (src.bot[i].getColourId() != 0)
            {
                ret++;
            }
        }
        return ret;
    }

    public int checkTar(bottle tar)     // returns number of free layers in the bottle
    {
        int ret = 0;
        for (int i = 3; i > -1; i--)
        {
            if (tar.bot[i].getColourId() == 0)
            {
                ret++;
            }
        }
        return ret;
    }

    public int checkColourSize(bottle src, int layer)   // returns size of colour of the given layer
    {
        int ret = 1;
        for (int i = 1; (layer-i) > -1; i++)
        {
            if (src.bot[layer].getColourId() != src.bot[layer-i].getColourId())
            {
                break;
            }
            ret++;
        }
        return ret;
    }

    public colour[] getBottle()
    {
        return bot;
    }


}
