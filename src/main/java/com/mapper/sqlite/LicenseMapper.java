package com.mapper.sqlite;

import com.license.entity.LicenseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LicenseMapper {
    List<Map<String, Object>> getLicenseList();
    void createLicense(LicenseEntity licenseEntity);
    void updateLicense(LicenseEntity licenseEntity);
    void deleteLicense(@Param("id") String id);
}
