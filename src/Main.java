import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import models.AtomicHorse;
import models.Circuit;
import models.HorsesThreadFactory;
import utils.Color;

public class Main {
    public static void main(String[] args) {

        Map<Integer, AtomicHorse> horseMap = new HashMap<Integer, AtomicHorse>();
        Circuit circuit = new Circuit(Math.round(new Random().nextDouble() * 10), new Random().nextDouble() * 100);

        AtomicHorse horse0 = HorsesThreadFactory.newHorse("Falcão Demoníaco",    new Random().nextDouble() * 5, new Random().nextDouble() * 14.5, circuit);
        AtomicHorse horse1 = HorsesThreadFactory.newHorse("Raio Veloz",          new Random().nextDouble() * 5, new Random().nextDouble() * 14.5, circuit);
        AtomicHorse horse2 = HorsesThreadFactory.newHorse("Fogo Rápido",         new Random().nextDouble() * 5, new Random().nextDouble() * 14.5, circuit);
        AtomicHorse horse3 = HorsesThreadFactory.newHorse("Estrela",             new Random().nextDouble() * 5, new Random().nextDouble() * 14.5, circuit);
        AtomicHorse horse4 = HorsesThreadFactory.newHorse("Sombra Branca",       new Random().nextDouble() * 5, new Random().nextDouble() * 14.5, circuit);
        // AtomicHorse horse5 = HorsesThreadFactory.newHorse("Vento Fugaz",         new Random().nextDouble() * 5, new Random().nextDouble() * 14.5, circuit);
        // AtomicHorse horse6 = HorsesThreadFactory.newHorse("Águia Rasante",       new Random().nextDouble() * 5, new Random().nextDouble() * 14.5, circuit);
        // AtomicHorse horse7 = HorsesThreadFactory.newHorse("Leão Marrom",         new Random().nextDouble() * 5, new Random().nextDouble() * 14.5, circuit);
        // AtomicHorse horse8 = HorsesThreadFactory.newHorse("Tigre de Crina",      new Random().nextDouble() * 5, new Random().nextDouble() * 14.5, circuit);
        // AtomicHorse horse9 = HorsesThreadFactory.newHorse("Lobo Dourado",        new Random().nextDouble() * 5, new Random().nextDouble() * 14.5, circuit);
        
        horseMap.put(horse0.getHorseId(), horse0);
        horseMap.put(horse1.getHorseId(), horse1);
        horseMap.put(horse2.getHorseId(), horse2);
        horseMap.put(horse3.getHorseId(), horse3);
        horseMap.put(horse4.getHorseId(), horse4);
        // horseMap.put(horse5.getHorseId(), horse5);
        // horseMap.put(horse6.getHorseId(), horse6);
        // horseMap.put(horse7.getHorseId(), horse7);
        // horseMap.put(horse8.getHorseId(), horse8);
        // horseMap.put(horse9.getHorseId(), horse9);

        HorseRace race = new HorseRace(horseMap, circuit);

        System.out.println("\n==================" + Color.YELLOW_BRIGHT + " INÍCIO DA CORRIDA " + Color.RESET + "==================");
        race.startRace();

        while (true) {
            if (race.allHorsesFinishedRace()) {
                System.out.println("==================" + Color.RED_BRIGHT + " FIM DA CORRIDA " + Color.RESET + "==================");
                System.out.println("\nCavalo vencedor:" + race.getWinnerHorse().toString() + "\n");
                System.out.println("Circuito da corrida:\n" + circuit.toString() + "\n");
                break;
            }
        }
    }
}
