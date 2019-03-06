import java.util.ArrayList;

public class ElectionResult {
    private double votesDem;
    private double votesGOP;
    private double totalVotes;
    private double perDem;
    private double perGOP;
    private double diff;
    private double perPointDiff;
    private String stateAbbrv;
    private String countyName;
    private double combinedFips;

    public ElectionResult() {

    }

    public void addData(ArrayList<String> list){
        int count = 0;
        this.votesDem = Double.parseDouble(list.get(count));
        this.votesGOP = Double.parseDouble(list.get(++count));
        this.totalVotes = Double.parseDouble(list.get(++count));
        this.perDem = Double.parseDouble(list.get(++count));
        this.perGOP = Double.parseDouble(list.get(++count));
        this.diff = Double.parseDouble(list.get(++count));
        this.perPointDiff = Double.parseDouble(list.get(++count));
        this.stateAbbrv = list.get(++count);
        this.countyName = list.get(++count);
        this.combinedFips = Double.parseDouble(list.get(++count));

    }

    public double getVotesDem() {
        return votesDem;
    }

    public void setVotesDem(double votesDem) {
        this.votesDem = votesDem;
    }

    public double getVotesGOP() {
        return votesGOP;
    }

    public void setVotesGOP(double votesGOP) {
        this.votesGOP = votesGOP;
    }

    public double getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(double totalVotes) {
        this.totalVotes = totalVotes;
    }

    public double getPerDem() {
        return perDem;
    }

    public void setPerDem(double perDem) {
        this.perDem = perDem;
    }

    public double getPerGOP() {
        return perGOP;
    }

    public void setPerGOP(double perGOP) {
        this.perGOP = perGOP;
    }

    public double getDiff() {
        return diff;
    }

    public void setDiff(double diff) {
        this.diff = diff;
    }

    public double getPerPointDiff() {
        return perPointDiff;
    }

    public void setPerPointDiff(double perPointDiff) {
        this.perPointDiff = perPointDiff;
    }

    public String getStateAbbrv() {
        return stateAbbrv;
    }

    public void setStateAbbrv(String stateAbbrv) {
        this.stateAbbrv = stateAbbrv;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public double getCombinedFips() {
        return combinedFips;
    }

    public void setCombinedFips(double combinedFips) {
        this.combinedFips = combinedFips;
    }
}
