package com.lohika.jclub.http2;

import lombok.extern.slf4j.Slf4j;

import org.eclipse.jetty.server.PushBuilder;
import org.eclipse.jetty.server.Request;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class PushFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String uri = httpRequest.getRequestURI();

    switch (uri) {
      case "/index.html":
        PushBuilder pushBuilder = Request.getBaseRequest(request).getPushBuilder();
        for (int i = 1; i <= 300; i++) {
          pushBuilder.path("/" + i + ".jpg").push();
        }
        pushBuilder.path("/application.js").push();
        log.info("Push builder....");
        break;
      default:
        break;
    }
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {

  }
}
