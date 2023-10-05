package dlv;
import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;

@Id("budget")
public class Budget {
    @Param(0)
    private int value;

    public Budget(){}

    public Budget(int value)
    {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void updateBudget(WebDriver driver) {
        String budget =driver.findElement(By.xpath("//div[@class='player-info--stash--container']/h5")).getText();
        try {
            setValue(Integer.parseInt(budget));
        }catch (NumberFormatException e)
        {
            setValue(0);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
