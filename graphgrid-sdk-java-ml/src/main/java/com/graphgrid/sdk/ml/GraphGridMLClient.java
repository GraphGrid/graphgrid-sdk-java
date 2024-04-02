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
package com.graphgrid.sdk.ml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.graphgrid.sdk.ml.constant.EndpointConstant;
import com.graphgrid.sdk.core.GraphGridSecurityClientBase;
import com.graphgrid.sdk.core.SessionFactory;
import com.graphgrid.sdk.core.model.GraphGridServiceRequest;
import com.graphgrid.sdk.core.model.GraphGridServiceResponse;
import com.graphgrid.sdk.core.security.SecurityConfig;
import com.graphgrid.sdk.core.utils.HttpMethod;
import com.graphgrid.sdk.ml.model.BatchInferenceResponse;
import com.graphgrid.sdk.ml.model.CreateBatchInferenceRequest;
import com.graphgrid.sdk.ml.model.CreateEventActionRequest;
import com.graphgrid.sdk.ml.model.CreateEventActionResponse;
import com.graphgrid.sdk.ml.model.CreateTransformationRequest;
import com.graphgrid.sdk.ml.model.CreateTransformationResponse;
import com.graphgrid.sdk.ml.model.CypherDataInferenceRequest;
import com.graphgrid.sdk.ml.model.CypherDataInferenceResponse;
import com.graphgrid.sdk.ml.model.DeleteBatchInferenceRequest;
import com.graphgrid.sdk.ml.model.DeleteBatchInferenceResponse;
import com.graphgrid.sdk.ml.model.DeleteModelRequest;
import com.graphgrid.sdk.ml.model.DeleteModelResponse;
import com.graphgrid.sdk.ml.model.DeleteTransformationRequest;
import com.graphgrid.sdk.ml.model.DeleteTransformationResponse;
import com.graphgrid.sdk.ml.model.GetBatchInferencePolicyRequest;
import com.graphgrid.sdk.ml.model.GetBatchInferencePolicyResponse;
import com.graphgrid.sdk.ml.model.GetInnerTransformationStatusRequest;
import com.graphgrid.sdk.ml.model.GetTrainingPolicyRequest;
import com.graphgrid.sdk.ml.model.GetTrainingPolicyResponse;
import com.graphgrid.sdk.ml.model.GetTransformationPolicyRequest;
import com.graphgrid.sdk.ml.model.GetTransformationPolicyResponse;
import com.graphgrid.sdk.ml.model.GetTransformationStatusRequest;
import com.graphgrid.sdk.ml.model.GetTransformationStatusResponse;
import com.graphgrid.sdk.ml.model.JsonDataInferenceRequest;
import com.graphgrid.sdk.ml.model.JsonDataInferenceResponse;
import com.graphgrid.sdk.ml.model.LoadModelRequest;
import com.graphgrid.sdk.ml.model.LoadModelResponse;
import com.graphgrid.sdk.ml.model.MLServiceStatusRequest;
import com.graphgrid.sdk.ml.model.MLServiceStatusResponse;
import com.graphgrid.sdk.ml.model.RetryBatchJobRequest;
import com.graphgrid.sdk.ml.model.ShowFeatureRequest;
import com.graphgrid.sdk.ml.model.ShowFeatureResponse;
import com.graphgrid.sdk.ml.model.ShowModelInfoRequest;
import com.graphgrid.sdk.ml.model.TrainModelRequest;
import com.graphgrid.sdk.ml.model.TrainedModelReport;
import com.graphgrid.sdk.ml.model.UnloadModelRequest;
import com.graphgrid.sdk.ml.model.UnloadModelResponse;

public class GraphGridMLClient extends GraphGridSecurityClientBase implements GraphGridML
{

    public static final String NULL_REQUEST_ERROR = "request";
    private static final Logger LOGGER = LoggerFactory.getLogger( GraphGridMLClient.class );

    public GraphGridMLClient( String serviceBaseUrl )
    {
        super( serviceBaseUrl );
    }

    public GraphGridMLClient( String serviceBaseUrl, SecurityConfig securityConfig )
    {
        super( serviceBaseUrl, securityConfig );
    }

    public GraphGridMLClient( String serviceBaseUrl, SecurityConfig securityConfig, SessionFactory sessionFactory )
    {
        super( serviceBaseUrl, securityConfig, sessionFactory );
    }

    @Override
    public MLServiceStatusResponse status( MLServiceStatusRequest request )
    {
        request.setEndpoint( getEndpointBuilder().create( request ).withServiceEndpoint( EndpointConstant.STATUS_PATH ).buildUrl() );
        return makeRequest( request, MLServiceStatusResponse.class, HttpMethod.GET );
    }

    @Override
    public CreateTransformationResponse createTransformation( CreateTransformationRequest request )
    {
        request.setEndpoint( getEndpointBuilder().create( request ).withServiceEndpoint(
                request.getClusterName() + "/" + EndpointConstant.TRANSFORMATIONS_PATH ).addPathVariable( request.getPolicy().getPolicyName() ).buildUrl() );
        request.setBody( request.getPolicy() );
        return makeRequest( request, CreateTransformationResponse.class, HttpMethod.POST );
    }

    @Override
    public GetTransformationPolicyResponse getTransformationPolicy( GetTransformationPolicyRequest request )
    {
        request.setEndpoint( getEndpointBuilder().create( request ).withServiceEndpoint(
                request.getClusterName() + "/" + EndpointConstant.TRANSFORMATIONS_PATH ).addQueryParam( "policyName", request.getPolicyName() ).buildUrl() );
        return makeRequest( request, GetTransformationPolicyResponse.class, HttpMethod.GET );
    }

    @Override
    public GetTransformationStatusResponse getTransformationStatus( GetTransformationStatusRequest request )
    {
        request.setEndpoint( getEndpointBuilder().create( request ).withServiceEndpoint(
                request.getClusterName() + "/" + EndpointConstant.TRANSFORMATIONS_PATH ).addPathVariable( EndpointConstant.STATUS_PATH ).addQueryParam(
                "policyName", request.getPolicyName() ).buildUrl() );
        return makeRequest( request, GetTransformationStatusResponse.class, HttpMethod.GET );
    }

    @Override
    public GetTransformationStatusResponse getInnerTransformationStatus( GetInnerTransformationStatusRequest request )
    {
        request.setEndpoint( getEndpointBuilder().create( request ).withServiceEndpoint(
                request.getClusterName() + "/" + EndpointConstant.TRANSFORMATIONS_PATH ).addPathVariable( EndpointConstant.STATUS_PATH ).addPathVariable(
                "inner" ).addQueryParam( "task", request.getTask() ).addQueryParam( "policyName", request.getPolicyName() ).buildUrl() );
        return makeRequest( request, GetTransformationStatusResponse.class, HttpMethod.GET );
    }

    @Override
    public DeleteTransformationResponse deleteTransformation( DeleteTransformationRequest request )
    {
        request.setEndpoint( getEndpointBuilder().create( request ).withServiceEndpoint(
                request.getClusterName() + "/" + EndpointConstant.TRANSFORMATIONS_PATH ).addQueryParam( "policyName", request.getPolicyName() ).buildUrl() );
        return makeRequest( request, DeleteTransformationResponse.class, HttpMethod.DELETE );
    }

    @Override
    public ShowFeatureResponse showFeature( ShowFeatureRequest request )
    {
        request.setEndpoint(
                getEndpointBuilder().create( request ).withServiceEndpoint( request.getClusterName() + "/" + EndpointConstant.FEATURES_PATH ).addQueryParam(
                        "nodeLabels", Arrays.asList( request.getNodeLabels() ) ).addQueryParam( "limit", Integer.toString( request.getLimit() ) ).buildUrl() );
        return makeRequest( request, ShowFeatureResponse.class, HttpMethod.GET );
    }

    @Override
    public TrainedModelReport trainModel( TrainModelRequest request )
    {
        request.setEndpoint(
                getEndpointBuilder().create( request ).withServiceEndpoint( request.getClusterName() + "/" + EndpointConstant.TRAININGS_PATH ).addPathVariable(
                        request.getPolicy().getTask() ).addPathVariable( request.getPolicy().getPolicyName() ).buildUrl() );
        request.setBody( request.getPolicy() );
        return makeRequest( request, TrainedModelReport.class, HttpMethod.POST );
    }

    @Override
    public GetTrainingPolicyResponse getTrainingPolicy( GetTrainingPolicyRequest request )
    {
        request.setEndpoint(
                getEndpointBuilder().create( request ).withServiceEndpoint( request.getClusterName() + "/" + EndpointConstant.TRAININGS_PATH ).addQueryParam(
                        "task", request.getTask() ).addQueryParam( "policyName", request.getPolicyName() ).buildUrl() );
        return makeRequest( request, GetTrainingPolicyResponse.class, HttpMethod.GET );
    }

    @Override
    public TrainedModelReport showModelInfo( ShowModelInfoRequest request )
    {
        request.setEndpoint(
                getEndpointBuilder().create( request ).withServiceEndpoint( request.getClusterName() + "/" + EndpointConstant.MODELS_PATH ).addQueryParam(
                        "task", request.getTask() ).addQueryParam( "policyName", request.getPolicyName() ).buildUrl() );
        return makeRequest( request, TrainedModelReport.class, HttpMethod.GET );
    }

    @Override
    public DeleteModelResponse deleteModel( DeleteModelRequest request )
    {
        request.setEndpoint(
                getEndpointBuilder().create( request ).withServiceEndpoint( request.getClusterName() + "/" + EndpointConstant.MODELS_PATH ).addQueryParam(
                        "task", request.getTask() ).addQueryParam( "policyName", request.getPolicyName() ).buildUrl() );
        return makeRequest( request, DeleteModelResponse.class, HttpMethod.DELETE );
    }

    @Override
    public LoadModelResponse loadModel( LoadModelRequest request )
    {
        request.setEndpoint(
                getEndpointBuilder().create( request ).withServiceEndpoint( request.getClusterName() + "/" + EndpointConstant.INFERENCE_PATH ).addPathVariable(
                        "model" ).buildUrl() );
        Map<String,String> body = new HashMap<>();
        body.put( "task", request.getTask() );
        body.put( "policyName", request.getPolicyName() );
        request.setBody( body );
        return makeRequest( request, LoadModelResponse.class, HttpMethod.POST );
    }

    @Override
    public UnloadModelResponse unloadModel( UnloadModelRequest request )
    {
        request.setEndpoint(
                getEndpointBuilder().create( request ).withServiceEndpoint( request.getClusterName() + "/" + EndpointConstant.INFERENCE_PATH ).addPathVariable(
                        "model" ).buildUrl() );
        return makeRequest( request, UnloadModelResponse.class, HttpMethod.DELETE );
    }

    @Override
    public CypherDataInferenceResponse createCypherDataInference( CypherDataInferenceRequest request )
    {
        request.setEndpoint(
                getEndpointBuilder().create( request ).withServiceEndpoint( request.getClusterName() + "/" + EndpointConstant.INFERENCE_PATH ).addPathVariable(
                        "cypher" ).buildUrl() );
        request.setBody( request.getCypherDataInference() );
        return makeRequest( request, CypherDataInferenceResponse.class, HttpMethod.POST );
    }

    @Override
    public JsonDataInferenceResponse createJsonDataInference( JsonDataInferenceRequest request )
    {
        request.setEndpoint(
                getEndpointBuilder().create( request ).withServiceEndpoint( request.getClusterName() + "/" + EndpointConstant.INFERENCE_PATH ).addPathVariable(
                        "json" ).buildUrl() );
        request.setBody( request.getJsonDataInference() );
        return makeRequest( request, JsonDataInferenceResponse.class, HttpMethod.POST );
    }

    @Override
    public BatchInferenceResponse createBatchInference( CreateBatchInferenceRequest request )
    {
        request.setEndpoint(
                getEndpointBuilder().create( request ).withServiceEndpoint( request.getClusterName() + "/" + EndpointConstant.INFERENCE_PATH ).addPathVariable(
                        "batch" ).buildUrl() );
        request.setBody( request.getPolicy() );
        return makeRequest( request, BatchInferenceResponse.class, HttpMethod.POST );
    }

    @Override
    public BatchInferenceResponse retryBatchInference( RetryBatchJobRequest request )
    {
        request.setEndpoint(
                getEndpointBuilder().create( request ).withServiceEndpoint( request.getClusterName() + "/" + EndpointConstant.INFERENCE_PATH ).addPathVariable(
                        "batch" ).addPathVariable( request.getJobId() ).buildUrl() );
        request.setBody( request.getPolicy() );
        return makeRequest( request, BatchInferenceResponse.class, HttpMethod.PUT );
    }

    @Override
    public GetBatchInferencePolicyResponse getBatchInferencePolicy( GetBatchInferencePolicyRequest request )
    {
        request.setEndpoint(
                getEndpointBuilder().create( request ).withServiceEndpoint( request.getClusterName() + "/" + EndpointConstant.INFERENCE_PATH ).addPathVariable(
                        "batch" ).addQueryParam( "jobId", request.getJobId() ).buildUrl() );
        return makeRequest( request, GetBatchInferencePolicyResponse.class, HttpMethod.GET );
    }

    @Override
    public DeleteBatchInferenceResponse deleteBatchInference( DeleteBatchInferenceRequest request )
    {
        request.setEndpoint(
                getEndpointBuilder().create( request ).withServiceEndpoint( request.getClusterName() + "/" + EndpointConstant.INFERENCE_PATH ).addPathVariable(
                        "batch" ).addQueryParam( "task", request.getTask() ).addQueryParam( "policyName", request.getPolicyName() ).addQueryParam( "jobId",
                        request.getJobId() ).buildUrl() );
        return makeRequest( request, DeleteBatchInferenceResponse.class, HttpMethod.DELETE );
    }

    @Override
    public CreateEventActionResponse createEventAction( CreateEventActionRequest request )
    {
        request.setEndpoint(
                getEndpointBuilder().create( request ).withServiceEndpoint( request.getClusterName() + "/" + EndpointConstant.EVENT_PATH ).buildUrl() );
        request.setBody( request.getPolicy() );
        return makeRequest( request, CreateEventActionResponse.class, HttpMethod.POST );
    }

    @Override
    protected <T extends GraphGridServiceResponse> T makeRequest( GraphGridServiceRequest request, Class responseType, HttpMethod httpMethod )
    {
        return super.makeRequest( request, responseType, httpMethod );
    }
}
