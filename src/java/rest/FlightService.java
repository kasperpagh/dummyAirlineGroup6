package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.FlightInstance;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author kaspe
 */
@Path("flightinfo")
public class FlightService
{

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_OPENSHIFT");

    @GET
    @Path("/{from}/{date}/{persons}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEscapePlan(@PathParam("from") String fromAirport, @PathParam("date") String dato, @PathParam("persons") String amountOfTickets)
    {
        EntityManager em = emf.createEntityManager();

        JsonObject jo = new JsonObject();
        jo.addProperty("airline", "Group_6 Airlines INC.");

        Query query = em.createNamedQuery("findByDato", FlightInstance.class);
        query.setParameter("dato", dato);
        query.setParameter("tickets", Integer.parseInt(amountOfTickets));
        query.setParameter("from", fromAirport);
        JsonArray ja = new JsonArray();

        for (int i = 0; i < query.getResultList().size(); i++)
        {
            FlightInstance fi = (FlightInstance) query.getResultList().get(i);

            JsonObject jo1 = new JsonObject();
            jo1.addProperty("flightID", fi.getFlightId());
            jo1.addProperty("numberOfSeats", fi.getAvailableSeats());
            jo1.addProperty("date", fi.getDato());
            jo1.addProperty("totalPrice", fi.getPrice());
            jo1.addProperty("travelTime", fi.getTid());
            jo1.addProperty("origin", fi.getFlight().getAirportFrom().getIATACode());
            jo1.addProperty("destination", fi.getFlight().getAirportTo().getIATACode());
            jo1.addProperty("flightNumber", fi.getFlight().getFlightNumber());

            ja.add(jo1);

        }

        jo.add("flights", ja);

        return gson.toJson(jo);
    }

    @GET
    @Path("/{from}/{to}/{date}/{persons}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFlights(@PathParam("from") String fromAirport, @PathParam("to") String toAirport, @PathParam("date") String dato, @PathParam("persons") String amountOfTickets)
    {
        EntityManager em = emf.createEntityManager();

        JsonObject jo = new JsonObject();
        jo.addProperty("airline", "Group_6 Airlines INC.");

        Query query = em.createNamedQuery("findByDato1", FlightInstance.class);
        query.setParameter("dato", dato);
        query.setParameter("tickets", Integer.parseInt(amountOfTickets));
        query.setParameter("from", fromAirport);
        query.setParameter("to", toAirport);
        JsonArray ja = new JsonArray();

        for (int i = 0; i < query.getResultList().size(); i++)
        {
            FlightInstance fi = (FlightInstance) query.getResultList().get(i);

            JsonObject jo1 = new JsonObject();
            jo1.addProperty("flightID", fi.getFlightId());
            jo1.addProperty("numberOfSeats", fi.getAvailableSeats());
            jo1.addProperty("date", fi.getDato());
            jo1.addProperty("totalPrice", fi.getPrice());
            jo1.addProperty("travelTime", fi.getTid());
            jo1.addProperty("origin", fi.getFlight().getAirportFrom().getIATACode());
            jo1.addProperty("destination", fi.getFlight().getAirportTo().getIATACode());
            jo1.addProperty("flightNumber", fi.getFlight().getFlightNumber());

            ja.add(jo1);

        }

        jo.add("flights", ja);

        return gson.toJson(jo);

    }
    
    

}
