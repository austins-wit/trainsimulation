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
	int startingLocation;
	int routeLength;
	int passengerCount;
	int passengerCapacity; //defined by the config
	int trainID;
	int stationID;
	
	static int trainCounter = 1;
	
	public Train(int routeLength, int passengerCapacity, int startingLocation, Direction direction)
	{
		this.routeLength = routeLength;
		this.passengerCapacity = passengerCapacity;
		this.startingLocation = startingLocation;
		this.direction = direction;
		this.trainID = trainCounter++;
	}
	
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
	
	public boolean isFull()
	{
		if(passengerCount == passengerCapacity)
		{
			return true;
		}
		return false;
	}
	
	public void disembarkPassengers(int stationID)
	{
		for(Iterator<Passenger> iterator = passengers.iterator(); iterator.hasNext();)
		{
			Passenger passenger = iterator.next();
			if(passenger.getDestination() == stationID)
			{
				iterator.remove();
				passengerCount--;
			}
		}
	}
	
	public void boardPassengers(Passenger passenger)
	{	
		passengers.add(passenger);
		passengerCount++;
	}
	
	public int getTrainID()
	{
		return trainID;
	}
	
	public Direction getDirection()
	{
		return direction;
	}
	
	public int getLocation()
	{
		return location;
	}
	
	@Override
	public String toString()
	{
		return "Train " + trainID;
	}
}
