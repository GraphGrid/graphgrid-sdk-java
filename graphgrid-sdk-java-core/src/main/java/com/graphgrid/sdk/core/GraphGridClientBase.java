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
import com.graphgrid.sdk.core.security.SecurityConfig;
import com.graphgrid.sdk.core.utils.HttpMethod;
import com.graphgrid.sdk.core.utils.RequestUrlBuilderFactory;

import static com.graphgrid.sdk.core.utils.Preconditions.checkNotNull;

/**
 * Base service for other graph grid client services
 */
public abstract class GraphGridClientBase
{

    private static final String NULL_REQUEST_ERROR = "request";
    private static final String HTTP_METHOD = "http_method";

    private GraphGridHttpClient client;
    private String serviceBaseUrl;
    private SecurityConfig securityConfig;
    private RequestUrlBuilderFactory endpointBuilder;
    protected SessionFactory sessionFactory;

    public GraphGridClientBase( String serviceBaseUrl )
    {
        this( new GraphGridHttpClient(), serviceBaseUrl, new EmptySecurityConfigs(), new SpringSecurityContextTokenFactory() );
    }

    public GraphGridClientBase( String serviceBaseUrl, SecurityConfig securityConfig )
    {
        this( new GraphGridHttpClient(), serviceBaseUrl, securityConfig, new SpringSecurityContextTokenFactory() );
    }

    public GraphGridClientBase( String serviceBaseUrl, SecurityConfig securityConfig, SessionFactory sessionFactory )
    {
        this( new GraphGridHttpClient(), serviceBaseUrl, securityConfig, sessionFactory );
    }

    public GraphGridClientBase( GraphGridHttpClient client, String serviceBaseUrl, SecurityConfig securityConfig, SessionFactory sessionFactory )
    {
        this.client = client;
        this.serviceBaseUrl = serviceBaseUrl;
        this.securityConfig = securityConfig;
        this.endpointBuilder = new RequestUrlBuilderFactory( getServiceBaseUrl() );
        this.sessionFactory = sessionFactory;
    }

    public GraphGridHttpClient getClient()
    {
        return client;
    }

    public String getServiceBaseUrl()
    {
        return serviceBaseUrl;
    }

    public SecurityConfig getSecurityConfig()
    {
        return securityConfig;
    }

    public RequestUrlBuilderFactory getEndpointBuilder()
    {
        return endpointBuilder;
    }

    /**
     * Core method that executes request, sub classes should overwrite method if added behavior is needed.
     */
    protected <T extends GraphGridServiceResponse> T makeRequest( GraphGridServiceRequest request, Class responseType, HttpMethod httpMethod )
    {
        checkNotNull( request, NULL_REQUEST_ERROR );
        checkNotNull( httpMethod, HTTP_METHOD );

        return (T) getClient().invoke( request, responseType, httpMethod );
    }

    public void setClient( GraphGridHttpClient client )
    {
        this.client = client;
    }

    public void setServiceBaseUrl( String serviceBaseUrl )
    {
        this.serviceBaseUrl = serviceBaseUrl;
    }

    public void setSecurityConfig( SecurityConfig securityConfig )
    {
        this.securityConfig = securityConfig;
    }

    public void setEndpointBuilder( RequestUrlBuilderFactory endpointBuilder )
    {
        this.endpointBuilder = endpointBuilder;
    }

    public void setSessionFactory( SessionFactory sessionFactory )
    {
        this.sessionFactory = sessionFactory;
    }
}
