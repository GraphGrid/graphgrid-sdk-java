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


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.graphgrid.sdk.core.model.GraphGridServiceRequest;

import static com.graphgrid.sdk.core.utils.Preconditions.checkNotEmpty;

@JsonAutoDetect
@JsonIgnoreProperties( ignoreUnknown = true )
public class DeleteFileRequest extends GraphGridServiceRequest
{

    private String orgGrn;

    private String fileGrn;

    private String region;

    public DeleteFileRequest()
    {
    }

    public DeleteFileRequest( String orgGrn, String fileGrn )
    {
        this.orgGrn = checkNotEmpty( orgGrn, "orgGrn" );
        this.fileGrn = checkNotEmpty( fileGrn, "fileGrn" );
    }

    public String getOrgGrn()
    {
        return orgGrn;
    }

    public void setOrgGrn( String orgGrn )
    {
        this.orgGrn = orgGrn;
    }

    public String getFileGrn()
    {
        return fileGrn;
    }

    public void setFileGrn( String fileGrn )
    {
        this.fileGrn = fileGrn;
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
