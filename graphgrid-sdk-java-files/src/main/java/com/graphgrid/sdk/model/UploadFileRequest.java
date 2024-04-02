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
package com.graphgrid.sdk.model;

import com.graphgrid.sdk.core.model.GraphGridServiceRequest;

import java.util.List;

public class UploadFileRequest extends GraphGridServiceRequest
{

    private String bucket;
    private String key;
    private List<String> metadata;
    // todo MultipartFile is a spring class find solution without using spring
    // MultipartFile
    private List<Object> fileData;
    private String orgGrn;
    private String region;

    private UploadFileMetadata uploadFileMetadata;

    public UploadFileMetadata getUploadFileMetadata()
    {
        return uploadFileMetadata;
    }

    public void setUploadFileMetadata( UploadFileMetadata uploadFileMetadata )
    {
        this.uploadFileMetadata = uploadFileMetadata;
    }


    public String getBucket()
    {
        return bucket;
    }

    public void setBucket( String bucket )
    {
        this.bucket = bucket;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey( String key )
    {
        this.key = key;
    }

    public List<String> getMetadata()
    {
        return metadata;
    }

    public void setMetadata( List<String> metadata )
    {
        this.metadata = metadata;
    }

    public List<Object> getFileData()
    {
        return fileData;
    }

    public void setFileData( List<Object> fileData )
    {
        this.fileData = fileData;
    }

    public String getOrgGrn()
    {
        return orgGrn;
    }

    public void setOrgGrn( String orgGrn )
    {
        this.orgGrn = orgGrn;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion( String region )
    {
        this.region = region;
    }
}
