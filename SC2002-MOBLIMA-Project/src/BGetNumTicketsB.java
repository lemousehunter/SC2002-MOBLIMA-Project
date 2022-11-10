public class BGetNumTicketsB extends BookingBoundary implements GetIntegerInputB {
    public BGetNumTicketsB(MovieManager movieMgr, MovieBoundary movieB) {
        super(movieMgr, movieB);
    }

    @Override
    public Integer get() {
        this.print("How many tickets would you like to book?");
        return this.sc.nextInt();
    }
}
