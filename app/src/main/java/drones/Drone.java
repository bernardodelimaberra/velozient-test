package drones;

public class Drone implements Comparable<Drone> {
    private String name;
    private int maxWeight;

    public Drone(String name, int maxWeight) {
        this.name = name;
        this.maxWeight = maxWeight;
    }

    public String getName() {
        return name;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    @Override
    public int compareTo(Drone o) {
        int compareMaxWeight = ((Drone) o).getMaxWeight();

        // For descending order
        return compareMaxWeight - this.maxWeight;
    }
}
