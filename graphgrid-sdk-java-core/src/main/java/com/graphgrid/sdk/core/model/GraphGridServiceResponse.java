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


/**
 * Base class for all service responses
 * Service modules of the SDK should extend this class for all service methods and add the service specific behavior.
 */
import com.graphgrid.sdk.core.exception.GraphGridSdkException;

public class GraphGridServiceResponse
{

    private Object response;

    private GraphGridSdkException exception;

    private String statusText;

    private int statusCode;


    public GraphGridServiceResponse()
    {
    }

    /**
     * used when response is exception
     *
     * @param exception
     * @param statusText
     * @param statusCode
     */
    public GraphGridServiceResponse( GraphGridSdkException exception, String statusText, int statusCode )
    {
        this.exception = exception;
        this.statusText = statusText;
        this.statusCode = statusCode;
    }


    @SuppressWarnings( "unchecked" )
    public <T extends GraphGridServiceResponse> T withException( GraphGridSdkException exception )
    {
        setException( exception );
        T t = (T) this;
        return t;
    }


    public Object getResponse()
    {
        return response;
    }

    public void setResponse( Object response )
    {
        this.response = response;
    }

    public GraphGridSdkException getException()
    {
        return exception;
    }

    public void setException( GraphGridSdkException exception )
    {
        this.exception = exception;
    }

    public String getStatusText()
    {
        return statusText;
    }

    public void setStatusText( String statusText )
    {
        this.statusText = statusText;
    }

    public int getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode( int statusCode )
    {
        this.statusCode = statusCode;
    }

}
