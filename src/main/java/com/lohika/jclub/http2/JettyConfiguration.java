package com.lohika.jclub.http2;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JettyConfiguration {


  @Bean
  public JettyServletWebServerFactory jettyServletWebServerFactory(JettyHttpServerCustomizer customizer) {
    JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
    factory.addServerCustomizers(customizer);
    return factory;
  }
}
