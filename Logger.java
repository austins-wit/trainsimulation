package edu.wit.dcsn.comp2000.queueapp;

/**
 * Provides static functionality to 
 * @author Scott Austin
 *
 */
public class Logger 
{
	public static void startNextTick(int tick)
	{
		System.out.println("Beginning tick " + tick + "...");
	}
	
	public static void passengersHaveBoarded(int numberOfPassengers)
	{
		System.out.println(numberOfPassengers + " passengers have boarded trains");
	}
	
	public static void passengersHaveDisembarked(int numberOfPassengers)
	{
		System.out.println(numberOfPassengers + " passengers have disembarked from a train");
	}
	
	public static void passengerBoardsTrain(Passenger passenger, Train train)
	{
		System.out.println(passenger.toString() + " has boarded " + train.toString());
	}
	
	public static void passengerDisembarksAtStation(Passenger passenger, Station station)
	{
		System.out.println(passenger.toString() + " have disembarked at " + station.toString());
	}
	
	public static void trainArrivedAtStation(Train train, Station station)
	{
		System.out.println(train.toString + " has arrived at " + station.toString());
	}
}
