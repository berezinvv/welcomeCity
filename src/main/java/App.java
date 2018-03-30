import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class App {

    static final Logger LOG = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        LOG.info("START");
        if (args.length == 0) {
            System.out.println("No options");
            LOG.info("No options");
            return;
        }

        WelcomeCity welcomeCity = new WelcomeCity();

        welcomeCity.setNameCity(args[0]);

        if (args.length >= 2) {
            welcomeCity.setTimeZoneCity(args[1]);
        }
        System.out.println(welcomeCity.getWelcomeCity());
        LOG.info("END");
    }
}
