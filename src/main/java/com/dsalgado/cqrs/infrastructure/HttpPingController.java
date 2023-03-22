package com.dsalgado.cqrs.infrastructure;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpPingController {
  @GetMapping("/ping")
  public String ping() {
    return "Pong";
  }
}
