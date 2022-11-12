import java.util.*;

public class TicketPriceManager extends Manager implements BaseManager {
    private ArrayList<TicketPrice> masterTicketPrices;

    public TicketPriceManager() {

    }

    @Override
    public void setManagers() {

    }

    @Override
    public void setMasterLists() {
        this.masterTicketPrices = this.getCentralManager().getMasterTicketPrices();
    }

    public char addTicketPrice(String dateType, String screenClass, String movieGoerAge, String movieType, double price) {
        for (TicketPrice ticketPrice : this.masterTicketPrices) {
            if (ticketPrice.getDayType().toString().equals(dateType) &&
                    ticketPrice.getScreenClass().toString().equals(screenClass) &&
                    ticketPrice.getMovieGoerAge().toString().equals(movieGoerAge) &&
                    ticketPrice.getMovieType().toString().equals(movieType)) {
                ticketPrice.setPrice(price);
                return 'U';
            }
        }

        TicketPrice t = new TicketPrice(DayTypeEN.valueOf(dateType), ScreenClassEN.valueOf(screenClass), MovieGoerAgeEN.valueOf(movieGoerAge), MovieTypeEN.valueOf(movieType), price);
        this.masterTicketPrices.add(t);
        return 'I';
    }

    public ArrayList<TicketPrice> listAllTicketPrices() {
        return this.masterTicketPrices;
    }
}
