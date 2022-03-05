package co.edu.eafit.bank.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Value("${otp.service.validate.url}")
    private String otpServiceValidateUrl;

    //Activate WebClient in this project
    @Bean
    public WebClient otpWebClient() {
        return WebClient.builder()
                .baseUrl(otpServiceValidateUrl)
                .build();
    }
}