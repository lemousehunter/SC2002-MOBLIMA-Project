package Controller;

import Entity.CentralManagerEY;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


/**
 * An IOManager Object
 * 
 * <p>
 * An <code>IOManager</code> object used 
 *  process input and output
 * </p>
 * 
 */
public class IoManager extends Manager implements BaseManager {

  /**
 * A master list of holidays
 */
private ArrayList<String> masterHolidaysList;

  /**
 * Constructor for IOmanager
 */
public IoManager() {
  }

  /**
 *{@inheritDoc}
 */
@Override
  public void setManagers() {
    CentralManagerEY centralMgr = this.getCentralManager();
  }

  /**
 *{@inheritDoc}
 */
@Override
  public void setMasterLists() {

  }

/**
 * Method to write data to file
 * @param fileName The file name
 * @param data The data to be written
 * @throws IOException If there's write error
 */
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

  /** Read the contents of the given file. 
  * @param fileName The file name
  * @return A list of data
  * @throws IOException error
  */
 
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
