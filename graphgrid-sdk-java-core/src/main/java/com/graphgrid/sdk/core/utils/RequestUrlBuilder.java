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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.graphgrid.sdk.core.exception.GraphGridSdkInvalidArgumentException;
import com.graphgrid.sdk.core.model.GraphGridServiceRequest;

import static com.graphgrid.sdk.core.utils.Preconditions.checkNotEmpty;
import static com.graphgrid.sdk.core.utils.Preconditions.checkNotNull;

/**
 * Wrapper around {@link UrlBuilder} for builds urls for {@link GraphGridServiceRequest} controls if a {@link GraphGridServiceRequest#getEndpoint()} should be
 * overwritten if present.
 */
public class RequestUrlBuilder
{

    private static final Logger LOGGER = LoggerFactory.getLogger( RequestUrlBuilder.class );

    private GraphGridServiceRequest request;
    /**
     * if request has url already defined reuse it default is true
     */
    private boolean overwriteUrl = true;

    private UrlBuilder urlBuilder;

    public RequestUrlBuilder( String baseUrl, GraphGridServiceRequest request )
    {
        this.urlBuilder = new UrlBuilder( checkNotEmpty( baseUrl ) );
        this.request = checkNotNull( request, "request" );

        // to ensure custom query parameter dont get overwritten
        if ( request.getCustomQueryParameters() != null )
        {
            urlBuilder.requireQueryParams( request.getCustomQueryParameters() );
        }
    }

    public RequestUrlBuilder overwriteUrl( boolean overwriteUrl )
    {
        this.overwriteUrl = overwriteUrl;
        return this;
    }

    public RequestUrlBuilder withServiceEndpoint( String serviceUrl )
    {
        urlBuilder.withServiceEndpoint( serviceUrl );
        return this;
    }

    public RequestUrlBuilder withPathVariables( List<String> pathVariables )
    {
        urlBuilder.requirePathVariables( pathVariables );
        return this;
    }

    public RequestUrlBuilder addPathVariable( String pathVariables )
    {
        urlBuilder.withPathVariable( pathVariables );
        return this;
    }

    public RequestUrlBuilder withQueryParams( Map<String,List<String>> queryParameters )
    {
        urlBuilder.requireQueryParams( queryParameters );
        return this;
    }

    public RequestUrlBuilder addQueryParam( String key, String value )
    {
        return addQueryParam( key, value, true );
    }

    public RequestUrlBuilder addQueryParam( String key, String value, boolean required )
    {
        urlBuilder.withQueryParam( key, value, required );
        return this;
    }

    public RequestUrlBuilder addQueryParam( String key, List<String> value )
    {
        return addQueryParam( key, value, true );
    }

    public RequestUrlBuilder addQueryParam( String key, List<String> value, boolean required )
    {
        urlBuilder.withQueryParam( key, value, required );
        return this;
    }

    public GraphGridServiceRequest buildRequestWithUrl()
    {
        if ( !overwriteUrl )
        {
            return request;
        }
        else
        {
            request.setEndpoint( buildUrl() );
            return request;
        }
    }

    public URL buildUrl()
    {
        try
        {
            if ( !overwriteUrl )
            {
                return checkNotNull( request.getEndpoint(), "request.endpoint" );
            }
            else
            {
                return urlBuilder.buildUrl();
            }
        }
        catch ( MalformedURLException e )
        {
            LOGGER.error( e.getMessage() );
        }
        throw new GraphGridSdkInvalidArgumentException( "Unable to build url for request" + this.toString() );
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }

        if ( !(o instanceof RequestUrlBuilder) )
        {
            return false;
        }

        RequestUrlBuilder that = (RequestUrlBuilder) o;

        return new EqualsBuilder().append( overwriteUrl, that.overwriteUrl ).append( request, that.request ).append( urlBuilder, that.urlBuilder ).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder( 17, 37 ).append( request ).append( overwriteUrl ).append( urlBuilder ).toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder( this ).append( "request", request ).append( "overwriteUrl", overwriteUrl ).append( "urlBuilder", urlBuilder ).toString();
    }
}
