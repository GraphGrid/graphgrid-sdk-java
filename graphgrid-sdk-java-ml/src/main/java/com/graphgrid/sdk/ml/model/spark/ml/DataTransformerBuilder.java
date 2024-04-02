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

public class DataTransformerBuilder
{

    private DataTransformer transform;

    public DataTransformerBuilder( String name, String dataFrame )
    {
        transform = new DataTransformer();
        transform.setName( name );
        transform.setDataFrame( dataFrame );
    }

    public DataTransformerBuilder output( String name, String field )
    {
        transform.getOutputs().put( name, field );
        return this;
    }

    public DataTransformer build()
    {
        return transform;
    }
}
