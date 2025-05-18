package pt.ipp.isep.dei.domain;

public class PeriodTime {

    private final int startYear;
    private final int endYear;



    public PeriodTime(int startYear, int endYear) {
        this.startYear = startYear;
        this.endYear = endYear;
    }


    public int getStartYear() {
        return startYear;
    }


    public int getEndYear() {
        return endYear;
    }


    public boolean isValid() {
        return startYear <= endYear;
    }
}
