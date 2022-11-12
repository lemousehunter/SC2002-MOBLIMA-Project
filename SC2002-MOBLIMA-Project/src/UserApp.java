import java.util.Scanner;

public class UserApp{
         int choice;
         static Scanner sc=new Scanner(System.in);
         public void Process(){
             do{
                 DisplayUserMenu();
             }while (choice <=10);
         }
         public void DisplayUserMenu(){
             System.out.println("""
                     ==================== Welcome to User  APP ====================
                                            1. View Movie Details                       
                                            2. Moving Listing                            
                                            3. Search Movie                              
                                            4. Check Seat Availability                   
                                            5. Make a Booking                            
                                            6. View Booking History                      
                                            7. List Top 5 Movies by Sales                
                                            8. List Top 5 Movies by Ratings              
                                            9. Review and Rate a Movie                   
                     ==============================================================""");
             System.out.println("Enter choice: ");
             while (!sc.hasNextInt()) {
                 System.out.println("Please enter an integer value.");
                 sc.next();
             }
             choice = sc.nextInt();
         }

}
