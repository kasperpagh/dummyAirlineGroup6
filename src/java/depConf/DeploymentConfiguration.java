package depConf;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DeploymentConfiguration implements ServletContextListener
{

    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dummyAirline6PU");
        EntityManager em = emf.createEntityManager();
        try
        {
            
        }
        catch(Exception e)
        {

        }
        finally
        {
            
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
