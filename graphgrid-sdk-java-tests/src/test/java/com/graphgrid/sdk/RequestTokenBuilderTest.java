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

import org.junit.Test;

import com.graphgrid.sdk.core.model.GraphGridServiceRequest;
import com.graphgrid.sdk.core.security.GraphGridSecurityClient;
import com.graphgrid.sdk.core.security.NoTokenRequest;
import com.graphgrid.sdk.core.security.RequestTokenBuilder;
import com.graphgrid.sdk.model.FindFileRequest;

import static org.junit.Assert.assertNotNull;

/**
 * @author bradnussbaum
 */
public class RequestTokenBuilderTest extends SdkTestBase
{
    @Test
    public void builderWithToken()
    {
        final FindFileRequest request = new FindFileRequest().withAuthMethod( new NoTokenRequest() );
        request.setGrn( "" );

        final FindFileRequest authenticate = new RequestTokenBuilder( new GraphGridSecurityClient( securityConfig ) ).authenticate( request );
        assertNotNull( authenticate.getGrn() );
    }

    @Test
    public void builderWithToken2()
    {
        final FindFileRequest request = new FindFileRequest();
        request.setGrn( "" );

        final GraphGridServiceRequest authenticate = new RequestTokenBuilder( new GraphGridSecurityClient( securityConfig ) ).authenticate( request );
        assertNotNull( authenticate.getHeaders() );
    }
}
