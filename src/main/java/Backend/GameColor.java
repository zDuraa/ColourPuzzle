package Backend;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class GameColor {

    List<Color> colors = new ArrayList<>();
    public GameColor()
    {
        // Liste mit Farben definieren

        colors.add(Color.WHITE);
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
    }

    public Color getColor(int id)
    {
        return colors.get(id);
    }
}
