package ru.nc.webshop1.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import ru.nc.webshop1.util.HibernateUtil;

public class HibernateListener implements ServletContextListener {  
  
    @Override
    public void contextInitialized(ServletContextEvent event) {  
        HibernateUtil.getSessionFactory();      
    }  
  
    @Override
    public void contextDestroyed(ServletContextEvent event) {  
        HibernateUtil.getSessionFactory().close();
    }  
}  
