package models;

public class Circuit {

    private final long laps;
    private final double lenght;

    public Circuit(long laps, double lenght) {
        this.laps = laps == 0 ? 1 : laps;
        this.lenght = lenght;
    }

    public long getLaps() {
        return laps;
    }

    public double getLenght() {
        return lenght;
    }

    @Override
    public String toString() {
        return "Circuit {" 
                        + "\n\tlaps: " + laps
                        + ",\n\tlenght: " + lenght
                        + "\n}";
    }

}
