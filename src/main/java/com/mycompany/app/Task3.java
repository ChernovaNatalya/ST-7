package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task3 {
    public static void getWeatherForecast() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\natal\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            String url = "https://api.open-meteo.com/v1/forecast?" +
                "latitude=56&longitude=44&" +
                "hourly=temperature_2m,rain&" +
                "current=cloud_cover&" +
                "timezone=Europe%2FMoscow&" +
                "forecast_days=1&" +
                "wind_speed_unit=ms";
            
            driver.get(url);
            
            Thread.sleep(2000);
            
            WebElement elem = driver.findElement(By.tagName("pre"));
            String jsonStr = elem.getText();
            
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonStr);
            JSONObject hourly = (JSONObject) json.get("hourly");
            
            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");
            
            StringBuilder table = new StringBuilder();
            table.append("| №  | Дата/время       | Температура (°C) | Осадки (мм) |\n");
            table.append("|----|------------------|-------------------|-------------|\n");
            
            for (int i = 0; i < times.size(); i++) {
                String time = (String) times.get(i);
                double temp = (double) temperatures.get(i);
                double rain = (double) rains.get(i);
                
                table.append(String.format("| %-2d | %-16s | %-16.1f | %-11.2f |\n", 
                        i + 1, time, temp, rain));
            }
            
            System.out.println("Прогноз погоды для Нижнего Новгорода:");
            System.out.println(table.toString());
            
            String fileName = "result/forecast_" + 
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".txt";
            
            Files.createDirectories(Paths.get("result"));
            try (BufferedWriter writer = Files.newBufferedWriter(
                    Paths.get(fileName), StandardCharsets.UTF_8)) {
                writer.write(table.toString());
                System.out.println("Результаты сохранены в: " + fileName);
            }
            
        } catch (Exception e) {
            System.out.println("Task3 Error:");
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}