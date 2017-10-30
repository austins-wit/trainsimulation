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
	 * @param detailedLog
	 * @throws IOException
	 */
	public static void initialize(boolean detailedLog) throws IOException
	{
		initialize(detailedLog, false);
	}
	
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
	
	public static void close()
	{
		printWriter.close();
	}
	
	public static void endSimulation()
	{
		printWriter.println("Done.");
	}
	
	public static void passengersHaveBoarded(int numberOfPassengers)
	{
		printWriter.println(numberOfPassengers + " passengers have boarded trains");
	}
	
	public static void passengersHaveBoardedTrain(int numberOfPassengers, Train train)
	{
		printWriter.println(numberOfPassengers + " passengers have boarded " + train.toString());
	}
	
	public static void passengersHaveDisembarkedTrain(int numberOfPassengers, Train train)
	{
		printWriter.println(numberOfPassengers + " passengers have disembarked from " + train.toString());
	}
	
	public static void passengersHaveDisembarkedTrainAtStation(int numberOfPassengers, Train train, Station station)
	{
		printWriter.println(numberOfPassengers + " passengers have disembarked from " + 
				train.toString() + " at " + station.toString());
	}
	
	public static void passengerArrivesAtStation(Passenger passenger, Station station)
	{
		if (isDetailedLog)
		{
			printWriter.println(passenger.toString() + " arrives at " + station.toString());
		}
	}
	
	public static void passengerBoardsTrain(Passenger passenger, Train train)
	{
		if (isDetailedLog)
		{
			printWriter.println(passenger.toString() + " has boarded " + train.toString());
		}
	}
	
	public static void passengerBoardsTrainFromStation(Passenger passenger, Train train, Station station)
	{
		if (isDetailedLog)
		{
			printWriter.println(passenger.toString() + " has boarded " + train.toString() + " at " + station.toString());
		}
	}
	
	public static void passengerDisembarksFromTrainAtStation(Passenger passenger, Train train, Station station)
	{
		if (isDetailedLog)
		{
			printWriter.println(passenger.toString() + " has disembarked from " + 
					train.toString() + " at " + station.toString());
		}
	}
	
	public static void passengerDisembarksAtStation(Passenger passenger, Station station)
	{
		if (isDetailedLog)
		{
			printWriter.println(passenger.toString() + " has disembarked at " + station.toString());
		}
	}
	
	public static void trainArrivedAtStation(Train train, Station station)
	{
		printWriter.println(train.toString() + " has arrived at " + station.toString());
	}
	
	public static void startNextTick(int tick)
	{
		printWriter.println("Beginning tick " + tick + "...");
	}

	public static void passengersHaveArrived(int newPassengers)
	{
		printWriter.println(newPassengers + " passengers have arrived.");
	}
	
	
}
