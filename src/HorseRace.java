import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.AtomicHorse;
import models.Circuit;

public class HorseRace {
    
    private final Map<Integer, AtomicHorse> horseMap;
    private final Circuit circuit;
    private AtomicHorse winnerHorse;

    public HorseRace(Map<Integer, AtomicHorse> horseMap, Circuit circuit) {
        this.horseMap = horseMap;
        this.circuit = circuit;
    }

    public Map<Integer, AtomicHorse> getHorseMap() {
        return this.horseMap;
    }

    public AtomicHorse getWinnerHorse() {
        return checkWinnerHorse(this.horseMap);
    }

    public void startRace() {

        List<Thread> horseRaceThreads = new ArrayList<Thread>();

        for (AtomicHorse horse : this.horseMap.values()) {
            horseRaceThreads.add(new Thread(horse));
        }

        horseRaceThreads.forEach(Thread::start);
    }

    public Boolean allHorsesFinishedRace() {

        List<Boolean> horsesStatus = new ArrayList<Boolean>();
        Boolean allFinished = false;

        for (AtomicHorse horse : this.horseMap.values()) {
            horsesStatus.add(horse.hasFinishedRace());
        }

        allFinished = horsesStatus.stream().allMatch(Boolean::booleanValue);

        return allFinished;
    }

    private AtomicHorse checkWinnerHorse(Map<Integer, AtomicHorse> horses) {

        double currentFastestCompletionTime = Integer.MAX_VALUE;
        AtomicHorse winnerHorse = null;

        for (AtomicHorse horse : horses.values()) {
            double horseCompletionTime = horse.getCompletionTimeByLap().values().stream().mapToDouble(Double::doubleValue).sum();

            if (horseCompletionTime < currentFastestCompletionTime) {
                currentFastestCompletionTime = horseCompletionTime;
                winnerHorse = horse;
            }
        }

        this.winnerHorse = winnerHorse;

        return this.winnerHorse;
    }

    @Override
    public String toString() {

        List<String> horseList = new ArrayList<String>();

        for (AtomicHorse horse : this.horseMap.values()) {
            horseList.add(horse.toString());
        }

        return "HorseRace {" 
                          + "\n\thorseMap: " + horseList.toString()
                          + ",\n\twinnerHorse: " + this.winnerHorse
                          + ",\n\tcircuit: " + this.circuit.toString()
                          + "\n}";
    }

}
