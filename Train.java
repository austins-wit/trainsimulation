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

import java.util.Iterator;
import java.util.LinkedList;
import edu.wit.dcsn.ds.rosenbergd.queueapp.Direction;

/**
 * Trains are responsible for moving themselves, and subsequently the passengers that they hold, along the tracks between 
 * stations.  Once a train arrives at a station, they must check to see if any of their passengers need to disembark at that
 * particular station.  If so, they disembark them and then proceed to board the passengers waiting at the station.  Beyond
 * that, they need to know their own capacity so they do not over-fill themselves.
 * 
 * @author Nico De Paolis
 */

public class Train 
{
	LinkedList<Passenger> passengers;
	Direction direction;
	int location;
	int routeLength;
	int passengerCount;
	int passengerCapacity;
	int trainID;
	int stationID;
	
	static int trainCounter = 1;
	
	/**
	 * Constructs a train with the following parameters.
	 * @param routeLength is the length of the route that the train moves along.
	 * @param passengerCapacity is the maximum number of passengers that the train can hold.
	 * @param startingLocation is the part of the trainRoute that the train is starting on.
	 * @param direction is the direction that the train is moving (clockwise or counter-clockwise).
	 */
	public Train(int routeLength, int passengerCapacity, int startingLocation, Direction direction)
	{
		this.routeLength = routeLength;
		this.passengers = new LinkedList<Passenger>();
		this.passengerCapacity = passengerCapacity;
		this.location = startingLocation;
		this.direction = direction;
		this.trainID = trainCounter++;
	}
	
	/**
	 * Checks to see which direction is assigned to the train, then moves it in that direction.
	 */
	public void move()
	{
		if(direction == Direction.CLOCKWISE)
		{
			location++;
			if(location == routeLength)
			{
				location = 0;
			}
		}
		else
		{
			location--;
			if(location < 0)
			{
				location = routeLength - 1;
			}
		}
	}
	
	/**
	 * Checks the passenger count with the passenger capacity to tell whether the train is full or not.
	 * @return the boolean value corresponding to whether or not the train is at maximum capacity.
	 */
	public boolean isFull()
	{
		if(passengerCount == passengerCapacity)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Checks the passenger's destination station with the ID of the station that the train is currently at, and if they
	 * match, the train disembarks (removes from the list) that passenger and decrements the passenger count.
	 * @param stationID is the ID of the station that will be checked with the passenger's destination.
	 * @return the number of passengers that disembarked.
	 */
	public int disembarkPassengers(int stationID)
	{
		int passengersDisembarked = 0;
		for(Iterator<Passenger> iterator = passengers.iterator(); iterator.hasNext();)
		{
			Passenger passenger = iterator.next();
			if(passenger.getDestinationStation() == stationID)
			{
				Logger.passengerDisembarksFromTrain(passenger, this);
				iterator.remove();
				passengerCount--;
				passengersDisembarked++;
			}
		}
		return passengersDisembarked;
	}
	
	/**
	 *  Takes a passenger from a station, adds it to the list of passengers, and increments the passenger count.
	 *  @param passenger is the passenger being given to the train by the station.
	 */
	public void boardPassengers(Passenger passenger)
	{	
		passengers.add(passenger);
		passengerCount++;
	}
	
	/**
	 * Returns the numeric ID of the train.
	 * @return the ID of the train
	 */
	public  int getTrainID()
	{
		return trainID;
	}
	
	/**
	 * Returns the direction of the train.
	 * @return the direction of the train.
	 */
	public Direction getDirection()
	{
		return direction;
	}
	
	/**
	 * Returns the location of the train.
	 * @return the location of the train.
	 */
	public int getLocation()
	{
		return location;
	}
	
	/**
	 * Overridden toString() method, changes the train ID from a number to a string that includes "train" and the number.
	 * @return the string of "Train" plus the ID of that train.
	 */
	@Override
	public String toString()
	{
		return "Train " + trainID;
	}
	
	
	/**
	 * Meant to test the methods of the class and ensure that no program-crippling bugs get through.
	 * @param args -unused
	 */
	public static void main(String[] args)
	{
		Train train = new Train(20, 50, 5, Direction.CLOCKWISE);
		
		train.passengerCount = 50;
		
		System.out.println(train.getTrainID());
		System.out.println(train.getDirection());
		System.out.println(train.getLocation());
		System.out.println(train.toString());
		System.out.println(train.isFull());
		
		train.passengerCount = 45;
		
		System.out.println(train.isFull());
		
		train.move();
		
		System.out.println(train.getLocation());
	}
}
