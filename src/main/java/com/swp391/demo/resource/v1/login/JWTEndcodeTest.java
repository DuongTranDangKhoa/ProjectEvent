/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.resource.v1.login;

import com.swp391.demo.dto.AccountDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 *
 * @author lnhtr
 */
public class JWTEndcodeTest {
    public static void main(String[] args) {
    try {
            String secretkey="qwertypassword";

            //The JWT signature algorithm we will be using to sign the token
            //setHeaderParam("user", new AccountDTO("user1", "12345", "u1", "sale", true))
            String jwtToken = Jwts.builder().setSubject("safsa")
                    .setAudience("sgvdg")
                .signWith(SignatureAlgorithm.HS256,secretkey.getBytes()).compact();

            System.out.println("jwtToken=");
            System.out.println(jwtToken);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
