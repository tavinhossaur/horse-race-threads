package models;
public class HorsesThreadFactory {

    private static int horseId = 0;

    public static AtomicHorse newHorse(String horseName, double speed, double acceleration, Circuit circuit) {
        horseId++;
        AtomicHorse horse = new AtomicHorse(horseId, horseName, speed, acceleration, circuit);

        return horse;
    }
}
