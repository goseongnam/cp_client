module com.example.javafx_practice {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.desktop;
    opens com.example.javafx_practice to javafx.fxml;
    exports com.example.javafx_practice;
    exports com.example.javafx_practice.item;
    opens com.example.javafx_practice.item to javafx.fxml;
}