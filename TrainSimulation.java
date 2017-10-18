package edu.wit.dcsn.comp2000.queueapp;

public class TrainSimulation
{
	private static TrainRoute trainRoute;
	
	/**
	 * Runs a simulation of a train route. Reads a config file that specifies certain parameters
	 * about how the simulation should be set up.
	 * @param args
	 */
	public static void main(String[] args)
	{
		//TODO: read config file
		trainRoute = new TrainRoute();
		
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
		for (int i = 0; i < 10; ++i)
		{
			Passenger passenger= new Passenger();
			trainRoute.addPassengerToStation(passenger);
		}
		
		int ticks = 50;
		for (int i = 0; i < ticks; ++i)
		{
			
			trainRoute.update();
		}
	}
}
