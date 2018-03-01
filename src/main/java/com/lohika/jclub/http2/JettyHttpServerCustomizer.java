package com.lohika.jclub.http2;

import lombok.RequiredArgsConstructor;

import org.eclipse.jetty.alpn.server.ALPNServerConnectionFactory;
import org.eclipse.jetty.http2.HTTP2Cipher;
import org.eclipse.jetty.http2.server.HTTP2ServerConnectionFactory;
import org.eclipse.jetty.server.ConnectionFactory;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class JettyHttpServerCustomizer implements JettyServerCustomizer {

  private final ServerProperties serverProperties;

  @Override
  public void customize(Server server) {
    if (serverProperties.getSsl() != null && serverProperties.getSsl().isEnabled()) {
      ServerConnector connector = (ServerConnector) server.getConnectors()[0];
      int port = connector.getPort();
      SslContextFactory sslContextFactory = connector.getConnectionFactory(SslConnectionFactory.class).getSslContextFactory();
      HttpConfiguration httpConfiguration = connector.getConnectionFactory(HttpConnectionFactory.class).getHttpConfiguration();

      sslContextFactory.setCipherComparator(HTTP2Cipher.COMPARATOR);
      sslContextFactory.setUseCipherSuitesOrder(true);

      ConnectionFactory[] connectionFactories = createConnectionFactories(sslContextFactory, httpConfiguration);

      ServerConnector serverConnector = new ServerConnector(server, connectionFactories);
      serverConnector.setPort(port);

      server.setConnectors(new Connector[]{serverConnector});
    }
  }

  private ConnectionFactory[] createConnectionFactories(SslContextFactory sslContextFactory, HttpConfiguration
      httpConfiguration) {

    ALPNServerConnectionFactory alpnServerConnectionFactory = new ALPNServerConnectionFactory("h2", "h2-17", "h2-16", "h2-15", "h2-14");

    SslConnectionFactory sslConnectionFactory = new SslConnectionFactory(sslContextFactory, "alpn");
    HTTP2ServerConnectionFactory http2ServerConnectionFactory = new HTTP2ServerConnectionFactory(httpConfiguration);

    return new ConnectionFactory[]{sslConnectionFactory, alpnServerConnectionFactory, http2ServerConnectionFactory};
  }
}
