package com.example.BookingSystem.service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Service
public class GeoLocationService {
    private final DatabaseReader databaseReader;

    public GeoLocationService() throws IOException {
        File database = new File("src/main/resources/GeoLite2-City.mmdb");
        this.databaseReader = new DatabaseReader.Builder(database).build();
    }

    public String getCountryByIp(String ip) throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = databaseReader.city(ipAddress);
        return response.getCountry().getIsoCode();
    }
}
