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
package com.graphgrid.sdk.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.graphgrid.sdk.core.exception.GraphGridSdkInvalidArgumentException;

import static com.graphgrid.sdk.core.utils.Preconditions.checkNotEmpty;
import static com.graphgrid.sdk.core.utils.Preconditions.checkNotNull;

/**
 * Builds raw URL.
 *
 */
public class UrlBuilder
{

    private static final Logger LOGGER = LoggerFactory.getLogger( UrlBuilder.class );

    private String serviceUrl;
    private String baseUrl;
    private List<String> pathVariables;
    private Map<String,List<String>> queryParameters;

    public UrlBuilder( String baseUrl )
    {
        this.baseUrl = baseUrl;
    }

    public UrlBuilder withBaseUrl( String baseUrl )
    {
        checkNotEmpty( baseUrl, "baseUrl" );
        this.baseUrl = baseUrl;
        return this;
    }

    public UrlBuilder withServiceEndpoint( String serviceUrl )
    {
        checkNotEmpty( serviceUrl, "serviceUrl" );
        this.serviceUrl = serviceUrl;
        return this;
    }

    public UrlBuilder requirePathVariables( List<String> pathVariables )
    {
        checkNotNull( pathVariables, "pathVariables" );
        pathVariables.forEach( p -> checkNotEmpty( p, "pathVariable for building url" ) );
        return withPathVariables( pathVariables );
    }

    /**
     * Applies path variables and filters out any that are blank.
     *
     * @param pathVariables path variables used in the request
     * @return a builder configured with path variables
     */
    public UrlBuilder withPathVariables( List<String> pathVariables )
    {
        this.pathVariables = pathVariables.stream().filter( StringUtils::isNotBlank ).collect( Collectors.toList() );
        return this;
    }

    /**
     * Adds a path variable if it is not blank.
     *
     * @param pathVariable the path variable to add to the request
     * @return a builder with path variable added
     */
    public UrlBuilder withPathVariable( String pathVariable )
    {
        if ( StringUtils.isBlank( pathVariable ) )
        {
            return this;
        }
        this.pathVariables = Optional.ofNullable( this.pathVariables ).orElse( new LinkedList<>() );
        this.pathVariables.add( pathVariable );
        return this;
    }

    /**
     * Use when path variable is required.
     *
     * @param pathVariable the path variable to add to the request
     * @return a builder with path variable added
     */
    public UrlBuilder requirePathVariable( String pathVariable )
    {
        checkNotEmpty( pathVariable, "pathVariable" );
        return withPathVariable( pathVariable );
    }

    public UrlBuilder withQueryParams( Map<String,List<String>> queryParameters )
    {
        this.queryParameters = queryParameters;
        return this;
    }

    public UrlBuilder requireQueryParams( Map<String,List<String>> queryParameters )
    {
        checkNotNull( queryParameters, "queryParameters" );
        return withQueryParams( queryParameters );
    }

    public UrlBuilder withQueryParam( String key, String value )
    {
        return withQueryParam( key, value, true );
    }

    public UrlBuilder withQueryParam( String key, String value, boolean required )
    {

        if ( !required )
        {
            if ( StringUtils.isEmpty( value ) )
            {
                return this;
            }
        }
        checkNotEmpty( key, "key for building url" );
        checkNotNull( value, "value", "for key " + key + " cannot be empty when building url" );
        queryParameters = Optional.ofNullable( queryParameters ).orElse( new HashMap<>() );
        queryParameters.put( key, Collections.singletonList( value ) );
        return this;
    }

    public UrlBuilder withQueryParam( String key, List<String> value )
    {
        return withQueryParam( key, value, true );
    }

    public UrlBuilder withQueryParam( String key, List<String> value, boolean required )
    {
        if ( !required )
        {
            if ( value == null || value.isEmpty() )
            {
                return this;
            }
        }
        checkNotEmpty( key, "key for building url" );
        checkNotNull( value, "value", "for key " + key + " cannot be empty when building url" );
        queryParameters = Optional.ofNullable( queryParameters ).orElse( new HashMap<>() );
        queryParameters.put( key, value );
        return this;
    }

    public URL buildUrl()
    {
        try
        {
            return new URL( buildUrlString() );
        }
        catch ( MalformedURLException e )
        {
            LOGGER.error( e.getMessage() );
        }
        throw new GraphGridSdkInvalidArgumentException( "Unable to build url for request" + this.toString() );
    }

    private String buildUrlString()
    {
        final StringBuilder url = new StringBuilder( checkNotEmpty( baseUrl, "base url" ) );
        if ( StringUtils.isNotEmpty( serviceUrl ) )
        {
            url.append( "/" ).append( serviceUrl );
        }
        if ( pathVariables != null && !pathVariables.isEmpty() )
        {
            url.append( "/" ).append( String.join( "/", this.pathVariables ) );
        }
        if ( queryParameters != null && !queryParameters.isEmpty() )
        {
            url.append( "?" ).append( convertQueryParamMapToString( queryParameters ) );
        }
        return url.toString();
    }

    private String convertQueryParamMapToString( Map<String,List<String>> queryParameters )
    {
        StringBuilder queryString = new StringBuilder();
        if ( queryParameters == null || queryParameters.isEmpty() )
        {
            return "";
        }
        else
        {
            for ( Map.Entry<String,List<String>> entry : queryParameters.entrySet() )
            {
                if ( entry.getValue().isEmpty() )
                {
                    continue;
                }
                else if ( entry.getValue().size() == 1 )
                {
                    queryString.append( entry.getKey() ).append( "=" ).append( entry.getValue().get( 0 ) );
                }
                else
                {
                    queryString.append( entry.getKey() ).append( "=" ).append( String.join( ",", entry.getValue() ) );
                }
                queryString.append( "&" );
            }
        }
        return StringUtils.removeEnd( queryString.toString(), "&" );
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder( this ).append( "serviceUrl", serviceUrl ).append( "baseUrl", baseUrl ).append( "pathVariables", pathVariables ).append(
                "queryParameters", queryParameters ).toString();
    }
}
