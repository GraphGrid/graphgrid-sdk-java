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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

import com.graphgrid.sdk.model.distributor.DistributionPolicy;
import com.graphgrid.sdk.model.neo4jWriter.Neo4jWriterPolicy;
import com.graphgrid.sdk.model.worker.WorkerPolicy;

public class PolicyDeserializer extends JsonDeserializer<Policy>
{
    @Override
    public Policy deserialize( JsonParser jsonParser, DeserializationContext context ) throws IOException
    {
        ObjectMapper objectMapper = (ObjectMapper) jsonParser.getCodec();
        ObjectNode root = objectMapper.readTree( jsonParser );

        // Each Policy implementation must include at least one unique field. This is used to determine which type of Policy to deserialize as.
        if ( root.has( "listeningBrokerEndpoint" ) )
        {
            return objectMapper.readValue( root.toString(), DistributionPolicy.class );
        }
        else if ( root.has( "defaultNeo4jCredentials" ) || root.has( "minBatchSize" ) || root.has( "maxBatchSize" ) || root.has( "listeningBrokerEndpoints" ) )
        {
            // Neo4jWriter policies may not necessarily have all these fields
            return objectMapper.readValue( root.toString(), Neo4jWriterPolicy.class );
        }
        else if ( root.has( "workers" ) )
        {
            return objectMapper.readValue( root.toString(), WorkerPolicy.class );
        }
        else
        {
            return objectMapper.readValue( root.toString(), Policy.class );
        }
    }
}
