import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class App {

    static final Logger LOG = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        Calendar timeCity = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        List<String> zone = new ArrayList<>();
        ResourceBundle mybundle = ResourceBundle.getBundle("message");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название города : ");
        String nameCity = scanner.nextLine();
        LOG.info("nameCity " + nameCity);

        int timeZoneCity = 0;
        try {
            System.out.print("Введите идетификатор часового пояса [-11,14] : ");
            timeZoneCity = Integer.parseInt(scanner.nextLine());
            LOG.info("timeZoneCity " + timeZoneCity);
        } catch (Exception ex) {
            ex.printStackTrace();
            LOG.error("", ex);
        }

        if (nameCity.isEmpty()) {
            System.out.println("Название города введено не верно.");
            return;
        }

        try {

            if (timeZoneCity < -11 || timeZoneCity > 14) {
                System.out.println("Не вверный идетификатор часового пояса.");
                timeZoneCity = 0;
            } else if (timeZoneCity == 0) {

                zone = ZoneId.getAvailableZoneIds().stream()
                        .filter(id -> id.contains(getCorrectCityName(nameCity))).collect(Collectors.toList());
            }

            if (zone.size() > 0 && timeZoneCity == 0) {
                for (String timeZone : zone) {
                    timeCity.setTimeZone(TimeZone.getTimeZone(timeZone));
                    LOG.info("TimeZone " + timeZone);
                    LOG.info("HOUR_OF_DAY " + timeCity.get(Calendar.HOUR_OF_DAY));
                    System.out.println(mybundle.getString(getWelcomeText(timeCity.get(Calendar.HOUR_OF_DAY))) + ", " + nameCity + "!");
                    break;
                }
            } else {
                LOG.info("HOUR_OF_DAY " + timeCity.get(Calendar.HOUR_OF_DAY));
                System.out.println(mybundle.getString(getWelcomeText(timeCity.get(Calendar.HOUR_OF_DAY) + timeZoneCity)) + ", " + nameCity + "!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            LOG.error("", ex);
        }
    }

    public static String getCorrectCityName(String cityName) {

        return cityName.trim().replace(" ", "_");
    }

    public static String getWelcomeText(int hourOfDay) {
        if (hourOfDay >= 6 && hourOfDay < 9) {
            return "Good_morning";
        } else if (hourOfDay >= 9 && hourOfDay < 19) {
            return "Good_afternoon";
        } else if (hourOfDay >= 19 && hourOfDay < 23) {
            return "Good_evening";
        } else {
            return "Good_night";
        }
    }
}
