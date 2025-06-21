package Backend;
import javafx.scene.paint.Color;

public class GameColor {

    int arrValue;
    Color cColor;
    public GameColor(int value, Color color)
    {
       this.arrValue = value;
       this.cColor = color;
    }

    public int getArrValue() {
        return arrValue;
    }

    public Color getColor() {
        return cColor;
    }
}
