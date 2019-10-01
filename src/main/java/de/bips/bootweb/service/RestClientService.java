package de.bips.bootweb.service;

import java.util.Arrays;
import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClientService {

  private static final Logger logger = LoggerFactory.getLogger(RestClientService.class);

  @Autowired
  private RestTemplate restTemplate;


  public void exchange(String endpoint) {

    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

    String auth = "swagger:test";// user:password
    byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
    String authHeader = "Basic " + new String(encodedAuth);

    headers.set("Authorization", authHeader);

    HttpEntity<String> entity = new HttpEntity<>(headers);

    ResponseEntity<String> responseEntity =
        restTemplate.exchange(endpoint, HttpMethod.GET, entity, String.class);



    logger.info("endpoint redirect: {}", responseEntity.getBody());

  }

}
