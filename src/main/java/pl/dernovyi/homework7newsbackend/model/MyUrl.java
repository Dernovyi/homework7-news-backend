package pl.dernovyi.homework7newsbackend.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyUrl {
    @Value("${url}")
    private  String url;
    @Value("${apiKey}")
    private String apyKey;

    public MyUrl() {
    }

    public String getUrl() {
        return url;
    }

    public String getApyKey() {
        return apyKey;
    }
}

