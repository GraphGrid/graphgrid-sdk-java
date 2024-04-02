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

import com.graphgrid.sdk.ml.constant.PipelineStageType;

public class PipelineStageBuilder
{

    private PipelineStage pipelineStage;

    public PipelineStageBuilder( String name, String type )
    {
        switch ( type )
        {
        case PipelineStageType.ALS:
        case PipelineStageType.DECISION_TREE_CLASSIFIER:
        case PipelineStageType.RANDOM_FOREST_CLASSIFIER:
        case PipelineStageType.KMEANS:
        case PipelineStageType.LINEAR_REGRESSION:
        case PipelineStageType.LOGISTIC_REGRESSION:
        case PipelineStageType.STRING_INDEXER:
        case PipelineStageType.WORD2VEC:
        case PipelineStageType.ONE_HOT_ENCODER_ESTIMATOR:
            pipelineStage = new Estimator();
            pipelineStage.setName( name );
            pipelineStage.setType( type );
            break;
        case PipelineStageType.INDEX_TO_STRING:
        case PipelineStageType.VECTOR_ASSEMBLER:
        case PipelineStageType.TOKENIZER:
        case PipelineStageType.STOP_WORDS_REMOVER:
        case PipelineStageType.N_GRAM:
        case PipelineStageType.FEATURE_HASHER:
            pipelineStage = new Transformer();
            pipelineStage.setName( name );
            pipelineStage.setType( type );
            break;
        default:
            throw new IllegalArgumentException( "[type] not recognized" );
        }
    }

    public PipelineStageBuilder parameter( String field, Object value )
    {
        pipelineStage.getParameters().put( field, value );
        return this;
    }

    public PipelineStageBuilder fit( DataFitter fit )
    {
        if ( !pipelineStage.getPipelineType().equals( PipelineStageType.ESTIMATOR ) )
        {
            throw new IllegalArgumentException( "[pipelineType] is not Estimator" );
        }
        ((Estimator) pipelineStage).setFit( fit );
        return this;
    }

    public PipelineStageBuilder transform( DataTransformer transform )
    {
        if ( !pipelineStage.getPipelineType().equals( PipelineStageType.TRANSFORMER ) )
        {
            throw new IllegalArgumentException( "[pipelineType] is not Transformer" );
        }
        ((Transformer) pipelineStage).setTransform( transform );
        return this;
    }

    public PipelineStageBuilder output( String name, String field )
    {
        pipelineStage.getOutputs().put( name, field );
        return this;
    }

    public PipelineStage build()
    {
        return pipelineStage;
    }
}
