/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.eips.config;



import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author lnhtr
 */
@ApplicationPath("api")
public class EIPSApplication extends ResourceConfig {

    public EIPSApplication() {
        packages("com.swp391.eips.resources");
    }

}
