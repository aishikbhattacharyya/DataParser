import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
            ArrayList<String> list = extract2016PresidentialData(arr[i]);
            ElectionResult er = new ElectionResult();
            er.addData(list);

            output.add(er);
        }
        return output;
    }

    private static ArrayList<String> extract2016PresidentialData(String s) {
        ArrayList<String> list = new ArrayList<>();
        String[] arrByComma = s.split(",");
        for (int i = 1; i < arrByComma.length; i++) {
            String curr = arrByComma[i];
            int length = arrByComma.length;

            if (i < 6 || i > length - 4) list.add(curr);
            else if (i == 6) {
                if (curr.substring(0, 1).equals("\"")) {
                    String num = removeQuotesCommasOneNumber(s);
                    int commas = numOfCommasToAdd(num);
                    i += commas;
                    list.add(num);
                } else list.add(curr);
            } else if (i == length - 4) {
                list.add(curr.substring(0, curr.length() - 1));
            }
        }
        return list;
    }

    private static int numOfCommasToAdd(String num) {
        int numOfCommas = num.length() / 3;
        if (num.length() % 3 == 0) {
            numOfCommas--;
        }
        return numOfCommas;
    }

    private static String removeQuotesCommasOneNumber(String s) {
        int indexOfQuote1 = s.indexOf("\"");
        int indexOfQuote2 = s.indexOf("\"", indexOfQuote1 + 1);
        String num = s.substring(indexOfQuote1 + 1, indexOfQuote2);
        num = num.replaceAll(",", "");

        return num;
    }

    public static ArrayList<State> parseStateData(String elecFile, String educFile, String employFile) {
        ArrayList<Election2016> elecData = parseElecData(elecFile);
        ArrayList<Education2016> educData = parseEducData(educFile);
        ArrayList<Employment2016> employData = parseEmployData(employFile);
        ArrayList<State> states = createStates(elecFile);
        putDataIntoState(elecFile, elecData, educFile, educData, employFile, employData, states);

        return states;
    }

    public static DataManager getDataManager(String elecFile, String educFile, String employFile){
        ArrayList<State> states = parseStateData(elecFile, educFile, employFile);
        DataManager dm = new DataManager();
        dm.setState(states);
        return dm;
    }

    private static void putDataIntoState(String elecFile, ArrayList<Election2016> elecData, String educFile, ArrayList<Education2016> educData, String employFile, ArrayList<Employment2016> employData, ArrayList<State> states) {
        String[] arr = elecFile.split("\n");
        ArrayList<County> counties = new ArrayList<>();
        String state = "CA";//""

        for(int i = 223; i < arr.length; i++){//1
            String[] curr = arr[i].split(",");
            Collections.reverse(Arrays.asList(curr));

            if(i == 1) state = curr[2];
            if(!curr[2].equals(state)){
                State s = getState(states, state);
                s.setCounties(counties);
                counties.clear();
                state = curr[2];
            }

            System.out.println(i);
            String county = curr[1];

            County c = new County(county);
            int fips = Integer.parseInt(curr[0]);
            c.setFips(fips);

            int educIndex = getData(educFile, fips, 6);
            int employIndex = getData(employFile, fips, 9);

            Education2016 educ = null;
            Employment2016 employ = null;
            if(educIndex != -1)educ = educData.get(educIndex);
            if(employIndex != -1) employ = employData.get(employIndex);

            c.setEduc2016(educ);
            c.setEmploy2016(employ);
            c.setVote2016(elecData.get(i-1));
        }
    }

    private static State getState(ArrayList<State> states, String state) {
        for(State s: states){
            if(s.getName().equals(state)) return s;
        }
        return null;
    }

    private static int getData(String educFile, int fips, int i) {
        String[] arr = educFile.split("\n");
        for(int j = i; j < arr.length; j++){
            String[] arrComma = arr[j].split(",");
            String currFips = arrComma[0];
            if(currFips.charAt(0) == '0') currFips = currFips.substring(1);

            if(Integer.parseInt(currFips) == fips) return j-i;
        }
        return -1;
    }

    /*private static ArrayList<County> createCounties(String elecFile, String educFile, String employFile, ArrayList<State> states) {
        ArrayList<State> counties = new ArrayList<>();
        String[] arr = elecFile.split("\n");
    }*/

    private static ArrayList<State> createStates(String elecFile) {
        ArrayList<State> states = new ArrayList<>();
        String[] arr = elecFile.split("\n");

        for(int i = 1; i < arr.length; i++){
            String[] curr = arr[i].split(",");
            Collections.reverse(Arrays.asList(curr));

            String state = curr[2];
            State s = new State(state);
            if(!states.contains(s)) states.add(s);
        }
        return states;
    }

    private static ArrayList<Education2016> parseEducData(String educFile) {
        ArrayList<Education2016> output = new ArrayList<>();
        String[] arr = educFile.split("\n");

        for (int i = 6; i < 3288; i++) {//6
            ArrayList<String> list = extractData(arr[i], 38, 41);
            Education2016 educ = new Education2016();
            educ.addData(list);

            output.add(educ);
        }
        return output;
    }

    private static ArrayList<String> extractData(String s, int start, int end) {
        ArrayList<String> list = new ArrayList<>();
        s = removeQuotesCommasLine(s);
        String[] arrByComma = s.split(",");

        for(int i = start; i <= end; i++) {
            String curr;
            if(i >= arrByComma.length) curr = "0";
            else curr = arrByComma[i];
            if(curr.equals("")) curr = "0";
            curr = curr.trim();
            list.add(curr);
        }
        return list;
    }

    private static ArrayList<Election2016> parseElecData(String elecFile) {
        ArrayList<Election2016> output = new ArrayList<>();
        String[] arr = elecFile.split("\n");

        for (int i = 1; i < arr.length; i++) {
            ArrayList<String> list = extractData(arr[i],1, 3);
            Election2016 elec = new Election2016();
            elec.addData(list);

            output.add(elec);
        }
        return output;
    }

    private static ArrayList<Employment2016> parseEmployData(String employFile) {
        ArrayList<Employment2016> output = new ArrayList<>();
        String[] arr = employFile.split("\n");

        for (int i = 9; i < arr.length; i++) {//9
            ArrayList<String> list = extractData(arr[i],42,45);//45, 48
            Employment2016 em = new Employment2016();
            em.addData(list);

            output.add(em);
        }
        return output;
    }

    private static String removeQuotesCommasLine(String curr) {
        String output = "";

        int le = curr.length();

        for(int i = 0; i < curr.length(); i++){//0
            String toAdd = curr.substring(i, i + 1);
            if(toAdd.equals("\"")){
                int index = curr.indexOf("\"", i + 1);
                String removed = removeQuotesCommasOneNumber(curr.substring(i, index + 1));
                toAdd = removed.trim();

                i = index;
            }
            output += toAdd;
        }
        return output;
    }
}