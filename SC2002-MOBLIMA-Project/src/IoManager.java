import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class IoManager extends Manager implements BaseManager {

  private ArrayList<String> masterHolidaysList;

  public IoManager() {
  }

  @Override
  public void setManagers() {
    CentralManagerEY centralMgr = this.getCentralManager();
  }

  @Override
  public void setMasterLists() {

  }

  public static void write(String fileName, List data) throws IOException {

    PrintWriter out = new PrintWriter(new FileWriter(fileName));

    try {
      for (int i = 0; i < data.size(); i++) {
        out.println((String) data.get(i));
      }
    } finally {
      out.close();
    }
  }

  /** Read the contents of the given file. */
  public static List read(String fileName) throws IOException {
    List data = new ArrayList();
    Scanner scanner = new Scanner(new FileInputStream(fileName));
    try {
      while (scanner.hasNextLine()) {
        data.add(scanner.nextLine());
      }
    } finally {
      scanner.close();
    }
    return data;
  }
}
