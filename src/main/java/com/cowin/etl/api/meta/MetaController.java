package com.cowin.etl.api.meta;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController(value = "metaController")
@RequestMapping("/meta")
@Slf4j
public class MetaController {

    @Autowired
    private MetaService metaService;

    @GetMapping("/test")
    public String test() {
        log.info("test -> ");
        return "test";
    }

    @GetMapping(value = "/states", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getStates() {
        log.info("get states -> ");
        return metaService.getStatesJson();
    }

    @GetMapping(value = "/districts/{state_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getDistricts(@PathVariable String state_id) {
        log.info("get districts -> state id: {}",state_id);
        return metaService.getDistrictsJson(Integer.parseInt(state_id));
    }

    @GetMapping(value = "/districts/ap", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getApDistricts() {
        log.info("get AP districts -> ");
        return metaService.getApDistrictsJson();
    }

    @GetMapping(value = "/districts/ts", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getTsDistricts() {
        log.info("get TS districts -> ");
        return metaService.getTsDistrictsJson();
    }

}
