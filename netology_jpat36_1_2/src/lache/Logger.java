package lache;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    protected int num = 1;

    private static Logger logger = null;

    private Logger() {
    }

    public void log(String msg) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date dateNow = new Date();

        System.out.println("[" + ft.format(dateNow) + " " + num++ + "] " + msg);
    }

    public static Logger getInstance() {
        if (logger == null) logger = new Logger();
        return logger;
    }

}
