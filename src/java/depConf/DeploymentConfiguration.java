package depConf;

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
import java.util.Random;
import java.util.TimeZone;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DeploymentConfiguration implements ServletContextListener
{

    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        Random rand = new Random(System.currentTimeMillis());
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_OPENSHIFT");
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            Airline al = new Airline("Group_6 Airlines INC.");
            Airport airportCPH = new Airport("CPH", "CET", "Copenhagen Kastrup", "Denmark", "Copenhagen");
            Airport airportSTN = new Airport("STN", "GMT+1", "London Stansted Airport", "England", "London");
            Flight flight = new Flight(al, "A1", "2 hours", (short) 100, airportSTN, airportCPH);
            long count = 1459720800000L;
            for (int i = 0; i < 20; i++)
            {
                FlightInstance flightInstance = new FlightInstance(flight, "CPH-STN-" + i + rand.nextInt(100), getISO8601StringForDate(new Date(count)), 120, (short) rand.nextInt(100), (float) 75.0);
                Reservation reservation = new Reservation((float) 150.0, flightInstance);
                Passenger pass1 = new Passenger("Bob " + i, "Arne " + i);
                Passenger pass2 = new Passenger("John " + i, "Lamasen " + i);
                reservation.addPassenger(pass2);
                reservation.addPassenger(pass1);
                flight.addFlightInstance(flightInstance);
                flightInstance.addReservation(reservation);

                count += 604800000L;
            }

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

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
 
    }

    private static String getISO8601StringForDate(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("CET"));
        return dateFormat.format(date);
    }

}
