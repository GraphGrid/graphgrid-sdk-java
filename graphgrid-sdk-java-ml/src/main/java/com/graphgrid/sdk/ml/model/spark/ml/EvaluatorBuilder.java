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

import java.util.Arrays;

import com.graphgrid.sdk.ml.constant.EvaluatorType;

public class EvaluatorBuilder
{

    private Evaluator evaluator;

    public EvaluatorBuilder( String type )
    {
        switch ( type )
        {
        case EvaluatorType.BINARY_CLASSIFICATION:
        case EvaluatorType.CLUSTERING:
        case EvaluatorType.MULTICLASS_CLASSIFICATION:
        case EvaluatorType.REGRESSION:
            evaluator = new Evaluator();
            evaluator.setType( type );
            break;
        default:
            throw new IllegalArgumentException( "[type] not recognized" );
        }
    }

    public EvaluatorBuilder parameter( String field, Object value )
    {
        evaluator.getParameters().put( field, value );
        return this;
    }

    public EvaluatorBuilder metrics( String... metrics )
    {
        evaluator.getMetrics().addAll( Arrays.asList( metrics ) );
        return this;
    }

    public Evaluator build()
    {
        return evaluator;
    }
}
