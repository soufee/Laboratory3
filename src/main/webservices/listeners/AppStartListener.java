package main.webservices.listeners;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Shoma on 20.04.2017.
 */
public class AppStartListener implements ServletContextListener {



    public void contextInitialized(ServletContextEvent servletContextEvent) {
        PropertyConfigurator.configure(AppStartListener.class.getClassLoader().getResource("log4j.xml"));

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
