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
        ArrayList<State> output = new ArrayList<>();

        //ArrayList<String> elecData = extractElecData(elecFile);
        //ArrayList<String> educData = extractEducData(arr[i], educFile);
        ArrayList<Employment2016> employData = parseEmployData(employFile);
        //State s = createState(elecData, educData, employData);

        //output.add(s);

        return output;
    }

    private static ArrayList<Employment2016> parseEmployData(String employFile) {
        ArrayList<Employment2016> output = new ArrayList<>();
        String[] arr = employFile.split("\n");

        for (int i = 9; i < arr.length; i++) {
            ArrayList<String> list = extractEmployData(arr[i]);
            Employment2016 em = new Employment2016();
            em.addData(list);

            output.add(em);
        }
        return output;
    }

    private static ArrayList<String> extractEmployData(String s) {
        ArrayList<String> list = new ArrayList<>();
        s = removeQuotesCommasLine(s);
        String[] arrByComma = s.split(",");
        int count = 0;

        for (int i = 45; i < arrByComma.length; i++) { //0
            String curr = arrByComma[i];

            if(curr.equals("")) count++;
            if(count >= 45 && count <= 48) list.add(curr);
            if(count == 48) return list;
        }
        return list;
    }

    private static String removeQuotesCommasLine(String curr) {
        String output = "";
        for(int i = 0; i < curr.length(); i++){
            String toAdd = curr.substring(i, i + 1);
            if(toAdd.equals("\"")){
                int index = curr.indexOf("\"", i + 1);
                String removed = removeQuotesCommasOneNumber(curr.substring(i, index + 1));
                toAdd = removed;

                i = index;
            }
            toAdd = toAdd.trim();
            output += toAdd;
        }
        return output;
    }

        /*String str = "FIPStxt,State,Area_name,Rural_urban_continuum_code_2013,Urban_influence_code_2013,Metro_2013,Civilian_labor_force_2007,Employed_2007,Unemployed_2007,Unemployment_rate_2007,Civilian_labor_force_2008,Employed_2008,Unemployed_2008,Unemployment_rate_2008,Civilian_labor_force_2009,Employed_2009,Unemployed_2009,Unemployment_rate_2009,Civilian_labor_force_2010,Employed_2010,Unemployed_2010,Unemployment_rate_2010,Civilian_labor_force_2011,Employed_2011,Unemployed_2011,Unemployment_rate_2011,Civilian_labor_force_2012,Employed_2012,Unemployed_2012,Unemployment_rate_2012,Civilian_labor_force_2013,Employed_2013,Unemployed_2013,Unemployment_rate_2013,Civilian_labor_force_2014,Employed_2014,Unemployed_2014,Unemployment_rate_2014,Civilian_labor_force_2015,Employed_2015,Unemployed_2015,Unemployment_rate_2015,Civilian_labor_force_2016,Employed_2016,Unemployed_2016,Unemployment_rate_2016,Civilian_labor_force_2017,Employed_2017,Unemployed_2017,Unemployment_rate_2017,Median_Household_Income_2016,Med_HH_Income_Percent_of_State_Total_2016";

        String[] arr2 = str.split(",");

        for(int i = 0; i < arr2.length; i++) {
            if (arr2[i].equals("Civilian_labor_force_2016")) {
                System.out.println(i);
            }
        }
        return new ArrayList<>();*/
}