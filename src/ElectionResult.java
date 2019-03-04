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
