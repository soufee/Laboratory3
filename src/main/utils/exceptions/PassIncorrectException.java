package main.utils.exceptions;

import main.webservices.listeners.MySessionListener;
import org.apache.log4j.Logger;

/**
 * Created by Shoma on 23.04.2017.
 */
public class PassIncorrectException extends Exception{
    private static Logger userLogger = Logger.getLogger(MySessionListener.class);

        public PassIncorrectException(){


        }

    }


