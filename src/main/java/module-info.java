module org.example.colourpuzzle {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens org.example.colourpuzzle to javafx.fxml;
    exports org.example.colourpuzzle;
}