/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver;

import com.jovana.videoklubserver.configuration.MyEmbeddedServletContainerCustomizer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author jmoldovan
 */
@SpringBootApplication
//@Configuration
@EntityScan(basePackages = {"com.jovana.videoklubzajednicko.domen"} )
@EnableJpaRepositories(basePackages = {"com.jovana.videoklubserver.repository"})
public class Application {
    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletCustomizer() {
        return new MyEmbeddedServletContainerCustomizer();
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    
}
