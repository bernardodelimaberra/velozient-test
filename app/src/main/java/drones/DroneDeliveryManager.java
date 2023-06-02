package drones;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DroneDeliveryManager {
    public static List<DroneRoutePlanner> process(List<Drone> drones, List<Location> locations) throws Exception {
        List<DroneRoutePlanner> routes = new ArrayList<>();
        List<Location> locationsLeft = new ArrayList<>(locations);

        int count = 0;
        while (!drones.isEmpty() && !locationsLeft.isEmpty() && count <= drones.size()) {
            final List<DroneRoutePlanner> finalRoutes = routes;
            routes = drones.stream()
                    .map(drone -> {
                        List<List<Location>> tripsForDrone = getRoutesForDrone(drone, finalRoutes);

                        List<Location> locationsPicked = pickLocations(drone.getMaxWeight(), locationsLeft);

                        if (!locationsPicked.isEmpty()) {
                            locationsLeft.removeIf(
                                    location -> locationsPicked.stream().anyMatch(picked -> picked.equals(location)));
                            tripsForDrone.add(locationsPicked);
                        }

                        return new DroneRoutePlanner(drone, tripsForDrone);
                    })
                    .collect(Collectors.toList());
            count++;
        }

        if (count > drones.size()) {
            throw new Exception("There are no drones available with sufficient capacity to pick up the remaining locations.");
        }
        return routes;
    }

    private static List<List<Location>> getRoutesForDrone(Drone drone, List<DroneRoutePlanner> route) {
        return route.stream()
                .filter(droneRoute -> droneRoute.getDrone().equals(drone))
                .map(DroneRoutePlanner::getTrips)
                .findFirst()
                .orElse(new ArrayList<>());
    }

    private static List<Location> pickLocations(double capacity, List<Location> locations) {
        List<Location> locationsPicked = new ArrayList<>();
        double currentLoad = 0;
        int index = 0;

        while (index < locations.size()) {
            Location currentLocation = locations.get(index);
            double remainingCapacity = capacity - currentLoad;

            if (currentLocation.getPackageWeight() < remainingCapacity && locations.size() > 1) {
                List<Location> found = pickLocations(remainingCapacity - currentLocation.getPackageWeight(),
                        locations.subList(index + 1, locations.size()));
                if (!found.isEmpty()) {
                    locationsPicked.add(currentLocation);
                    locationsPicked.addAll(found);
                    break;
                }
            } else if (currentLocation.getPackageWeight() <= remainingCapacity) {
                locationsPicked.add(currentLocation);
                break;
            }

            index++;
        }

        return locationsPicked;
    }
}
