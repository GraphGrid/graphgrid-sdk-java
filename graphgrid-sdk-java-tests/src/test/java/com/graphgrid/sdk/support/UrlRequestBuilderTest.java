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
package com.graphgrid.sdk.support;

import org.junit.Assert;
import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.graphgrid.sdk.core.model.GraphGridServiceRequest;
import com.graphgrid.sdk.core.security.NoTokenRequest;
import com.graphgrid.sdk.core.utils.RequestUrlBuilderFactory;
import com.graphgrid.sdk.core.utils.UrlBuilder;
import com.graphgrid.sdk.model.FindFileRequest;

import static org.junit.Assert.assertEquals;

public class UrlRequestBuilderTest
{

    @Test
    public void buildUrl1()
    {
        final URL url = new UrlBuilder( "http://localhost/gg.file.com" ).withPathVariable( "grn:file:123" ).buildUrl();
        assertEquals( url.toString(), "http://localhost/gg.file.com/grn:file:123" );
    }

    @Test
    public void buildUrl2()
    {
        final URL url = new UrlBuilder( "http://localhost/gg.file.com" ).withServiceEndpoint( "upload" ).withPathVariable( "grn:file:123" ).buildUrl();
        assertEquals( url.toString(), "http://localhost/gg.file.com/upload/grn:file:123" );
    }

    @Test
    public void buildUrl3()
    {
        final FindFileRequest request = new FindFileRequest( "grn:file:123" ).withAuthMethod( new NoTokenRequest() );
        final URL url = new UrlBuilder( "http://localhost/gg.file.com" ).withBaseUrl( "http://localhost/gg.file.com" ).withPathVariable(
                request.getGrn() ).withPathVariable( "grn:gg:org:213" ).buildUrl();
        assertEquals( url.toString(), "http://localhost/gg.file.com/grn:file:123/grn:gg:org:213" );
    }

    @Test
    public void buildUrl3b()
    {
        final FindFileRequest request = new FindFileRequest( "grn:file:123" ).withAuthMethod( new NoTokenRequest() );
        final URL url = new UrlBuilder( "http://localhost/gg.file.com" ).withBaseUrl( "http://localhost/gg.file.com" ).withPathVariable(
                request.getGrn() ).withPathVariable( "grn:gg:org:213" ).withQueryParam( "k1", "", false ).buildUrl();
        assertEquals( url.toString(), "http://localhost/gg.file.com/grn:file:123/grn:gg:org:213" );
    }

    @Test
    public void buildUrl4()
    {
        final FindFileRequest request = new FindFileRequest().withAuthMethod( new NoTokenRequest() );
        request.setGrn( "" );

        final URL url = new UrlBuilder( "http://localhost/gg.file.com" ).withBaseUrl( "http://localhost/gg.file.com" ).withQueryParam( "k1", "v1" ).buildUrl();
        assertEquals( url.toString(), "http://localhost/gg.file.com?k1=v1" );
    }

    @Test
    public void buildUrl5()
    {
        final URL url =
                new UrlBuilder( "http://localhost/gg.file.com" ).withBaseUrl( "http://localhost/gg.file.com" ).withQueryParam( "k1", "v1" ).withQueryParam(
                        "k2", "v2" ).buildUrl();
        assertEquals( url.toString(), "http://localhost/gg.file.com?k1=v1&k2=v2" );
    }

    @Test
    public void buildUrl6()
    {
        final URL url =
                new UrlBuilder( "http://localhost/gg.file.com" ).withBaseUrl( "http://localhost/gg.file.com" ).withQueryParam( "k1", "v1" ).withQueryParam(
                        "k2", Collections.singletonList( "v21" ) ).buildUrl();
        assertEquals( url.toString(), "http://localhost/gg.file.com?k1=v1&k2=v21" );
    }

    @Test
    public void buildUrl7()
    {
        final URL url =
                new UrlBuilder( "http://localhost/gg.file.com" ).withQueryParam( "k1", "v1" ).withQueryParam( "k2", Arrays.asList( "v21", "v22" ) ).buildUrl();
        assertEquals( url.toString(), "http://localhost/gg.file.com?k1=v1&k2=v21,v22" );
    }

    @Test
    public void buildWithFactory()
    {
        final RequestUrlBuilderFactory urlBuilder = new RequestUrlBuilderFactory( "http://localhost/gg.file.com" );
        final URL url = urlBuilder.create().withServiceEndpoint( "payment" ).buildUrl();
        assertEquals( url.toString(), "http://localhost/gg.file.com/payment" );
    }

    @Test
    public void buildWithFactory2()
    {
        final RequestUrlBuilderFactory urlBuilder = new RequestUrlBuilderFactory( "http://localhost/gg.file.com" );
        final URL url = urlBuilder.create().withServiceEndpoint( "payment" ).buildUrl();
        assertEquals( url.toString(), "http://localhost/gg.file.com/payment" );
    }

    @Test
    public void buildWithFactory3() throws Exception
    {
        final FindFileRequest request = new FindFileRequest().withAuthMethod( new NoTokenRequest() );
        request.setGrn( "" );

        final RequestUrlBuilderFactory urlBuilder = new RequestUrlBuilderFactory( "http://localhost/gg.file.com" );
        final GraphGridServiceRequest requestWithUrl = urlBuilder.create( request ).withServiceEndpoint( "payment" ).buildRequestWithUrl();
        assertEquals( requestWithUrl.getEndpoint().toString(), "http://localhost/gg.file.com/payment" );
    }

    @Test
    public void buildWithFactory4() throws Exception
    {
        final FindFileRequest request = new FindFileRequest().withAuthMethod( new NoTokenRequest() );
        request.setGrn( "" );

        final RequestUrlBuilderFactory urlBuilder = new RequestUrlBuilderFactory( "http://localhost/gg.file.com" );
        final GraphGridServiceRequest requestWithUrl =
                urlBuilder.create( request ).withServiceEndpoint( "payment" ).addPathVariable( "delete" ).buildRequestWithUrl();
        assertEquals( requestWithUrl.getEndpoint().toString(), "http://localhost/gg.file.com/payment/delete" );
    }

    @Test( expected = com.graphgrid.sdk.core.exception.GraphGridSdkInvalidArgumentException.class )
    public void buildWithFactory5()
    {
        final RequestUrlBuilderFactory urlBuilder = new RequestUrlBuilderFactory( null );
        final URL url = urlBuilder.create().withServiceEndpoint( "payment" ).buildUrl();
    }

    @Test( expected = com.graphgrid.sdk.core.exception.GraphGridSdkInvalidArgumentException.class )
    public void buildWithFactory6()
    {
        final RequestUrlBuilderFactory urlBuilder = new RequestUrlBuilderFactory( "http://localhost:8080" );
        final URL url = urlBuilder.create().withServiceEndpoint( "go" ).requirePathVariable( null ).buildUrl();
        assertEquals( url.toString(), "http://localhost:8080/go" );
    }

    @Test
    public void buildWithFactory6b()
    {
        final RequestUrlBuilderFactory urlBuilder = new RequestUrlBuilderFactory( "http://localhost:8080" );
        final URL url = urlBuilder.create().withServiceEndpoint( "go" ).withPathVariable( null ).buildUrl();
        assertEquals( url.toString(), "http://localhost:8080/go" );
    }

    @Test( expected = com.graphgrid.sdk.core.exception.GraphGridSdkInvalidArgumentException.class )
    public void buildWithFactory7()
    {
        List<String> p = new ArrayList<>();
        p.add( null );
        final RequestUrlBuilderFactory urlBuilder = new RequestUrlBuilderFactory( "http://localhost:8080" );
        urlBuilder.create().withServiceEndpoint( "go" ).requirePathVariables( p ).buildUrl();
        Assert.fail();
    }

    @Test
    public void buildWithFactory7b()
    {
        List<String> p = new ArrayList<>();
        p.add( null );
        final RequestUrlBuilderFactory urlBuilder = new RequestUrlBuilderFactory( "http://localhost:8080" );
        final URL url = urlBuilder.create().withServiceEndpoint( "go" ).withPathVariables( p ).buildUrl();
        assertEquals( url.toString(), "http://localhost:8080/go" );
    }

    @Test( expected = com.graphgrid.sdk.core.exception.GraphGridSdkInvalidArgumentException.class )
    public void buildWithFactory8()
    {
        List<String> p = new ArrayList<>();
        p.add( "" );
        p.add( null );

        final RequestUrlBuilderFactory urlBuilder = new RequestUrlBuilderFactory( "http://localhost:8080" );
        urlBuilder.create().withServiceEndpoint( "go" ).requirePathVariables( p ).buildUrl();
        Assert.fail();
    }

    @Test
    public void buildWithFactory8b()
    {
        final RequestUrlBuilderFactory urlBuilder = new RequestUrlBuilderFactory( "http://localhost:8080" );
        final URL url = urlBuilder.create().withServiceEndpoint( "go" ).withQueryParam( "k1", "", false ).buildUrl();
        assertEquals( url.toString(), "http://localhost:8080/go" );
    }

    @Test
    public void buildWithFactory8c()
    {
        final RequestUrlBuilderFactory urlBuilder = new RequestUrlBuilderFactory( "http://localhost:8080" );
        final URL url = urlBuilder.create().withServiceEndpoint( "go" ).withQueryParam( "k1", "", true ).buildUrl();
        assertEquals( url.toString(), "http://localhost:8080/go?k1=" );
    }

    @Test
    public void buildWithFactory8d()
    {
        final RequestUrlBuilderFactory urlBuilder = new RequestUrlBuilderFactory( "http://localhost:8080" );
        final URL url = urlBuilder.create().withServiceEndpoint( "go" ).withQueryParam( "k1", "v1", false ).buildUrl();
        assertEquals( url.toString(), "http://localhost:8080/go?k1=v1" );
    }

    @Test( expected = com.graphgrid.sdk.core.exception.GraphGridSdkInvalidArgumentException.class )
    public void buildWithFactory9_null()
    {
        String param = null;

        final RequestUrlBuilderFactory urlBuilder = new RequestUrlBuilderFactory( "http://localhost:8080" );
        final URL url = urlBuilder.create().withServiceEndpoint( "go" ).withQueryParam( "k1", param, true ).buildUrl();
    }
}
