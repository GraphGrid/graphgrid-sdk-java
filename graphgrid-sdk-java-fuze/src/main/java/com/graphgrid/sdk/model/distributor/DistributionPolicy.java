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
package com.graphgrid.sdk.model.distributor;

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
@JsonTypeName( "distributionPolicy" )
@JsonIgnoreProperties( ignoreUnknown = true )
@JsonDeserialize( as = DistributionPolicy.class )
public class DistributionPolicy implements Policy
{
    private Metadata metadata;
    private Status status;

    private BrokerEndpoint listeningBrokerEndpoint;
    private List<ForwardingRule> forwardingRules;

    private Neo4jCredentials neo4jCredentials;

    public DistributionPolicy()
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

    public BrokerEndpoint getListeningBrokerEndpoint()
    {
        return listeningBrokerEndpoint;
    }

    public void setListeningBrokerEndpoint( BrokerEndpoint listeningBrokerEndpoint )
    {
        this.listeningBrokerEndpoint = listeningBrokerEndpoint;
    }

    public List<ForwardingRule> getForwardingRules()
    {
        return forwardingRules;
    }

    public void setForwardingRules( List<ForwardingRule> forwardingRules )
    {
        this.forwardingRules = forwardingRules;
    }

    public Neo4jCredentials getNeo4jCredentials()
    {
        return neo4jCredentials;
    }

    public void setNeo4jCredentials( Neo4jCredentials neo4jCredentials )
    {
        this.neo4jCredentials = neo4jCredentials;
    }

    @JsonAutoDetect
    @JsonIgnoreProperties( ignoreUnknown = true )
    public static class ForwardingRule
    {
        private String cypher;
        private Multicast multicast;
        private String resultKey;

        public ForwardingRule()
        {
        }

        public ForwardingRule( String cypher, Multicast multicast, String resultKey )
        {
            this.cypher = cypher;
            this.multicast = multicast;
            this.resultKey = resultKey;
        }

        public String getCypher()
        {
            return cypher;
        }

        public void setCypher( String cypher )
        {
            this.cypher = cypher;
        }

        public Multicast getMulticast()
        {
            return multicast;
        }

        public void setMulticast( Multicast multicast )
        {
            this.multicast = multicast;
        }

        public String getResultKey()
        {
            return resultKey;
        }

        public void setResultKey( String resultKey )
        {
            this.resultKey = resultKey;
        }

        @JsonAutoDetect
        @JsonIgnoreProperties( ignoreUnknown = true )
        public static class Multicast
        {
            private int retry;
            private boolean stopOnFailure;
            private List<BrokerEndpoint> brokers;

            public Multicast()
            {
            }

            public Multicast( List<BrokerEndpoint> brokers )
            {
                this.brokers = brokers;
            }

            public int getRetry()
            {
                return retry;
            }

            public void setRetry( int retry )
            {
                this.retry = retry;
            }

            public boolean isStopOnFailure()
            {
                return stopOnFailure;
            }

            public void setStopOnFailure( boolean stopOnFailure )
            {
                this.stopOnFailure = stopOnFailure;
            }

            public List<BrokerEndpoint> getBrokers()
            {
                return brokers;
            }

            public void setBrokers( List<BrokerEndpoint> brokers )
            {
                this.brokers = brokers;
            }
        }
    }
}
