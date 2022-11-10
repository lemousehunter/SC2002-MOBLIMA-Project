import java.util.*;

public class CineplexBoundary {

  private Scanner s;
  private Scanner q;
  private String cineplexName;
  private String cineplexLocation;

  public CineplexBoundary() {
    s = new Scanner(System.in);
    q = new Scanner(System.in);
  }

 
  public void printCineplexDuplicateError() {
    System.out.println("\n" + cineplexName + " already exits  \n");
  }

  public String setName() {
    System.out.print("Please enter Cineplex Name: ");
    cineplexName=s.nextLine();
    return cineplexName;
  }

public String setLocation() {
  System.out.print("Please enter " + cineplexName + "  Location: ");
  cineplexLocation=s.nextLine();
  return cineplexLocation;}


public void printAddCineplexSuccessMessaage() {
  System.out.println("\nCineplex " + cineplexName + " has been added !\n");
}


public void printineplexFoundMessaage(Cineplex cineplex) {
  System.out.println("\n Found Cineplex  : " + cineplex.getName() + " at location " + cineplex.getLocation() + "\n");
}


public void printineplexNotFoundMessaage() {
  System.out.println("\nCineplex " + cineplexName + " not found! \n");
}


public void printAllCineplexes(ArrayList<Cineplex> masterCineplexes) {
  System.out.println("\nCineplex List : \n ");
  for(Cineplex c: masterCineplexes) {
        System.out.println(c.viewDetails());
  }
  System.out.println("\n---------------------------X---------------------------\n");

}

}
