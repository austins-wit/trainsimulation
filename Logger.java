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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Provides logging functions for the train simulation
 * @author Scott Austin
 *
 */
public class Logger 
{
	private static PrintWriter printWriter;
	private static boolean isDetailedLog;
	
	/**
	 * Initialize the logger to write to the log file.
	 * @param detailedLog if true, write detailed logs.
	 * @throws IOException thrown if the file cannot be opened for writing.
	 */
	public static void initialize(boolean detailedLog) throws IOException
	{
		initialize(detailedLog, false);
	}
	
	/**
	 * Initialize the logger.
	 * @param detailedLog if true, write detailed logs.
	 * @param useConsole if true, write to the console. if false, write to the standard log file.
	 * @throws IOException thrown if the file cannot be opened for writing.
	 */
	public static void initialize(boolean detailedLog, boolean useConsole) throws IOException
	{
		isDetailedLog = detailedLog;
		if (useConsole)
		{
			printWriter = new PrintWriter(System.out);
		}
		else
		{
			FileWriter fileWriter = new FileWriter("TrainSimulation.log");
			printWriter = new PrintWriter(fileWriter);
		}
	}
	
	/**
	 * Close the file.
	 */
	public static void close()
	{
		printWriter.close();
	}
	
	/**
	 * Print the end of simulation statement.
	 */
	public static void endSimulation()
	{
		printWriter.println("Done.");
	}
	
	/**
	 * Print the number of passengers that have boarded trains.
	 * @param numberOfPassengers the number of passengers.
	 */
	public static void passengersHaveBoarded(int numberOfPassengers)
	{
		printWriter.println(numberOfPassengers + " passengers have boarded trains");
	}
	
	/**
	 * Print the number of passengers that have boarded a train.
	 * @param numberOfPassengers the number of passengers.
	 * @param train the train that is boarding.
	 */
	public static void passengersHaveBoardedTrain(int numberOfPassengers, Train train)
	{
		printWriter.println(numberOfPassengers + " passengers have boarded " + train.toString());
	}
	
	public static void passengersHaveBoardedTrainAtStation(int numberOfPassengers, Train train, Station station)
	{
		printWriter.println(numberOfPassengers + " passengers have boarded " 
				+ train.toString() + " at " + station.toString());
	}
	
	/**
	 * Print the number of passengers that have disembarked from a train.
	 * @param numberOfPassengers the number of passengers
	 * @param train the train that is disembarking passengers.
	 */
	public static void passengersHaveDisembarkedTrain(int numberOfPassengers, Train train)
	{
		printWriter.println(numberOfPassengers + " passengers have disembarked from " + train.toString());
	}
	
	/**
	 * Prints the number of passengers that have disembarked from a train at a station.
	 * @param numberOfPassengers the number of passengers
	 * @param train the train that is disembarking passengers
	 * @param station the station the passengers have arrived at
	 */
	public static void passengersHaveDisembarkedTrainAtStation(int numberOfPassengers, Train train, Station station)
	{
		printWriter.println(numberOfPassengers + " passengers have disembarked from " + 
				train.toString() + " at " + station.toString());
	}
	
	/**
	 * Print a passenger has arrived at a station.
	 * @param passenger the passenger
	 * @param station the station
	 */
	public static void passengerArrivesAtStation(Passenger passenger, Station station)
	{
		if (isDetailedLog)
		{
			printWriter.println(passenger.toString() + " arrives at " + station.toString());
		}
	}
	
	/**
	 * Print the passenger has boarded a train.
	 * @param passenger the passenger
	 * @param train the train
	 */
	public static void passengerBoardsTrain(Passenger passenger, Train train)
	{
		if (isDetailedLog)
		{
			printWriter.println(passenger.toString() + " has boarded " + train.toString());
		}
	}
	
	/**
	 * Print a passenger has boarded a train at a station
	 * @param passenger the passenger
	 * @param train the train
	 * @param station the station
	 */
	public static void passengerBoardsTrainFromStation(Passenger passenger, Train train, Station station)
	{
		if (isDetailedLog)
		{
			printWriter.println(passenger.toString() + " has boarded " + train.toString() + " at " + station.toString());
		}
	}
	
	/**
	 * Print a passenger has disembarked from a train at a station.
	 * @param passenger the passenger
	 * @param train the train
	 * @param station the station
	 */
	public static void passengerDisembarksFromTrainAtStation(Passenger passenger, Train train, Station station)
	{
		if (isDetailedLog)
		{
			printWriter.println(passenger.toString() + " has disembarked from " + 
					train.toString() + " at " + station.toString());
		}
	}
	
	/**
	 * Print a passenger has disembarked from a train.
	 * @param passenger the passenger
	 * @param train the train
	 */
	public static void passengerDisembarksFromTrain(Passenger passenger, Train train)
	{
		if (isDetailedLog)
		{
			printWriter.println(passenger.toString() + " has disembarked from " + train.toString());
		}
	}
	
	/**
	 * Print a passenger has disembarked at a station
	 * @param passenger the passenger
	 * @param station the station
	 */
	public static void passengerDisembarksAtStation(Passenger passenger, Station station)
	{
		if (isDetailedLog)
		{
			printWriter.println(passenger.toString() + " has disembarked at " + station.toString());
		}
	}
	
	/**
	 * Print a train has arrived at a station
	 * @param train the train
	 * @param station the station
	 */
	public static void trainArrivedAtStation(Train train, Station station)
	{
		printWriter.println(train.toString() + " has arrived at " + station.toString());
	}
	
	/**
	 * Print a message signifying the start of a tick.
	 * @param tick the current tick
	 */
	public static void startNextTick(int tick)
	{
		printWriter.println("Beginning tick " + tick + "...");
	}

	/**
	 * Print that passengers have arrived into the route.
	 * @param newPassengers the number of passengers that have arrived.
	 */
	public static void passengersHaveArrived(int newPassengers)
	{
		printWriter.println(newPassengers + " passengers have arrived.");
	}
}
