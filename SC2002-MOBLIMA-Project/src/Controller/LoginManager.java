package Controller;

import Entity.StaffEY;
import Entity.UserEY;

import java.util.ArrayList;

public class LoginManager extends Manager implements BaseManager {
    ArrayList<UserEY> masterUsers;
    UserManager userManager;

    @Override
    public void setManagers() {
        this.userManager = this.getCentralManager().getUserMgr();
    }

    @Override
    public void setMasterLists() {
        this.masterUsers = this.getCentralManager().getMasterUsers();
    }

    public UserEY loginUser(String username) {
        try {
            return this.userManager.getUserByUserName(username);
        } catch (ClassCastException e) {
            return null;
        }
    }

    public StaffEY loginStaff(String username, String password) {
        try {
            StaffEY staff = (StaffEY) this.userManager.getUserByUserName(username);
            if (staff != null) {
                if (staff.getPassword().equals(password)) {
                    return staff;
                }
            }
            return null;
        } catch (ClassCastException e) {
            return null;
        }
    }
}
