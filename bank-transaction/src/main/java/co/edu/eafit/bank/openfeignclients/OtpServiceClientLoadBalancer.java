package co.edu.eafit.bank.openfeignclients;

import feign.Feign;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

@LoadBalancerClient("bank-otp")
public class OtpServiceClientLoadBalancer {
    @LoadBalanced
    @Bean
    public Feign.Builder buildBalancer() {
        return Feign.builder();
    }
}
