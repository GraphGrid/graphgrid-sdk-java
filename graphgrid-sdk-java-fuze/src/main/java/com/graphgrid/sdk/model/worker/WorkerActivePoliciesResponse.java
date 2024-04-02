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
package com.graphgrid.sdk.model.worker;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import com.graphgrid.sdk.core.model.GraphGridServiceResponse;

@JsonAutoDetect
@JsonIgnoreProperties( ignoreUnknown = true )
public class WorkerActivePoliciesResponse extends GraphGridServiceResponse
{
    private List<WorkerPolicy> activePolicies;

    public WorkerActivePoliciesResponse()
    {
    }

    public List<WorkerPolicy> getActivePolicies()
    {
        return activePolicies;
    }

    public void setActivePolicies( List<WorkerPolicy> activePolicies )
    {
        this.activePolicies = activePolicies;
    }
}
