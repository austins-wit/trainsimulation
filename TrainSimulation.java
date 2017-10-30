package edu.wit.dcsn.comp2000.queueapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import edu.wit.dcsn.ds.rosenbergd.queueapp.Configuration;

public class TrainSimulation
{
	private static TrainRoute trainRoute;
	private static Random rand;
	private static int ticks;

	/**
	 * Runs a simulation of a train route.
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		Configuration config = new Configuration();

		// Build simulation based on config values.
		Logger.initialize(config.getDetailedLog());
		ticks = config.getTicks();
		trainRoute = new TrainRoute(config.getRoute().length);
		rand = new Random(config.getSeed());
		
		Configuration.TrainSpec[] trainSpecs = config.getTrains();
		for (int i = 0; i < trainSpecs.length; ++i)
		{
			trainRoute.addTrain(trainSpecs[i].location, trainSpecs[i].direction, trainSpecs[i].capacity);
		}
		
		int[] stationLocations = config.getStations();
		for (int i = 0; i < stationLocations.length; ++i)
		{
			trainRoute.addStation(stationLocations[i]);
		}
		
		Configuration.PairedLimit[] passengerSpecs = config.getPassengers();
		int passengersInitialMin = passengerSpecs[Configuration.PASSENGERS_INITIAL].minimum;
		int passengersInitialMax = passengerSpecs[Configuration.PASSENGERS_INITIAL].maximum;
		createRandomNewPassengers(passengersInitialMin, passengersInitialMax);
		
		int passengersPerTickMin = passengerSpecs[Configuration.PASSENGERS_PER_TICK].minimum;
		int passengersPerTickMax = passengerSpecs[Configuration.PASSENGERS_PER_TICK].maximum;
		
		// Iterate through the simulation
		for (int i = 1; i <= ticks; ++i)
		{
			Logger.startNextTick(i);
			
			int newPassengers = createRandomNewPassengers(passengersPerTickMin, passengersPerTickMax);
			Logger.passengersHaveArrived(newPassengers);

			trainRoute.update();
		}

		Logger.close();
	}
	
	/**
	 * Creates a random number of passengers between passengersMin and passengersMax, inclusive,
	 * and add them to the stations on the route.
	 * @param passengersMin the minimum number of passengers to be created
	 * @param passengersMax the maximum number of passengers to be created
	 * @return the number of passengers created
	 */
	private static int createRandomNewPassengers(int passengersMin, int passengersMax)
	{
		int newPassengers = passengersMin + rand.nextInt(passengersMax - passengersMin + 1);
		for (int i = 0; i < newPassengers; ++i)
		{
			int startingStationId = rand.nextInt(trainRoute.getNumberOfStations()) + 1;
			int destinationStationId;
			do
			{
				destinationStationId = rand.nextInt(trainRoute.getNumberOfStations()) + 1;
			} while (startingStationId == destinationStationId);
			
			trainRoute.addPassengerToStation(new Passenger(startingStationId, destinationStationId));
		}
		return newPassengers;
	}
}
