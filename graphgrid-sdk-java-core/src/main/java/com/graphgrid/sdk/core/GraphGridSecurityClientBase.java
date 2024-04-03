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
package com.graphgrid.sdk.core;

import com.graphgrid.sdk.core.model.GraphGridServiceRequest;
import com.graphgrid.sdk.core.model.GraphGridServiceResponse;
import com.graphgrid.sdk.core.security.EmptySecurityConfigs;
import com.graphgrid.sdk.core.security.GraphGridSecurityClient;
import com.graphgrid.sdk.core.security.RequestTokenBuilder;
import com.graphgrid.sdk.core.security.SecurityConfig;
import com.graphgrid.sdk.core.security.SecurityService;
import com.graphgrid.sdk.core.utils.HttpMethod;

import static com.graphgrid.sdk.core.utils.Preconditions.checkNotNull;

/**
 * Adds security services/ context {@link GraphGridClientBase} Handles authentication and token management for request Should be extended by Client class that
 * calls actual gg service endpoints.
 *
 */
public class GraphGridSecurityClientBase extends GraphGridClientBase
{

    private SecurityService securityService;
    private RequestTokenBuilder tokenRequestBuilder;

    public GraphGridSecurityClientBase( String serviceBaseUrl )
    {
        super( serviceBaseUrl );
        configureSecurityService( new EmptySecurityConfigs() );
    }

    public GraphGridSecurityClientBase( String serviceBaseUrl, SecurityConfig securityConfig )
    {
        super( serviceBaseUrl, securityConfig );
        configureSecurityService( securityConfig );
    }

    public GraphGridSecurityClientBase( String serviceBaseUrl, SecurityConfig securityConfig, SessionFactory sessionFactory )
    {
        super( serviceBaseUrl, securityConfig, sessionFactory );
        configureSecurityService( securityConfig );
    }

    private void configureSecurityService( SecurityConfig securityConfig )
    {
        checkNotNull( securityConfig, "securityConfig" );
        securityService = new GraphGridSecurityClient( securityConfig );
        tokenRequestBuilder = new RequestTokenBuilder( securityService );
    }

    public SecurityService getSecurityService()
    {
        return securityService;
    }

    public void setSecurityService( SecurityService securityService )
    {
        this.securityService = securityService;
    }

    public RequestTokenBuilder getTokenRequestBuilder()
    {
        return tokenRequestBuilder;
    }

    public void setTokenRequestBuilder( RequestTokenBuilder tokenRequestBuilder )
    {
        this.tokenRequestBuilder = tokenRequestBuilder;
    }

    /**
     * Core method that executes request, added security => handles authentication and applies additional monitoring headers.
     */
    @Override
    protected <T extends GraphGridServiceResponse> T makeRequest( GraphGridServiceRequest request, Class responseType, HttpMethod httpMethod )
    {
        request = sessionFactory.addTokenToRequest( request );
        request = getTokenRequestBuilder().authenticate( request );
        return super.makeRequest( request, responseType, httpMethod );
    }
}
