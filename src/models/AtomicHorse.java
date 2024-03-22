package models;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import utils.Color;

public class AtomicHorse implements Runnable {

    private final Integer horseId;
    private final String horseName;
    private final double averageSpeed;
    private final double acceleration;
    private final Map<Integer, Double> completionTimeByLap = new HashMap<Integer, Double>();
    private Boolean finishedRace = false;
    private final Circuit circuit;
    
    public AtomicHorse(Integer horseId, String horseName, double averageSpeed, double acceleration, Circuit circuit) {
        this.horseId = horseId;
        this.horseName = horseName;
        this.averageSpeed = averageSpeed;
        this.acceleration = acceleration;
        this.circuit = circuit;

        for (int lap = 0; lap < this.circuit.getLaps(); lap++) {
            this.completionTimeByLap.put(lap, this.calculateEstimatedLapTime());
        }
    }

    @Override
    public void run() {

        NumberFormat timeFormatter = new DecimalFormat("#000.000");
        NumberFormat idFormatter = new DecimalFormat("#000");

        long startTime = System.currentTimeMillis();

        for (int lap = 0; lap < this.circuit.getLaps(); lap++) {
            try {
                Thread.sleep((long) (this.completionTimeByLap.get(lap) * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Color.WHITE_BACKGROUND_BRIGHT + "ID: #" + idFormatter.format(this.horseId) + Color.RESET + " [" + timeFormatter.format((System.currentTimeMillis() - startTime) / 1000d) + "] " + this.horseName + " completou a " + Color.BLUE + "" + (lap + 1) + "º volta!" + Color.RESET);
        }
        System.out.println(Color.GREEN_BACKGROUND_BRIGHT + "ID: #" + idFormatter.format(this.horseId) + Color.RESET + " [" + timeFormatter.format((System.currentTimeMillis() - startTime) / 1000d) + "] " + Color.GREEN_BRIGHT + this.horseName + " completou todo o percurso." + Color.RESET);
        this.finishedRace = true;
    }

    public Integer getHorseId() {
        return this.horseId;
    }

    public String getHorseName() {
        return this.horseName;
    }

    public Boolean hasFinishedRace() {
        return this.finishedRace;
    }

    public Map<Integer, Double> getCompletionTimeByLap() {
        return this.completionTimeByLap;
    }

    public double calculateEstimatedLapTime() {
        double speedVariation = new Random().nextDouble() * this.acceleration;
        double finalSpeed = this.averageSpeed + speedVariation;

        return circuit.getLenght() / finalSpeed;
    }

    @Override
    public String toString() {
        return "\nAtomicHorse {" 
                            + "\n\thorseId: " + this.horseId
                            + ",\n\thorseName: " + this.horseName
                            + ",\n\taverageSpeed: " + this.averageSpeed
                            + ",\n\tacceleration: " + this.acceleration
                            + ",\n\tcompletionTimeByLap: " + this.completionTimeByLap.values()
                            + ",\n\ttotalCompletionTime: " + this.completionTimeByLap.values().stream().mapToDouble(Double::doubleValue).sum() + " segundos."
                            + "\n}";
    }

}
