# velozient-test (Drone Delivery Service)

## Description

A squad of drones is tasked with delivering packages for a major online reseller in a world
where time and distance do not matter. Each drone can carry a specific weight and can make
multiple deliveries before returning to home base to pick up additional loads; however, the goal
is to make the fewest number of trips as each time the drone returns to home base, it is
extremely costly to refuel and reload the drone.
The purpose of the written software is to accept input which will include the name of each
drone and the maximum weight it can carry, along with a series of locations and the total weight
needed to be delivered to that specific location. The software should highlight the most efficient
deliveries for each drone to make on each trip.
Assume that time and distance to each drop off location do not matter, and that the size of
each package is also irrelevant. It is also assumed that the cost to refuel and restock each
drone is a constant and does not vary between drones. The maximum number of drones

## Solution

To optimize the algorithm and ensure efficient package deliveries, the drones and locations are sorted in descending order based on their respective capacities and package weights. This ensures that drones with higher capacity are assigned to handle larger packages.

To balance the workload among the drones and avoid overburdening a single drone, the locations are assigned to drones in a round-robin fashion. This means that after each assignment, the next drone in line is selected, cyclically.

The assignment of locations to drones is done recursively until the full capacity of each drone is reached or no more locations are available. This recursive approach ensures efficient utilization of drone capacity by trying to accommodate as many packages as possible on each trip.

These improvements aim to optimize the delivery process, minimize the number of trips, and distribute the workload evenly among the drones, leading to an efficient and balanced drone delivery service.

### Dependencies:

- Java 20
- Gradle 8.0.2
- JUnit Jupiter 5.9.1
- Visual Studio Code Version: 1.78.2

### Build project:

~~~sh
$ ./gradlew build
~~~

### Execute tests:

~~~sh
$ ./gradlew test
~~~

### Run project:

![image](https://github.com/bernardodelimaberra/velozient-test/assets/135295173/c0ab83c9-9890-4bb5-9d12-780b3483168f)
