package com.finche.registration.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "db-service")
public class DbServiceClient {

}
