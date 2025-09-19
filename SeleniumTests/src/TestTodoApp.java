import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

public class TestTodoApp {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mellow-sopapillas-66dd49.netlify.app/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("🔄 Setup complete, browser opened.");
    }

    @Test
    public void testHeadingVisible() {
        WebElement heading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/h1"))
        );
        assertTrue(heading.isDisplayed(), "Heading should be visible");
        assertEquals("To-Do App", heading.getText());
        System.out.println("✅ testHeadingVisible passed: Heading is visible and correct.");
    }

    @Test
    public void testAddTask() {
        WebElement enterTask = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/input"))
        );
        WebElement addButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/button"));

        String taskText = "Complete Selenium Assignment";
        enterTask.sendKeys(taskText);
        addButton.click();
        System.out.println("➕ Task added: " + taskText);

        WebElement addedTask = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'" + taskText + "')]"))
        );
        assertTrue(addedTask.isDisplayed(), "Task should be added");
        System.out.println("✅ testAddTask passed: Task was added successfully.");
    }

    @Test
    public void testDeleteTask() {
        WebElement enterTask = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/input"))
        );
        WebElement addButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/button"));

        String taskText = "Temporary Task";
        enterTask.sendKeys(taskText);
        addButton.click();
        System.out.println("➕ Task added for deletion test.");

        WebElement addedTask = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'" + taskText + "')]"))
        );
        WebElement deleteButton = addedTask.findElement(By.xpath("./button[text()='Delete']"));
        deleteButton.click();

        boolean taskGone = wait.until(
                ExpectedConditions.invisibilityOfElementLocated(By.xpath("//li[contains(text(),'" + taskText + "')]"))
        );
        assertTrue(taskGone, "Task should be deleted ");
        System.out.println("✅ testDeleteTask passed: Task was deleted successfully.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            try {
                // Delete all tasks before quitting
                while (true) {
                    List<WebElement> deleteButtons = driver.findElements(By.xpath("//li/button[text()='Delete']"));
                    if (deleteButtons.isEmpty()) break;
                    deleteButtons.get(0).click();
                    Thread.sleep(500);
                }
                System.out.println("🧹 Cleanup complete: All tasks deleted.");
            } catch (Exception e) {
                System.out.println("⚠️ Cleanup failed: " + e.getMessage());
            } finally {
                driver.quit();
                System.out.println("🚪 Browser closed.");
            }
        }
    }
}
