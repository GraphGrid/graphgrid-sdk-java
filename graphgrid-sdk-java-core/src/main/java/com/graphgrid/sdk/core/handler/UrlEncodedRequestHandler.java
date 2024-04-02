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
package com.graphgrid.sdk.core.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import com.graphgrid.sdk.core.model.GraphGridServiceRequest;
import com.graphgrid.sdk.core.utils.HttpMethod;

public class UrlEncodedRequestHandler implements RequestHandler
{
    @Override
    public HttpResponse executeRequest( GraphGridServiceRequest request, HttpMethod httpMethod ) throws IOException
    {
        return executeRequest( request, httpMethod, HttpClientBuilder.create().build() );
    }

    @Override
    public HttpResponse executeRequest( GraphGridServiceRequest ggRequest, HttpMethod httpMethod, HttpClient client ) throws IOException
    {
        final String url = ggRequest.getEndpoint().toString();

        HttpUriRequest request = null;
        if ( httpMethod == HttpMethod.GET )
        {
            request = new HttpGet( url );
        }
        else if ( httpMethod == HttpMethod.POST )
        {
            request = new HttpPost( url );
            ((HttpPost) request).setEntity( new UrlEncodedFormEntity( (List<NameValuePair>) ggRequest.getBody(), StandardCharsets.UTF_8 ) );
        }
        else if ( httpMethod == HttpMethod.PUT )
        {
            request = new HttpPut( url );
            ((HttpPut) request).setEntity( new UrlEncodedFormEntity( (List<NameValuePair>) ggRequest.getBody(), StandardCharsets.UTF_8 ) );
        }
        else if ( httpMethod == HttpMethod.DELETE )
        {
            request = new HttpDelete( url );
        }
        else if ( httpMethod == HttpMethod.PATCH )
        {
            request = new HttpPatch( url );
        }
        request = addHeaders( ggRequest.getHeaders(), request );
        return client.execute( request );
    }

    private HttpUriRequest addHeaders( final Map<String,String> headers, HttpUriRequest request )
    {
        if ( headers != null )
        {
            for ( Map.Entry<String,String> e : headers.entrySet() )
            {
                request.addHeader( e.getKey(), e.getValue() );
            }
        }
        return request;
    }

    @Override
    public HttpResponse executeRequest( GraphGridServiceRequest ggRequest, HttpMethod httpMethod, ObjectMapper objectMapper, HttpClient client )
            throws IOException
    {
        return executeRequest( ggRequest, httpMethod, client );
    }
}
