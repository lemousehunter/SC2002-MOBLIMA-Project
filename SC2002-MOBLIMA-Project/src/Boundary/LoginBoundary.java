package Boundary;

import Controller.LoginManager;
import Controller.MovieGoerManager;
import Controller.UserManager;
import Entity.MovieGoerEY;
import Entity.StaffEY;

public class LoginBoundary extends Boundary implements BaseBoundary{
    LoginManager loginManager;
    UserManager userManager;
    MovieGoerManager movieGoerManager;
    @Override
    public void setManagers() {
        this.loginManager = this.getCentralManager().getLoginManager();
        this.movieGoerManager = this.getCentralManager().getMovieGoerMgr();
        this.userManager = this.getCentralManager().getUserMgr();
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

    public void registerStaff() {
        String userName = this.getInputLine("Please enter your Username: ");
        if (this.userManager.userExists(userName)) {
                System.out.println("Failed to register staff as " + userName + "  Already Registered");
                return;
        }
        String password = this.getInputLine("Please enter your Password: ");
        this.userManager.registerStaff(userName, password);
        System.out.println(userName + " Successfully Registered");
    }

    public void registerMovieGoer() {
        String userName = this.getInputLine("Enter UserName: ");

        if (this.movieGoerManager.movieGoerExist(userName)) {
            System.out.println("\n" + userName + "  Already Registered");
            return;
        }

        String emailID = this.getInputLine("Enter Email ID: ");
        String  mobileNumber = this.getInputLine("Enter Mobile #: ");
        int  age  = this.getInputInt("Enter Age     : ");
        this.movieGoerManager.registerMovieGoer(userName, emailID, mobileNumber, age);

        System.out.println("\n" + userName + " Successfully Registered");
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
        return null;
    }


}
