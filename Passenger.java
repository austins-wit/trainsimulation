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


/**
 * Passengers are created with 2 attributes the starting station and the
 * destination location. Once a passenger is created the passenger heads to the
 * starting station that is was given at the beginning of the simulation. When
 * the passenger gets to its starting station it is placed on a outbound
 * platform or the inbound platform. This is dependent on what the starting and
 * destination stations of the passenger are. Once a train arrives the passenger
 * is put onto the train until the train is full.
 * 
 * @author piercec5
 *
 */
public class Passenger {
	private int ID;
	private int startingStation;
	private int destinationStation;
	
	private static int nextID = 1;

	Passenger(int startingStationID, int destinationStationID) {
		this.startingStation = startingStationID;
		this.destinationStation = destinationStationID;
		this.ID = nextID;
		nextID++;
	}

	/**
	 * Returns the ID of the passenger
	 * 
	 * @return the ID of the passenger
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Returns the starting location of the passenger
	 * 
	 * @return the starting location of the passenger
	 */
	public int getStartingStation() {
		return startingStation;
	}

	/**
	 * Returns the destination station of the passenger
	 * 
	 * @return the destination station of the passenger
	 */
	public int getDestinationStation() {
		return destinationStation;
	}
	
	/**
	 * Returns a string identifying the passenger.
	 * 
	 * @return A string representing the passenger with its unique id.
	 */
	public String toString(){
		return "Passenger " + ID;
	}

}
