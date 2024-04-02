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

import java.io.Serializable;

@JsonAutoDetect
@JsonIgnoreProperties( ignoreUnknown = true )
public class FileNode implements Serializable
{

    private static final long serialVersionUID = -3520359194534813167L;

    private String filename;

    private String grn;

    private String contentType;

    private Long size;

    private String bucket;

    private String key;

    private String url;

    private String description;

    private String md5;

    private String sha1;

    private String sha256;

    public String getFilename()
    {
        return filename;
    }

    public void setFilename( final String filename )
    {
        this.filename = filename;
    }

    public String getContentType()
    {
        return contentType;
    }

    public void setContentType( final String contentType )
    {
        this.contentType = contentType;
    }

    public Long getSize()
    {
        return size;
    }

    public void setSize( final Long size )
    {
        this.size = size;
    }

    public String getBucket()
    {
        return bucket;
    }

    public void setBucket( final String bucket )
    {
        this.bucket = bucket;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey( final String key )
    {
        this.key = key;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl( final String url )
    {
        this.url = url;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getMd5()
    {
        return md5;
    }

    public void setMd5( final String md5 )
    {
        this.md5 = md5;
    }

    public String getSha1()
    {
        return sha1;
    }

    public void setSha1( final String sha1 )
    {
        this.sha1 = sha1;
    }

    public String getSha256()
    {
        return sha256;
    }

    public void setSha256( final String sha256 )
    {
        this.sha256 = sha256;
    }

    public String getGrn()
    {
        return grn;
    }

    public void setGrn( String grn )
    {
        this.grn = grn;
    }
}
