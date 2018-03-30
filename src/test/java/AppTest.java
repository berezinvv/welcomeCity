import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class AppTest {
    @Test
    public void getCorrectCityName() throws Exception {
        Assert.assertEquals("New_York", new WelcomeCity().getCorrectCityName(" New York "));
    }

    @Test
    public void getWelcomeText() throws Exception {
        WelcomeCity welcomeCity = new WelcomeCity();
        Assert.assertEquals("Good_morning", welcomeCity.getWelcomeText(6));
        Assert.assertEquals("Good_afternoon", welcomeCity.getWelcomeText(9));
        Assert.assertEquals("Good_evening", welcomeCity.getWelcomeText(19));
        Assert.assertEquals("Good_night", welcomeCity.getWelcomeText(23));
    }

    @Test
    public void checkCity() throws Exception {
        Calendar timeCity = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        ResourceBundle mybundle = ResourceBundle.getBundle("message");

        String nameCity = "Dnipro";

        Assert.assertEquals(mybundle.getString( new WelcomeCity().getWelcomeText(timeCity.get(Calendar.HOUR_OF_DAY))) + ", " + nameCity + "!",
                new WelcomeCity(nameCity).getWelcomeCity());


        nameCity = "New York";

        Assert.assertNotEquals(mybundle.getString( new WelcomeCity().getWelcomeText(timeCity.get(Calendar.HOUR_OF_DAY))) + ", " + nameCity + "!",
                new WelcomeCity(nameCity).getWelcomeCity());
    }
}