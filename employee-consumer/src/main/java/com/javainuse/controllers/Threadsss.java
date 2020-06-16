package com.javainuse.controllers;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author PhanHoang
 * 6/16/2020
 */
public class Threadsss implements Runnable {

    private LoadBalancerClient loadBalancer;
    public Threadsss(LoadBalancerClient loadBalancer){
        this.loadBalancer =loadBalancer;
    }

    @Override
    public void run() {


        ServiceInstance serviceInstance = loadBalancer.choose("employee-producer");

        System.out.println(serviceInstance.getUri());

        String baseUrl = serviceInstance.getUri().toString();
//        String baseUrl ="http://host.docker.internal:8081/";

        baseUrl = baseUrl + "/employee";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(baseUrl,
                    HttpMethod.GET, getHeaders(), String.class);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println(response.getBody());
    }

    private static HttpEntity<?> getHeaders() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }

}
