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
package com.graphgrid.sdk.module;

import org.assertj.core.util.Lists;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;

import com.graphgrid.sdk.GraphGridFilesClient;
import com.graphgrid.sdk.SdkTestBase;
import com.graphgrid.sdk.core.exception.GraphGridSdkException;
import com.graphgrid.sdk.core.security.ClientCredentialsTokenRequest;
import com.graphgrid.sdk.core.security.GraphGridSecurityClient;
import com.graphgrid.sdk.core.security.NoTokenRequest;
import com.graphgrid.sdk.core.security.SecurityService;
import com.graphgrid.sdk.core.security.TokenRequest;
import com.graphgrid.sdk.core.security.UserTokenRequest;
import com.graphgrid.sdk.model.FileNode;
import com.graphgrid.sdk.model.FileServiceStatusRequest;
import com.graphgrid.sdk.model.FileServiceStatusResponse;
import com.graphgrid.sdk.model.FindFileRequest;
import com.graphgrid.sdk.model.FindFileResponse;
import com.graphgrid.sdk.model.PersistFileNodeOnlyRequest;
import com.graphgrid.sdk.model.UploadFileMetadata;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class FilesModuleTest extends SdkTestBase
{

    private final String FILE_GRN = "grn:gg:file:Mm5FzYHWZd92Tx3rqKpGHaDc0pdjMmZclyKgK4fe8sUL";
    private final String REGION_GRN = "grn:gg:region:yP20RrZjWXahpjp4mBSWlK0Wwem7vBOGyM9pqIxns3Ml";
    private final String ORG_GRN = "grn:gg:org:T4NQL5jsr2MUzbJFVabOSWaqbXu7fbYvMWGo2egE37n2";

    @Test
    public void testStatus()
    {
        final GraphGridFilesClient client = new GraphGridFilesClient( "https://dev-api.graphgrid.com/1.0/files", securityConfig );
        final FileServiceStatusResponse response = client.status( new FileServiceStatusRequest().withAuthMethod( new ClientCredentialsTokenRequest() ) );

        assertNotNull( response );
    }

    @Test
    @Ignore( "Used to setup data" )
    public void uploadGetDeleteFileInternalServerError()
    {
        final UploadFileMetadata.CreateRelationship createRelationship = new UploadFileMetadata.CreateRelationship();
        createRelationship.setGrn( REGION_GRN );
        createRelationship.setDirection( "OUTGOING" );
        createRelationship.setType( "IN_REGION" );

        // Upload files
        final FileNode fileNode = new FileNode();
        fileNode.setBucket( "sample-bucket" );
        fileNode.setFilename( "sample-file" );
        fileNode.setKey( "sample-key" );
        fileNode.setDescription( "sample" );
        fileNode.setGrn( FILE_GRN );

        final UploadFileMetadata uploadFileMetadata = new UploadFileMetadata();
        uploadFileMetadata.withCreateProperties(
                new UploadFileMetadata.CreateProperties().withMd5( "auto" ).withSha1( "auto" ).withSha256( "auto" ) ).withFileNode(
                fileNode ).withCreateRelationships( Lists.newArrayList( createRelationship ) );
        uploadFileMetadata.setFileNode( fileNode );

        final PersistFileNodeOnlyRequest request = new PersistFileNodeOnlyRequest();
        request.setOrgGrn( ORG_GRN );
        request.setUploadFileMetadata( uploadFileMetadata );

        final GraphGridFilesClient client = new GraphGridFilesClient( "https://dev-api.graphgrid.com/1.0/files", securityConfig );
        client.createFileNodeWithoutUploading( request );
    }

    @Test
    public void incorrectFilesServiceShouldProduceBadGateway()
    {
        GraphGridSdkException exception = null;
        final GraphGridFilesClient client = new GraphGridFilesClient( "https://dev-api.graphgrid.com/1.0/filesServiceNotHere" );
        try
        {
            client.status( new FileServiceStatusRequest().withAuthMethod( new NoTokenRequest() ) );
        }
        catch ( GraphGridSdkException ex )
        {
            exception = ex;
        }
        assertNotNull( exception );
        assertEquals( 502, exception.getHttpStatusCode() );
    }

    @Test
    public void notAuthorized()
    {
        final FileNode fileNode = new FileNode();
        final UploadFileMetadata uploadFileMetadata = new UploadFileMetadata();
        uploadFileMetadata.setFileNode( fileNode );

        final PersistFileNodeOnlyRequest request = new PersistFileNodeOnlyRequest();
        request.setOrgGrn( "grn" );
        request.setUploadFileMetadata( uploadFileMetadata );
        request.setRequestAuthMethod( new NoTokenRequest() );

        final HashMap<String,String> map = new HashMap<>();
        request.withHeaders( map );

        final GraphGridFilesClient client = new GraphGridFilesClient( "https://dev-api.graphgrid.com/1.0/files" );

        GraphGridSdkException exception = null;
        try
        {
            client.createFileNodeWithoutUploading( request );
        }
        catch ( GraphGridSdkException ex )
        {
            exception = ex;
        }
        assertNotNull( exception );
        assertEquals( exception.getHttpStatusCode(), 401 );
    }

    @Test( expected = com.graphgrid.sdk.core.exception.GraphGridSdkException.class )
    public void invalidToken()
    {
        final GraphGridFilesClient client = new GraphGridFilesClient( "https://dev-api.graphgrid.com/1.0/files" );

        final FindFileRequest request = new FindFileRequest( FILE_GRN ).withAuthMethod( new TokenRequest( "ddf08ff3-ee0c-4b02-86e7-1fa551a2faa7" ) );
        request.setGrn( FILE_GRN );

        final FindFileResponse file = client.findFileByGrn( request );
        assertNotNull( file );
        assertNotNull( file.getFileNode() );
    }

    @Test
    public void getFileNodeWithPreexistingGrn()
    {
        SecurityService securityService = new GraphGridSecurityClient( securityConfig );
        String token = securityService.getTokenForSecurityCredentials().getAccessToken();

        final GraphGridFilesClient client = new GraphGridFilesClient( "https://dev-api.graphgrid.com/1.0/files" );

        final FindFileRequest request = new FindFileRequest( FILE_GRN ).withAuthMethod( new TokenRequest( token ) );
        request.setGrn( FILE_GRN );

        final FindFileResponse file = client.findFileByGrn( request );
        assertNotNull( file );
        assertNotNull( file.getFileNode() );
    }

    @Test
    @Ignore
    public void getFileNodeWithUserToken()
    {
        final GraphGridFilesClient client = new GraphGridFilesClient( "https://dev-api.graphgrid.com/1.0/files", securityConfig );

        final FindFileRequest request = new FindFileRequest( FILE_GRN ).withAuthMethod( new UserTokenRequest( username, password ) );
        request.setGrn( FILE_GRN );

        final FindFileResponse file = client.findFileByGrn( request );
        assertNotNull( file );
        assertNotNull( file.getFileNode() );
    }

    @Test
    public void getFileNodeWithUserClientToken()
    {
        final GraphGridFilesClient client = new GraphGridFilesClient( "https://dev-api.graphgrid.com/1.0/files", securityConfig );

        // somewhere else
        final FindFileRequest request = new FindFileRequest( FILE_GRN );

        final FindFileResponse file = client.findFileByGrn( request );
        assertNotNull( file );
        assertNotNull( file.getFileNode() );
    }
}
