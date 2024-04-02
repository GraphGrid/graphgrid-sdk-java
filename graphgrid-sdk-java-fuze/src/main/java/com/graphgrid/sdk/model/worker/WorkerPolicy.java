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
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;
import java.util.Map;

import com.graphgrid.sdk.model.Metadata;
import com.graphgrid.sdk.model.Neo4jCredentials;
import com.graphgrid.sdk.model.Policy;
import com.graphgrid.sdk.model.broker.BrokerEndpoint;

@JsonAutoDetect
@JsonTypeName( "workerPolicy" )
@JsonIgnoreProperties( ignoreUnknown = true )
@JsonDeserialize( as = WorkerPolicy.class )
public class WorkerPolicy implements Policy
{
    private Metadata metadata;
    private  Status status;

    private Map<String,Worker> workers;
    private Neo4jCredentials defaultNeo4jCredentials;

    public WorkerPolicy()
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

    public Map<String,Worker> getWorkers()
    {
        return workers;
    }

    public void setWorkers( Map<String,Worker> workers )
    {
        this.workers = workers;
    }

    public Neo4jCredentials getDefaultNeo4jCredentials()
    {
        return defaultNeo4jCredentials;
    }

    public void setDefaultNeo4jCredentials( Neo4jCredentials defaultNeo4jCredentials )
    {
        this.defaultNeo4jCredentials = defaultNeo4jCredentials;
    }

    @JsonAutoDetect
    @JsonIgnoreProperties( ignoreUnknown = true )
    public static class Worker
    {
        private Metadata metadata;

        private String cypher;
        private List<BrokerEndpoint> listeningEndpoints;
        private List<BrokerEndpoint> route;
        private Neo4jCredentials neo4jCredentials;

        public Worker()
        {
        }

        public Metadata getMetadata()
        {
            return metadata;
        }

        public void setMetadata( Metadata metadata )
        {
            this.metadata = metadata;
        }

        public String getCypher()
        {
            return cypher;
        }

        public void setCypher( String cypher )
        {
            this.cypher = cypher;
        }

        public List<BrokerEndpoint> getListeningEndpoints()
        {
            return listeningEndpoints;
        }

        public void setListeningEndpoints( List<BrokerEndpoint> listeningEndpoints )
        {
            this.listeningEndpoints = listeningEndpoints;
        }

        public List<BrokerEndpoint> getRoute()
        {
            return route;
        }

        public void setRoute( List<BrokerEndpoint> route )
        {
            this.route = route;
        }

        public Neo4jCredentials getNeo4jCredentials()
        {
            return neo4jCredentials;
        }

        public void setNeo4jCredentials( Neo4jCredentials neo4jCredentials )
        {
            this.neo4jCredentials = neo4jCredentials;
        }
    }
}
