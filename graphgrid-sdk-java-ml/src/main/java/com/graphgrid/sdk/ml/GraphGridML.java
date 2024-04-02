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

public interface GraphGridML
{

    MLServiceStatusResponse status( MLServiceStatusRequest request );

    // Transformation
    CreateTransformationResponse createTransformation( CreateTransformationRequest request );

    GetTransformationPolicyResponse getTransformationPolicy( GetTransformationPolicyRequest request );

    GetTransformationStatusResponse getTransformationStatus( GetTransformationStatusRequest request );

    GetTransformationStatusResponse getInnerTransformationStatus( GetInnerTransformationStatusRequest request );

    DeleteTransformationResponse deleteTransformation( DeleteTransformationRequest request );

    // Feature
    ShowFeatureResponse showFeature( ShowFeatureRequest request );

    // Training
    TrainedModelReport trainModel( TrainModelRequest request );

    GetTrainingPolicyResponse getTrainingPolicy( GetTrainingPolicyRequest request );

    // Model
    TrainedModelReport showModelInfo( ShowModelInfoRequest request );

    DeleteModelResponse deleteModel( DeleteModelRequest request );

    // Inference
    LoadModelResponse loadModel( LoadModelRequest request );

    UnloadModelResponse unloadModel( UnloadModelRequest request );

    CypherDataInferenceResponse createCypherDataInference( CypherDataInferenceRequest request );

    JsonDataInferenceResponse createJsonDataInference( JsonDataInferenceRequest request );

    // Batch
    BatchInferenceResponse createBatchInference( CreateBatchInferenceRequest request );

    BatchInferenceResponse retryBatchInference( RetryBatchJobRequest request );

    GetBatchInferencePolicyResponse getBatchInferencePolicy( GetBatchInferencePolicyRequest request );

    DeleteBatchInferenceResponse deleteBatchInference( DeleteBatchInferenceRequest request );

    // Event
    CreateEventActionResponse createEventAction( CreateEventActionRequest request );
}
