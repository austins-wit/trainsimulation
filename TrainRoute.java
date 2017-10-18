package edu.wit.dcsn.comp2000.queueapp;

import java.util.ArrayList;

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
	private ArrayList<Passenger> passengers;
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
	}
	
	/**
	 * Adds a passenger to its starting station.
	 * @param passenger The passenger object to add.
	 */
	public void addPassengerToStation(Passenger passenger)
	{
		//TODO: Implement
	}
	
	/**
	 * Adds a passenger to a train.
	 * @param passenger The passenger object to add.
	 * @param trainId The id of the train the passenger will be added to.
	 */
	public void addPassengerToTrain(Passenger passenger, int trainId)
	{
		//TODO: Implement
	}
	
	/**
	 * Add a station to the route.
	 * @param location The location of the station on the route.
	 */
	public void addStation(int location)
	{
		//TODO: Implement
	}
	
	/**
	 * Add a train to the route.
	 * @param location The location of the train on the route.
	 * @param inbound The direction of the train.
	 */
	public void addTrain(int location, boolean inbound)
	{
		//TODO: Implement
	}
	
	/**
	 * Get a station object from an id.
	 * @param id Id number of the station.
	 * @return The station object.
	 */
	public Station getStation(int id)
	{
		//TODO: Implement
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
		//TODO: Implement
	}
	
	/**
	 * Requests that all trains on the route move forward one track.
	 */
	private void moveTrains()
	{
		//TODO: Implement
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
	}
}
