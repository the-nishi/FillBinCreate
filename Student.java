package fxmlapplicationpkg;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class FXMLMainSceneController implements Initializable {
    
    @FXML private TextField idTxt;
    @FXML private TextField nameTxt;
    @FXML    private TextField deptTxt;
    @FXML private TextField cgpaTxt;
    @FXML private DatePicker birthdayDatePicker;
    
    @FXML private TableView<Student> tableView;
    @FXML    private TableColumn<Student, String> idColumn;
    @FXML    private TableColumn<Student, String> nameColumn;
    @FXML    private TableColumn<Student, LocalDate> birthdayColumn;
    @FXML    private TableColumn<Student, String> deptColumn;
    @FXML    private TableColumn<Student, String> cgpaColumn; 

    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        idColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Student,LocalDate>("birthday"));
        deptColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("dept"));
        cgpaColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("cgpa"));
    }    

    @FXML
    private void saveToFileButtonOnClick(ActionEvent event) {             
        Student stud = new Student(  
                    Integer.parseInt(idTxt.getText()),
                    nameTxt.getText(),
                    birthdayDatePicker.getValue(),
                    deptTxt.getText(),
                    Float.parseFloat(cgpaTxt.getText())  
                );
        idTxt.setText(null);    nameTxt.setText(null);  cgpaTxt.setText(null);  deptTxt.setText(null);
        stud.display();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream("Stud.bin", true)
            );
            oos.writeObject(stud);
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void loadTableFromFileButtonOnClick(ActionEvent event) {
        ObjectInputStream ois=null;
         try {
            Student s;
            ois = new ObjectInputStream(new FileInputStream("Stud.bin"));
            s = (Student) ois.readObject();
            tableView.getItems().add(s);
            //s = (Student) ois.readObject(); tableView.getItems().add(s);            
            
        } catch (Exception ex) {
            try {
                if(ois!=null)
                    ois.close();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        }        
    }

    @FXML
    private void idTxtOnMouseClick(MouseEvent event) {
        idTxt.setText(null);
    }
    @FXML
    private void nameTxtOnMouseClick(MouseEvent event) {
        nameTxt.setText(null);
    }
    @FXML
    private void cgpaTxtOnMouseClick(MouseEvent event) {
        cgpaTxt.setText(null);
    }

    @FXML
    private void deptTxtOnMouseClick(MouseEvent event) {
        deptTxt.setText(null);
    }
    
}
