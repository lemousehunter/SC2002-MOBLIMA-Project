import java.io.IOException;
import java.util.*;

public class TicketPriceManager extends Manager implements BaseManager {
    // managers
    private IoManager ioManager;

    private ArrayList<TicketPrice> masterTicketPrices;

    public TicketPriceManager() {

    }

    @Override
    public void setManagers() {
        this.ioManager = this.getCentralManager().getIoManager();
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

    public ArrayList<String> listAllTicketPrices() {
        ArrayList<String> printList = new ArrayList<String>();
        if (masterTicketPrices.size() > 0) {
            printList.add("\nList of TicketPrices configured : \n");
            for (TicketPrice ticketPrice : masterTicketPrices) {
                printList.add(
                        String.format("| %-15s", ticketPrice.getDayType().toString()) +
                                String.format("| %-22s", ticketPrice.getScreenClass().toString()) +
                                String.format("| %-15s", ticketPrice.getMovieGoerAge().toString()) +
                                String.format("| %-15s| ", ticketPrice.getMovieType().toString()) +
                                ticketPrice.getPrice());
            }
        } else {
            printList.add("No Ticket Prices configured !");
        }

        return printList;
    }

    public void primeTicketPrice() throws IOException {
        String SEPARATOR = "|";
        String filename = this.getCentralManager().getDataFolder().concat("TicketPrices.txt");
        ArrayList stringArray = null;
        stringArray = (ArrayList) ioManager.read(filename);
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, SEPARATOR);// pass in the string to the string tokenizer
                                                                      // using delimiter "|"
            String dayType = star.nextToken().trim(); // first token
            String screenClass = star.nextToken().trim();
            String movieGoerAge = star.nextToken().trim();
            String movieType = star.nextToken().trim();
            double price = Double.parseDouble(star.nextToken().trim());
            TicketPrice ticketPrice = new TicketPrice(DayTypeEN.valueOf(dayType), ScreenClassEN.valueOf(screenClass),
                    MovieGoerAgeEN.valueOf(movieGoerAge), MovieTypeEN.valueOf(movieType), price);
            this.masterTicketPrices.add(ticketPrice);
        }
    }

    public void writeTicketPrice() throws IOException {
        String filename = this.getCentralManager().getDataFolder().concat("TicketPrices.txt");
        String SEPARATOR = "|";
        List alw = new ArrayList();
        TicketPrice ticketPrice;
        for (int i = 0; i < this.masterTicketPrices.size(); i++) {
            ticketPrice = this.masterTicketPrices.get(i);
            StringBuilder st = new StringBuilder();
            st.append(ticketPrice.getDayType().toString());
            st.append(SEPARATOR);
            st.append(ticketPrice.getScreenClass().toString());
            st.append(SEPARATOR);
            st.append(ticketPrice.getMovieGoerAge().toString());
            st.append(SEPARATOR);
            st.append(ticketPrice.getMovieType().toString());
            st.append(SEPARATOR);
            st.append(ticketPrice.getPrice());
            st.append(SEPARATOR);
            alw.add(st.toString());

        }
         ioManager.write(filename, alw);;

    }

}
