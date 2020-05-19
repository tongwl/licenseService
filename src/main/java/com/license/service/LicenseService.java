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

    private static final String MAC = "mac";
    private static final String WINDOWS = "windows";
    private static final String LINUX = "linux";

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
        String operatingSystem = getOperatingSystem();
        //don't support windows util now
        if (operatingSystem.equals(MAC)) {
            //if mac, use tool licensegen
            sb.append("licensegen ");
        } else {
            //if linux or unix, use go_lic
            sb.append("/usr/java/projects/go_lic ");
        }
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
        String operatingSystem = getOperatingSystem();
        //for mac, linux or unix doesn't need this
        if (operatingSystem.equals(MAC)) {
            command = getClass().getResource("/").getFile().toString() + command;
        }
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

    private String getOperatingSystem() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.startsWith("mac os")) {
            return MAC;
        } else if (osName.startsWith("windows")) {
            return WINDOWS;
        } else if (osName.startsWith("linux")) {
            return LINUX;
        } else {
            return "";
        }
    }
}
