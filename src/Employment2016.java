import java.util.ArrayList;

public class Employment2016 {
    private int totalLaborForce;
    private int employedLaborForce;
    private int unemployedLaborForce;
    private double unemployedPercent;

    public int getTotalLaborForce() {
        return totalLaborForce;
    }

    public void setTotalLaborForce(int totalLaborForce) {
        this.totalLaborForce = totalLaborForce;
    }

    public int getEmployedLaborForce() {
        return employedLaborForce;
    }

    public void setEmployedLaborForce(int employedLaborForce) {
        this.employedLaborForce = employedLaborForce;
    }

    public int getUnemployedLaborForce() {
        return unemployedLaborForce;
    }

    public void setUnemployedLaborForce(int unemployedLaborForce) {
        this.unemployedLaborForce = unemployedLaborForce;
    }

    public double getUnemployedPercent() {
        return unemployedPercent;
    }

    public void setUnemployedPercent(double unemployedPercent) {
        this.unemployedPercent = unemployedPercent;
    }

    public void addData(ArrayList<String> list) {
        this.totalLaborForce = Integer.parseInt(list.get(0));
        this.employedLaborForce = Integer.parseInt(list.get(1));
        this.unemployedLaborForce = Integer.parseInt(list.get(2));
        this.unemployedPercent = Double.parseDouble(list.get(3));
    }
}
