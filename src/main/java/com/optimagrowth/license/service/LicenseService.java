package com.optimagrowth.license.service;

import com.optimagrowth.license.model.License;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Locale;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LicenseService {

    private final MessageSource messages;

    public License getLicense(String licenseId, String organization){
        return License.builder()
                .id(new Random().nextInt(100))
                .licenseId(licenseId)
                .organizationId(organization)
                .description("Software product")
                .productName("Ostock")
                .licenseType("full")
                .build();
    }

    public String createLicense(License license, String organizationId, Locale locale){
        String responseMessage = null;
        if (!StringUtils.isEmpty(license)){
            license.setOrganizationId(organizationId);
            responseMessage = String.format(messages.getMessage("license.create.message", null, locale), license.toString());
        }
        return responseMessage;
    }

    public String deleteLicense(String licenseId, String organizationId){
        return String.format("Deleting license with id %s for the organization %s", licenseId, organizationId);
    }

    public String updateLicesnse(License license, String organizationId) {
        String responseMessage = null;
        if(license != null){
            license.setOrganizationId(organizationId);
            responseMessage = String.format("This is the put and the object is: %s", license.toString());
        }
        return responseMessage;
    }
}
