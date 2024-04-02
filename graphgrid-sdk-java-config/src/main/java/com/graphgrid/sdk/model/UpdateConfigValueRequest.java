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

@JsonAutoDetect
@JsonIgnoreProperties( ignoreUnknown = true )
public class UpdateConfigValueRequest extends GraphGridServiceRequest
{
    private String path;

    private String application = "application";
    private String environment = "default";
    private String branch = "1.4";

    private String key;
    private String value;

    public UpdateConfigValueRequest()
    {
    }

    public UpdateConfigValueRequest( String path, String key, String value )
    {
        this.path = path;
        this.key = key;
        this.value = value;
    }

    public UpdateConfigValueRequest( String application, String environment, String branch, String key, String value )
    {
        this.application = application;
        this.environment = environment;
        this.branch = branch;
        this.key = key;
        this.value = value;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath( String path )
    {
        this.path = path;
    }

    public String getApplication()
    {
        return application;
    }

    public void setApplication( String application )
    {
        this.application = application;
    }

    public String getEnvironment()
    {
        return environment;
    }

    public void setEnvironment( String environment )
    {
        this.environment = environment;
    }

    public String getBranch()
    {
        return branch;
    }

    public void setBranch( String branch )
    {
        this.branch = branch;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey( String key )
    {
        this.key = key;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue( String value )
    {
        this.value = value;
    }
}
