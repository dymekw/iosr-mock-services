package pl.edu.agh.ki.iosr.mockservices;

import org.springframework.cloud.client.discovery.DiscoveryClient;

public class URLProvider {
    public static String getURL(DiscoveryClient dc, String serviceName, String urlPrefix) {
        return dc.getInstances(serviceName).stream()
                .findAny()
                .orElseThrow(() -> new RuntimeException("UNABLE TO FIND ANY SERVICE WITH NAME: " + serviceName))
                .getUri() + urlPrefix;
    }
}
