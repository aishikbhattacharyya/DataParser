import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

    public static String readFileAsString(String filepath) {
        StringBuilder output = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filepath))) {

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                output.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    public static ArrayList<ElectionResult> parse2016PresidentialResults(String file) {
        ArrayList<ElectionResult> output = new ArrayList<>();

        String[] arr = file.split("\n");

        for (int i = 1; i < arr.length; i++) {
            ArrayList<String> list = extractData(arr[i]);
            ElectionResult er = createElectionResult(list);
            output.add(er);
        }
        return output;
    }

    private static ElectionResult createElectionResult(ArrayList<String> list) {
        ElectionResult er = new ElectionResult();

        int count = 0;
        er.setVotesDem(Double.parseDouble(list.get(count)));
        er.setVotesGOP(Double.parseDouble(list.get(++count)));
        er.setTotalVotes(Double.parseDouble(list.get(++count)));
        er.setPerDem(Double.parseDouble(list.get(++count)));
        er.setPerGOP(Double.parseDouble(list.get(++count)));
        er.setDiff(Double.parseDouble(list.get(++count)));
        er.setPerPointDiff(Double.parseDouble(list.get(++count)));
        er.setStateAbbrv(list.get(++count));
        er.setCountyName(list.get(++count));
        er.setCombinedFips(Double.parseDouble(list.get(++count)));

        return er;
    }

    private static ArrayList<String> extractData(String s) {
        ArrayList<String> list = new ArrayList<>();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        String[] arrByComma = s.split(",");

        for (int i = 1; i < arrByComma.length; i++) {
            String curr = arrByComma[i];
            int indexOfQuote1 = s.indexOf("\"");

            if (alphabet.contains(curr.substring(0, 1))) {
                list.add(curr);
            } else if (i == 6) {//
                if(curr.substring(0, 1).equals("\"")) {
                    int indexOfQuote2 = s.indexOf("\"", indexOfQuote1 + 1);
                    String num = s.substring(indexOfQuote1 + 1, indexOfQuote2);
                    num = num.replaceAll(",", "");

                    int numOfCommas = num.length() / 3;
                    if (num.length() % 3 == 0) {
                        numOfCommas--;
                    }
                    i += numOfCommas;
                    list.add(num);
                }
                else list.add(curr);


            } else if (curr.substring(curr.length() - 1).equals("%")) {
                list.add(curr.substring(0, curr.length() - 1));
            } else list.add(curr);
        }
        return list;
    }
}
