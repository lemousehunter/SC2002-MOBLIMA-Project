import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ReviewManager extends Manager implements BaseManager {
    // managers
    private IoManager ioManager;
    private MovieManager movieManager;

    private ArrayList<MovieEY> masterMovies;
    private ArrayList<ReviewEY> masterRatings;

    @Override
    public void setManagers() {
        this.ioManager = this.getCentralManager().getIoManager();
        this.movieManager = this.getCentralManager().getMovieMgr();
    }

    @Override
    public void setMasterLists() {
        this.masterMovies = this.getCentralManager().getMasterMovies();
        this.masterRatings = this.getCentralManager().getMasterRatings();
    }

    public void addReview(String userID, String movieID, double scale, String reviewString) {

        ReviewEY review = new ReviewEY(userID, movieID, scale, reviewString);
        this.masterRatings.add(review);
        for (MovieEY m : this.masterMovies) {
            if (m.getMovieID().equals(movieID)) {
                m.addViewerRatingsID(review.getViewerRatingId());
                break;
            }

        }
    }

    public ArrayList<ReviewEY> getListByMovieID(String movieId) {
        ArrayList<ReviewEY> reviewList = new ArrayList<ReviewEY>();

        for (ReviewEY temp : this.masterRatings) {
            if (temp.getMovieId().equals(movieId)) {
                reviewList.add(temp);
            }
        }

        return reviewList;
    }

    public ArrayList<ReviewEY> getListByUserID(String userId) {
        ArrayList<ReviewEY> reviewByUser = new ArrayList<ReviewEY>();

        for (ReviewEY temp : this.masterRatings) {
            if (temp.getUserId().equals(userId)) {
                reviewByUser.add(temp);
            }
        }

        return reviewByUser;
    }


    public double getAvgRating(String movieId) {
        double sum = 0;
        int i = 0;
        ArrayList<ReviewEY> temp = getListByMovieID(movieId);
        if (temp.size() > 1) {
            for (i = 0; i < temp.size(); i++) {
                sum += temp.get(i).getRating();
            }

            /*below is to calculate to 1 significant figure */
            sum = sum / i;
            sum = sum * 10;
            sum = Math.round(sum);
            sum = sum / 10;

        } else {
            sum = -1.0;
        }
        return sum;
    }

    public ArrayList<String> top5MoviesByViewerRatings() // returns list of movieNames
    {
        HashMap<String, ArrayList<ReviewEY>> reviewMap = buildHashMap(this.masterRatings);// build hashmap of master reviews based on movieID as key
        HashMap<String, Double> avgRatingOfmasterMovies = new HashMap<String, Double>();
        ArrayList<String> top5mv = new ArrayList<String>();
        double max = 0.00;
        double avgRating = 0;


        for (String key : reviewMap.keySet()) {
            avgRating = 0;
            if (reviewMap.get(key).size() > 1) {
                for (int i = 0; i < reviewMap.get(key).size(); i++) {
                    avgRating += reviewMap.get(key).get(i).getRating();
                }

            } else {
                avgRating = -1.0;
            }

            avgRatingOfmasterMovies.put(key, avgRating);
            avgRating = 0.0;
        }


        for (int i = 0; i < 5; i++) {
            try {
                max = (Collections.max(avgRatingOfmasterMovies.values()));
            } catch (Exception e) {
                return null;
            }


            for (String key : avgRatingOfmasterMovies.keySet()) {
                if (avgRatingOfmasterMovies.get(key) == max) {
                    avgRatingOfmasterMovies.put(key, -1.0);
                    top5mv.add(this.movieManager.ID2Name(key));
                    break;

                }
            }
        }
        return top5mv;
    }
    

    private HashMap<String, ArrayList<ReviewEY>> buildHashMap(ArrayList<ReviewEY> pass) {
        HashMap<String, ArrayList<ReviewEY>> reviewMap = new HashMap<String, ArrayList<ReviewEY>>();
        for (int i = 0; i < pass.size(); i++) {
            if (reviewMap.containsKey(pass.get(i).getMovieId())) {
                reviewMap.get(pass.get(i).getMovieId()).add(pass.get(i));
            } else {
                reviewMap.put(pass.get(i).getMovieId(), new ArrayList<ReviewEY>());
                reviewMap.get(pass.get(i).getMovieId()).add(pass.get(i));
            }
        }
        return reviewMap;
    }

    public ArrayList<ReviewEY> getMovieRatings(MovieEY movie) {
        ArrayList<ReviewEY> movieViewerRatings = new ArrayList<ReviewEY>();
        for (String ratingID : movie.getViewerRatingsID()) {
            for (ReviewEY rating : this.masterRatings) {
                if (rating.getViewerRatingId().equals(ratingID)) {
                    movieViewerRatings.add(rating);
                    break;
                }
            }
        }

        return movieViewerRatings;
    }

    public void primeViewerRatings() throws IOException {
        String bookingSEPARATOR = "|";
        String filename = this.getCentralManager().getDataFolder().concat("Ratings.txt");
        ArrayList stringArray = null;
        try {
            stringArray = (ArrayList) ioManager.read(filename);
        } catch (FileNotFoundException e) {
            System.out.println("Priming of ViewerRatings objects is skipped as there is no master data");
            return;
        }
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, bookingSEPARATOR);// pass in the string to the string
                                                                             // tokenizer using delimiter ","
            String viewerRatingID = star.nextToken().trim(); // first token
            String review = star.nextToken().trim();
            Double scale = Double.parseDouble(star.nextToken().trim());
            String userID = star.nextToken().trim();
            String movieID = star.nextToken().trim();
            ReviewEY rating = new ReviewEY(viewerRatingID, userID, movieID, scale, review);
            this.masterRatings.add(rating);
        }
    }

    public void writeViewerRatings() throws IOException {
        String ratingsEPARATOR = " | ";
        String filename = this.getCentralManager().getDataFolder().concat("Ratings.txt");
        ArrayList<String> alw = new ArrayList<String>();
        ReviewEY rating;
        for (ReviewEY masterRating : this.masterRatings) {
            rating = masterRating;
            String st = rating.getViewerRatingId().trim() +
                    ratingsEPARATOR +
                    rating.getReview().trim() +
                    ratingsEPARATOR +
                    rating.getRating() +
                    ratingsEPARATOR +
                    rating.getUserId().trim() +
                    ratingsEPARATOR +
                    rating.getMovieId().trim();
            alw.add(st);

        }
         ioManager.write(filename, alw);;
    }

}
