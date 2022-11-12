public class ShowBoundary extends Boundary implements BaseBoundary{
    ShowManager showManager;
    @Override
    public void setManagers() {
        this.showManager = this.getCentralManager().getShowMgr();
    }

    @Override
    public void setBoundaries() {

    }

    public void addShowB() {
        String date = this.getInputLine("Enter Show Date: ");
        String time = this.getInputLine("Enter Show Time: ");
        String movieName = this.getInputLine("Enter Movie Name: ");
        String screenName = this.getInputLine("Enter Screen Name: ");
        int emptySeats = this.getInputInt("Enter total seat capacity: ");
        int numOfRows = this.getInputInt("Enter number of rows: ");
        int seatsPerRow = this.getInputInt("Enter number of number of seats per row: ");

        this.showManager.addShowC(date, time, movieName, screenName, emptySeats, numOfRows, seatsPerRow);
        // TODO: add verification logic in showManager to ensure no duplicates are added
        // TODO: link verification logic to print statements here
    }

    public void showOperations() {

    }
}
