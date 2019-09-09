/**
 * Spring REST Examples v1 (https://www.dariawan.com)
 * Copyright (C) 2019 Dariawan <hello@dariawan.com>
 *
 * Creative Commons Attribution-ShareAlike 4.0 International License
 *
 * Under this license, you are free to:
 * # Share - copy and redistribute the material in any medium or format
 * # Adapt - remix, transform, and build upon the material for any purpose,
 *   even commercially.
 *
 * The licensor cannot revoke these freedoms
 * as long as you follow the license terms.
 *
 * License terms:
 * # Attribution - You must give appropriate credit, provide a link to the
 *   license, and indicate if changes were made. You may do so in any
 *   reasonable manner, but not in any way that suggests the licensor
 *   endorses you or your use.
 * # ShareAlike - If you remix, transform, or build upon the material, you must
 *   distribute your contributions under the same license as the original.
 * # No additional restrictions - You may not apply legal terms or
 *   technological measures that legally restrict others from doing anything the
 *   license permits.
 *
 * Notices:
 * # You do not have to comply with the license for elements of the material in
 *   the public domain or where your use is permitted by an applicable exception
 *   or limitation.
 * # No warranties are given. The license may not give you all of
 *   the permissions necessary for your intended use. For example, other rights
 *   such as publicity, privacy, or moral rights may limit how you use
 *   the material.
 *
 * You may obtain a copy of the License at
 *   https://creativecommons.org/licenses/by-sa/4.0/
 *   https://creativecommons.org/licenses/by-sa/4.0/legalcode
 */
package com.dariawan.rest.controller;

import static io.restassured.RestAssured.with;
import io.restassured.authentication.FormAuthConfig;
import java.net.InetAddress;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Desson Ariawan
 */
public class HelloControllerTestIT {

    protected static final String USERNAME = "root";
    protected static final String PASSWORD = "admin";

    // protected String login = "http://{serverName}:9699/j_spring_security_check";
    protected String login = "j_spring_security_check";
    protected String target = "http://{serverName}:9699/hello";
    
    @Before
    public void changeTarget() throws Exception {
        String computerName = InetAddress.getLocalHost().getHostName();
        if (computerName.equals("")) {
            computerName = "localhost";
        }
        target = target.replace("{serverName}", computerName);
    }
    
    @Test
    public void testSayHello() {
        with().header("Accept", "application/json")
                // .given().urlEncodingEnabled(false)
                .auth().form(USERNAME, PASSWORD, new FormAuthConfig(login, "j_username", "j_password"))
                .expect()
                .statusCode(200)
                .body("helloFrom", equalTo("Dariawan"), "helloMessage", equalTo("Greetings!"))
                .when().get(target +"/getHello");
    }
    
    @Test
    public void testSayHelloAsString() {
        
        with().header("Accept", "application/json")
                // .given().urlEncodingEnabled(false)
                .auth().form(USERNAME, PASSWORD, new FormAuthConfig(login, "j_username", "j_password"))
                .expect()
                .statusCode(200)
                .body(equalTo("Greetings from Dariawan!"))
                .when().get(target +"/getString");
    }
}
