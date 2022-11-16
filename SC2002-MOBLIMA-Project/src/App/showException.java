package App;



/**
 * A showException class that extends RuntimeException
 *
 */
public class showException extends RuntimeException {
	
	/**
	 * A App.showException class that extends RuntimeException
     * @param errorMsg The error message
     */
    public showException(String errorMsg) {
        super(errorMsg);
    }
}
