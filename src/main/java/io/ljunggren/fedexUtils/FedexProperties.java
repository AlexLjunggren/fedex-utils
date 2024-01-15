package io.ljunggren.fedexUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FedexProperties {

    private static Properties properties = new Properties();

    private static final String API_OAUTH_URL = "api.oauth.url";
    private static final String API_TRACKING_URL = "api.tracking.url";
    
    
    public FedexProperties(FedexEnvironment environment) {
        Map<FedexEnvironment, String> fileMap = generateFileMap();
        setProperties(fileMap.get(environment));
    }
    
    private static Map<FedexEnvironment, String> generateFileMap() {
        Map<FedexEnvironment, String> fileMap = new HashMap<>();
        fileMap.put(FedexEnvironment.SANDBOX, "/properties/sandbox/fedex.properties");
        fileMap.put(FedexEnvironment.PRODUCTION, "/properties/production/fedex.properties");
        return fileMap;
    }
    
    private static void setProperties(String file) {
        try {
            InputStream is = FedexProperties.class.getResourceAsStream(file);
            properties.load(is);
            is.close();
        } catch(IOException e) {
            throw new RuntimeException("Error loading properties", e);
        }
    }
    
    public String getOauthUrl() {
        return properties.getProperty(API_OAUTH_URL);
    }
    public String getTrackingUrl() {
        return properties.getProperty(API_TRACKING_URL);
    }
    
}
