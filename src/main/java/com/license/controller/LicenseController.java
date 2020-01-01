package com.license.controller;

import com.license.entity.LicenseEntity;
import com.license.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/license")
public class LicenseController {
    @Autowired
    private LicenseService licenseService;

    @RequestMapping("licenseList")
    public List<Map<String, Object>> getLicenseList() {
        return licenseService.getLicenseList();
    }

    @RequestMapping(method = RequestMethod.POST, value="create")
    public void createLicense(
            @RequestBody LicenseEntity licenseEntity
            ) throws InterruptedException {
        licenseService.createLicense(licenseEntity);
    }

    @RequestMapping(method = RequestMethod.PUT, value="update")
    public void updateLicense(
            @RequestBody LicenseEntity licenseEntity
    ) throws InterruptedException {
        licenseService.updateLicense(licenseEntity);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/delete")
    public void deleteLicense(@RequestParam(value = "id") String id) {
        licenseService.deleteLicense(id);

    }
}
