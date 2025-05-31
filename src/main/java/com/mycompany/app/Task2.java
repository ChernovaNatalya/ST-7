package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

public class Task2 {
    public static void getAndPrintIP() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\natal\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://api.ipify.org/?format=json");
            
            Thread.sleep(2000);
            
            WebElement elem = driver.findElement(By.tagName("pre"));
            String jsonStr = elem.getText();
            
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonStr);
            
            String ip = (String) json.get("ip");
            System.out.println("Ваш IP-адрес: " + ip);
            
        } catch (Exception e) {
            System.out.println("Task2 Error:");
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}