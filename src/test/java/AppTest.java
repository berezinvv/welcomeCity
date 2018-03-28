import org.junit.Assert;
import org.junit.Test;

public class AppTest {
    @Test
    public void getCorrectCityName() throws Exception {
        Assert.assertEquals("New_York", App.getCorrectCityName(" New York "));
    }

    @Test
    public void getWelcomeText() throws Exception {
        Assert.assertEquals("Good_morning", App.getWelcomeText(6));
        Assert.assertEquals("Good_afternoon", App.getWelcomeText(9));
        Assert.assertEquals("Good_evening", App.getWelcomeText(19));
        Assert.assertEquals("Good_night", App.getWelcomeText(23));
    }
}