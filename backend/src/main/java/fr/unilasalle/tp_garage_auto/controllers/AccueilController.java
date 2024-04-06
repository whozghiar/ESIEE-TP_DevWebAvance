package fr.unilasalle.tp_garage_auto.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/demo")
@RequiredArgsConstructor
@Slf4j
public class AccueilController {

    @GetMapping("/hello")
    @PreAuthorize("hasAnyRole('admin','client')")
    public String hello() {
        return "Hello World! - CLIENT & ADMIN";
    }

    @GetMapping("/goodbye")
    @PreAuthorize("hasRole('client')")
    public String goodbye() {
        return "Goodbye World! - Client";
    }
}
