package edu.wit.dcsn.comp2000.queueapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class TrainSimulation
{
	private static TrainRoute trainRoute;
	private static Random rand;
	private static int ticks;
	private static long seed;
	private static int trackLength;
	private static int trainCapacity;
	private static int initialPassengers;
	private static int passengerRate;
	
	private static final int DEFAULT_TICKS = 50;
	private static final long DEFAULT_SEED = 0;
	private static final int DEFAULT_TRACK_LENGTH = 50;
	private static final int DEFAULT_TRAIN_CAPACITY = 20;
	private static final int DEFAULT_INITIAL_PASSENGERS = 50;
	private static final int DEFAULT_PASSENGER_RATE = 50;
	
	private static final String CONFIG_FILENAME = "TrainSimulation.config";
	
	/**
	 * Runs a simulation of a train route. Reads a config file that specifies certain parameters
	 * about how the simulation should be set up.
	 * @param args
	 */
	public static void main(String[] args)
	{
		try {
			Logger.initialize();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		trainRoute = new TrainRoute();
		
		initializeConfigValues();
		
		loadConfig();
		
		// Build simulation based on config values.
		trainRoute.setTrackLength(trackLength);
		rand = new Random(seed);
		
		// Initialize stations
		for (int i = 0; i < 10; ++i)
		{
			int location = (int)Math.random() * trainRoute.getTrackLength() + 1;
			trainRoute.addStation(location);
		}
		
		// Initialize trains
		for (int i = 0; i < 10; ++i)
		{
			int location = (int)Math.random() * trainRoute.getTrackLength() + 1;
			boolean inbound = Math.random() < 0.5;
			trainRoute.addTrain(location, inbound);
		}
		
		// Initialize passengers
		for (int i = 0; i < initialPassengers; ++i)
		{
			trainRoute.addPassengerToStation(new Passenger());
		}
		
		// Iterate through the simulation
		for (int i = 1; i <= ticks; ++i)
		{
			Logger.startNextTick(i);
			int newPassengers = rand.nextInt(passengerRate+1);
			Logger.passengersHaveArrived(newPassengers);
			for (int j = 0; j < newPassengers; ++j)
			{
				trainRoute.addPassengerToStation(new Passenger());
			}
			trainRoute.update();
		}
		
		Logger.close();
	}
	
	private static void initializeConfigValues()
	{
		ticks = DEFAULT_TICKS;
		seed = DEFAULT_SEED;
		trackLength = DEFAULT_TRACK_LENGTH;
		trainCapacity = DEFAULT_TRAIN_CAPACITY;
		initialPassengers = DEFAULT_INITIAL_PASSENGERS;
		passengerRate = DEFAULT_PASSENGER_RATE;
	}
	
	private static void loadConfig()
	{
		Scanner sc;
		try {
			sc = new Scanner(new File(CONFIG_FILENAME));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		while (sc.hasNextLine())
		{
			String nextLine = sc.nextLine();
			nextLine = nextLine.split("#")[0];
			
			String[] assignmentPair = nextLine.split("=");
			if (assignmentPair.length == 2)
			{
				String variable = assignmentPair[0].trim();
				String value = assignmentPair[1].trim();
				String[] locations;
				String[] trainDatas;
				String[] trainData;
				switch (variable)
				{
				case "ticks":
					ticks = Integer.parseInt(value);
					break;
				case "seed":
					seed = Long.parseLong(value);
					break;
				case "trackLength":
					trackLength = Integer.parseInt(value);
					break;
				case "stationsAt":
					locations = value.split(",");
					for (int i = 0; i < locations.length; ++i)
					{
						trainRoute.addStation(Integer.parseInt(locations[i].trim()));
					}
					break;
				case "trainsAt":
					trainDatas = value.split(",");
					for (int i = 0; i < trainDatas.length; ++i)
					{
						trainData = trainDatas[i].split("/");
						// trainData[0] = location
						// trainData[1] = direction
						// trainData[2] = capacity (can be excluded)
					}
					break;
				case "trainCapacity":
					trainCapacity = Integer.parseInt(value); 
					break;
				case "initialPassengers":
					initialPassengers = Integer.parseInt(value);
					break;
				case "passengerRate":
					passengerRate = Integer.parseInt(value);
					break;
				default:
					break;
				}
			}
		}
		
		sc.close();
	}
}
