package com.cowin.etl.api.avail;

import com.cowin.etl.enums.DistrictEnum;
import com.cowin.etl.enums.StateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.StringJoiner;

@RestController(value = "availController")
@RequestMapping("/avail")
@Slf4j
public class AvailController {

    @Autowired
    private AvailService availService;


    @GetMapping("/test")
    public String test() {
        log.info("test");
        return "test";
    }

    @GetMapping(value = "/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findByDate(@PathVariable String date) {
        log.info("find by date -> date: {}", date);
        return availService.findByDateJson(date);
    }

    @GetMapping(value = "filter/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findByDateAndFilter(@PathVariable String date) {
        log.info("find by date -> date: {}", date);
        return availService.findByDateAndFilterJson(date);
    }

    @GetMapping(value = "/state/{state_id}/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findByState(@PathVariable String state_id, @PathVariable String date) {
        log.info("find by state-> state id: {} and date: {}", state_id, date);
        return availService.findByStateJson(Integer.parseInt(state_id), date);
    }

    @GetMapping(value = "/ts/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findInTs(@PathVariable String date) {
        log.info("find in TS -> date: {}", date);
        return availService.findByStateJson(StateEnum.TS.getId(), date);
    }

    @GetMapping(value = "/ap/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findInAp(@PathVariable String date) {
        log.info("find in AP -> date: {}", date);
        return availService.findByStateJson(StateEnum.AP.getId(), date);
    }

    @GetMapping(value = "/district/{district_id}/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findByDistrict(@PathVariable String district_id, @PathVariable String date) {
        log.info("find by district -> district_id: {} and date: {}", district_id, date);
        return availService.findByDistrictJson(Integer.parseInt(district_id), date);
    }

    @GetMapping(value = "/hyd/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findInHyd(@PathVariable String date) {
        log.info("find In Hyd -> date: {}", date);
        return availService.findByDistrictJson(DistrictEnum.HYD.getId(), date);
    }

    @GetMapping(value = "/rnr/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findInRnr(@PathVariable String date) {
        log.info("find In Rnr -> date: {}", date);
        return availService.findByDistrictJson(DistrictEnum.RND.getId(), date);
    }

    @GetMapping(value = "/sgr/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findBySgr(@PathVariable String date) {
        log.info("find In Sgr -> date: {}", date);
        return availService.findByDistrictJson(DistrictEnum.SGR.getId(), date);
    }

    @GetMapping(value = "/mdl/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findByMdc(@PathVariable String date) {
        log.info("find In Mdc -> date: {}", date);
        return availService.findByDistrictJson(DistrictEnum.MDL.getId(), date);
    }

    @GetMapping(value = "/ghmc/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findInGhmc(@PathVariable String date) {
        log.info("find In Ghmc -> date: {}", date);
        return availService.findByDistrictsJson(date, DistrictEnum.HYD, DistrictEnum.MDL, DistrictEnum.SGR, DistrictEnum.RND);
    }

    @GetMapping(value = "/ghmc/filter/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findInGhmcAndFilter(@PathVariable String date) {
        log.info("find In Ghmc And Filter-> date: {}", date);
        return availService.findByDistrictsAndFilterJson(date, DistrictEnum.HYD, DistrictEnum.MDL, DistrictEnum.SGR, DistrictEnum.RND);
    }


    @GetMapping(value = "/krl/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findInKrl(@PathVariable String date) {
        log.info("find In Krl -> date: {}", date);
        return availService.findByDistrictJson(DistrictEnum.KRL.getId(), date);
    }

    @GetMapping(value = "/wgd/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findByWgd(@PathVariable String date) {
        log.info("find In wgd -> date: {}", date);
        return availService.findByDistrictJson(DistrictEnum.WGD.getId(), date);
    }

    @GetMapping(value = "/vsp/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findByVsp(@PathVariable String date) {
        log.info("find In Vsp -> date: {}", date);
        return availService.findByDistrictJson(DistrictEnum.VSP.getId(), date);
    }
}
