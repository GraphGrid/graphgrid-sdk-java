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
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import com.graphgrid.sdk.core.exception.GraphGridSdkException;
import com.graphgrid.sdk.core.handler.JsonRequestHandler;
import com.graphgrid.sdk.core.handler.JsonResponseHandler;
import com.graphgrid.sdk.core.handler.RequestHandler;
import com.graphgrid.sdk.core.handler.ResponseHandler;
import com.graphgrid.sdk.core.model.GraphGridServiceRequest;
import com.graphgrid.sdk.core.model.GraphGridServiceResponse;
import com.graphgrid.sdk.core.utils.HttpMethod;

import static com.graphgrid.sdk.core.utils.Preconditions.checkNotNull;

/**
 * Handles making http request to graph grid rest endpoint.
 *
 * @author bradnussbaum
 */
public class GraphGridHttpClient
{

    private static final Logger LOGGER = LoggerFactory.getLogger( GraphGridHttpClient.class );

    private final ObjectMapper objectMapper;
    private final HttpClient apacheClient;

    public GraphGridHttpClient()
    {
        this( new ObjectMapper(), HttpClientBuilder.create().build() );
    }

    public GraphGridHttpClient( ObjectMapper objectMapper, HttpClient client )
    {
        this.objectMapper = checkNotNull( objectMapper, "objectMapper" );
        this.apacheClient = checkNotNull( client, "client" );
    }

    public <T extends GraphGridServiceResponse> T invoke( GraphGridServiceRequest ggRequest, Class<T> responseType, HttpMethod httpMethod )
    {
        IOException ex;
        try
        {
            return (T) processResponse( ggRequest.getResponseHandler(), executeRequest( ggRequest.getRequestHandler(), ggRequest, httpMethod ), objectMapper,
                    responseType );
        }
        catch ( IOException e )
        {
            LOGGER.error( "Error processing request: " + ggRequest, e.getMessage() );
            ex = e;
        }
        throw new GraphGridSdkException( "Error processing request: " + ggRequest, ex );
    }

    public <T extends GraphGridServiceResponse> T processResponse( ResponseHandler handler, HttpResponse httpResponse, ObjectMapper mapper,
            Class<T> responseType ) throws IOException
    {
        if ( handler == null && mapper == null )
        {
            return (T) new JsonResponseHandler().handle( httpResponse, objectMapper, responseType );
        }
        else if ( handler != null && mapper == null )
        {
            return (T) handler.handle( httpResponse, objectMapper, responseType );
        }
        else if ( handler == null && mapper != null )
        {
            return (T) new JsonResponseHandler().handle( httpResponse, mapper, responseType );
        }
        else
        {
            return (T) handler.handle( httpResponse, mapper, responseType );
        }
    }

    private HttpResponse executeRequest( RequestHandler handler, GraphGridServiceRequest request, HttpMethod httpMethod ) throws IOException
    {
        if ( handler == null )
        {
            return new JsonRequestHandler().executeRequest( request, httpMethod, this.apacheClient );
        }
        else
        {
            return request.getRequestHandler().executeRequest( request, httpMethod, this.apacheClient );
        }
    }
}
