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
package com.graphgrid.sdk.ml.model.spark.ml;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotBlank;

import com.graphgrid.sdk.ml.constant.PipelineStageType;

@JsonAutoDetect
@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXTERNAL_PROPERTY, visible = true )
@JsonSubTypes( {
        // Features
        @JsonSubTypes.Type( value = Transformer.class, name = PipelineStageType.INDEX_TO_STRING ),
        @JsonSubTypes.Type( value = Estimator.class, name = PipelineStageType.STRING_INDEXER ),
        @JsonSubTypes.Type( value = Transformer.class, name = PipelineStageType.VECTOR_ASSEMBLER ),
        @JsonSubTypes.Type( value = Estimator.class, name = PipelineStageType.WORD2VEC),
        @JsonSubTypes.Type( value = Transformer.class, name = PipelineStageType.TOKENIZER ),
        @JsonSubTypes.Type( value = Transformer.class, name = PipelineStageType.STOP_WORDS_REMOVER ),
        @JsonSubTypes.Type( value = Transformer.class, name = PipelineStageType.N_GRAM ),
        @JsonSubTypes.Type( value = Transformer.class, name = PipelineStageType.FEATURE_HASHER ),
        @JsonSubTypes.Type( value = Estimator.class, name = PipelineStageType.ONE_HOT_ENCODER_ESTIMATOR ),
        // Classification
        @JsonSubTypes.Type( value = Estimator.class, name = PipelineStageType.LOGISTIC_REGRESSION ),
        @JsonSubTypes.Type( value = Estimator.class, name = PipelineStageType.DECISION_TREE_CLASSIFIER ),
        @JsonSubTypes.Type( value = Estimator.class, name = PipelineStageType.RANDOM_FOREST_CLASSIFIER ),
        // Regression
        @JsonSubTypes.Type( value = Estimator.class, name = PipelineStageType.LINEAR_REGRESSION ),
        // Clustering
        @JsonSubTypes.Type( value = Estimator.class, name = PipelineStageType.KMEANS ),
        // Recommendation
        @JsonSubTypes.Type( value = Estimator.class, name = PipelineStageType.ALS )} )
@Getter
@Setter
@EqualsAndHashCode
public abstract class PipelineStage
{

    @NotBlank
    protected String name;
    @JsonIgnore
    protected String pipelineType;
    @NotBlank
    protected String type;
    protected Map<String,Object> parameters;
    protected Map<String,String> outputs;

    public PipelineStage()
    {
        parameters = new HashMap<>();
        outputs = new HashMap<>();
    }
}
