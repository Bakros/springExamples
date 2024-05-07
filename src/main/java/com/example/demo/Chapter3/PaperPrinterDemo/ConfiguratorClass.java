package com.example.demo.Chapter3.PaperPrinterDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguratorClass {

    @Bean
    public Printer printerPoint(){
        return new PaperPrinter();
    }

}
