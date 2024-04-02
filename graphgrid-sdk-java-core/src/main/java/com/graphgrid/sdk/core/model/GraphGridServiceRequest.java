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
package com.graphgrid.sdk.core.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.graphgrid.sdk.core.handler.RequestHandler;
import com.graphgrid.sdk.core.handler.ResponseHandler;
import com.graphgrid.sdk.core.security.ClientCredentialsTokenRequest;
import com.graphgrid.sdk.core.security.RequestAuthMethod;
import com.graphgrid.sdk.core.utils.ExecutionContext;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Base class that is used by the SDK to build REST requests that are executed against GraphGrid service.
 * Service modules of the SDK should extend this class for all service methods and add the service specific behavior.
 */
abstract public class GraphGridServiceRequest
{
    @JsonIgnore
    private URL endpoint;
    @JsonIgnore
    private String serviceUrl;
    @JsonIgnore
    private Object body;
    @JsonIgnore
    private Map<String,String> headers;
    @JsonIgnore
    private Map<String,List<String>> customQueryParameters;
    @JsonIgnore
    private RequestHandler requestHandler;
    @JsonIgnore
    private ResponseHandler responseHandler;
    @JsonIgnore
    private RequestAuthMethod requestAuthMethod;
    @JsonIgnore
    private ExecutionContext context;

    public GraphGridServiceRequest()
    {
        this.requestAuthMethod = new ClientCredentialsTokenRequest();
    }

    public GraphGridServiceRequest( RequestAuthMethod requestAuthMethod )
    {
        this.requestAuthMethod = requestAuthMethod;
    }

    @SuppressWarnings( "unchecked" )
    public <T extends GraphGridServiceRequest> T withHeaders( Map<String,String> headers )
    {
        setHeaders( headers );
        T t = (T) this;
        return t;
    }

    @SuppressWarnings( "unchecked" )
    public <T extends GraphGridServiceRequest> T addHeader( String key, String value )
    {
        final Map<String,String> map = Optional.ofNullable( this.headers ).orElse( new HashMap<>() );
        map.put( key, value );
        setHeaders( map );
        T t = (T) this;
        return t;
    }

    @SuppressWarnings( "unchecked" )
    public <T extends GraphGridServiceRequest> T withAuthMethod( RequestAuthMethod authenticationMethod )
    {
        setRequestAuthMethod( authenticationMethod );
        T t = (T) this;
        return t;
    }

    public URL getEndpoint() throws MalformedURLException
    {
        return endpoint;
    }

    /**
     * Used internally by the sdk to specify the url
     *
     * @param endpoint
     */
    public void setEndpoint( URL endpoint )
    {
        this.endpoint = endpoint;
    }

    public Object getBody()
    {
        return body;
    }

    /**
     * Used internally by the sdk to create the request body of the request
     *
     * @param body
     */
    public void setBody( Object body )
    {
        this.body = body;
    }

    public Map<String,String> getHeaders()
    {
        return headers;
    }

    /**
     * Used internally by the sdk to create the request header of the request
     *
     * @param headers
     */
    public void setHeaders( Map<String,String> headers )
    {
        this.headers = headers;
    }

    public Map<String,List<String>> getCustomQueryParameters()
    {
        return customQueryParameters;
    }

    /**
     * @param customQueryParameters
     */
    public void setCustomQueryParameters( Map<String,List<String>> customQueryParameters )
    {
        this.customQueryParameters = customQueryParameters;
    }

    public RequestHandler getRequestHandler()
    {
        return requestHandler;
    }

    /**
     * Used internally by the sdk to execute the REST request
     *
     * @param requestHandler
     */
    public void setRequestHandler( RequestHandler requestHandler )
    {
        this.requestHandler = requestHandler;
    }

    public ResponseHandler getResponseHandler()
    {
        return responseHandler;
    }

    /**
     * Used internally by the sdk handle the REST respond
     *
     * @param responseHandler
     */
    public void setResponseHandler( ResponseHandler responseHandler )
    {
        this.responseHandler = responseHandler;
    }

    public String getServiceUrl()
    {
        return serviceUrl;
    }

    /**
     * Used internally by the sdk. Defines the REST service endpoint of a GraphGrid service
     *
     * @param serviceUrl
     */
    public void setServiceUrl( String serviceUrl )
    {
        this.serviceUrl = serviceUrl;
    }

    public RequestAuthMethod getRequestAuthMethod()
    {
        return requestAuthMethod;
    }

    /**
     * Used internally by the sdk
     *
     * @param requestAuthMethod
     */
    public void setRequestAuthMethod( RequestAuthMethod requestAuthMethod )
    {
        this.requestAuthMethod = requestAuthMethod;
    }


    @Override
    public String toString()
    {
        return new ToStringBuilder( this ).append( "endpoint", endpoint ).append( "serviceUrl", serviceUrl ).append( "body", body ).append( "headers", headers )
                .append( "customQueryParameters", customQueryParameters ).append( "requestHandler", requestHandler )
                .append( "responseHandler", responseHandler ).append( "requestAuthMethod", requestAuthMethod ).toString();
    }
}
