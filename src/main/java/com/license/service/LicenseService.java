package com.license.service;

import com.license.entity.LicenseEntity;
import com.mapper.sqlite.LicenseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class LicenseService {
    @Autowired
    LicenseMapper licenseMapper;

    public List<Map<String, Object>> getLicenseList() {
        return licenseMapper.getLicenseList();
    }

    public void createLicense(LicenseEntity licenseEntity) throws InterruptedException {
        licenseMapper.createLicense(setLicenseEntity(licenseEntity));
    }

    public void updateLicense(LicenseEntity licenseEntity) throws InterruptedException {
        licenseMapper.updateLicense(setLicenseEntity(licenseEntity));
    }

    public void deleteLicense(String id) {
        licenseMapper.deleteLicense(id);
    }

    private LicenseEntity setLicenseEntity(LicenseEntity licenseEntity) throws InterruptedException {
        StringBuilder sb = new StringBuilder();
        sb.append("licensegen ");
        sb.append(licenseEntity.getCompany());
        sb.append(" ");
        sb.append(licenseEntity.getInstalledID());
        sb.append(" ");
        sb.append(licenseEntity.getLicenseDays());
        sb.append(" ");
        sb.append(licenseEntity.getClusterSize());
        String licenseKey = execCommand(sb.toString());
        licenseEntity.setLicenseKey(licenseKey);
        licenseEntity.setActiveTime(System.currentTimeMillis());
        return licenseEntity;
    }

    private String execCommand(String command) throws InterruptedException {
        command = getClass().getResource("/").getFile().toString() + command;
        System.out.println(getClass().getResource("/").getFile().toString());
        String returnString = "";
        Process pro = null;
        Runtime runTime = Runtime.getRuntime();
        if (runTime == null) {
            System.err.println("Create runtime false!");
        }
        try {
            pro = runTime.exec(command);
            BufferedReader input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            PrintWriter output = new PrintWriter(new OutputStreamWriter(pro.getOutputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                returnString = returnString + line + "\n";
            }
            input.close();
            output.close();
            pro.destroy();
        } catch (IOException ex) {
        }
        return returnString;
    }
}
