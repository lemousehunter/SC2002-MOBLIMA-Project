public class BGetCineplexB extends CineplexBoundary implements GetStringInputB {
    CineplexBoundary cineplexB;
    public BGetCineplexB(CineplexManager cineplexMgr, CineplexBoundary cineplexB) {
        super();
        this.cineplexB = cineplexB;
    }

    @Override
    public String get() {
        this.print("Please enter the corresponding index of the cinema you would like to visit");
        this.cineplexB.printAllCineplexes();
        return this.cineplexMgr.convertIDX2CineplexID(sc.nextInt());
    }
}
