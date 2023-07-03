module com.yashkumarroy.connect4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;

    opens com.yashkumarroy.connect4 to javafx.fxml;
    exports com.yashkumarroy.connect4;
}