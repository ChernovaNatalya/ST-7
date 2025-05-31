package com.mycompany.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) {
        System.out.println("Execution Task2...");
        Task2.getAndPrintIP();
        
        System.out.println("\nExecution Task3...");
        Task3.getWeatherForecast();
    }
}
