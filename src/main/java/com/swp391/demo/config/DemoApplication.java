/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.config;


import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author lnhtr
 */
@ApplicationPath("api")
public class DemoApplication extends ResourceConfig {

    public DemoApplication() {
        packages("com.swp391.demo.resource");
    }

}
