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

import com.dariawan.rest.model.Hello;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping("/")
@Api(value = "/", description = "apis for Hello")
public class HelloController {

    
    @RequestMapping(value = "dariawan/hello/getHello", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Greetings using Swagger, return Hello", notes = "return Hello", response = Hello.class)
    @ResponseStatus(HttpStatus.OK)
    public Hello sayHello() {
        Hello hello = new Hello();
        hello.setHelloFrom("Dariawan");
        hello.setHelloMessage("Greetings!");
        return hello;
    }
    
    @RequestMapping(value = "dariawan/hello/sayHello/{from}/{message}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Greetings using Swagger, return Hello", notes = "return Hello", response = Hello.class)
    @ResponseStatus(HttpStatus.OK)
    public Hello sayHello(
            @ApiParam(name = "from", value = "greetings from?", required = true) @PathVariable("from") String from, 
            @ApiParam(name = "message", value = "greeting message?", required = true) @PathVariable("message") String message) {
        Hello hello = new Hello();
        hello.setHelloFrom(from);
        hello.setHelloMessage(message);
        return hello;
    }
    
    /**
     * 
     * @return greetings
     */
    @RequestMapping(value = "dariawan/hello/getString", method = RequestMethod.GET)
    @ApiOperation(value = "Greetings using Swagger, return String", notes = "return Hello as String", response = String.class)
    public String sayHelloAsString() {
        return "Greetings from Dariawan!";
    }
}
