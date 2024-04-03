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

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import com.graphgrid.sdk.core.model.GraphGridServiceRequest;
import com.graphgrid.sdk.core.model.UserRequest;

/**
 * Acts as an adapter to get a user token from spring security context
 */
public class SpringSecurityContextTokenFactory implements SessionFactory
{
    public static final String GRAPHGRID_USER_TOKEN = "graphgrid-user-token";
    private ObjectMapper mapper;

    private final Function<Object,UserRequest> userRequestMapper = ( obj ) -> {
        Map<String,String> mappedHeaders = mapper.convertValue( obj, Map.class );
        mappedHeaders.get( "tokenValue" );
        mappedHeaders.get( "remoteAddress" );
        return new UserRequest( mappedHeaders.getOrDefault( "tokenValue", "null" ) );
    };

    private final Function<Object,String> tokenMapper = ( obj ) -> {
        Map<String,String> mappedHeaders = mapper.convertValue( obj, Map.class );
        return mappedHeaders.getOrDefault( "tokenValue", "null" );
    };

    private Supplier<Object> tokenSupplier;

    public SpringSecurityContextTokenFactory()
    {
        this( HashMap::new );
    }

    public SpringSecurityContextTokenFactory( Supplier<Object> tokenSupplier )
    {
        mapper = new ObjectMapper();
        this.tokenSupplier = tokenSupplier;
    }

    public UserRequest convertSecurityContextToUserRequest()
    {
        return new UserRequest( userRequestMapper.apply( tokenSupplier.get() ).getToken() );
    }

    @Override
    public GraphGridServiceRequest addTokenToRequest( GraphGridServiceRequest request )
    {
        return request.addHeader( GRAPHGRID_USER_TOKEN, getTokenFromRequest() );
    }

    @Override
    public String getTokenFromRequest()
    {
        final Optional<Object> suppliedToken = Optional.ofNullable( tokenSupplier.get() );
        if ( !suppliedToken.isPresent() )
        {
            return "null";
        }
        return tokenMapper.apply( suppliedToken.get() );
    }
}
