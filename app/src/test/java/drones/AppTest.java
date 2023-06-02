/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package drones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppTest {
    private List<Drone> drones;
    private List<Location> locations;

    @BeforeEach
    public void setUp() {
        drones = new ArrayList<Drone>();
        drones.add(new Drone("DroneA", 30));
        drones.add(new Drone("DroneB", 50));

        locations = new ArrayList<Location>();
        locations.add(new Location("LocationA", 10));
        locations.add(new Location("LocationB", 5));
        locations.add(new Location("LocationC", 10));
        locations.add(new Location("LocationD", 20));
        locations.add(new Location("LocationE", 15));
        locations.add(new Location("LocationF", 10));
        locations.add(new Location("LocationG", 25));
        locations.add(new Location("LocationH", 5));
        locations.add(new Location("LocationI", 10));
        locations.add(new Location("LocationJ", 20));
    }

    @Test
    public void testProcess_shouldFillDronesToMaxCapacityOnEachRoute() {
        List<DroneRoutePlanner> routes = new ArrayList<DroneRoutePlanner>();
        try {
            routes = DroneDeliveryManager.process(drones, locations);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        int totalNumberOfTrips = calculateNumOfTrips(routes);
        assertEquals(3, totalNumberOfTrips);

        DroneRoutePlanner droneBitchip = routes.get(0);
        assertEquals(1, droneBitchip.getTrips().size());
        List<Location> result = new ArrayList<Location>() {
            {
                add(locations.get(0));
                add(locations.get(1));
                add(locations.get(2));
                add(locations.get(7));
            }
        };
        assertArrayEquals(result.toArray(), droneBitchip.getTrips().get(0).toArray());

        DroneRoutePlanner droneFintone = routes.get(1);
        assertEquals(2, droneFintone.getTrips().size());

        List<Location> result1 = new ArrayList<Location>() {
            {
                add(locations.get(3));
                add(locations.get(5));
                add(locations.get(9));
            }
        };
        assertArrayEquals(result1.toArray(), droneFintone.getTrips().get(0).toArray());

        List<Location> result2 = new ArrayList<Location>() {
            {
                add(locations.get(4));
                add(locations.get(6));
                add(locations.get(8));
            }
        };
        assertArrayEquals(result2.toArray(), droneFintone.getTrips().get(1).toArray());
    }

    @Test
    public void testProcess_whenExceptionThrown_thenAssertionSucceeds() {
        locations = new ArrayList<Location>();
        locations.add(new Location("There are no drones available that can accommodate this package", 1000));

        Exception exception = assertThrows(Exception.class, () -> {
            DroneDeliveryManager.process(drones, locations);
        });

        String expectedMessage = "There are no drones available with sufficient capacity to pick up the remaining locations.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testProcess_shouldReturnEmptyRoutesWhenNoLocationsProvided() {
        locations = new ArrayList<Location>();

        List<DroneRoutePlanner> routes = new ArrayList<DroneRoutePlanner>();
        try {
            routes = DroneDeliveryManager.process(drones, locations);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertEquals(routes.size(), 0);
    }

    @Test
    public void testProcess_shouldReturnEmptyRoutesWhenNoDronesProvided() {
        drones = new ArrayList<Drone>();

        List<DroneRoutePlanner> routes = new ArrayList<DroneRoutePlanner>();
        try {
            routes = DroneDeliveryManager.process(drones, locations);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertEquals(routes.size(), 0);
    }

    private int calculateNumOfTrips(List<DroneRoutePlanner> routes) {
        int numOfTrips = 0;
        for (DroneRoutePlanner route : routes) {
            numOfTrips += route.getTrips().size();
        }
        return numOfTrips;
    }
}
