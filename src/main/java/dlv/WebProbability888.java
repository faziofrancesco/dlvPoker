package dlv;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import dlv.Card;
public class WebProbability888 {
    public int cont=1;
    private WebDriver driver;
    public WebProbability888() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
        FirefoxOptions options=new FirefoxOptions();
        options.addArguments("--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors", "--headless", "--disable-infobars", "--disable-extensions");
        //options.addArguments( "--window-size=1920,1200","--ignore-certificate-errors", "--disable-extensions");

        driver=new FirefoxDriver(options);
        driver.navigate().to("https://www.888poker.com/poker/poker-odds-calculator");
        driver.manage().window().fullscreen();

        //Chiude banner dei cookie, sembra possa dare problemi
        if(driver.findElements(By.xpath("div[@class='message_block']/img[2]")).size()!=0  )
       driver.findElement(By.xpath("div[@class='message_block']/img[2]")).click();



    }
    public void setAvversario(int N)//l'avversario rientra nel turno attuale
    {
       // System.out.println("HO settato "+N);
        int player=N+2;
        driver.findElement(By.xpath("//*[@id=\"player-listing\"]/div["+String.valueOf(player)+"]/div[3]/p")).click();
        cont++;
    }
    public int GetProbabilityVictory()
    {
        try {
            Thread.sleep(500);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        String value=driver.findElement(By.xpath("//*[@id=\"player-win-0\"]/span[2]")).getText();
        return (int) Float.parseFloat(value.substring(0,value.length()-1));
    }
    public void setcards(dlv.Card card, int poscard)
    {
        new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"poker-table\"]/ul/li["+String.valueOf(poscard)+"]/div/div/div[2]")));
        clickAndCheckPopup(By.xpath("//*[@id=\"poker-table\"]/ul/li["+String.valueOf(poscard)+"]/div/div/div[2]"));
        putCard(card);
        new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.id("card-selection-complete")));
        clickAndCheckPopup(By.id("card-selection-complete"));
    }
    private void putCard(dlv.Card card)
    {
        int number=card.getNumber()-1;
        int seed=0;
        if(card.getSeed().equals("clubs"))
        {
            seed=1;
        }
        if(card.getSeed().equals("diamonds"))
        {
            seed=2;
        }
        if(card.getSeed().equals("spades"))
        {
            seed=3;
        }
        if(card.getSeed().equals("hearts"))
        {
            seed=4;
        }
        //da aggiustare il fatto che per come e ora non possiamo inserire numeri a meno di if
        new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"card-selection-table\"]/div[1]/div["+String.valueOf(seed)+"]/a["+String.valueOf(number)+"]/span")));
        clickAndCheckPopup(By.xpath("//*[@id=\"card-selection-table\"]/div[1]/div["+String.valueOf(seed)+"]/a["+String.valueOf(number)+"]/span"));

    }
    public void setPlayerCards(Card card1, Card card2) {
        //new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"player-listing\"]/div[1]/div[3]/div[1]/div[1]/div/div/div[2]")));
        clickAndCheckPopup(By.xpath("//*[@id=\"player-listing\"]/div[1]/div[3]/div[1]/div[1]/div/div/div[2]"));
        putCard(card1);
        putCard(card2);
        clickAndCheckPopup(By.id("card-selection-complete"));
    }
    private void clickAndCheckPopup(By by)
    {
        boolean exit = false;
        while (!exit) {
            try {
                driver.findElement(by).click();
                exit = true;
            } catch (Exception e) {
                closePopup();
                exit = false;
            }
        }
    }

    private void closePopup()
    {
        try {
            driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[6]/div[1]/div[1]/div[1]/button[1]/div[1]")).click();
        }catch (Exception e){}
    }
    public void closeWeb()
    {
        driver.close();
    }
    public void resetTable() throws ElementClickInterceptedException {
        new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.id("reset-poker-table-desktop")));
        clickAndCheckPopup(By.id("reset-poker-table-desktop"));
        new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.id("reset-confirm-btn")));
        clickAndCheckPopup(By.id("reset-confirm-btn"));
    }
    //elimina sempre il primo avversario che si ritrova
    public void deletefirst()
    {
     /*   new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"player-listing\"]/div["+String.valueOf(pos)+"]")));
        if(driver.findElement(By.xpath("//*[@id=\"player-listing\"]/div["+String.valueOf(pos)+"]")).getAttribute("data-status").equals("active")) {
            WebElement elm =driver.findElement(By.xpath("//*[@id=\"player-listing\"]/div[\"++String.valueOf(pos)+\"]/div[1]"));

        }*/

     /*
     ID del bottone div@data-remove-player=1

      */
        WebElement el = driver.findElement(By.cssSelector("#player-win-1 > span.stat"));
        Actions builder = new Actions(driver);
        builder.moveToElement(el).click(el);
        builder.perform();
        new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"player-listing\"]/div[2]/div[2]")));
        WebElement element=driver.findElement(By.xpath("//*[@id=\"player-listing\"]/div[2]/div[2]"));
        element.click();
        cont--;
    }



    public int numberPlayer888table(){



        return cont;
    }
}

