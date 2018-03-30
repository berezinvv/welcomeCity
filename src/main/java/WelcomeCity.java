import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class WelcomeCity {

    private String nameCity;
    private String timeZoneCity;

    private static final Logger LOG = LogManager.getLogger(WelcomeCity.class);

    public WelcomeCity() {
        timeZoneCity = "";
    }

    public WelcomeCity(String nameCity) {
        this.nameCity = nameCity;
        this.timeZoneCity = "";
    }

    public WelcomeCity(String nameCity, String timeZoneCity) {
        this.nameCity = nameCity;
        this.timeZoneCity = timeZoneCity;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getTimeZoneCity() {
        return timeZoneCity;
    }

    public void setTimeZoneCity(String timeZone) {
        this.timeZoneCity = timeZone;
    }

    public String getCorrectCityName(String cityName) {

        return cityName.trim().replace(" ", "_");
    }

    public String getWelcomeText(int hourOfDay) {
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

    public String getWelcomeCity() {
        Calendar timeCity = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        List<String> zoneList = new ArrayList<>();
        ResourceBundle mybundle = ResourceBundle.getBundle("message");

        LOG.info("nameCity " + nameCity);
        LOG.info("timeZoneCity " + timeZoneCity);

        try {

            if (timeZoneCity.isEmpty()) {

                zoneList = ZoneId.getAvailableZoneIds().stream()
                        .filter(id -> id.contains(getCorrectCityName(nameCity))).collect(Collectors.toList());
            } else {
                timeCity = Calendar.getInstance(TimeZone.getTimeZone(timeZoneCity));
            }

            if (zoneList.size() > 0 && timeZoneCity.isEmpty()) {
                for (String timeZone : zoneList) {
                    timeCity.setTimeZone(TimeZone.getTimeZone(timeZone));
                    LOG.info("TimeZone " + timeZone);
                    LOG.info("HOUR_OF_DAY " + timeCity.get(Calendar.HOUR_OF_DAY));
                    return mybundle.getString(getWelcomeText(timeCity.get(Calendar.HOUR_OF_DAY))) + ", " + nameCity + "!";
                }
            } else {
                LOG.info("HOUR_OF_DAY " + timeCity.get(Calendar.HOUR_OF_DAY));
                return mybundle.getString(getWelcomeText(timeCity.get(Calendar.HOUR_OF_DAY))) + ", " + nameCity + "!";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            LOG.error("", ex);
        }
        return "";
    }

}
