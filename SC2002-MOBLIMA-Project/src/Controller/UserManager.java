package Controller;

import Entity.CentralManagerEY;
import Entity.MovieGoerEY;
import Entity.UserEY;
import Entity.StaffEY;
import Enum.UserTypeEN;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * A UserManager object
 * <p>
 * A <code>UserManager</code> object contains all the parameters and methods required
 * to communicate between entity and boundary of User Class
 * </p>
 */

public class UserManager extends Manager implements BaseManager {

    /**
     * A CentralManagerEY object
     */
    private CentralManagerEY centralManager;
    /**
     * an arraylist of master users
     */
    private ArrayList<UserEY> masterUsers;
    /**
     * an arraylist of master movie goers
     */
    private ArrayList<MovieGoerEY> masterMovieGoers;

    /**
     * An IO manager object
     */
    private IoManager ioManager;

    /**
     * Constructor for user manager
     */
    public UserManager() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setManagers() {
        this.ioManager = this.getCentralManager().getIoManager();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMasterLists() {
        CentralManagerEY centralMgr = this.getCentralManager();
        this.masterUsers = centralMgr.getMasterUsers();
        this.masterMovieGoers = centralMgr.getMasterMovieGoers();
    }

    public UserEY getUserByUserName(String userName) { // iterates over masterUsers and checks list, returns user if user with matching name found, else returns null
        for (UserEY user: this.masterUsers) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    public void registerStaff(String userName, String password) {
        StaffEY staff = new StaffEY(userName, password);
        this.getCentralManager().getMasterUsers().add(staff);
    }

    public boolean userExists(String userName) {
        return this.getUserByUserName(userName) != null;
    }

    /**
     * A method to prime User data from file
     *
     * @throws IOException If there's IO error
     */
    public void primeUser() throws IOException {
        String userSEPARATOR = "|";
        String bookingsEPERATOR = "~";
        String filename = this.getCentralManager().getDataFolder().concat("Users.txt");
        ArrayList stringArray = null;
        try {
            stringArray = (ArrayList) this.ioManager.read(filename);
        } catch (FileNotFoundException e) {
            System.out.println("Priming of User objects is skipped as there is no master data");
            return;
        }
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, userSEPARATOR);// pass in the string to the string tokenizer
            // using delimiter ","
            String userID = star.nextToken().trim(); // first token
            String userName = star.nextToken().trim();
            String userType = star.nextToken().trim();
            if (userType.equals(UserTypeEN.STAFF.toString())) {
                String password = star.nextToken().trim();// first token
                StaffEY staff = new StaffEY(userID, userName, password);
                this.masterUsers.add((UserEY) staff);

            } else {
                String emailID = star.nextToken().trim();
                String mobileNumber = star.nextToken().trim();
                int movieGoerAge = Integer.parseInt(star.nextToken().trim());
                ArrayList<String> bookings = new ArrayList<String>();
                String bookingIDString = star.nextToken().trim();
                StringTokenizer bookingToken = new StringTokenizer(bookingIDString, bookingsEPERATOR);
                while (bookingToken.hasMoreTokens()) {
                    bookings.add(bookingToken.nextToken().trim());
                }
                MovieGoerEY movieGoer = new MovieGoerEY(userID, userName, emailID, mobileNumber, movieGoerAge,
                        bookings);
                this.masterMovieGoers.add(movieGoer);
                this.masterUsers.add((UserEY) movieGoer);

            }

        }
    }


    /**
     * A method to write user data to a text file
     *
     * @throws IOException If there's IO error
     */
    public void writeUser() throws IOException {
        String userSEPARATOR = " | ";
        String bookingSEPARATOR = " ~ ";
        String filename = this.getCentralManager().getDataFolder().concat("Users.txt");
        List alw = new ArrayList();
        ArrayList<String> bookings;
        UserEY user;
        for (int i = 0; i < this.masterUsers.size(); i++) {
            user = this.masterUsers.get(i);
            StringBuilder st = new StringBuilder();
            st.append(user.getUserID().trim());
            st.append(userSEPARATOR);
            st.append(user.getUserName().trim());
            st.append(userSEPARATOR);
            String userType = user.getUserType().toString().trim();
            st.append(userType);
            st.append(userSEPARATOR);
            if (userType.equals(UserTypeEN.STAFF.toString())) {
                StaffEY staff = (StaffEY) user;
                st.append(staff.getPassword().trim());
            } else {
                MovieGoerEY movieGoer = (MovieGoerEY) user;
                st.append(movieGoer.getEmailID().trim());
                st.append(userSEPARATOR);
                st.append(movieGoer.getMobileNumber());
                st.append(userSEPARATOR);
                st.append(Integer.toString(movieGoer.getAge()));
                st.append(userSEPARATOR);
                ArrayList<String> bookingIDs = movieGoer.getBookings();
                for (String bookingID : bookingIDs) {
                    st.append(bookingID);
                    st.append(bookingSEPARATOR);
                }
            }

            alw.add(st.toString());

        }
        ioManager.write(filename, alw);
        ;

    }

}
