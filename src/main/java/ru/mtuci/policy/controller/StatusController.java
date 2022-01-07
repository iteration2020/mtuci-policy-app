package ru.mtuci.policy.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mtuci.policy.dto.Status;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/status", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatusController {

    @GetMapping
    public Status get() throws UnknownHostException {
        log.info("get status");
        return new Status(InetAddress.getLocalHost().getHostName(), "Привет!");
    }
}
