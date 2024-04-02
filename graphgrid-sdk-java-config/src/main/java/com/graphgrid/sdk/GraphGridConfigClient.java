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
package com.graphgrid.sdk;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

import com.graphgrid.sdk.core.GraphGridSecurityClientBase;
import com.graphgrid.sdk.core.SessionFactory;
import com.graphgrid.sdk.core.model.GraphGridServiceRequest;
import com.graphgrid.sdk.core.model.GraphGridServiceResponse;
import com.graphgrid.sdk.core.security.SecurityConfig;
import com.graphgrid.sdk.core.utils.HttpMethod;
import com.graphgrid.sdk.model.UpdateConfigValueRequest;
import com.graphgrid.sdk.model.UpdateConfigValueResponse;
import com.graphgrid.sdk.support.Endpoints;

public class GraphGridConfigClient extends GraphGridSecurityClientBase implements GraphGridConfig
{
    public GraphGridConfigClient( String serviceBaseUrl )
    {
        super( serviceBaseUrl );
    }

    public GraphGridConfigClient( String serviceBaseUrl, SecurityConfig securityConfig )
    {
        super( serviceBaseUrl, securityConfig );
    }

    public GraphGridConfigClient( String serviceBaseUrl, SecurityConfig securityConfig, SessionFactory sessionFactory )
    {
        super( serviceBaseUrl, securityConfig, sessionFactory );
    }

    @Override
    public UpdateConfigValueResponse updateConfigValue( UpdateConfigValueRequest request )
    {
        Map<String,String> requestBody = new HashMap<>();

        if ( StringUtils.isNotEmpty( request.getPath() ) )
        {
            request.setEndpoint( getEndpointBuilder().create( request ).withServiceEndpoint( Endpoints.UPDATE_CONFIG_VALUE ).buildUrl() );
            requestBody.put( "path", request.getPath() );
        }
        else
        {
            request.setEndpoint( getEndpointBuilder().create( request ).withServiceEndpoint( Endpoints.UPDATE_CONFIG_VALUE ).addPathVariable( request.getApplication() ).addPathVariable(
                    request.getEnvironment() ).addPathVariable( request.getBranch() ).buildUrl() );
        }

        requestBody.put( "key", request.getKey() );
        requestBody.put( "value", request.getValue() );
        request.setBody( requestBody );

        return makeRequest( request, UpdateConfigValueResponse.class, HttpMethod.POST );
    }

    @Override
    protected <T extends GraphGridServiceResponse> T makeRequest( GraphGridServiceRequest request, Class responseType, HttpMethod httpMethod )
    {
        return super.makeRequest( request, responseType, httpMethod );
    }
}
