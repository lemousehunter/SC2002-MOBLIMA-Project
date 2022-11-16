# MOBLIMA
Movie Booking and Listing Management Application (MOBLIMA) is built on Object-Oriented Programming concepts, such as Entity Controller Boundary (ECB) Architecture and SOLID principles.
Using this application, movie tickets can be booked and purchased online, movies can be listed and sale reports can be generated. Both movie goers and cinema employees will have a unified point of entry, which will then lead two separate login pages running off the same controllers.

## Design Considerations
### ECB
We have achieved ECB architecture by creating boundaries and entities for all the classes. The boundaries is the layer between user and controllers. Thus, boundary type classes are solely responsible for getting user input, and printing information gotten from controller classes to screen. The Manager (controller) objects on the other hand, solely responsible for reading and writing necessary information to and from files, and are designed to keep track of all the entities it is responsible for. For example, the BookingManager class, manages all the booking objects and contains methods that are relevant to processing bookings as well as passing required information to other Managers(controllers). Entity Classes have the sole responsibility of retaining data, and are treated as data containers. For example, a BookingEY entity contains all relevant booking information, much like what you would see in your receipt when tickets are booked. Any BookingEY entity will thus contain the bookingID, userID, movieID, screenID, cinemaID, date (of show) and time (of show).

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

### Inheritance
Inheritance is one of the OOP’s significant features. Child class can inherit attributes and behavior of the parent class. This enhances reusability and reduces workload when creating new classes. Inheritance is central to our app design - all manager classes inherit from Manager class, whereas all boundary classes inherit from Boundary class. Inheritance allowed us to easily create wrapped classes around a single Input() instance, which provides printing methods as well as getInput methods with in-built type validation.
This speeds up our class creation, since if we ever need to print a prompt for input, and subsequently read that input, we can just call this.getInputX, with X being the type to validate for.
As both Manager and Boundary classes will contain such wrapping, as well as the getter and setter methods for CentralManagerEY, we can access these future class of manager or boundary type can just call this.method, with the method being any of the above-listed method to call, in order to access these functions.

### Encapsulation
Encapsulation is the wrapping of an entire class and limiting access of data to the class itself. This is important because other classes do not need to know the functionality of every other class. We have practiced encapsulation throughout our implementation by utilizing private access modifiers within classes and allowing access to class attributes through assessors (getters) for certain data and establishing mutators (setters) to set specific data for the class. For example, each the BookingEY entity, has getter methods to for its private class attributes. This allows for a clear separation of concern, a hallmark of the Single Responsiblity Principle (SRP) of SOLID that we will cover later on.

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

### Interface Segregation Principle
The definition of ISP is that all classes that implement the interface should have valueable use for all the methods in the interface. For example, both BaseManager and BaseBoundary interfaces extend from the same Base interface, but each of the child interfaces implement different getter methods. BaseManager templates the setMasterLists() method needed by manager classes and unneeded by boundary classes, whereas BaseBoundary templates the setBoundaries() method needed by boundary classes but unneeded by manager classes.

### Dependency Inversion Principle
The Dependency inversion principle states that high-level modules should not depend on low-level modules; both should depend on abstractions. In our implementation we have a Base interface that the base manager implements and subsequently other managers implement the base manager. This allows the other managers to implement their own version of methods that are in the Base interface. As such the relationship between the classes are loosely coupled.
