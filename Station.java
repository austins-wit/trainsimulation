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

import com.pearson.carrano.ArrayQueue;

import edu.wit.dcsn.ds.rosenbergd.queueapp.Direction;

/**
 * The station takes care of putting the passengers in their correct platforms.
 * These platforms are the inbound and outbound platforms.
 *
 * @author piercec5
 *
 */
public class Station {
	private TrainRoute trainRoute;
	private int location;
	private static int nextID = 1;
	private int stationID;
	ArrayQueue<Passenger> inboundPlatform;
	ArrayQueue<Passenger> outboundPlatform;

	Station(TrainRoute trainRoute, int location) {
		inboundPlatform = new ArrayQueue<Passenger>();
		outboundPlatform = new ArrayQueue<Passenger>();
		this.trainRoute = trainRoute;
		this.location = location;
		stationID = nextID;
		nextID++;
	}
    /**
    Returns the ID of the station
    @return the ID of the Station
    */
	public int getId() {
		return this.stationID;
	}
    /**
    Returns the location of the station on the track
    @return the station's location on the track
    */
	public int getLocation() {
		return this.location;
	}
    /**
    When the passenger arrives at the station this method gets the passengers starting station and
    destination station. It then compares the two stations' location on the tracks. Once the comparison is
    complete the passenger either gets added to the inbound platform or the outbound platform.
    @param passenger the arriving passenger
    */
	public void passengerArrives(Passenger passenger) {
		int clockwise_distance = 0;
		int countercw_distance = 0;
		Station destination = trainRoute.getStation(passenger.getDestinationStation());
		Station starting = trainRoute.getStation(passenger.getStartingStation());

		if(destination.location > starting.location){
			 clockwise_distance = destination.location - starting.location;
			 countercw_distance = trainRoute.getTrackLength() + starting.location - destination.location;
		}else{
			 clockwise_distance = trainRoute.getTrackLength() + destination.location - starting.location;
			 countercw_distance = starting.location - destination.location;
		}

		if(clockwise_distance < countercw_distance){
			inboundPlatform.enqueue(passenger);
		}else{
			outboundPlatform.enqueue(passenger);
		}

	}
    /**
    When the train arrives the queues on the platforms start to add the passengers on the train. This keeps happening
    until the train tells the station that it is full or the platform is out of characters.
    @param train the arriving train
    @return the number of passengers that boarded the train
    */
	public int trainArrives(Train train){
		int passengersBoarded = 0;
		if(train.getDirection() == Direction.CLOCKWISE){
			while(!train.isFull() && !inboundPlatform.isEmpty()){
				Passenger passenger = inboundPlatform.dequeue();
				Logger.passengerBoardsTrainFromStation(passenger, train, this);
				train.boardPassengers(passenger);
				passengersBoarded++;
			}
		}else{
			while(!train.isFull() && !outboundPlatform.isEmpty()){
				Passenger passenger = outboundPlatform.dequeue();
				Logger.passengerBoardsTrainFromStation(passenger, train, this);
				train.boardPassengers(passenger);
				passengersBoarded++;
			}
		}
		return passengersBoarded;
	}
	
	/**
	 * Returns a string identifying the station.
	 * 
	 * @return A string representing the station with its unique id.
	 */
	public String toString(){
		return "Station " + stationID;
	}

}
