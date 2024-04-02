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
package com.graphgrid.sdk;

import org.apache.commons.lang3.StringUtils;

import com.graphgrid.sdk.core.GraphGridSecurityClientBase;
import com.graphgrid.sdk.core.SessionFactory;
import com.graphgrid.sdk.core.model.GraphGridServiceRequest;
import com.graphgrid.sdk.core.model.GraphGridServiceResponse;
import com.graphgrid.sdk.core.security.SecurityConfig;
import com.graphgrid.sdk.core.utils.HttpMethod;
import com.graphgrid.sdk.core.utils.RequestUrlBuilder;
import com.graphgrid.sdk.model.FuzeServiceStatusRequest;
import com.graphgrid.sdk.model.FuzeServiceStatusResponse;
import com.graphgrid.sdk.model.distributor.DistributorActivePoliciesRequest;
import com.graphgrid.sdk.model.distributor.DistributorActivePoliciesResponse;
import com.graphgrid.sdk.model.distributor.DistributorPolicyStatusRequest;
import com.graphgrid.sdk.model.distributor.DistributorPolicyStatusResponse;
import com.graphgrid.sdk.model.neo4jWriter.Neo4jWriterActivePoliciesRequest;
import com.graphgrid.sdk.model.neo4jWriter.Neo4jWriterActivePoliciesResponse;
import com.graphgrid.sdk.model.neo4jWriter.Neo4jWriterAsyncResponse;
import com.graphgrid.sdk.model.neo4jWriter.Neo4jWriterBatchExecutionRequest;
import com.graphgrid.sdk.model.neo4jWriter.Neo4jWriterBatchExecutionResponse;
import com.graphgrid.sdk.model.neo4jWriter.Neo4jWriterClearQuarantineRequest;
import com.graphgrid.sdk.model.neo4jWriter.Neo4jWriterClearQuarantineResponse;
import com.graphgrid.sdk.model.neo4jWriter.Neo4jWriterRequest;
import com.graphgrid.sdk.model.neo4jWriter.Neo4jWriterResponse;
import com.graphgrid.sdk.model.neo4jWriter.Neo4jWriterResultRequest;
import com.graphgrid.sdk.model.neo4jWriter.Neo4jWriterTxRequestResponse;
import com.graphgrid.sdk.model.neo4jWriter.Neo4jWriterTxRequestStatusResponse;
import com.graphgrid.sdk.model.neo4jWriter.Neo4jWriterViewQuarantineRequest;
import com.graphgrid.sdk.model.neo4jWriter.Neo4jWriterViewQuarantineResponse;
import com.graphgrid.sdk.model.orchestrator.OrchestratorActivePoliciesRequest;
import com.graphgrid.sdk.model.orchestrator.OrchestratorActivePoliciesResponse;
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
import com.graphgrid.sdk.model.worker.WorkerActivePoliciesRequest;
import com.graphgrid.sdk.model.worker.WorkerActivePoliciesResponse;
import com.graphgrid.sdk.support.Endpoints;

public class GraphGridFuzeClient extends GraphGridSecurityClientBase implements GraphGridFuze
{

    public GraphGridFuzeClient( String serviceBaseUrl )
    {
        super( serviceBaseUrl );
    }

    public GraphGridFuzeClient( String serviceBaseUrl, SecurityConfig securityConfig )
    {
        super( serviceBaseUrl, securityConfig );
    }

    public GraphGridFuzeClient( String serviceBaseUrl, SecurityConfig securityConfig, SessionFactory sessionFactory )
    {
        super( serviceBaseUrl, securityConfig, sessionFactory );
    }

    @Override
    public FuzeServiceStatusResponse status( final FuzeServiceStatusRequest request )
    {
        request.setEndpoint( getEndpointBuilder().create( request ).withServiceEndpoint( Endpoints.STATUS ).buildUrl() );
        return makeRequest( request, FuzeServiceStatusResponse.class, HttpMethod.GET );
    }

    ///////////////////////
    // Policy Management //
    ///////////////////////

    @Override
    public PolicySaveResponse savePolicy( PolicySaveRequest request )
    {
        request.setEndpoint(
                getEndpointBuilder().create( request ).addPathVariable( request.getClusterName() ).addPathVariable( Endpoints.SAVE_POLICY ).addPathVariable(
                        request.getPolicyName() ).buildUrl() );
        request.setBody( request.getPolicy() );
        return makeRequest( request, PolicySaveResponse.class, HttpMethod.POST );
    }

    @Override
    public PolicyRetrieveResponse retrievePolicy( PolicyRetrieveRequest request )
    {
        request.setEndpoint(
                getEndpointBuilder().create( request ).addPathVariable( request.getClusterName() ).addPathVariable( Endpoints.RETRIEVE_POLICY ).addPathVariable(
                        request.getPolicyName() ).buildUrl() );
        return makeRequest( request, PolicyRetrieveResponse.class, HttpMethod.GET );
    }

    @Override
    public PolicyDeleteResponse deletePolicy( PolicyDeleteRequest request )
    {
        request.setEndpoint(
                getEndpointBuilder().create( request ).addPathVariable( request.getClusterName() ).addPathVariable( Endpoints.DELETE_POLICY ).addPathVariable(
                        request.getPolicyName() ).buildUrl() );
        return makeRequest( request, PolicyDeleteResponse.class, HttpMethod.DELETE );
    }

    @Override
    public PolicyActivateResponse activatePolicy( PolicyActivateRequest request )
    {
        request.setEndpoint(
                getEndpointBuilder().create( request ).addPathVariable( request.getClusterName() ).addPathVariable( Endpoints.ACTIVATE_POLICY ).addPathVariable(
                        request.getPolicyName() ).buildUrl() );
        return makeRequest( request, PolicyActivateResponse.class, HttpMethod.POST );
    }

    @Override
    public PolicyDeactivateResponse deactivatePolicy( PolicyDeactivateRequest request )
    {
        request.setEndpoint( getEndpointBuilder().create( request ).addPathVariable( request.getClusterName() ).addPathVariable(
                Endpoints.DEACTIVATE_POLICY ).addPathVariable( request.getPolicyName() ).buildUrl() );
        return makeRequest( request, PolicyDeactivateResponse.class, HttpMethod.POST );
    }

    /////////////////
    // Neo4jWriter //
    /////////////////

    @Override
    public Neo4jWriterActivePoliciesResponse activeNeo4jWriterPolicies( final Neo4jWriterActivePoliciesRequest request )
    {
        RequestUrlBuilder endpoint = getEndpointBuilder().create( request );

        if ( request.getClusterName() != null && !request.getClusterName().isEmpty() )
        {
            endpoint.addPathVariable( request.getClusterName() );
        }

        endpoint.addPathVariable( Endpoints.ACTIVE_NEO4J_WRITER_POLICIES );
        request.setEndpoint( endpoint.buildUrl() );

        return makeRequest( request, Neo4jWriterActivePoliciesResponse.class, HttpMethod.GET );
    }

    @Override
    public Neo4jWriterResponse write( final Neo4jWriterRequest request )
    {
        if ( StringUtils.isNotEmpty( request.getClusterName() ) && StringUtils.isNotEmpty( request.getPolicyName() ) )
        {
            request.setEndpoint(
                    getEndpointBuilder().create( request ).addPathVariable( request.getClusterName() ).addPathVariable( Endpoints.WRITE ).addPathVariable(
                            request.getPolicyName() ).buildUrl() );
        }
        else
        {
            request.setEndpoint( getEndpointBuilder().create( request ).withServiceEndpoint( Endpoints.WRITE ).buildUrl() );
        }
        request.setBody( request.getTransactionRequest() );
        return makeRequest( request, Neo4jWriterResponse.class, HttpMethod.POST );
    }

    @Override
    public Neo4jWriterAsyncResponse writeAsync( final Neo4jWriterRequest request )
    {
        if ( StringUtils.isNotEmpty( request.getClusterName() ) && StringUtils.isNotEmpty( request.getPolicyName() ) )
        {
            request.setEndpoint(
                    getEndpointBuilder().create( request ).addPathVariable( request.getClusterName() ).addPathVariable( Endpoints.WRITE_ASYNC ).addPathVariable(
                            request.getPolicyName() ).buildUrl() );
        }
        else
        {
            request.setEndpoint( getEndpointBuilder().create( request ).withServiceEndpoint( Endpoints.WRITE_ASYNC ).buildUrl() );
        }
        request.setBody( request.getTransactionRequest() );
        return makeRequest( request, Neo4jWriterAsyncResponse.class, HttpMethod.POST );
    }

    @Override
    public Neo4jWriterResponse read( final Neo4jWriterRequest request )
    {
        if ( StringUtils.isNotEmpty( request.getClusterName() ) && StringUtils.isNotEmpty( request.getPolicyName() ) )
        {
            request.setEndpoint(
                    getEndpointBuilder().create( request ).addPathVariable( request.getClusterName() ).addPathVariable( Endpoints.READ ).addPathVariable(
                            request.getPolicyName() ).buildUrl() );
        }
        else
        {
            request.setEndpoint( getEndpointBuilder().create( request ).withServiceEndpoint( Endpoints.READ ).buildUrl() );
        }
        request.setBody( request.getTransactionRequest() );
        return makeRequest( request, Neo4jWriterResponse.class, HttpMethod.POST );
    }

    @Override
    public Neo4jWriterAsyncResponse readAsync( final Neo4jWriterRequest request )
    {
        if ( StringUtils.isNotEmpty( request.getClusterName() ) && StringUtils.isNotEmpty( request.getPolicyName() ) )
        {
            request.setEndpoint(
                    getEndpointBuilder().create( request ).addPathVariable( request.getClusterName() ).addPathVariable( Endpoints.READ_ASYNC ).addPathVariable(
                            request.getPolicyName() ).buildUrl() );
        }
        else
        {
            request.setEndpoint( getEndpointBuilder().create( request ).withServiceEndpoint( Endpoints.READ_ASYNC ).buildUrl() );
        }
        request.setBody( request.getTransactionRequest() );
        return makeRequest( request, Neo4jWriterAsyncResponse.class, HttpMethod.POST );
    }

    @Override
    public Neo4jWriterAsyncResponse queueRequestForBatching( final Neo4jWriterRequest request )
    {
        if ( StringUtils.isNotEmpty( request.getClusterName() ) && StringUtils.isNotEmpty( request.getPolicyName() ) )
        {
            request.setEndpoint( getEndpointBuilder().create( request ).addPathVariable( request.getClusterName() ).addPathVariable(
                    Endpoints.QUEUE_REQUEST_FOR_BATCHING ).addPathVariable( request.getPolicyName() ).buildUrl() );
        }
        else
        {
            request.setEndpoint( getEndpointBuilder().create( request ).withServiceEndpoint( Endpoints.QUEUE_REQUEST_FOR_BATCHING ).buildUrl() );
        }
        request.setBody( request.getTransactionRequest() );
        return makeRequest( request, Neo4jWriterAsyncResponse.class, HttpMethod.POST );
    }

    @Override
    public Neo4jWriterBatchExecutionResponse batchAndExecuteQueuedRequests( final Neo4jWriterBatchExecutionRequest request )
    {
        if ( StringUtils.isNotEmpty( request.getClusterName() ) && StringUtils.isNotEmpty( request.getPolicyName() ) )
        {
            request.setEndpoint( getEndpointBuilder().create( request ).addPathVariable( request.getClusterName() ).addPathVariable(
                    Endpoints.BATCH_AND_EXECUTE_QUEUED_REQUESTS ).addPathVariable( request.getPolicyName() ).buildUrl() );
        }
        else
        {
            request.setEndpoint( getEndpointBuilder().create( request ).withServiceEndpoint( Endpoints.BATCH_AND_EXECUTE_QUEUED_REQUESTS ).buildUrl() );
        }
        return makeRequest( request, Neo4jWriterBatchExecutionResponse.class, HttpMethod.POST );
    }

    @Override
    public Neo4jWriterResponse getTransactionResult( final Neo4jWriterResultRequest request )
    {
        request.setEndpoint( getEndpointBuilder().create( request ).withServiceEndpoint( Endpoints.GET_TRANSACTION_RESULT ).addQueryParam( "fuzeId",
                request.getFuzeId() ).buildUrl() );
        return makeRequest( request, Neo4jWriterResponse.class, HttpMethod.GET );
    }

    @Override
    public Neo4jWriterTxRequestResponse getTransactionRequest( final Neo4jWriterResultRequest request )
    {
        request.setEndpoint( getEndpointBuilder().create( request ).withServiceEndpoint( Endpoints.GET_TRANSACTION_REQUEST ).addQueryParam( "fuzeId",
                request.getFuzeId() ).buildUrl() );
        return makeRequest( request, Neo4jWriterTxRequestResponse.class, HttpMethod.GET );
    }

    @Override
    public Neo4jWriterTxRequestStatusResponse getTransactionRequestStatus( final Neo4jWriterResultRequest request )
    {
        request.setEndpoint( getEndpointBuilder().create( request ).withServiceEndpoint( Endpoints.CHECK_TRANSACTION_REQUEST_STATUS ).addQueryParam( "fuzeId",
                request.getFuzeId() ).buildUrl() );
        return makeRequest( request, Neo4jWriterTxRequestStatusResponse.class, HttpMethod.GET );
    }

    @Override
    public Neo4jWriterViewQuarantineResponse viewQuarantine( final Neo4jWriterViewQuarantineRequest request )
    {
        request.setEndpoint( getEndpointBuilder().create( request ).withServiceEndpoint( Endpoints.VIEW_QUARANTINE ).buildUrl() );
        return makeRequest( request, Neo4jWriterViewQuarantineResponse.class, HttpMethod.GET );
    }

    @Override
    public Neo4jWriterClearQuarantineResponse clearQuarantine( final Neo4jWriterClearQuarantineRequest request )
    {
        request.setEndpoint( getEndpointBuilder().create( request ).withServiceEndpoint( Endpoints.CLEAR_QUARANTINE ).buildUrl() );
        return makeRequest( request, Neo4jWriterClearQuarantineResponse.class, HttpMethod.DELETE );
    }

    /////////////////
    // Distributor //
    /////////////////

    @Override
    public DistributorPolicyStatusResponse policyStatus( final DistributorPolicyStatusRequest request )
    {
        request.setEndpoint(
                getEndpointBuilder().create( request ).addPathVariable( request.getClusterName() ).addPathVariable( Endpoints.POLICY_STATUS ).addPathVariable(
                        request.getPolicyName() ).buildUrl() );
        return makeRequest( request, DistributorPolicyStatusResponse.class, HttpMethod.GET );
    }

    @Override
    public DistributorActivePoliciesResponse activeDistributionPolicies( final DistributorActivePoliciesRequest request )
    {
        RequestUrlBuilder endpoint = getEndpointBuilder().create( request );

        if ( request.getClusterName() != null && !request.getClusterName().isEmpty() )
        {
            endpoint.addPathVariable( request.getClusterName() );
        }

        endpoint.addPathVariable( Endpoints.ACTIVE_DISTRIBUTION_POLICIES );
        request.setEndpoint( endpoint.buildUrl() );

        return makeRequest( request, DistributorActivePoliciesResponse.class, HttpMethod.GET );
    }

    /////////////////
    // Fuze Worker //
    /////////////////

    @Override
    public WorkerActivePoliciesResponse activeWorkerPolicies( final WorkerActivePoliciesRequest request )
    {
        RequestUrlBuilder endpoint = getEndpointBuilder().create( request );

        if ( request.getClusterName() != null && !request.getClusterName().isEmpty() )
        {
            endpoint.addPathVariable( request.getClusterName() );
        }

        endpoint.addPathVariable( Endpoints.ACTIVE_WORKER_POLICIES );
        request.setEndpoint( endpoint.buildUrl() );

        return makeRequest( request, WorkerActivePoliciesResponse.class, HttpMethod.GET );
    }

    //////////////////
    // Orchestrator //
    //////////////////

    @Override
    public OrchestratorActivePoliciesResponse activeOrchestratorPolicies( final OrchestratorActivePoliciesRequest request )
    {
        RequestUrlBuilder endpoint = getEndpointBuilder().create( request );

        if ( request.getClusterName() != null && !request.getClusterName().isEmpty() )
        {
            endpoint.addPathVariable( request.getClusterName() );
        }

        endpoint.addPathVariable( Endpoints.ACTIVE_ORCHESTRATOR_POLICIES );
        request.setEndpoint( endpoint.buildUrl() );

        return makeRequest( request, OrchestratorActivePoliciesResponse.class, HttpMethod.GET );
    }

    @Override
    protected <T extends GraphGridServiceResponse> T makeRequest( GraphGridServiceRequest request, Class responseType, HttpMethod httpMethod )
    {
        return super.makeRequest( request, responseType, httpMethod );
    }
}
