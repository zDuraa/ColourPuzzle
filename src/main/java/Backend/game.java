package Backend;

public class game {
    int num;
    bottle[] jug;

    public game(int num){
        this.num = num;
        this.jug = new bottle[num+1];

    }

    public void fillJug(int num)
    {
        for (int i = 0; i < num+1;i++)
        {
            this.jug[i] = new bottle(new colour(0),
                                     new colour(0),
                                     new colour(0),
                                     new colour(0));
        }
    }



    public bottle[] getJug() {
        return jug;
    }

    public void setJug(bottle[] jug) {
        this.jug = jug;
    }
}
