package com.corpotrate.lookiz.apis;

import com.corpotrate.lookiz.apis.dto.ZapierResDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${app.version}")
    String version;

    @GetMapping("/")
    public ZapierResDto home() {
        return new ZapierResDto(version);
    }
}