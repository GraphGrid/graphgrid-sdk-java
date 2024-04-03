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
package com.graphgrid.sdk.module;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collections;

import com.graphgrid.sdk.GraphGridFuze;
import com.graphgrid.sdk.GraphGridFuzeClient;
import com.graphgrid.sdk.SdkTestBase;
import com.graphgrid.sdk.core.security.ClientCredentialsTokenRequest;
import com.graphgrid.sdk.model.FuzeServiceStatusRequest;
import com.graphgrid.sdk.model.FuzeServiceStatusResponse;
import com.graphgrid.sdk.model.Neo4jCredentials;
import com.graphgrid.sdk.model.broker.BrokerEndpoint;
import com.graphgrid.sdk.model.distributor.DistributionPolicy;
import com.graphgrid.sdk.model.policy.PolicyActivateRequest;
import com.graphgrid.sdk.model.policy.PolicyActivateResponse;
import com.graphgrid.sdk.model.policy.PolicyDeactivateRequest;
import com.graphgrid.sdk.model.policy.PolicyDeactivateResponse;
import com.graphgrid.sdk.model.policy.PolicyDeleteRequest;
import com.graphgrid.sdk.model.policy.PolicyDeleteResponse;
import com.graphgrid.sdk.model.policy.PolicyRetrieveRequest;
import com.graphgrid.sdk.model.policy.PolicyRetrieveResponse;
import com.graphgrid.sdk.model.policy.PolicySaveRequest;
import com.graphgrid.sdk.model.policy.PolicySaveResponse;

public class FuzeModuleTest extends SdkTestBase
{

    @Test
    public void testStatus()
    {
        final GraphGridFuzeClient client = new GraphGridFuzeClient( "https://dev-api.graphgrid.com/1.0/fuze", securityConfig );
        final FuzeServiceStatusResponse status = client.status( new FuzeServiceStatusRequest().withAuthMethod( new ClientCredentialsTokenRequest() ) );

        Assert.assertNotNull( status );
    }

    @Ignore( "calls service" )
    @Test
    public void policyManagementEndpoints()
    {
        final GraphGridFuze graphGridFuze = new GraphGridFuzeClient( "https://dev-api.graphgrid.com/1.0/fuze", securityConfig );

        DistributionPolicy distributionPolicy = new DistributionPolicy();
        distributionPolicy.setListeningBrokerEndpoint( BrokerEndpoint.kafkaEndpoint( "com.graphgrid.topic.sdk-test" ) );
        distributionPolicy.setNeo4jCredentials( new Neo4jCredentials( "bolt://ongdb:7687", "ongdb", "admin" ) );
        distributionPolicy.setForwardingRules( Collections.singletonList(
                new DistributionPolicy.ForwardingRule( "WITH {txData} as txData RETURN txData AS result", new DistributionPolicy.ForwardingRule.Multicast(
                        Collections.singletonList( BrokerEndpoint.kafkaEndpoint( "com.graphgrid.topic.skd-next" ) ) ), "result" ) ) );

        final String clusterName = "gg-test-ongdb";
        final String policyName = "gg-test-policy-distributor" + RandomStringUtils.randomAlphabetic( 5 );

        PolicySaveResponse policySaveResponse = graphGridFuze.savePolicy( new PolicySaveRequest( clusterName, policyName, distributionPolicy ) );
        PolicyRetrieveResponse policyRetrieveResponse = graphGridFuze.retrievePolicy( new PolicyRetrieveRequest( clusterName, policyName ) );
        PolicyActivateResponse policyActivateResponse = graphGridFuze.activatePolicy( new PolicyActivateRequest( clusterName, policyName ) );
        PolicyDeactivateResponse policyDeactivateResponse = graphGridFuze.deactivatePolicy( new PolicyDeactivateRequest( clusterName, policyName ) );
        PolicyDeleteResponse policyDeleteResponse = graphGridFuze.deletePolicy( new PolicyDeleteRequest( clusterName, policyName ) );
    }
}
