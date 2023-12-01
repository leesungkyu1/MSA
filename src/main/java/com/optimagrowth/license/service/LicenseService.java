package com.optimagrowth.license.service;

import com.optimagrowth.license.model.License;
import com.optimagrowth.license.repository.LicenseRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
//@NoArgsConstructor
public class LicenseService {


    @Qualifier("messageSource")
    MessageSource messages;

    private final LicenseRepository licenseRepository;

    private final ServiceConfig config;



    public License getLicense(String licenseId, String organization){
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organization, licenseId);

        if(null == license)
            throw new IllegalArgumentException(String.format(messages.getMessage("license.search.error.message", null, null), licenseId, organization));

        return license.withComment(config.getProperty());
    }

    public License createLicense(License license, String organizationId, Locale locale){
        license.setLicenseId(UUID.randomUUID().toString());
        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }

    public String deleteLicense(String licenseId, String organizationId){
        String responseMessage = null;
        License license = new License();
        license.setLicenseId(licenseId);
        licenseRepository.delete(license);
        responseMessage = String.format(messages.getMessage("license.delete.message", null, null), licenseId);
        return responseMessage;
    }

    public License updateLicesnse(License license, String organizationId) {
        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }
}
