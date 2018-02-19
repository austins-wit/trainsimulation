/*
 * Group #32
 * Scott Austin, Nico De Paolis, Corey Pierce
 * COMP 2000-03
 * Application 3: Train Simulation
 * Due Monday 10/30/2017
 * 
 * This project is a implementation of simple simulation of a train route. 
 */
package edu.wit.dcsn.comp2000.queueapp;

import java.io.IOException;
import java.util.ArrayList;

import edu.wit.dcsn.ds.rosenbergd.queueapp.Direction;

/**
 * Representation of a train route. Route has two tracks, one going clockwise and
 * one going counter-clockwise. The two tracks have identical lengths. Stations
 * exist between the tracks such that the location of a station refers to the same
 * location on either track. When a train moves beyond the end of the track, it simply
 * loops around to the opposite end, going the same direction.
 * @author Scott Austin
 *
 */
public class TrainRoute
{
	private ArrayList<Station> stations;
	private ArrayList<Train> trains;
	private int trackLength;
	
	/**
	 * Creates a TrainRoute.
	 * @param trackLength Length of the track.
	 */
	TrainRoute(int trackLength)
	{
		this.trackLength = trackLength;
		stations = new ArrayList<Station>();
		trains = new ArrayList<Train>();
	}
	
	/**
	 * Adds a passenger to its starting station.
	 * @param passenger The passenger object to add.
	 */
	public void addPassengerToStation(Passenger passenger)
	{
		int stationId = passenger.getStartingStation();
		Station station = getStation(stationId);
		station.passengerArrives(passenger);
		Logger.passengerArrivesAtStation(passenger, station);
	}
	
	/**
	 * Add a station to the route.
	 * @param location The location of the station on the route.
	 */
	public void addStation(int location)
	{
		stations.add(new Station(this, location));
	}
	
	/**
	 * Add a train to the route.
	 * @param location The location of the train on the route.
	 * @param direction The direction of the train.
	 * @param capacity The capacity of the train.
	 */
	public void addTrain(int location, Direction direction, int capacity)
	{
		trains.add(new Train(trackLength, capacity, location, direction));
	}
	
	/**
	 * Get the number of stations on the route.
	 * @return the number of stations on the route.
	 */
	public int getNumberOfStations()
	{
		return stations.size();
	}
	
	/**
	 * Get the number of trains on the route.
	 * @return the number of trains on the route.
	 */
	public int getNumberOfTrains()
	{
		return trains.size();
	}
	
	/**
	 * Get a station object from an id.
	 * @param id Id number of the station.
	 * @return The station object. If the id does not match a station on this route, returns null.
	 */
	public Station getStation(int id)
	{
		for (Station station : stations)
		{
			if (station.getId() == id)
			{
				return station;
			}
		}
		return null;
	}
	
	public int getTrackLength()
	{
		return trackLength;
	}
	
	/**
	 * Get a train object from an id.
	 * @param id Id number of the train.
	 * @return The train object. If the id does not match a train on this route, returns null.
	 */
	public Train getTrain(int id)
	{
		for (Train train : trains)
		{
			if (train.getTrainID() == id)
			{
				return train;
			}
		}
		return null;
	}
	
	/**
	 * Converts the data of the object into a string-friendly format.
	 * @return  A string 
	 */
	public String toString()
	{
		StringBuilder string = new StringBuilder();
		string.append("TrainRoute: length=" + getTrackLength());
		string.append(", trains=" + getNumberOfTrains());
		string.append(", stations=" + getNumberOfStations());
		
		return string.toString();
	}
	
	/**
	 * Update all the objects on the train route. There are 3 steps to the update process.
	 * 1. Move all the trains by 1 train length.
	 * 2. If a train has arrived at a station, any passengers with that station as their destination will disembark.
	 * 3. Passengers at the station platform will board trains heading the correct direction at their station.
	 * 
	 */
	public void update()
	{
		moveTrains();
		checkForArrivals();
	}
	
	/**
	 * Requests that all trains on the route move forward one track.
	 */
	private void moveTrains()
	{
		//TODO: Implement
		for (Train train : trains)
		{
			train.move();
		}
	}
	
	/**
	 * Check if any trains have arrived at a station. For any trains that have arrived at a station,
	 * have the train disembark any passengers that have that station id as a destination. Passengers
	 * that have disembarked are no longer of interest so they are discarded. Then, have the appropriate 
	 * station platform board its passengers onto that train.
	 */
	private void checkForArrivals()
	{
		//TODO: Implement
		for (Train train : trains)
		{
			for (Station station : stations)
			{
				 if (train.getLocation() == station.getLocation())
				 {
					 Logger.trainArrivedAtStation(train, station);
					 
				     int passengersDisembarked = train.disembarkPassengers(station.getId());
				     Logger.passengersHaveDisembarkedTrainAtStation(passengersDisembarked, train, station);
				     
				     int passengersBoarded = station.trainArrives(train);
				     Logger.passengersHaveBoardedTrainAtStation(passengersBoarded, train, station);
				     
				     break;
				 }
			}
		}
	}
	
	/**
	 * Unit test driver for the train route.
	 * @param args  -unused-
	 * @throws IOException if log file cannot be opened
	 */
	public static void main(String[] args) throws IOException
	{
		Logger.initialize(true, true);
		System.out.println("Testing TrainRoute...");

		System.out.println("\nTesting TrainRoute setup...");
		System.out.println("Testing new TrainRoute(20):");
		TrainRoute trainRoute = new TrainRoute(20);
		System.out.println(trainRoute.toString());
		
		System.out.println();
		System.out.println("Testing trainRoute.addStation(0);");
		System.out.println("        trainRoute.addStation(10)");
		trainRoute.addStation(0);
		trainRoute.addStation(10);
		System.out.println(trainRoute.toString());
		Station station1 = trainRoute.getStation(1);
		System.out.println("station1.toString(): station1.getLocation()");
		System.out.println(station1.toString() + ": " + station1.getLocation());
		Station station2 = trainRoute.getStation(2);
		System.out.println("station2.toString(): station2.getLocation()");
		System.out.println(station2.toString() + ": " + station2.getLocation());
		
		System.out.println();
		System.out.println("Testing trainRoute.addTrain(8, Direction.CLOCKWISE, 20)");
		System.out.println("        trainRoute.addTrain(2, Direction.COUNTER_CLOCKWISE, 30)");
		trainRoute.addTrain(8, Direction.CLOCKWISE, 20);
		trainRoute.addTrain(2, Direction.COUNTER_CLOCKWISE, 30);
		System.out.println(trainRoute.toString());
		Train train1 = trainRoute.getTrain(1);
		System.out.println("train1.toString(): train1.getLocation(), train1.getDirection()");
		System.out.println(train1.toString() + ": " + train1.getLocation() + ", " + train1.getDirection());
		Train train2 = trainRoute.getTrain(2);
		System.out.println("train2.toString(): train2.getLocation(), train2.getDirection()");
		System.out.println(train2.toString() + ": " + train2.getLocation() + ", " + train2.getDirection());
		
		System.out.println();
		System.out.println("Testing trainRoute.addPassengerToStation(new Passenger(1,2));");
		System.out.println("        trainRoute.addPassengerToStation(new Passenger(2,1));");
		trainRoute.addPassengerToStation(new Passenger(1,2));
		trainRoute.addPassengerToStation(new Passenger(2,1));
		
		System.out.println();
		System.out.println("Testing trainRoute.update()");
		trainRoute.update();
		
		System.out.println("Invoke trainRoute.update() 3 more times");
		trainRoute.update();
		trainRoute.update();
		trainRoute.update();
		
		System.out.println();
		
		Logger.close();
	}
	
}
