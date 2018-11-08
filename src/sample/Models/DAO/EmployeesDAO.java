package sample.Models.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import sample.Models.Employees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDAO
{
    Connection conn;

    private static ObservableList<Employees> data = FXCollections.observableArrayList();

    public EmployeesDAO(Connection conn) { this.conn = conn; }

    public static void addTransaction(Employees employee)
    {
        data.add(employee);
    }


    public List<Employees> findAll()
    {
        List<Employees> employees = new ArrayList<Employees>();

        try {
            String query = "SELECT * FROM Employee";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Employees p = null;
            while(rs.next())
            {
                p = new Employees
                        (
                                rs.getInt("cveEmployee"),
                                rs.getString("name"),
                                rs.getString("password")
                        );
                employees.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return employees;
    }


    public sample.Models.Employees fetchByUserAndPassword(String username, String password)
    {
        ResultSet rs = null;
        sample.Models.Employees e = null;
        try {
            String query = "SELECT * FROM Employees where username = " + "'" + username + "' and password = "+ "'" + password + "'";
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);

            if (rs.first())
            {
                e = new sample.Models.Employees(
                        rs.getInt("cveEmployee"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }

        } catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("Error al recuperar información..." + ex.getMessage());
        }

        return e;
    }
}
