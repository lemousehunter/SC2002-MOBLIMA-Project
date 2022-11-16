package Boundary;

import Controller.LoginManager;
import Entity.MovieGoerEY;
import Entity.StaffEY;

public class LoginBoundary extends Boundary implements BaseBoundary{
    LoginManager loginManager;
    @Override
    public void setManagers() {
        this.loginManager = this.getCentralManager().getLoginManager();
    }

    @Override
    public void setBoundaries() {

    }

    public String getUserName() {
        return this.getInputLine("Please enter your Username: ");
    }

    public String getPassword() {
        return this.getInputLine("Please enter your Password: ");
    }

    public boolean getLoginSuccessStaff() {
        String userName = this.getUserName();
        String password = this.getPassword();

        StaffEY staff = this.loginManager.loginStaff(userName, password);
        if (staff != null) {
            this.println("Logged in successfully!! Welcome " + userName);
            return true;
        }
        else {
            this.println("Login Unsuccessful. Please recheck your username or password.");
        }
        return false;
    }

    public MovieGoerEY getLoginSuccessMovieGoer() {
        String userName = this.getUserName();

        MovieGoerEY movieGoer = (MovieGoerEY) this.loginManager.loginUser(userName);
        if (movieGoer != null) {
            this.println("Logged in successfully!! Welcome " + userName);
            return movieGoer;
        }
        else {
            this.println("Login Unsuccessful. Please recheck your username.");
        }
        return movieGoer;
    }


}
