/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.Airline;
import entity.Airport;
import entity.Flight;
import entity.FlightInstance;
import entity.Passenger;
import entity.Reservation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author kaspe
 */
public class Tester
{

    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dummyAirline6PU");
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            //dec//
            Date d = new Date(2016, 04, 04);
            Airline al = new Airline("Group_6 Airlines INC.");
            Airport airportCPH = new Airport("CPH", "CET", "Copenhagen Kastrup", "Denmark", "Copenhagen");
            Airport airportSTN = new Airport("STN", "GMT+1", "London Stansted Airport", "England", "London");
            Flight flight = new Flight(al, "A1", "2 hours", (short) 100, airportSTN, airportCPH);
            FlightInstance flightInstance = new FlightInstance(flight, "1a", getISO8601StringForDate(d), 120, (short) 45, (float) 75.0);
            Reservation reservation = new Reservation((float) 150.0, flightInstance);
            Passenger pass1 = new Passenger("Bob", "Arne");
            Passenger pass2 = new Passenger("John", "Lamasen");
            //dec//


            reservation.addPassenger(pass2);
            reservation.addPassenger(pass1);
            flightInstance.addReservation(reservation);
            flight.addFlightInstance(flightInstance);
            flight.setAirportTo(airportSTN);
            flight.setAirportFrom(airportCPH);
            al.addFlight(flight);
            em.persist(al);
            em.getTransaction().commit();

        }
        finally
        {
            em.close();
        }
    }

    public static String getISO8601StringForDate(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        dateFormat.setTimeZone(TimeZone.getTimeZone("CET"));
        return dateFormat.format(date);
    }

}
