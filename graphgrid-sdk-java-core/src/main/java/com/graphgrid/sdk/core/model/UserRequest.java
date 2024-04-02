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

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Holds information specific to each call executed against the sdk.
 * Can be used for monitoring and logging.
 */
public class UserRequest
{
    private String started;

    private String token;

    private String requestId;

    public UserRequest( String token )
    {
        this.token = token;
        this.requestId = RandomStringUtils.randomAlphanumeric( 20 );
    }


    public String getStarted()
    {
        return started;
    }

    public String getToken()
    {
        return token;
    }

    public String getRequestId()
    {
        return requestId;
    }

    public void setStarted( String started )
    {
        this.started = started;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder( this ).append( "started", started ).append( "token", token ).append( "requestId", requestId ).toString();
    }
}
