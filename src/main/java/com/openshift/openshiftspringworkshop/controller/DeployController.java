package com.openshift.openshiftspringworkshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping(path = "/v1/random")
@Tag(name = "Deploy", description = "Endpoint to check random number")
public class DeployController {
    @Operation(
            summary = "Check number",
            description = "Check random number",
            tags = {"GetMethod"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "successful operation")
    })
    @GetMapping(
            path = ""
    )
    public ResponseEntity<Response> getMyDummy() {
        log.debug("Entering getMyDummy ");
        // Instance of random class
        Random rand = new Random();
        // Setting the upper bound to generate the
        // random numbers in specific range
        int upperbound = 25;
        // Generating random values from 0 - 24
        // using nextInt()
        int intRandom = rand.nextInt(upperbound);
        // Generating random using nextDouble
        // in 0.0 and 1.0 range
        double doubleRandom = rand.nextDouble();
        // Generating random using nextDouble
        // in 0.0 and 1.0 range
        float floatRandom = rand.nextFloat();

        // Printing the generated random numbers
        log.info("Random integer value from 0 to {} : {}", (upperbound - 1), intRandom);
        log.info("Random float value between 0.0 and 1.0 : {}", floatRandom);
        log.info("Random double value between 0.0 and 1.0 : {}", doubleRandom);

        log.debug("Leaving getMyDummy ");
        return ok(new Response("ok"));
    }

}
