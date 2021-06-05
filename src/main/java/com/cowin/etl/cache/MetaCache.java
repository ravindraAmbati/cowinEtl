package com.cowin.etl.cache;

import com.cowin.etl.api.meta.MetaService;
import com.cowin.etl.model.District;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MetaCache {

    public static HashMap<Integer, String> stateMap = new HashMap<>();
    public static HashMap<Integer, List<Integer>> stateDistrictsMap = new HashMap<>();
    public static HashMap<Integer, String> districtMap = new HashMap<>();

    @Autowired
    private MetaService metaService;

    public void load(){
        log.info("starting caching of metadata");
        metaService.getStates().forEach(
                state -> {
                    stateMap.put(state.getState_id(), state.getState_name());
                    List<District> districts = metaService.getDistricts(state.getState_id());
                    stateDistrictsMap.put(state.getState_id(), districts.stream().map(District::getDistrict_id).collect(Collectors.toList()));
                    districts.forEach(
                            district -> districtMap.put(district.getDistrict_id(), district.getDistrict_name())
                    );
                }
        );
        log.info("Cached States: {}", stateMap);
        log.info("Cached Districts: {}", districtMap);
    }
}
