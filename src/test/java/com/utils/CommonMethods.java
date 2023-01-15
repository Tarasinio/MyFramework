package com.utils;

import com.testbase.PageInitializer;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CommonMethods extends PageInitializer {


    /**
     * this method will return an object of Explicit wait with time set to 20 sec
     *
     * @return WebDriverWait
     */
    public static WebDriverWait getExplicitWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT));
    }

    /**
     * this method will wait until given element becomes clickable
     */
    public static void waitForClickability(WebElement element) {
        assert element != null;
        getFluentWait(10, 1).until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * this method will wait until given element becomes visible
     */
    public static void waitForVisibility(WebElement element) {
        getExplicitWait().until(ExpectedConditions.visibilityOf(element));
    }

    public static FluentWait<WebDriver> getFluentWait(long timeout, long pollingInterval) {
        if (timeout <= 0) {
            throw new IllegalArgumentException("Timeout must be a positive value");
        }
        if (pollingInterval <= 0) {
            throw new IllegalArgumentException("Polling interval must be a positive value");
        }
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(timeout));
        fluentWait.pollingEvery(Duration.ofSeconds(pollingInterval));
        fluentWait.ignoring(Exception.class);
        fluentWait.withMessage("Timed out waiting for condition to be met after " + timeout + " seconds with a polling interval of " + pollingInterval + " seconds");
        return fluentWait;
    }

    /**
     * this method will wait until given element becomes invisible
     */
    public static void waitForInvisibility(WebElement element) {
        getExplicitWait().until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * this method will wait until elements become invisible
     */
    public static void waitListForInvisibility(List<WebElement> elements) {
        getExplicitWait().until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    /**
     * this method will wait until given text in element becomes visible
     */
    public static void waitForTextPresent(WebElement element, String elementText) {
        waitForVisibility(element);
        getExplicitWait().until(ExpectedConditions.textToBePresentInElement(element, elementText));
    }

    /**
     * this method will wait until given List of elements becomes visible
     */
    public static void waitListForVisibility(List<WebElement> elements) {
        getExplicitWait().until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    /**
     * this method will wait until and then click
     */
    public static void click(WebElement element) {
        waitForVisibility(element);
        waitForClickability(element);
        element.click();
    }

    /**
     * this method will click and wait until element become clickable
     */
    public static void clickAndWaitTillClickable(WebElement element) {
        waitForClickability(element);
        element.click();
        getExplicitWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * this method will click on element and wait till exact Text become visible in the same element
     */
    public static void clickAndWaitTillTextPresent(WebElement element, String text) {
        waitForClickability(element);
        element.click();
        getExplicitWait().until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    /**
     * this method will click on element and wait till exact new element become visible
     */
    public static void clickAndWaitTillElementPresent(WebElement element, WebElement otherElement) {
        waitForClickability(element);
        element.click();
        getExplicitWait().until(ExpectedConditions.visibilityOf(otherElement));
    }

    /**
     * this method will click on element and wait till list of new elements become visible
     */
    public static void clickAndWaitTillElementsPresent(WebElement element, List<WebElement> elementsList) {
        waitForClickability(element);
        element.click();
        getExplicitWait().until(ExpectedConditions.visibilityOfAllElements(elementsList));
    }

    /**
     * this method will return an Object of Actions
     */
    public static Actions getActionBuilder() {
        return new Actions(driver);
    }

    /**
     * this method will click and wait Two seconds
     */
    public static void clickAndPause(WebElement element, long time) {
        waitForClickability(element);
        getActionBuilder().click(element).pause(time).build().perform();
    }

    /**
     * this method will clear a text box
     */
    public static void clearText(WebElement element) {
        waitForClickability(element);
        try {
            for (int i = 0; i <= 100; i++) {
                element.sendKeys(Keys.BACK_SPACE);
            }
        } catch (NoSuchElementException e) {
            element.clear();
        }
    }

    /**
     * this method will send text to element
     */
    public static void sendText(WebElement element, String textToSend) {
        waitForVisibility(element);
        element.sendKeys(textToSend);
    }

    /**
     * this method will return an Object of JavascriptExecutor
     */
    public static JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) driver;
    }

    /**
     * this method will click on element using JavascriptExecutor
     */
    public static void jsClick(WebElement element) {
        getJSExecutor().executeScript("arguments[0].click();", element);
    }

    /**
     * this method will Scroll Up page by providing negative number (f.e -250) in parameters using JavascriptExecutor
     */
    public static void jsScrollUp(int negativeNumberToScroll) {
        getJSExecutor().executeScript("window.scrollBy(0," + negativeNumberToScroll + ")");
    }

    /**
     * this method will Scroll Down page by providing positive number (f.e 250) in parameters using JavascriptExecutor
     */
    public static void jsScrollDown(int positiveNumberToScroll) {
        getJSExecutor().executeScript("window.scrollBy(0," + positiveNumberToScroll + ")");
    }

    /**
     * this method will Scroll page Into element view
     */
    public static void jsScrollIntoView(WebElement element) {
        waitForVisibility(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        pause(1000);
    }

    /**
     * this method will wait till all page elements be loaded using JavascriptExecutor
     */
    public static void jsWaitTillPageLoaded() {
        getExplicitWait().until(webDriver -> "complete".equals(getJSExecutor().executeScript("return document.readyState")));
    }

    /**
     * this method will click on element using JavascriptExecutor and wait for time provided in milliseconds
     */
    public static void jsClickAndPause(WebElement element, long time) {
        getJSExecutor().executeScript("arguments[0].click();", element);
        pause(time);
    }

    /**
     * this method will clear element using JavascriptExecutor
     */
    public static void jsClear(WebElement element) {
        getJSExecutor().executeScript("arguments[0].value = '';", element);
        element.sendKeys(Keys.ENTER);
    }

    /**
     * this method will send text using JavascriptExecutor
     */
    public static void jsSendText(WebElement element, String textToSend) {
        getJSExecutor().executeScript("arguments[0].value = '" + textToSend + "';", element);
    }

    /**
     * this method will pause thread for amound of time provided as a parameter in milliseconds
     */
    public static void pause(long time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method will refresh browser page
     */
    public static void reloadPage() {
        driver.navigate().refresh();
        jsWaitTillPageLoaded();
        pause(3000);
    }

    /**
     * This method will loop through list of elements and click if list Element contains String
     */
    public static boolean checkIfListContain(List<WebElement> list, String str) {
        for (WebElement element :
                list) {
            if (element.getText().trim().contains(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method generates random number form 1 till maximum sent number
     */
    public static int selectRandom(int maxNum) {
        int min = 1;
        int range = maxNum - min + 1;
        int random = 0;
        for (int i = 0; i < maxNum - 1; i++) {
            random = (int) (Math.random() * range);
        }
        return random;
    }

    /**
     * This method generates random number form 1 till maximum sent number
     */
    public static int selectRandom(int minNum, int maxNum) {
        int range = maxNum - minNum + 1;
        int random = 0;
        for (int i = 0; i < maxNum - 1; i++) {
            random = (int) (Math.random() * range) + minNum;
        }
        return random;
    }

    /**
     * this method will get random element from given List by the random index
     */
    public static WebElement getRandomElement(List<WebElement> webElements) {
        waitListForVisibility(webElements);
        if (webElements.size() > 0) {
            int randomIndex = selectRandom(webElements.size() - 1);
            return webElements.get(randomIndex);
        } else {
            return null;
        }
    }

    /**
     * this method will get element from List by exact text
     */
    public static WebElement getElementByExactText(List<WebElement> webElements, String visibleText) {
        waitListForVisibility(webElements);
        for (WebElement element :
                webElements) {
            String elementText = element.getAttribute("textContent").trim();
            if (elementText.equalsIgnoreCase(visibleText)) {
                return element;
            }
        }
        return null;
    }

    /**
     * this method will scroll dd to exact element
     */
    public static void scrollDdToElement(WebElement element) {
        waitForVisibility(element);
        getJSExecutor().executeScript("arguments[0].scrollIntoViewIfNeeded(true);", element);
    }

    /**
     * this method will navigate cursor to exact element
     */
    public static void navigateCursorToElement(WebElement element) {
        waitForVisibility(element);
        getActionBuilder().moveToElement(element).build().perform();
    }

    /**
     * this method will get element from List by contain text
     */
    public static WebElement getElementByContainText(List<WebElement> webElements, String containText) {
        waitListForVisibility(webElements);
        for (WebElement element :
                webElements) {
            String elementText = element.getText().toLowerCase();
            if (elementText.contains(containText.toLowerCase())) {
                return element;
            }
        }
        return null;
    }

    /**
     * this method will element from List by Array of Strings text
     */
    public static WebElement getElementByContainText(List<WebElement> webElements, String[] containText) {
        List<String> containTextLowCase = Arrays.stream(containText).map(String::toLowerCase).collect(Collectors.toList());
        waitListForVisibility(webElements);
        List<WebElement> elementChainList;
        List<String> elementContainTextList = new ArrayList<>();
        for (WebElement element :
                webElements) {
            elementChainList = element.findElements(By.tagName("*"));
            for (int i = 0; i < elementChainList.size(); i++) {
                elementContainTextList.add(elementChainList.get(i).getAttribute("textContent").toLowerCase().trim());
            }
            if (elementContainTextList.containsAll(containTextLowCase)) {
                return element;
            }
            elementChainList.clear();
            elementContainTextList.clear();
        }
        return null;
    }

    /**
     * this method will get element and will loop through this inner element to find contained text
     */
    public static WebElement elementAsList_ByTextAndXpath(WebElement element, String containText, String locator) {
        assert element != null;
        waitForVisibility(element);
        List<WebElement> list = element.findElements(By.xpath(locator));
        for (WebElement e :
                list) {
            String elementText = e.getText().toLowerCase();
            if (elementText.contains(containText.toLowerCase())) {
                return e;
            }
        }
        return null;
    }

    /**
     * this method will get element from given List by index
     */
    public static WebElement getElementByIndex(List<WebElement> webElements, int index) {
        return webElements.get(index);
    }

    /**
     * this method will assert if List of WE are not Null
     */
    public static boolean assertListNotNull(List<WebElement> webElements) {
        if (webElements == null) {
            throw new NullPointerException();
        }
        return true;
    }

    /**
     * this method will change Date formant. It Except Date as String, and two Patterns String patternOldFormat and String patternNewFormat format needed
     */
    public static String getDateFormat(String date, String patternOldFormat, String patternNewFormat) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternOldFormat);
            Date dateToFormat = simpleDateFormat.parse(date);
            simpleDateFormat.applyPattern(patternNewFormat);
            return simpleDateFormat.format(dateToFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * this method will take a screenshot and put today's data-time format in file name
     */
    public static byte[] takeScreenshot(String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] bytes = ts.getScreenshotAs(OutputType.BYTES);
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(sourceFile, new File(Constants.SCREENSHOT_FILEPATH + fileName + getTimeStamp(" yyyy-MM-dd-HH-mm-ss") + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * this method will return today's date format based on Pattern ir receives (" yyyy-MM-dd-HH-mm-ss")
     */
    public static String getTimeStamp(String pattern) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * This method will return String Full Month by sent int parameter of count from current month
     */
    public static String getForwardMonth(int monthForward) {
        LocalDate todayDate = LocalDate.now().plusMonths(monthForward);
        return getDateFormat(todayDate.toString(), "yyyy-MM-dd", "MMMM");
    }

    /**
     * this method will switch to a child window. And save mainTab to the Global
     */
    private static String mainWindow;

    public static void switchToChildTab() {
        mainWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        pause(2000);
    }

    /**
     * this method will switch to mainTab
     */
    public static void switchToMainTab() {
        driver.switchTo().window(mainWindow);
    }

    /**
     * this method will switch to next Tab
     */
    public static void switchToNewTab() {
        mainWindow = driver.getWindowHandle();
        getJSExecutor().executeScript("window.open('about:blank','_blank');");
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }

    /**
     * this method will switch to next Tab
     */
    public static void switchToNextTab() {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "" + Keys.TAB);
    }

    /**
     * this method will close current Tab
     */
    public static void closeCurrentTab() {
        driver.close();
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }

    public static Map<String, String> chooseUser(String user) {
        Map<String, String> userCredentials = new HashMap<>();
        switch (user) {
            case "admin":
                userCredentials.put("adminUsername", "adminPassword");
                break;
            default:
                LOGGER.info("User + " + user);
        }
        return userCredentials;
    }

    /**
     * this method will insert login credentials based on provided Map<String,String>
     */
    public static void enterCorrectCredAtLoginPage(String user) {
        for (String userEmail : chooseUser(user).keySet()) {
            String userPassword = chooseUser((user)).get(userEmail);
            sendText(loginPage.emailField, ConfigsReader.getPropertyValue(userEmail));
            sendText(loginPage.passwordField, ConfigsReader.getPropertyValue(userPassword));
        }
    }

    /**
     * this method will insert incorrect email by removing first char and correct password based on provided Map<String,String>
     */
    public static void enterIncorrectEmailCredAtLoginPage(String user) {
        for (String userEmail : chooseUser(user).keySet()) {
            String userPassword = chooseUser((user)).get(userEmail);
            sendText(loginPage.emailField, ConfigsReader.getPropertyValue(userEmail).substring(1));
            sendText(loginPage.passwordField, ConfigsReader.getPropertyValue(userPassword));
        }
    }

    /**
     * this method will insert incorrect password by removing first char and correct email based on provided Map<String,String>
     */
    public static void enterIncorrectPassCredAtLoginPage(String user) {
        for (String userEmail : chooseUser(user).keySet()) {
            String userPassword = chooseUser((user)).get(userEmail);
            sendText(loginPage.emailField, ConfigsReader.getPropertyValue(userEmail));
            sendText(loginPage.passwordField, ConfigsReader.getPropertyValue(userPassword).substring(1));
        }
    }

    /**
     * this method will get LoggedIn user Email based on provided Map<String,String>
     */
    public static String getLoggedInUserEmail(String user) {
        for (String userLogin : chooseUser(user).keySet()) {
            return ConfigsReader.getPropertyValue(userLogin);
        }
        return null;
    }

    public static void assertEquals(String actual, String expected) {
        Assert.assertEquals(actual.toLowerCase(), expected.toLowerCase());
    }

    public static void assertEquals(int actual, int expected) {
        Assert.assertEquals(actual, expected);
    }

    public static void assertEquals(double actual, double expected) {
        Assert.assertEquals(actual, expected);
    }

    public static void assertNotEquals(int actual, int expected) {
        Assert.assertNotEquals(actual, expected);
    }

    public static void assertTrue(boolean bool) {
        Assert.assertTrue(bool);
    }

    public static void assertFalse(String actual, String expected) {
        Assert.assertNotEquals(actual.toLowerCase(), expected.toLowerCase());
    }

    public static void assertFalse(boolean bool) {
        Assert.assertFalse(bool);
    }

    public static void isDisplayed(WebElement element) {
        assert element != null;
        waitForVisibility(element);
        assertTrue(element.isDisplayed());
    }

    public static void isEnabled(WebElement element) {
        assert element != null;
        waitForVisibility(element);
        assertTrue(element.isEnabled());
    }

    public static void is_Disabled(WebElement element) {
        assert element != null;
        waitForVisibility(element);
        assertTrue(!element.isEnabled());
    }

    public static void isDisplayedAndEnabled(WebElement element) {
        assert element != null;
        isDisplayed(element);
        isEnabled(element);
    }

    public static void isDisplayedAndContainText(WebElement element, String containText) {
        assert element != null;
        isDisplayed(element);
            assertTrue(element.getAttribute("textContent").toLowerCase().contains(containText.toLowerCase()));
    }

    public static void isDisplayedAndContainAttr(WebElement element, String attributeName, String attributeValue) {
        assert element != null;
        isDisplayed(element);
        assertTrue(element.getAttribute(attributeName).toLowerCase().contains(attributeValue.toLowerCase()));
    }

    public static void isEnabledAndContainText(WebElement element, String containText) {
        assert element != null;
        isEnabled(element);
        assertTrue(element.getAttribute("textContent").toLowerCase().contains(containText.toLowerCase()));
    }

    public static void isEnabledAndContainAttr(WebElement element, String attributeName, String attributeValue) {
        assert element != null;
        isEnabled(element);
        assertTrue(element.getAttribute(attributeName.toLowerCase()).contains(attributeValue.toLowerCase()));
    }

    public static String getEventOwner(String userName) {
        assert userName != null;
        return Constants.USERS_FULL_NAME_LIST.get(userName);
    }

    public static String getRandomLocation(String[] locationsList) {
        assert locationsList != null;
        return locationsList[selectRandom(locationsList.length - 1)];
    }

    public static void selectDdByText(WebElement ddElement, String text) {
        Select select = new Select(ddElement);
        select.selectByVisibleText(text);
    }

    public static void navigateToURI(String urlEndPoint) {
        //assert urlInUse != null;
        driver.navigate().to(urlEndPoint);
        jsWaitTillPageLoaded();
        pause(4000);
    }
}