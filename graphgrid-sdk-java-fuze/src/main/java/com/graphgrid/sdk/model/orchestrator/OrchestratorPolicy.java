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
package com.graphgrid.sdk.model.orchestrator;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;
import java.util.Map;

import com.graphgrid.sdk.model.Metadata;
import com.graphgrid.sdk.model.Policy;

@JsonAutoDetect
@JsonTypeName( "orchestratorPolicy" )
@JsonIgnoreProperties( ignoreUnknown = true )
@JsonDeserialize( as = OrchestratorPolicy.class )
public class OrchestratorPolicy implements Policy
{
    private Metadata metadata;
    private Status status;

    private List<Task> tasks;

    public OrchestratorPolicy()
    {
    }

    @Override
    public Metadata getMetadata()
    {
        return metadata;
    }

    @Override
    public void setMetadata( Metadata metadata )
    {
        this.metadata = metadata;
    }

    @Override
    public Status getStatus()
    {
        return status;
    }

    @Override
    public void setStatus( Status status )
    {
        this.status = status;
    }

    public List<Task> getTasks()
    {
        return tasks;
    }

    public void setTasks( List<Task> tasks )
    {
        this.tasks = tasks;
    }

    @JsonAutoDetect
    @JsonIgnoreProperties( ignoreUnknown = true )
    public class Task
    {
        private Map<String,Object> metadata;
        private FuzeComponent fuzeComponent;
        private Policy policy;
        private List<String> receiveFrom;
        private List<String> sendTo;

        public Task()
        {
        }

        public Map<String,Object> getMetadata()
        {
            return metadata;
        }

        public void setMetadata( Map<String,Object> metadata )
        {
            this.metadata = metadata;
        }

        public FuzeComponent getFuzeComponent()
        {
            return fuzeComponent;
        }

        public void setFuzeComponent( FuzeComponent fuzeComponent )
        {
            this.fuzeComponent = fuzeComponent;
        }

        public Policy getPolicy()
        {
            return policy;
        }

        public void setPolicy( Policy policy )
        {
            this.policy = policy;
        }

        public List<String> getReceiveFrom()
        {
            return receiveFrom;
        }

        public void setReceiveFrom( List<String> receiveFrom )
        {
            this.receiveFrom = receiveFrom;
        }

        public List<String> getSendTo()
        {
            return sendTo;
        }

        public void setSendTo( List<String> sendTo )
        {
            this.sendTo = sendTo;
        }
    }

    public enum FuzeComponent
    {
        @JsonProperty( "ApocTrigger" )
        APOC_TRIGGER,
        @JsonProperty( "Distributor" )
        DISTRIBUTOR,
        @JsonProperty( "Worker" )
        WORKER,
        @JsonProperty( "External" )
        EXTERNAL,
        @JsonProperty( "Neo4jWriter" )
        NEO4JWRITER
    }
}
