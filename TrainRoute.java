package edu.wit.dcsn.comp2000.queueapp;

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
	
	private static final int DEFAULT_TRACK_LENGTH = 50;
	
	/**
	 * Creates a TrainRoute with the default track length.
	 */
	TrainRoute()
	{
		this(DEFAULT_TRACK_LENGTH);
	}
	
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
		//TODO: Implement
		// get passenger starting station id
		// find station in stations with station id
		// add passenger to station
	}
	
	/**
	 * Adds a passenger to a train.
	 * @param passenger The passenger object to add.
	 * @param trainId The id of the train the passenger will be added to.
	 */
	public void addPassengerToTrain(Passenger passenger, int trainId)
	{
		//TODO: Implement
		// find train in trains with train id
		// add passenger to train
	}
	
	/**
	 * Add a station to the route.
	 * @param location The location of the station on the route.
	 */
	public void addStation(int location)
	{
		//TODO: Implement
		
		//Station station = new Station();
		// add location data to station
		//stations.add(station);
	}
	
	/**
	 * Add a train to the route.
	 * @param location The location of the train on the route.
	 * @param inbound The direction of the train.
	 * @param capacity The capacity of the train.
	 */
	public void addTrain(int location, Direction direction, int capacity)
	{
		//TODO: Implement
		
		//Train train = new Train();
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
	 * @return The station object.
	 */
	public Station getStation(int id)
	{
		//TODO: Implement
		for (Station station : stations)
		{
			// if station.getId() == id, then return station
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
	 * @return The train object.
	 */
	public Train getTrain(int id)
	{
		//TODO: Implement
		for (Train train : trains)
		{
			// if train.getId() == id, then return train;
		}
		return null;
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
			// train.move()
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
				// if train.getLocation() == station.getLocation()
				//     train.arrivesAtStation()?
				//     station.trainArrives()?
				//     then break out of loop
			}
		}
	}
}
