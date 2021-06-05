package com.cowin.etl.api.avail;

import com.cowin.etl.enums.DistrictEnum;
import com.cowin.etl.enums.StateEnum;
import com.cowin.etl.model.Center;
import com.cowin.etl.utils.Filters;
import com.cowin.etl.utils.JsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Predicate;

@RestController(value = "availController")
@RequestMapping("/avail")
@Slf4j
public class AvailController {

    @Autowired
    private AvailService availService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailability() {
        log.info("searchAvailability");
        return JsonConverter.toJson(availService.searchAvail(Filters.filters.get(0)));
    }

    @GetMapping(value = "/filter/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailability(@PathVariable int filter) {
        log.info("searchAvailability -> filter: {}", filter);
        Predicate<Center> predicate = Filters.filters.get(filter);
        if (ObjectUtils.isEmpty(filter) || ObjectUtils.isEmpty(predicate)) {
            predicate = Filters.filters.get(0);
        }
        return JsonConverter.toJson(availService.searchAvail(predicate));
    }

    @GetMapping(value = "/state/{state_id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityByState(@PathVariable int state_id) {
        log.info("find by state-> state id: {}", state_id);
        return JsonConverter.toJson(availService.searchAvailByState(state_id, Filters.filters.get(0)));
    }

    @GetMapping(value = "/state/{state_id}/filter/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityByState(@PathVariable int state_id, @PathVariable int filter) {
        log.info("searchAvailabilityByState -> state id: {} and filter: {}", state_id, filter);
        Predicate<Center> predicate = Filters.filters.get(filter);
        if (ObjectUtils.isEmpty(filter) || ObjectUtils.isEmpty(predicate)) {
            predicate = Filters.filters.get(0);
        }
        return JsonConverter.toJson(availService.searchAvailByState(state_id, predicate));
    }

    @GetMapping(value = "/ts", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityInTs() {
        log.info("searchAvailabilityInTs -> ");
        return JsonConverter.toJson(availService.searchAvailByState(StateEnum.TS.getId(), Filters.filters.get(0)));
    }

    @GetMapping(value = "/ts/filter/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityInTs(@PathVariable int filter) {
        log.info("searchAvailabilityInTs -> filter: {}", filter);
        Predicate<Center> predicate = Filters.filters.get(filter);
        if (ObjectUtils.isEmpty(filter) || ObjectUtils.isEmpty(predicate)) {
            predicate = Filters.filters.get(0);
        }
        return JsonConverter.toJson(availService.searchAvailByState(StateEnum.TS.getId(), predicate));
    }

    @GetMapping(value = "/ap", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityInAp() {
        log.info("searchAvailabilityInAp -> ");
        return JsonConverter.toJson(availService.searchAvailByState(StateEnum.AP.getId(), Filters.filters.get(0)));
    }

    @GetMapping(value = "/ap/filter/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityInAp(@PathVariable int filter) {
        log.info("searchAvailabilityInTs -> filter: {}", filter);
        Predicate<Center> predicate = Filters.filters.get(filter);
        if (ObjectUtils.isEmpty(filter) || ObjectUtils.isEmpty(predicate)) {
            predicate = Filters.filters.get(0);
        }
        return JsonConverter.toJson(availService.searchAvailByState(StateEnum.AP.getId(), predicate));
    }

    @GetMapping(value = "/district/{district_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityByDistrict(@PathVariable int district_id) {
        log.info("searchAvailabilityByDistrict -> district_id: {}", district_id);
        return JsonConverter.toJson(availService.searchAvailByDistrict(district_id, Filters.filters.get(0)));
    }

    @GetMapping(value = "/hyd", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityInHyd() {
        log.info("searchAvailabilityInHyd ->");
        return JsonConverter.toJson(availService.searchAvailByDistrict(DistrictEnum.HYD.getId(), Filters.filters.get(0)));
    }

    @GetMapping(value = "/hyd/filter/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityInHyd(@PathVariable int filter) {
        log.info("searchAvailabilityInHyd -> filter: {}", filter);
        Predicate<Center> predicate = Filters.filters.get(filter);
        if (ObjectUtils.isEmpty(filter) || ObjectUtils.isEmpty(predicate)) {
            predicate = Filters.filters.get(0);
        }
        return JsonConverter.toJson(availService.searchAvailByState(DistrictEnum.HYD.getId(), predicate));
    }

    @GetMapping(value = "/sgr", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityInSgr() {
        log.info("searchAvailabilityInSgr ->");
        return JsonConverter.toJson(availService.searchAvailByDistrict(DistrictEnum.SGR.getId(), Filters.filters.get(0)));
    }

    @GetMapping(value = "/hyd/filter/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityInSgr(@PathVariable int filter) {
        log.info("searchAvailabilityInSgr -> filter: {}", filter);
        Predicate<Center> predicate = Filters.filters.get(filter);
        if (ObjectUtils.isEmpty(filter) || ObjectUtils.isEmpty(predicate)) {
            predicate = Filters.filters.get(0);
        }
        return JsonConverter.toJson(availService.searchAvailByState(DistrictEnum.SGR.getId(), predicate));
    }

    @GetMapping(value = "/rnr", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityInRnr() {
        log.info("searchAvailabilityInRnr ->");
        return JsonConverter.toJson(availService.searchAvailByDistrict(DistrictEnum.RND.getId(), Filters.filters.get(0)));
    }

    @GetMapping(value = "/rnr/filter/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityInRnr(@PathVariable int filter) {
        log.info("searchAvailabilityInRnr -> filter: {}", filter);
        Predicate<Center> predicate = Filters.filters.get(filter);
        if (ObjectUtils.isEmpty(filter) || ObjectUtils.isEmpty(predicate)) {
            predicate = Filters.filters.get(0);
        }
        return JsonConverter.toJson(availService.searchAvailByState(DistrictEnum.RND.getId(), predicate));
    }

    @GetMapping(value = "/mdl", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityInMdl() {
        log.info("searchAvailabilityInMdl ->");
        return JsonConverter.toJson(availService.searchAvailByDistrict(DistrictEnum.MDL.getId(), Filters.filters.get(0)));
    }

    @GetMapping(value = "/mdl/filter/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityInMdl(@PathVariable int filter) {
        log.info("searchAvailabilityInMdl -> filter: {}", filter);
        Predicate<Center> predicate = Filters.filters.get(filter);
        if (ObjectUtils.isEmpty(filter) || ObjectUtils.isEmpty(predicate)) {
            predicate = Filters.filters.get(0);
        }
        return JsonConverter.toJson(availService.searchAvailByState(DistrictEnum.MDL.getId(), predicate));
    }

    @GetMapping(value = "/krl", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityInKrl() {
        log.info("searchAvailabilityInKrl ->");
        return JsonConverter.toJson(availService.searchAvailByDistrict(DistrictEnum.KRL.getId(), Filters.filters.get(0)));
    }

    @GetMapping(value = "/hyd/filter/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityInKrl(@PathVariable int filter) {
        log.info("searchAvailabilityInKrl -> filter: {}", filter);
        Predicate<Center> predicate = Filters.filters.get(filter);
        if (ObjectUtils.isEmpty(filter) || ObjectUtils.isEmpty(predicate)) {
            predicate = Filters.filters.get(0);
        }
        return JsonConverter.toJson(availService.searchAvailByState(DistrictEnum.KRL.getId(), predicate));
    }

    @GetMapping(value = "/wgd", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityInWgd() {
        log.info("searchAvailabilityInWgd ->");
        return JsonConverter.toJson(availService.searchAvailByDistrict(DistrictEnum.WGD.getId(), Filters.filters.get(0)));
    }

    @GetMapping(value = "/wgd/filter/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityInWgd(@PathVariable int filter) {
        log.info("searchAvailabilityInWgd -> filter: {}", filter);
        Predicate<Center> predicate = Filters.filters.get(filter);
        if (ObjectUtils.isEmpty(filter) || ObjectUtils.isEmpty(predicate)) {
            predicate = Filters.filters.get(0);
        }
        return JsonConverter.toJson(availService.searchAvailByState(DistrictEnum.WGD.getId(), predicate));
    }

    @GetMapping(value = "/vsp", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityInVsp() {
        log.info("searchAvailabilityInVsp ->");
        return JsonConverter.toJson(availService.searchAvailByDistrict(DistrictEnum.VSP.getId(), Filters.filters.get(0)));
    }

    @GetMapping(value = "/vsp/filter/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityInVsp(@PathVariable int filter) {
        log.info("searchAvailabilityInVsp -> filter: {}", filter);
        Predicate<Center> predicate = Filters.filters.get(filter);
        if (ObjectUtils.isEmpty(filter) || ObjectUtils.isEmpty(predicate)) {
            predicate = Filters.filters.get(0);
        }
        return JsonConverter.toJson(availService.searchAvailByState(DistrictEnum.VSP.getId(), predicate));
    }

    @GetMapping(value = "/ghmc", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityInGhmc() {
        log.info("searchAvailabilityInGhmc ->");

        return JsonConverter.toJson(availService.searchAvailByDistricts(Filters.filters.get(0), List.of(DistrictEnum.HYD.getId(), DistrictEnum.SGR.getId(), DistrictEnum.RND.getId(), DistrictEnum.MDL.getId())));
    }

    @GetMapping(value = "/hyd/filter/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String searchAvailabilityInGhmc(@PathVariable int filter) {
        log.info("searchAvailabilityInGhmc -> filter: {}", filter);
        Predicate<Center> predicate = Filters.filters.get(filter);
        if (ObjectUtils.isEmpty(filter) || ObjectUtils.isEmpty(predicate)) {
            predicate = Filters.filters.get(0);
        }
        return JsonConverter.toJson(availService.searchAvailByState(DistrictEnum.HYD.getId(), predicate));
    }
}
