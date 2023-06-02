package drones;

public class Location implements Comparable<Location> {
    private String name;
    private int packageWeight;

    public Location(String name, int packageWeight) {
        this.name = name;
        this.packageWeight = packageWeight;
    }

    public String getName() {
        return name;
    }

    public int getPackageWeight() {
        return packageWeight;
    }

    @Override
    public int compareTo(Location o) {
        int comparePackageWeight = ((Location) o).getPackageWeight();

        // For descending order
        return comparePackageWeight - this.packageWeight;
    }
}