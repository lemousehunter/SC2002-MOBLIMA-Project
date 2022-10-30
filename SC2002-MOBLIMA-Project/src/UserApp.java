import java.util.Scanner;

public class UserApp{
         static Scanner sc=new Scanner(System.in);
         public void Process(){
             do{
                 DisplayUserMenu();
             }while (choice<=10);
         }
         public void DisplayUserMenu(){
             System.out.println("==================== Welcome to User  APP ====================\n" +
                      " 1. View Movie Details                                              \\n\" +\n" +
                     "                     \" 2. Moving Listing                                            \\n\" +\n" +
                     "                     \" 3. Search Movie                                \\n\" +\n" +
                     "                     \" 4. Check Seat Availability                                \\n\" +\n" +
                     "                     \"5. Make a Booking                                           \\n\"+\n" +
                     "                     \"6. View Booking History                              \\n\" +\n" +
                     "                     \"7. List Top 5 Movies by Sales                             \\n\" +\n" +
                     "                     \" 8. List Top 5 Movies by Ratings                               \\n\" +\n" +
                     "                     \" 9. Review and Rate a Movie                             \n" +
                     "===========================================================");
             System.out.println("Enter choice: ");
             while (!sc.hasNextInt()) {
                 System.out.println("Please enter an integer value.");
                 sc.next();
             }
             int subchoice = sc.nextInt();
         }

}
