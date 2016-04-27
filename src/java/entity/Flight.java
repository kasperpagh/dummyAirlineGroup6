/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author pagh
 */
@Entity
public class Flight implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Airline airline;  
    private String flightNumber;
    private String flightTime;
    private short seats;
    @ManyToOne(fetch = FetchType.EAGER)
    private Airport airport;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "flight")
    private List<FlightInstance> flightInstances;
    
    public void addFlightInstance(FlightInstance fi)
    {
        flightInstances.add(fi);
    }
       public void removeFlightInstance(FlightInstance fi)
    {
        flightInstances.remove(fi);
    }

    public Flight(Airline airline, String flightNumber, String flightTime, short seats, Airport airport, List<FlightInstance> flightInstances)
    {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.flightTime = flightTime;
        this.seats = seats;
        this.airport = airport;
        this.flightInstances = flightInstances;
    }

    public Flight()
    {
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flight)) {
            return false;
        }
        Flight other = (Flight) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Flight[ id=" + id + " ]";
    }

    public Airline getAirline()
    {
        return airline;
    }

    public void setAirline(Airline airline)
    {
        this.airline = airline;
    }



    public String getFlightNumber()
    {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber)
    {
        this.flightNumber = flightNumber;
    }

    public String getFlightTime()
    {
        return flightTime;
    }

    public void setFlightTime(String flightTime)
    {
        this.flightTime = flightTime;
    }

    public short getSeats()
    {
        return seats;
    }

    public void setSeats(short seats)
    {
        this.seats = seats;
    }

    public Airport getAirport()
    {
        return airport;
    }

    public void setAirport(Airport airport)
    {
        this.airport = airport;
    }

    public List<FlightInstance> getFlightInstances()
    {
        return flightInstances;
    }

    public void setFlightInstances(List<FlightInstance> flightInstances)
    {
        this.flightInstances = flightInstances;
    }
    
}
