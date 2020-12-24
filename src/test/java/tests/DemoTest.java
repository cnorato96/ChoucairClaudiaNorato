package tests;

import environment.EnvironmentManager;
import environment.RunEnvironment;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.interactions.Actions;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DemoTest {

    WebDriver driver;
    String [][] arraylinks = {{"http://iso25000.com/index.php/normas-iso-25000/iso-25010", "//*[@href=\"http://iso25000.com/index.php/normas-iso-25000/iso-25010\"]"},
            {"http://www.rspa.com/spi/index.html", "//*[@href=\"http://www.rspa.com/spi/index.html\"]"},
            {"http://www.youtube.com/watch?v=bipTWWHya8A", "//*[@href=\"http://www.youtube.com/watch?v=bipTWWHya8A\"]"},
            {"https://www.youtube.com/watch?v=EzVolGw-P3k", "//*[@href=\"https://www.youtube.com/watch?v=EzVolGw-P3k\"]"},
            {"https://www.pingdom.com/blog/2009", "//*[@href=\"https://www.pingdom.com/blog/2009\"]"},
            {"https://www.youtube.com/watch?v=Hf-47kSvkHc&t=12s", "//*[@href=\"https://www.youtube.com/watch?v=Hf-47kSvkHc&t=12s\"]"},
            {"https://www.choucairtesting.com/wp-content/uploads/2018/09/ISO29119-Estandar-para-las-Pruebas-de-Software-2007.pdf", "//*[@href=\"https://www.choucairtesting.com/wp-content/uploads/2018/09/ISO29119-Estandar-para-las-Pruebas-de-Software-2007.pdf\"]"},
            {"https://www.youtube.com/watch?v=yph3_90SNGM&feature=youtu.be", "//*[@href=\"https://www.youtube.com/watch?v=yph3_90SNGM&feature=youtu.be\"]"},
            {"https://www.youtube.com/watch?v=Uo2xvx7zhwo", "//*[@href=\"https://www.youtube.com/watch?v=Uo2xvx7zhwo\"]"},
            {"https://www.youtube.com/watch?v=RQRkRLYujWA", "//*[@href=\"https://www.youtube.com/watch?v=RQRkRLYujWA\"]"},
            {"https://www.youtube.com/watch?v=JuwHxdWkRH0", "//*[@href=\"https://www.youtube.com/watch?v=JuwHxdWkRH0\"]"},
            {"https://www.youtube.com/watch?v=ZMFaUvJTW-4", "//*[@href=\"https://www.youtube.com/watch?v=ZMFaUvJTW-4\"]"},
            {"https://www.youtube.com/watch?v=AEiRa5xZaZw", "//*[@href=\"https://www.youtube.com/watch?v=AEiRa5xZaZw\"]"},
            {"https://www.youtube.com/watch?v=mzI90pTT5PY", "//*[@href=\"https://www.youtube.com/watch?v=mzI90pTT5PY\"]"},
            {"https://www.youtube.com/watch?v=M_M8T8G4Og8", "//*[@href=\"https://www.youtube.com/watch?v=M_M8T8G4Og8\"]"},
            {"../wp-content/uploads/2018/09/manual-practico-sql.pdf", "//*[@href=\"../wp-content/uploads/2018/09/manual-practico-sql.pdf\"]"},
            {"../wp-content/uploads/2018/09/fundamentosgerenciaproyectos.pdf", "//*[@href=\"../wp-content/uploads/2018/09/fundamentosgerenciaproyectos.pdf\"]"},
            {"https://www.youtube.com/watch?v=5ISd3NssJKs", "//*[@href=\"https://www.youtube.com/watch?v=5ISd3NssJKs\"]"},};
    String [][] arraysearch = {{"Fernando","Google"},
            {"Analista de pruebas","Panamá"},
            {"Claudia","Medellín"},
            {"","Medellín"}};
    String [][] arrayTittle = {{"Servicios – Choucair Testing","Servicios"},
            {"empleos testing – Choucair Testing","Empleos"},
            {"Industrias – Choucair Testing","Industrias"},
            {"empleos testing – Choucair Testing","Empleos"},
            {"Formación – Choucair Testing","Formación"},
            {"empleos testing – Choucair Testing","Empleos"},
            {"Comunidad – Choucair Testing","Comunidad"},
            {"empleos testing – Choucair Testing","Empleos"},
            {"Nosotros testing – Choucair Testing","Nosotros"},{"empleos testing – Choucair Testing","Empleos"},
            {"Contáctenos – Choucair Testing","Contáctenos"},};

    @Before
    public void startBrowser() {
        EnvironmentManager.initWebDriver();
        driver = RunEnvironment.getWebDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.choucairtesting.com/empleos-testing/");
    }

    @After
    public void tearDown() {
        EnvironmentManager.shutDownDriver();
    }

    @Test
    public void test1Searchtests () throws InterruptedException {
        for (int i = 0; i < arraysearch.length; i++) {
            WebElement textos = driver.findElement(By.name("search_keywords"));
            textos.sendKeys(arraysearch[i][0]);
            WebElement textos1 = driver.findElement(By.name("search_location"));
            textos1.sendKeys(arraysearch[i][1]);
            WebElement Boton =  driver.findElement(By.xpath("//*[@value=\"Search Jobs\"]"));
            Boton.click();
            Thread.sleep(5000);
            textos.clear();  textos1.clear();
            Boolean existeElemento = driver.findElements(By.xpath("//*[@class=\"job_listings\"]")).size() != 0;
            System.out.println(existeElemento);
            Assert.assertTrue(existeElemento);
        }
    }

    @Test
    public void test2Linktests () throws InterruptedException {
        Thread.sleep(5000);
        for (int i = 0; i < arraylinks.length; i++) {
            WebElement obtener = driver.findElement(By.xpath(arraylinks[i][1]));
            Actions actions = new Actions(driver);
            actions.moveToElement(obtener).click().perform();
            Thread.sleep(5000);
            ArrayList<String> windowshanldle = new ArrayList(driver.getWindowHandles());
            driver.switchTo().window(windowshanldle.get(1));
            String actual = driver.getCurrentUrl();
            Thread.sleep(3000);
            String experado = arraylinks[i][0];
            Assert.assertEquals(experado,actual);
            windowshanldle.remove(windowshanldle.get(1));
            driver.close();
            driver.switchTo().window(windowshanldle.get(0));
            Thread.sleep(2000);
        }
    }

    @Test
    public void test3Tittle() throws InterruptedException {
        Thread.sleep(5000);
        for (int i = 0; i < arrayTittle.length; i++) {
            WebElement obtener = driver.findElement(By.linkText(arrayTittle[i][1]));
            obtener.click();
            Thread.sleep(5000);
            String actual = driver.getTitle();
            Thread.sleep(3000);
            String experado = arrayTittle[i][0];
            Assert.assertEquals(experado,actual);
        }
    }
}
