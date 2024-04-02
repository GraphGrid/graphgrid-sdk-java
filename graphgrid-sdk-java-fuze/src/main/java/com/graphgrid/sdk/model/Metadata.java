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

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@JsonAutoDetect
@JsonIgnoreProperties( ignoreUnknown = true )
public class Metadata
{
    private String description;
    private String displayName;
    private String createdAt;
    private String updatedAt;
    private List<Policy> versions;

    public Metadata()
    {
        this.createdAt = ZonedDateTime.now().format( DateTimeFormatter.ISO_INSTANT ); // ISO 8601 format
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName( String displayName )
    {
        this.displayName = displayName;
    }

    public String getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt( String createdAt )
    {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt( String updatedAt )
    {
        this.updatedAt = updatedAt;
    }

    public List<Policy> getVersions()
    {
        return versions;
    }

    public void setVersions( List<Policy> versions )
    {
        this.versions = versions;
    }
}
