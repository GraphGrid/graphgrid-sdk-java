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

import java.util.List;

@JsonAutoDetect
@JsonIgnoreProperties( ignoreUnknown = true )
public class DownloadFilesRequest extends GraphGridServiceRequest
{

    private List<String> grns;

    private Long duration;

    public List<String> getGrns()
    {
        return grns;
    }

    public void setGrns( List<String> grns )
    {
        this.grns = grns;
    }

    public Long getDuration()
    {
        return duration;
    }

    public void setDuration( Long duration )
    {
        this.duration = duration;
    }
}
