module com.example.project02_clark {
    requires javafx.controls;
    requires javafx.fxml;
    requires jbcrypt;


    opens com.example.project02_clark to javafx.fxml;
    exports com.example.project02_clark;
}