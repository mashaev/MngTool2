package sample;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class AppendCourseView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TextField txtCoursePrice;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnRefresh;

    @FXML
    private TableView<Course> tbCourses;

    @FXML
    private TableColumn<Course, String> clmnID;

    @FXML
    private TableColumn<Course, String> clmnName;

    @FXML
    private TableColumn<Course, Integer> clmnPrice;

    @FXML
    void initialize() {

        DbHandler dbHandler = new DbHandler();


        clmnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        ObservableList<Course> list = FXCollections.observableArrayList(dbHandler.getCourses());
        tbCourses.setItems(list);

        btnOk.setOnAction(event -> {
            appendCourse();
        });

        btnRefresh.setOnAction(event -> {
            DbHandler db = new DbHandler();
            db.ShowCourses();
        });
    }

    private void appendCourse(){
        String name = txtCourseName.getText();
        int price = Integer.parseInt(txtCoursePrice.getText());

        DbHandler db = new DbHandler();
        db.appendCourse(name, price);
        System.out.println("OK");

        txtCourseName.clear();
        txtCoursePrice.clear();
    }
}
