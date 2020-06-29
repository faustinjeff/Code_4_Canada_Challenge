/**
 * @author [Jefferson Faustin]
 * @email [faustinj@hotmail.fr]
 * @create date 2020-06-29 11:58:53
 * @modify date 2020-06-29 11:58:53
 * @desc [My code for the Code_4_Canada challenge]
 */
public class Violation{
    private String category;
    private int numberViolations;
    private String earliestDate;
    private String latestDate;
    
    public Violation(String c, int nv, String ed, String lt) {
        category = c;
        numberViolations = nv;
        earliestDate = ed;
        latestDate = lt;
    }
    
    public String toString() {
        return "category: " + category + "\n" + "number of violations: " + numberViolations + "\n" + "earliest violation date: " + earliestDate + "\n" + "lates violation date: " + latestDate; 
    }
}

