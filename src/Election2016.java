import java.util.ArrayList;

public class Election2016 {
    private double demVotes;
    private double gopVotes;
    private double totalVotes;

    public double getDemVotes() {
        return demVotes;
    }

    public void setDemVotes(double demVotes) {
        this.demVotes = demVotes;
    }

    public double getGopVotes() {
        return gopVotes;
    }

    public void setGopVotes(double gopVotes) {
        this.gopVotes = gopVotes;
    }

    public double getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(double totalVotes) {
        this.totalVotes = totalVotes;
    }

    public void addData(ArrayList<String> list) {
        this.demVotes = Double.parseDouble(list.get(0));
        this.gopVotes = Double.parseDouble(list.get(1));
        this.totalVotes = Double.parseDouble(list.get(2));
    }
}
