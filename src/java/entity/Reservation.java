/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author pagh
 */
@Entity
public class Reservation implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float price;
    @ManyToOne(fetch = FetchType.LAZY)
    private FlightInstance fi;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Passenger> passengers = new ArrayList();

    public Reservation(short price, FlightInstance fi, List<Passenger> passengers)
    {
        this.price = price;
        this.fi = fi;
        this.passengers = passengers;

    }

    public Reservation(float price, FlightInstance fi)
    {
        this.price = price;
        this.fi = fi;
    }

    public Reservation()
    {
    }

    public void addPassenger(Passenger p)
    {
        p.setReserv(this);
        passengers.add(p);

    }

    public void removePassenger(Passenger p)
    {
        passengers.remove(p);
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public FlightInstance getFi()
    {
        return fi;
    }

    public void setFi(FlightInstance fi)
    {
        this.fi = fi;
    }

    public List<Passenger> getPassengers()
    {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers)
    {
        this.passengers = passengers;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation))
        {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Reservation[ id=" + id + " ]";
    }

}
