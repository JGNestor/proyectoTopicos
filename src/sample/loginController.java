package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import sample.Models.DAO.EmployeesDAO;
import sample.Models.Employees;
import sample.Models.MySQL;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    @FXML JFXButton log, exit;
    @FXML JFXPasswordField pass;
    @FXML JFXTextField uname;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.setOnAction(logger);
        exit.setOnAction(stop);
    }

    boolean adminLog, correctLogin = false;
    Employees employees = new Employees();
    EmployeesDAO employeesDAO = new EmployeesDAO(MySQL.getConnection());

    //login según usuario
    private EventHandler<ActionEvent> logger = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            if (validateLogin())
            {
                if (validateUser())
                {
                    showAdminInterface(event);
                } else {
                    showEmployeeInterface(event);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Wrong user/password");
                alert.show();
            }
        }
    };

    //cerrar programa antes del login
    private EventHandler<ActionEvent> stop = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            System.exit(0);
        }
    };

    //validar usuario
    private boolean validateLogin()
    {
        String usr = uname.getText(), ps = pass.getText();
        employees = employeesDAO.fetchByUserAndPassword(usr, ps);

        if (employees != null)
        {
            correctLogin = true;
        }
        return correctLogin;
    }

    //logeo segun usuario
    private boolean validateUser()
    {
        String usr = uname.getText(), ps = pass.getText();
        employees = employeesDAO.fetchByUserAndPassword(usr, ps);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");

        if (usr.equals("admin"))
        {
            adminLog = true;
            alert.setContentText("Successful login as: \nAdministrator");
            alert.showAndWait();
        } else {
            adminLog = false;
            alert.setContentText("Successful login as: \nRegistered employee");
            alert.showAndWait();
        }
        return adminLog;
    }

    //cargado de interfaz de administrador
    private void showAdminInterface(ActionEvent event)
    {
        try
        {
            Stage administratorInterface = new Stage();
            administratorInterface.setTitle("Administrator Interface");
            Parent root = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Models/UserFormat/adminFormat.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            //scene.getStylesheets().add("bootstrapfx.css");
            administratorInterface.setScene(scene);
            administratorInterface.setMaximized(true);
            administratorInterface.setResizable(false);
            administratorInterface.show();
            (((Button) event.getSource()).getScene().getWindow()).hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //cargado de interfaz de empleado
    private void showEmployeeInterface(ActionEvent event)
    {
            try
            {
                Stage employeeInterface = new Stage();
                employeeInterface.setTitle("Employees Interface");
                Parent root = null;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Models/UserFormat/employeeFormat.fxml"));
                root = loader.load();
                Scene scene = new Scene(root);
                //scene.getStylesheets().add("bootstrapfx.css");
                employeeInterface.setScene(scene);
                employeeInterface.setMaximized(true);
                employeeInterface.setResizable(false);
                employeeInterface.show();
                (((Button) event.getSource()).getScene().getWindow()).hide();
            }
            catch (IOException e) { e.printStackTrace(); }
    }
}
