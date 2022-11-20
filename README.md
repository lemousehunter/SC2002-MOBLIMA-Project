# MOBLIMA
Movie Booking and Listing Management Application (MOBLIMA) is built on Object-Oriented Programming concepts, such as Entity Controller Boundary (ECB) Architecture and SOLID principles.
Using this application, movie tickets can be booked and purchased online, movies can be listed and sale reports can be generated. Both movie goers and cinema employees will have a unified point of entry, which will then lead two separate login pages running off the same controllers.

# Documentation and Release
## Documentation
Code documentation was generated with JavaDocs and can be viewed on this repository under [GitHub-Pages](https://lemousehunter.github.io/SC2002-MOBLIMA-Project).

## Release
Latest release was compiled on MacOS Ventura, and can be downloaded from [releases](https://github.com/lemousehunter/SC2002-MOBLIMA-Project/releases). 
To run, just type in `java -jar Path\To\SC2002-MOBLIMA-Project.jar` in your Command Prompt or Termainal. `Path\To\SC2002-MOBLIMA-Project.jar` refers to the absolute path where the `SC2002-MOBLIMA-Project.jar` is stored at. 

Login details for the Moblima application are as follows:  
**MovieGoer**  
Username: varsha  

**Staff**  
Username: VARSHA  
Password: PASSWD123  


## Design Considerations
### ECB
We have achieved ECB architecture by creating boundaries and entities for all the classes. The boundaries is the layer between user and controllers. Thus, boundary type classes are solely responsible for getting user input, and printing information gotten from controller classes to screen. The Manager (controller) objects on the other hand, solely responsible for reading and writing necessary information to and from files, and are designed to keep track of all the entities it is responsible for. For example, the BookingManager class, manages all the booking objects and contains methods that are relevant to processing bookings as well as passing required information to other Managers(controllers). Entity Classes have the sole responsibility of retaining data, and are treated as data containers. For example, a BookingEY entity contains all relevant booking information, much like what you would see in your receipt when tickets are booked. Any BookingEY entity will thus contain the bookingID, userID, movieID, screenID, cinemaID, date (of show) and time (of show).

<em>CentralManagerEY</em><br>
Leveraging on the benefits of Object Oriented, we contained all managers, boundaries and masterArrayLists in CentralManagerEY, an entity whose sole purpose is to serve as a central data container.

<a href="https://ibb.co/PxM3TJP"><img src="https://i.ibb.co/4W29sCX/Screenshot-2022-11-17-at-03-08-46.jpg" alt="Screenshot-2022-11-17-at-03-08-46" border="0"></a>

This CentralManagerEY is instantiated once at the app level (in Moblima)

<a href="https://ibb.co/5Y5Mz05"><img src="https://i.ibb.co/B4gz7pg/Screenshot-2022-11-17-at-03-09-13.jpg" alt="Screenshot-2022-11-17-at-03-09-13" border="0"></a>

and passed to each boundary and manager when each masterList, boundary and manager is instantiated within (the constructor of CentralManagerEY).

<a href="https://ibb.co/Nsj94V5"><img src="https://i.ibb.co/hyWBq7b/Screenshot-2022-11-17-at-03-09-37.jpg" alt="Screenshot-2022-11-17-at-03-09-37" border="0"></a>

All required instantiation are completed in the constructor, from instantiating of manger, boundary and masterlists (as above), to initializing them (aka priming them).

<a href="https://ibb.co/PtrxTLD"><img src="https://i.ibb.co/XVWXyQY/Screenshot-2022-11-17-at-03-09-47.jpg" alt="Screenshot-2022-11-17-at-03-09-47" border="0"></a>

<hr>

## Class Classification

<table class="tg">
<thead>
  <tr>
    <th class="tg-0lax">Entity</th>
    <th class="tg-0lax">Boundary</th>
    <th class="tg-0lax">Controller</th>
    <th class="tg-0lax">Enum</th>
    <th class="tg-0lax">Interface</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td class="tg-0lax">BookingEY</td>
    <td class="tg-0lax">MoblimaApp</td>
    <td class="tg-0lax">BookingManager</td>
    <td class="tg-0lax">cineplexTypeEN</td>
    <td class="tg-0lax">Base</td>
  </tr>
  <tr>
    <td class="tg-0lax">CentralManagerEY</td>
    <td class="tg-0lax">BookingBoundary</td>
    <td class="tg-0lax">CineplexManager</td>
    <td class="tg-0lax">DayTypeEN</td>
    <td class="tg-0lax">BaseBoundary</td>
  </tr>
  <tr>
    <td class="tg-0lax">MovieEY</td>
    <td class="tg-0lax">Boundary</td>
    <td class="tg-0lax">HolidayManager</td>
    <td class="tg-0lax">MovieGoerAgeEN</td>
    <td class="tg-0lax">BaseManager</td>
  </tr>
  <tr>
    <td class="tg-0lax">MovieGoerEY</td>
    <td class="tg-0lax">CineplexBoundary</td>
    <td class="tg-0lax">IoManager</td>
    <td class="tg-0lax">MovieRatingEN</td>
    <td class="tg-0lax"></td>
  </tr>
  <tr>
    <td class="tg-0lax">ReviewEY</td>
    <td class="tg-0lax">HolidayBoundary</td>
    <td class="tg-0lax">Manager</td>
    <td class="tg-0lax">MovieTypeEN</td>
    <td class="tg-0lax"></td>
  </tr>
  <tr>
    <td class="tg-0lax">ScreenEY</td>
    <td class="tg-0lax">LoginBoundary</td>
    <td class="tg-0lax">MovieGoerManager</td>
    <td class="tg-0lax">RatingScaleEN</td>
    <td class="tg-0lax"></td>
  </tr>
  <tr>
    <td class="tg-0lax">ShowEY</td>
    <td class="tg-0lax">MovieBoundary</td>
    <td class="tg-0lax">MovieManager</td>
    <td class="tg-0lax">ScreenClassEN</td>
    <td class="tg-0lax"></td>
  </tr>
  <tr>
    <td class="tg-0lax">ShowSeatEY</td>
    <td class="tg-0lax">MovieGoerBoundary</td>
    <td class="tg-0lax">ReviewManager</td>
    <td class="tg-0lax">ShowStatusEN</td>
    <td class="tg-0lax"></td>
  </tr>
  <tr>
    <td class="tg-0lax">StaffEY</td>
    <td class="tg-0lax">ReviewBoundary</td>
    <td class="tg-0lax">ScreenManager</td>
    <td class="tg-0lax">UserTypeEN</td>
    <td class="tg-0lax"></td>
  </tr>
  <tr>
    <td class="tg-0lax">TicketEY</td>
    <td class="tg-0lax">ScreenBoundary</td>
    <td class="tg-0lax">ShowManager</td>
    <td class="tg-0lax"></td>
    <td class="tg-0lax"></td>
  </tr>
  <tr>
    <td class="tg-0lax">TicketPrice</td>
    <td class="tg-0lax">ShowBoundary</td>
    <td class="tg-0lax">TicketPriceManager</td>
    <td class="tg-0lax"></td>
    <td class="tg-0lax"></td>
  </tr>
  <tr>
    <td class="tg-0lax">UserEY</td>
    <td class="tg-0lax">StaffBoundary</td>
    <td class="tg-0lax">UserManager</td>
    <td class="tg-0lax"></td>
    <td class="tg-0lax"></td>
  </tr>
  <tr>
    <td class="tg-0lax"></td>
    <td class="tg-0lax">TicketPriceBoundary</td>
    <td class="tg-0lax"></td>
    <td class="tg-0lax"></td>
    <td class="tg-0lax"></td>
  </tr>
  <tr>
    <td class="tg-0lax"></td>
    <td class="tg-0lax">Input</td>
    <td class="tg-0lax"></td>
    <td class="tg-0lax"></td>
    <td class="tg-0lax"></td>
  </tr>
</tbody>
</table>

<hr>

## OOP PRINCIPLES

### Abstraction
In abstraction, essential characteristics are identified and the irrelevant details are ignored. In our project interfaces are used to implement abstraction. At the highest level of abstraction, we have Base, which indicates that setCentralManager(), getCentralManager and setManagers() are methods to be implemented by classes that implement its template. As our CentralManagerEY is our central data container (entity), all our managers and boundaries will have to have methods that get and set the CentralManagerEY attribute for easy access in those classes.

<a href="https://imgbb.com/"><img src="https://i.ibb.co/gm1hrps/Screenshot-2022-11-17-at-03-10-01.jpg" alt="Screenshot-2022-11-17-at-03-10-01" border="0"></a>

Our BaseManager interface that extends from Base interface contains abstract methods from Base, along with setMasterLists(). It functions as a template that manager classes which daserive from it, have to implement. For example, BookingManager, CineplexManager which implements the BaseManager will contain an implementation of the abstract method setMasterLists().

<a href="https://ibb.co/mNPQ8Y6"><img src="https://i.ibb.co/KK8CwQF/Screenshot-2022-11-17-at-03-10-10.jpg" alt="Screenshot-2022-11-17-at-03-10-10" border="0"></a>

BaseBoundary similarly inherits from Base interface, and along with Base’s abstract methods, setBoundaries() will be implemented by boundary methods that implement BaseBoundary, such as BookingBoundary and MovieBoundary.

<a href="https://ibb.co/mNPQ8Y6"><img src="https://i.ibb.co/KK8CwQF/Screenshot-2022-11-17-at-03-10-10.jpg" alt="Screenshot-2022-11-17-at-03-10-10" border="0"></a>

### Inheritance
Inheritance is one of the OOP’s significant features. Child class can inherit attributes and behavior of the parent class. This enhances reusability and reduces workload when creating new classes. Inheritance is central to our app design - all manager classes inherit from Manager class, whereas all boundary classes inherit from Boundary class. Inheritance allowed us to easily create wrapped classes around a single Input() instance, which provides printing methods as well as getInput methods with in-built type validation.

<a href="https://ibb.co/ncW4htf"><img src="https://i.ibb.co/tsdnyFc/Screenshot-2022-11-17-at-03-10-23.jpg" alt="Screenshot-2022-11-17-at-03-10-23" border="0"></a>

This speeds up our class creation, since if we ever need to print a prompt for input, and subsequently read that input, we can just call this.getInputX, with X being the type to validate for.

<a href="https://ibb.co/nn8D9Lv"><img src="https://i.ibb.co/CMsngHZ/Screenshot-2022-11-17-at-03-10-30.jpg" alt="Screenshot-2022-11-17-at-03-10-30" border="0"></a>

As both Manager and Boundary classes will contain such wrapping, as well as the getter and setter methods for CentralManagerEY, we can access these future class of manager or boundary type can just call this.method, with the method being any of the above-listed method to call, in order to access these functions.

### Encapsulation
Encapsulation is the wrapping of an entire class and limiting access of data to the class itself. This is important because other classes do not need to know the functionality of every other class. We have practiced encapsulation throughout our implementation by utilizing private access modifiers within classes and allowing access to class attributes through assessors (getters) for certain data and establishing mutators (setters) to set specific data for the class. For example, each the BookingEY entity, has getter methods to for its private class attributes. This allows for a clear separation of concern, a hallmark of the Single Responsiblity Principle (SRP) of SOLID that we will cover later on.

<a href="https://imgbb.com/"><img src="https://i.ibb.co/0q9yr4n/Screenshot-2022-11-17-at-03-11-06.jpg" alt="Screenshot-2022-11-17-at-03-11-06" border="0"></a>

### Error Handling
We have incorporated try-catch blocks to handle any invalid input entered by the user or staff. If such instances occur, the program will prompt the user/staff to re-enter the correct input. This is implemented to prevent the program from crashing when an exception error happens.

<hr>

## SOLID PRINCIPLES

### Single Responsibility Principle
The concept of SRP has also been utilized throughout our implementation. Essentially, SRP is the separation of concerns between classes, allowing classes to have only 1 reason to change. This means that each class and/or module should focus on a single task, which allows for much cleaner code, and plays into the strengths of abstraction since we are no longer tightly coupling our code. This allows us to build our codebase relying solely on the relevant method signatures, without needing to depend on its exact implementation (internals), which allows us to modularize our code - another hallmark of OOP. In our codebase, we utilized Manager classes as controllers that are responsible for a single entity each. (below are the list are examples to illustrate and not all methods are listed)

BookingManager - handles all booking object related processes
- BookTicket
- genBookingID
- ComputePrice
- getAllBookings

### Open/Closed Principle
The OCP principle is defined as the class being closed for modification but open for extension. In our design, we have a user class which is inherited by a StaffEY class and MovieGoerEY class. As can be seen above, MovieGoerEY and StaffEY classes are separate Entity classes, which share certain similarity despite having their differences. Thus, such differences are written into the child classes (MovieGoerEY and StaffEY) respectively, all the way retaining the similarities via inheritance (of the user class). Essentially this illustrates that the user class is closed for modification but is open for extension. In such cases, more classes that inherit from the user class can be added without much effort. This illustrates the ease of scalability.

### Liskov Substitution Principle
The LSP principle states that a user of a base class should continue to function properly if a derivative of the base class is passed to it. For example, we used arrays of type UserEY to hold users of both MovieGoer and Staff types.

<a href="https://imgbb.com/"><img src="https://i.ibb.co/SmsNyPJ/Screenshot-2022-11-17-at-03-11-17.jpg" alt="Screenshot-2022-11-17-at-03-11-17" border="0"></a>

### Interface Segregation Principle
The definition of ISP is that all classes that implement the interface should have valueable use for all the methods in the interface. For example, both BaseManager and BaseBoundary interfaces extend from the same Base interface, but each of the child interfaces implement different getter methods. BaseManager templates the setMasterLists() method needed by manager classes and unneeded by boundary classes, whereas BaseBoundary templates the setBoundaries() method needed by boundary classes but unneeded by manager classes.

### Dependency Inversion Principle
The Dependency inversion principle states that high-level modules should not depend on low-level modules; both should depend on abstractions. In our implementation we have a Base interface that the base manager implements and subsequently other managers implement the base manager. This allows the other managers to implement their own version of methods that are in the Base interface. As such the relationship between the classes are loosely coupled.
