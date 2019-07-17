package com.example.progetto.Spring;


/**
 * class used in {@link DataCsvRowServices} for numerical statistic of a column
 */
public class NumberStats
{
    private double avg;
    private int min;
    private int max;
    private double std;
    private long sum;

    /**
     * @param avg is the param where we calculate the average value of the column
     * @param min is the param where we insert the minimum value in the column
     * @param max is the param where we insert the maximum value in the column
     * @param std is the param where we calculate the standard deviation value of the column
     * @param sum is the param where we calculate the sum value of the column
     */
    NumberStats(double avg, int min, int max, double std, long sum) {
        this.avg = avg;
        this.min = min;
        this.max = max;
        this.std = std;
        this.sum = sum;
    }

     double getAvg() {
        return avg;
    }

     void setAvg(double avg) {
        this.avg = avg;
    }

     int getMin() {
        return min;
    }

     void setMin(int min) {
        this.min = min;
    }

     int getMax() {
        return max;
    }

     void setMax(int max) {
        this.max = max;
    }

     double getStd() {
        return std;
    }

     void setStd(double std) {
        this.std = std;
    }

     long getSum() {
        return sum;
    }

     void setSum(long sum) {
        this.sum = sum;
    }

    @Override
    public String toString()
    {
        return "NumberStats{" +
                "avg=" + avg +
                ", min=" + min +
                ", max=" + max +
                ", std=" + std +
                ", sum=" + sum +
                '}';
    }
}
