package petrbalat.airtask.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration(proxyBeanMethods = false)
class AppConfig {

    @Bean("jsonplaceholder")
    fun webClient(): WebClient = jsonWebClient
}

inline val jsonWebClient: WebClient
    get() = WebClient.create("https://jsonplaceholder.typicode.com")
