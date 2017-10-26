package id.ridon.rss.ws.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("NC") // NextCloud
                .secret("secret")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .authorities("ROLE_CLIENT")
                .scopes("read", "write")
                .and()
                .withClient("SM") // SivionMobile
                .secret("secret")
                .authorizedGrantTypes("authorization_code", "refresh_token") //.autoApprove(true)
                .authorities("ROLE_CLIENT")
                .scopes("read", "write");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }
}
