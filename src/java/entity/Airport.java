package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author pagh
 */
@Entity
public class Airport implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String IATACode;
    private String timeZone;
    private String name;
    private String country;
    private String city;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "airport")
    private List<Flight> flightList;

    public Airport()
    {
    }

    public void addFlight(Flight f)
    {
        flightList.add(f);
    }
     public void removeFlight(Flight f)
    {
        flightList.remove(f);
    }


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Airport(String IATACode, String timeZone, String name, String country, String city, List<Flight> flightList)
    {
        this.IATACode = IATACode;
        this.timeZone = timeZone;
        this.name = name;
        this.country = country;
        this.city = city;
        this.flightList = flightList;
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
        if (!(object instanceof Airport))
        {
            return false;
        }
        Airport other = (Airport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Airport[ id=" + id + " ]";
    }

    public String getIATACode()
    {
        return IATACode;
    }

    public void setIATACode(String IATACode)
    {
        this.IATACode = IATACode;
    }

    public String getTimeZone()
    {
        return timeZone;
    }

    public void setTimeZone(String timeZone)
    {
        this.timeZone = timeZone;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public List<Flight> getFlightList()
    {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList)
    {
        this.flightList = flightList;
    }

}
