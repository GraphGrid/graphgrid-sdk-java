/*
 * Copyright (c) 2018-2024 "GraphGrid,"
 * GraphGrid, Inc. [https://graphgrid.com]
 *
 * This file is part of GraphGrid. The included source
 * code can be redistributed and/or modified under the terms of the
 * GNU AFFERO GENERAL PUBLIC LICENSE Version 3
 * (http://www.fsf.org/licensing/licenses/agpl-3.0.html) as found
 * in the associated LICENSE.txt file.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 */
package com.graphgrid.sdk.core.security;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.graphgrid.sdk.core.GraphGridClientBase;
import com.graphgrid.sdk.core.GraphGridHttpClient;
import com.graphgrid.sdk.core.SessionFactory;
import com.graphgrid.sdk.core.handler.UrlEncodedRequestHandler;
import com.graphgrid.sdk.core.model.GetTokenResponse;
import com.graphgrid.sdk.core.model.GetTokenResponseSystem;
import com.graphgrid.sdk.core.model.GraphGridSecurityRequest;
import com.graphgrid.sdk.core.utils.HttpMethod;

import static com.graphgrid.sdk.core.utils.Preconditions.checkNotEmpty;
import static com.graphgrid.sdk.core.utils.Preconditions.checkNotNull;

public class GraphGridSecurityClient extends GraphGridClientBase implements SecurityService
{

    private static final String AUTH_HEADER_KEY = "Authorization";
    private static final String BASIC_HEADER_KEY = "Basic ";
    private static final String GRANT_TYPE_KEY = "grant_type";
    private static final String PASSWORD_KEY = "password";
    private static final String USERNAME_KEY = "username";

    private Optional<GetTokenResponseSystem> reusableToken = Optional.empty();

    private SecurityConfig securityConfig;

    public GraphGridSecurityClient( SecurityConfig securityConfig )
    {
        super( securityConfig.getBaseSecurityUrl() );
        this.securityConfig = securityConfig;
    }

    public GraphGridSecurityClient( GraphGridHttpClient client, SecurityConfig securityConfig, SessionFactory sessionFactory )
    {
        super( client, securityConfig.getBaseSecurityUrl(), securityConfig, sessionFactory );
    }

    public GetTokenResponse getToken( String username, String password )
    {
        checkNotNull( username, "username" );
        checkNotNull( password, "password" );

        final List<NameValuePair> nvps = new ArrayList<>();
        nvps.add( new BasicNameValuePair( PASSWORD_KEY, password ) );
        nvps.add( new BasicNameValuePair( USERNAME_KEY, username ) );
        nvps.add( new BasicNameValuePair( GRANT_TYPE_KEY, "password" ) );

        final GraphGridSecurityRequest ggRequest = new GraphGridSecurityRequest();
        ggRequest.addHeader( AUTH_HEADER_KEY, baseEncodedHeader( securityConfig.getClientId(), securityConfig.getClientSecret() ) );
        ggRequest.setRequestHandler( new UrlEncodedRequestHandler() );
        ggRequest.setBody( nvps );
        ggRequest.setEndpoint( getEndpointBuilder().create().withServiceEndpoint( "oauth/token" ).buildUrl() );

        return getClient().invoke( ggRequest, GetTokenResponse.class, HttpMethod.POST );
    }

    public GetTokenResponse getTokenForSecurityCredentials( String oauthClientId, String oauthClientSecret )
    {
        checkNotEmpty( oauthClientId, "oauthClientId" );
        checkNotEmpty( oauthClientSecret, "oauthClientSecret" );

        final List<NameValuePair> nvps = new ArrayList<>();
        nvps.add( new BasicNameValuePair( GRANT_TYPE_KEY, "client_credentials" ) );

        final GraphGridSecurityRequest ggRequest = new GraphGridSecurityRequest();
        ggRequest.addHeader( AUTH_HEADER_KEY, baseEncodedHeader( oauthClientId, oauthClientSecret ) );
        ggRequest.setRequestHandler( new UrlEncodedRequestHandler() );
        ggRequest.setBody( nvps );
        ggRequest.setEndpoint( getEndpointBuilder().create().withServiceEndpoint( "oauth/token" ).buildUrl() );

        return getClient().invoke( ggRequest, GetTokenResponse.class, HttpMethod.POST );
    }

    public GetTokenResponse getTokenForSecurityCredentials()
    {
        if ( !reusableToken.isPresent() )
        {
            reusableToken = Optional.of(
                    new GetTokenResponseSystem( getTokenForSecurityCredentials( securityConfig.getClientId(), securityConfig.getClientSecret() ) ) );
        }
        else if ( reusableToken.get().isExpired() )
        {
            reusableToken = Optional.of(
                    new GetTokenResponseSystem( getTokenForSecurityCredentials( securityConfig.getClientId(), securityConfig.getClientSecret() ) ) );
        }
        return reusableToken.get().getGetTokenResponse();
    }

    private String baseEncodedHeader( String clientId, String clientKey )
    {
        final String auth = clientId + ":" + clientKey;
        byte[] encodedAuth = Base64.encodeBase64( auth.getBytes( StandardCharsets.ISO_8859_1 ) );
        return BASIC_HEADER_KEY + new String( encodedAuth );
    }
}
