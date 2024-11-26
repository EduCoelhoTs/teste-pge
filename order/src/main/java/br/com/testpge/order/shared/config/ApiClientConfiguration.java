package br.com.testpge.order.shared.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.testpge.order.shared.httpclient.ApiErrorDecoder;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;

@Configuration
public class ApiClientConfiguration {

    @Bean
    public Decoder feignDecoder(ObjectMapper objectMapper) {
        HttpMessageConverters httpMessageConverters = new HttpMessageConverters();
        return new ResponseEntityDecoder(new SpringDecoder(() -> httpMessageConverters));
    }

    @Bean
    public ErrorDecoder apiErrorDecoder(ObjectMapper objectMapper) {
        return new ApiErrorDecoder(objectMapper);
    }
}
