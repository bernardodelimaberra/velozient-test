package drones;

import java.util.List;

public class DroneRoutePlanner {
    private Drone drone;
    private List<List<Location>> trips;

    public DroneRoutePlanner(Drone drone, List<List<Location>> trips) {
        this.drone = drone;
        this.trips = trips;
    }

    public Drone getDrone() {
        return drone;
    }

    public List<List<Location>> getTrips() {
        return trips;
    }
}