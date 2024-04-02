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
package com.graphgrid.sdk.model.neo4jWriter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

import com.graphgrid.sdk.model.Metadata;
import com.graphgrid.sdk.model.Neo4jCredentials;
import com.graphgrid.sdk.model.Policy;
import com.graphgrid.sdk.model.broker.BrokerEndpoint;

@JsonAutoDetect
@JsonTypeName( "neo4jWriterPolicy" )
@JsonIgnoreProperties( ignoreUnknown = true )
@JsonDeserialize( as = Neo4jWriterPolicy.class )
public class Neo4jWriterPolicy implements Policy
{
    private Metadata metadata;
    private Status status;

    private Neo4jCredentials defaultNeo4jCredentials;
    private List<BrokerEndpoint> txRequestBrokerEndpoints;
    private List<BrokerEndpoint> txDataBrokerEndpoints;
    private Integer maxWriteAttempts;
    private Integer maxReadAttempts;
    private Integer minBatchSize;
    private Integer maxBatchSize;

    public Neo4jWriterPolicy()
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

    public Neo4jCredentials getDefaultNeo4jCredentials()
    {
        return defaultNeo4jCredentials;
    }

    public void setDefaultNeo4jCredentials( Neo4jCredentials defaultNeo4jCredentials )
    {
        this.defaultNeo4jCredentials = defaultNeo4jCredentials;
    }

    public List<BrokerEndpoint> getTxRequestBrokerEndpoints()
    {
        return txRequestBrokerEndpoints;
    }

    public void setTxRequestBrokerEndpoints( List<BrokerEndpoint> txRequestBrokerEndpoints )
    {
        this.txRequestBrokerEndpoints = txRequestBrokerEndpoints;
    }

    public List<BrokerEndpoint> getTxDataBrokerEndpoints()
    {
        return txDataBrokerEndpoints;
    }

    public void setTxDataBrokerEndpoints( List<BrokerEndpoint> txDataBrokerEndpoints )
    {
        this.txDataBrokerEndpoints = txDataBrokerEndpoints;
    }

    public Integer getMaxWriteAttempts()
    {
        return maxWriteAttempts;
    }

    public void setMaxWriteAttempts( Integer maxWriteAttempts )
    {
        this.maxWriteAttempts = maxWriteAttempts;
    }

    public Integer getMaxReadAttempts()
    {
        return maxReadAttempts;
    }

    public void setMaxReadAttempts( Integer maxReadAttempts )
    {
        this.maxReadAttempts = maxReadAttempts;
    }

    public Integer getMinBatchSize()
    {
        return minBatchSize;
    }

    public void setMinBatchSize( Integer minBatchSize )
    {
        this.minBatchSize = minBatchSize;
    }

    public Integer getMaxBatchSize()
    {
        return maxBatchSize;
    }

    public void setMaxBatchSize( Integer maxBatchSize )
    {
        this.maxBatchSize = maxBatchSize;
    }
}
