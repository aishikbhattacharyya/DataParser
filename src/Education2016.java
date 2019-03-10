import java.util.ArrayList;

public class Education2016 {
    private double noHighSchool;
    private double onlyHighSchool;
    private double someCollege;
    private double bachelorsOrMore;

    public double getNoHighSchool() {
        return noHighSchool;
    }

    public void setNoHighSchool(double noHighSchool) {
        this.noHighSchool = noHighSchool;
    }

    public double getOnlyHighSchool() {
        return onlyHighSchool;
    }

    public void setOnlyHighSchool(double onlyHighSchool) {
        this.onlyHighSchool = onlyHighSchool;
    }

    public double getSomeCollege() {
        return someCollege;
    }

    public void setSomeCollege(double someCollege) {
        this.someCollege = someCollege;
    }

    public double getBachelorsOrMore() {
        return bachelorsOrMore;
    }

    public void setBachelorsOrMore(double bachelorsOrMore) {
        this.bachelorsOrMore = bachelorsOrMore;
    }

    public void addData(ArrayList<String> list) {
        this.noHighSchool = Double.parseDouble(list.get(0));
        this.onlyHighSchool = Double.parseDouble(list.get(1));
        this.someCollege = Double.parseDouble(list.get(2));
        this.bachelorsOrMore = Double.parseDouble(list.get(3));
    }
}
