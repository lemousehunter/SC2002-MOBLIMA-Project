package Controller;

import Entity.TicketPrice;
import Enum.ScreenClassEN;
import Enum.DayTypeEN;
import Enum.MovieGoerAgeEN;
import Enum.MovieTypeEN;

import java.io.IOException;
import java.util.*;

/**
 * A Ticket Price Controller.Manager Object
 * 
 * <p>
 * A <code>Controller.TicketPriceManager</code> object used
 * process all ticket price information
 * </p>
 * 
 */
public class TicketPriceManager extends Manager implements BaseManager {
    // managers
    /**
     * Private IOManager object
     */
    private IoManager ioManager;

    /**
     * Master ticket prices array list
     */
    private ArrayList<TicketPrice> masterTicketPrices;

    /**
     * Controller.TicketPriceManager object constructor
     */
    public TicketPriceManager() {

    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void setManagers() {
        this.ioManager = this.getCentralManager().getIoManager();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void setMasterLists() {
        this.masterTicketPrices = this.getCentralManager().getMasterTicketPrices();
    }

    /**
     * Method to add ticket price
     * @param dateType The dateType (HOLIDAY or WEEKEND)
     * @param screenClass The type of screen class (PLATINUM_MOVIE_SUITES or REGULAR_SCREEN)
     * @param movieGoerAge The movie goer age
     * @param movieType The movie type (Blockbuster/3D/Documentary)
     * @param price The price of the ticket
     * @return character U if successfully updated or I if has to be created and then updated
     */
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

    /**
     * Method to get ticket price
     * @param dayType The dateType (HOLIDAY or WEEKEND)
     * @param screenClass The type of screen class (PLATINUM_MOVIE_SUITES or REGULAR_SCREEN)
     * @param movieGoerAge The movie goer age
     * @param movieType The movie type (Blockbuster/3D/Documentary)
     * @return The ticket price
     */
    public double getTicketPrice(String dayType, String screenClass, String movieGoerAge, String movieType)
    {
        for (TicketPrice ticketPrice : this.masterTicketPrices) {
            if (ticketPrice.getDayType().toString().equals(dayType) &&
                    ticketPrice.getScreenClass().toString().equals(screenClass) &&
                    ticketPrice.getMovieGoerAge().toString().equals(movieGoerAge) &&
                    ticketPrice.getMovieType().toString().equals(movieType)) {
                    return ticketPrice.getPrice();
            }
        }
        return -1;
    }
/**
     * Method to return a list of all ticket prices
     * @return An arrayList of ticket prices if exist
     */
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

    /**
     * Method to read ticket prices from text file
     * @throws IOException if there's IO error
     */
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

    /**
     * Method to write ticket price to text file
     * @throws IOException if there's IO error
     */
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
