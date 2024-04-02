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

import com.graphgrid.sdk.model.CreateRelationshipRequest;
import com.graphgrid.sdk.model.CreateRelationshipResponse;
import com.graphgrid.sdk.model.DeleteFileRequest;
import com.graphgrid.sdk.model.DownloadFilesRequest;
import com.graphgrid.sdk.model.DownloadFilesResponse;
import com.graphgrid.sdk.model.FileServiceStatusRequest;
import com.graphgrid.sdk.model.FileServiceStatusResponse;
import com.graphgrid.sdk.model.FindByResourceRequest;
import com.graphgrid.sdk.model.FindFileRequest;
import com.graphgrid.sdk.model.FindFileResponse;
import com.graphgrid.sdk.model.PersistFileNodeOnlyRequest;
import com.graphgrid.sdk.model.PersistFileNodeOnlyResponse;
import com.graphgrid.sdk.model.UploadFileRequest;
import com.graphgrid.sdk.model.UploadFileResponse;

/**
 * @author bradnussbaum
 */
public interface GraphGridFiles
{

    PersistFileNodeOnlyResponse createFileNodeWithoutUploading( PersistFileNodeOnlyRequest request );

    FileServiceStatusResponse status( FileServiceStatusRequest request );

    void deleteFile( DeleteFileRequest request );

    FindFileResponse findFileByGrn( FindFileRequest request );

    UploadFileResponse uploadFile( UploadFileRequest request );

    DownloadFilesResponse downloadFile( DownloadFilesRequest request );

    CreateRelationshipResponse createRelationship( CreateRelationshipRequest request );

    FindFileResponse findByResource( FindByResourceRequest request );
}
