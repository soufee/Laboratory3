package listeners;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Shoma on 20.04.2017.
 */
public class MySessionListener implements HttpSessionListener{

    static final Logger rootLogger = LogManager.getRootLogger();
    static final Logger userLogger = LogManager.getLogger(MySessionListener.class);

    public void sessionCreated(HttpSessionEvent se) {
        userLogger.debug("Смотри, логгер работает! = " + se.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }


}
