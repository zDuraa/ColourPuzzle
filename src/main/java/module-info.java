module org.example.colourpuzzle {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.colourpuzzle to javafx.fxml;
    exports org.example.colourpuzzle;
}