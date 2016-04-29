/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.FlightInstance;
import entity.Passenger;
import entity.Reservation;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author kaspe
 */
@Path("reservation")
public class Reserver
{

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_OPENSHIFT");

    @POST
    @Path("/{flightId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String makeReservation(@PathParam("flightId") String flightId, String personJson)
    {
        EntityManager em = emf.createEntityManager();
        try
        {

            em.getTransaction().begin();
            Query query = em.createNamedQuery("findById", FlightInstance.class);
            query.setParameter("flightId", flightId);
            FlightInstance fi = (FlightInstance) query.getResultList().get(0);
            Reservation res = new Reservation();
            res.setFi(fi);

            JsonObject jsonIn = new JsonParser().parse(personJson).getAsJsonObject();
            String reserveeName = jsonIn.get("reserveeName").getAsString();
            res.setReserveeName(reserveeName);
            JsonArray ja = jsonIn.get("passengers").getAsJsonArray();

            res.setPrice(fi.getPrice() * ja.size());
            for (int i = 0; i < ja.size(); i++)
            {
                res.addPassenger(new Passenger(ja.get(i).getAsJsonObject().get("firstName").getAsString(), ja.get(i).getAsJsonObject().get("lastName").getAsString()));

            }

            List<Reservation> reL = new ArrayList();
            reL.add(res);

            fi.addReservation(res);
            fi.setAvailableSeats((short) (fi.getAvailableSeats() - res.getPassengers().size()));

            em.getTransaction().commit();

            //////////////
            JsonObject jo = new JsonObject();
            jo.addProperty("flightNumber", flightId);
            jo.addProperty("origin", fi.getFlight().getAirportFrom().getName() + fi.getFlight().getAirportFrom().getIATACode());
            jo.addProperty("destination", fi.getFlight().getAirportTo().getName() + fi.getFlight().getAirportTo().getIATACode());
            jo.addProperty("date", fi.getDato());
            jo.addProperty("flightTime", fi.getFlight().getFlightTime());
            jo.addProperty("numberOfSeats", fi.getAvailableSeats());
            jo.add("passengers", ja);
            return gson.toJson(jo);
            
        }
        finally
        {
            em.close();
        }

    }
}
