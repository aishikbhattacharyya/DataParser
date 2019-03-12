import java.util.ArrayList;

/***
 * Main class for data parsers
 * @author: Aishik Bhattacharyya
 */
public class Main{
    public static void main(String[] args) {
        //Test of Utils

        String data = Utils.readFileAsString("data/2016_Presidential_Results.csv");
        //System.out.println(data);

        ArrayList<ElectionResult> list = Utils.parse2016PresidentialResults(data);

        String file1 = Utils.readFileAsString("data/Education.csv");
        String file2 = Utils.readFileAsString("data/Unemployment.csv");
        Utils.parseStateData(data, file1, file2);
    }
}
