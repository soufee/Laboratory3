package main.utils.eceptions;

import listeners.MySessionListener;
import org.apache.log4j.Logger;

/**
 * Created by Shoma on 23.04.2017.
 */
public class UserIsRegisteredException extends Exception {
    private static Logger userLogger = Logger.getLogger(MySessionListener.class);

    public UserIsRegisteredException(){

        String message = "User with such login or email is registered in the system";
        userLogger.debug("Получена ошибка некорректного ввода пароля" + message);
    }
}
