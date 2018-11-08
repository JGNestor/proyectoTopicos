package sample.Models;

public class Employees
{
    int cveEmployee;
    String username, password;

    public Employees () { };

    public Employees(int cveEmployee, String name, String password) {
        this.cveEmployee = cveEmployee;
        this.username = name;
        this.password = password;
    }

    public int getCveEmployee() {
        return cveEmployee;
    }

    public void setCveEmployee(int cveEmployee) {
        this.cveEmployee = cveEmployee;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "cveEmployee=" + cveEmployee +
                ", name='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
