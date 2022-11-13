import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserManager extends Manager implements BaseManager {

  private CentralManagerEY centralManager;
  private ArrayList<User> masterUsers;

  private IoManager ioManager;

  public UserManager() {

  }

  @Override
  public void setManagers() {
    this.ioManager = this.getCentralManager().getIoManager();
  }

  @Override
  public void setMasterLists() {
    CentralManagerEY centralMgr = this.getCentralManager();
    this.masterUsers = centralMgr.getMasterUsers();
  }
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
        if (userType.equals(UserType.STAFF.toString())) {
            String password = star.nextToken().trim();// first token
            Staff staff = new Staff(userID, userName, password);
            this.masterUsers.add((User) staff);
            
        } else {
            String emailID = star.nextToken().trim();
            String  mobileNumber = star.nextToken().trim();
            int movieGoerAge = Integer.parseInt(star.nextToken().trim());
            ArrayList<String> bookings = new ArrayList<String>();
            String bookingIDString = star.nextToken().trim();
            StringTokenizer bookingToken = new StringTokenizer(bookingIDString, bookingsEPERATOR);
            while (bookingToken.hasMoreTokens()) {
                bookings.add(bookingToken.nextToken().trim());
            }
            MovieGoerEY movieGoer = new MovieGoerEY(userID, userName, emailID, mobileNumber, movieGoerAge,
                    bookings);
            this.masterUsers.add((User) movieGoer);

        }

    }
}

public void writeUser() throws IOException {
    String userSEPARATOR = " | ";
    String bookingSEPARATOR = " ~ ";
    String filename = this.getCentralManager().getDataFolder().concat("Users.txt");
    List alw = new ArrayList();
    ArrayList<String> bookings;
    User user;
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
        if (userType.equals(UserType.STAFF.toString())) {
            Staff staff = (Staff) user;
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
            for (int j = 0; j < bookingIDs.size(); j++) {
                String bookingID = bookingIDs.get(j);
                st.append(bookingID);
                st.append(bookingSEPARATOR);
            }
        }

        alw.add(st.toString());

    }
     ioManager.write(filename, alw);;

}

}
