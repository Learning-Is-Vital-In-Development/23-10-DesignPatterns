package code.choi.ch01.controller;

import code.choi.ch01.duck.DuckType;
import code.choi.ch01.service.DuckService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DuckController {

    private final DuckService duckService;

    @GetMapping("/ducks/{duckType}/fly")
    public String getDuckFly(@PathVariable String duckType) {
        return duckService.getDuckFly(DuckType.valueOf(duckType.toUpperCase()));
    }

}
