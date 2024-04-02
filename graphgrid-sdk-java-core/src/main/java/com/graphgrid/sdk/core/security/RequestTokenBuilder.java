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

import java.util.Optional;

import com.graphgrid.sdk.core.exception.GraphGridSecurityException;
import com.graphgrid.sdk.core.model.GetTokenResponse;
import com.graphgrid.sdk.core.model.GraphGridServiceRequest;

/**
 * @author bradnussbaum
 */
public class RequestTokenBuilder
{

    private final static String AUTH_HEADER_KEY = "Authorization";
    private final static String AUTH_HEADER_BEARER_VALUE = "Bearer";

    private SecurityService securityService;

    public RequestTokenBuilder( SecurityService securityService )
    {
        this.securityService = securityService;
    }

    public <T extends GraphGridServiceRequest> T authenticate( GraphGridServiceRequest request )
    {
        if ( request.getRequestAuthMethod() == null )
        {
            throw new IllegalArgumentException( "Request authentication method must be provided." );
        }
        else if ( request.getRequestAuthMethod() instanceof TokenRequest )
        {
            return (T) authenticateWithExistingToken( request, ((TokenRequest) request.getRequestAuthMethod()).getToken() );
        }
        else if ( request.getRequestAuthMethod() instanceof ClientCredentialsTokenRequest )
        {
            return (T) authenticateWithClientCredentials( request );
        }
        else if ( request.getRequestAuthMethod() instanceof UserTokenRequest )
        {
            return (T) authenticateWithUser( request, ((UserTokenRequest) request.getRequestAuthMethod()).getUserName(),
                    ((UserTokenRequest) request.getRequestAuthMethod()).getPassword() );
        }
        else if ( request.getRequestAuthMethod() instanceof NoTokenRequest )
        {
            return (T) request;
        }
        else
        {
            throw new IllegalArgumentException( "Unknown request authentication." );
        }
    }

    public GraphGridServiceRequest authenticateWithExistingToken( GraphGridServiceRequest request, String token )
    {
        request.addHeader( AUTH_HEADER_KEY, AUTH_HEADER_BEARER_VALUE + " " + token );
        return request;
    }

    public GraphGridServiceRequest authenticateWithExistingToken( GraphGridServiceRequest request, String token, Class c )
    {
        request.addHeader( AUTH_HEADER_KEY, AUTH_HEADER_BEARER_VALUE + " " + token );
        return request;
    }

    public GraphGridServiceRequest authenticateWithUser( GraphGridServiceRequest request, String username, String password )
    {
        request.addHeader( AUTH_HEADER_KEY, AUTH_HEADER_BEARER_VALUE + " " +
                Optional.ofNullable( securityService.getToken( username, password ) ).map( GetTokenResponse::getAccessToken ).orElseThrow(
                        () -> new GraphGridSecurityException( "No token was returned" ) ) );
        return request;
    }

    public GraphGridServiceRequest authenticateWithClientCredentials( GraphGridServiceRequest request )
    {
        request.addHeader( AUTH_HEADER_KEY, AUTH_HEADER_BEARER_VALUE + " " +
                Optional.ofNullable( securityService.getTokenForSecurityCredentials() ).map( GetTokenResponse::getAccessToken ).orElseThrow(
                        () -> new GraphGridSecurityException( "No token was returned" ) ) );
        return request;
    }
}
