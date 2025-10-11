package com.hms.controller;


import com.hms.model.AffiliatedWith;
import com.hms.service.AffiliatedWithService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/affiliated_with")
public class AffiliatedWithController {

    @Autowired
    private AffiliatedWithService affiliatedWithService;

    @GetMapping("/physicians/{deptid}")
    public ResponseEntity<List<AffiliatedWith>> getPhysiciansByDepartment(@PathVariable Integer deptid) {
        return ResponseEntity.ok(affiliatedWithService.getPhysiciansByDepartment(deptid));
    }
    @PostMapping("/post")
    public ResponseEntity<String> addAffiliation(@RequestBody AffiliatedWith affiliatedWith) {
        String result = affiliatedWithService.addAffiliation(affiliatedWith);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/department/{physicianid}")
    public ResponseEntity<List<AffiliatedWith>> getDepartmentsByPhysician(@PathVariable Integer physicianid) {
        return ResponseEntity.ok(affiliatedWithService.getDepartmentsByPhysician(physicianid));
    }

    @GetMapping("/countphysician/{deptid}")
    public ResponseEntity<Long> countPhysicians(@PathVariable Integer deptid) {
        return ResponseEntity.ok(affiliatedWithService.countPhysiciansInDepartment(deptid));
    }

    @GetMapping("/primary/{physicianid}")
    public ResponseEntity<Boolean> checkPrimaryAffiliation(@PathVariable Integer physicianid) {
        return ResponseEntity.ok(affiliatedWithService.hasPrimaryAffiliation(physicianid));
    }

    @PutMapping("/primary/{physicianid}")
    public ResponseEntity<Boolean> updatePrimaryAffiliation(@PathVariable Integer physicianid) {
        return ResponseEntity.ok(affiliatedWithService.updatePrimaryAffiliation(physicianid));
    }
}
