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
package com.graphgrid.sdk.core.exception;

public class GraphGridSdkException extends RuntimeException
{
    private int httpStatusCode;

    public GraphGridSdkException( String message )
    {
        super( message );
    }

    public GraphGridSdkException()
    {
        super( "graph grid service error" );
    }

    public GraphGridSdkException( String message, Throwable cause )
    {
        super( message, cause );
    }

    public GraphGridSdkException withStatusCode( int httpStatusCode )
    {
        this.httpStatusCode = httpStatusCode;
        return this;
    }

    public int getHttpStatusCode()
    {
        return httpStatusCode;
    }

    public void setHttpStatusCode( int httpStatusCode )
    {
        this.httpStatusCode = httpStatusCode;
    }
}
