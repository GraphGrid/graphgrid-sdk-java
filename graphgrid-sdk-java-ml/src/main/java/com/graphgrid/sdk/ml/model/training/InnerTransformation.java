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
package com.graphgrid.sdk.ml.model.training;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.graphgrid.sdk.ml.model.transformation.Assignment;
import com.graphgrid.sdk.ml.model.transformation.DataDestination;
import com.graphgrid.sdk.ml.model.transformation.DataSource;
import com.graphgrid.sdk.ml.model.transformation.Feature;
import com.graphgrid.sdk.ml.model.transformation.TransformationOption;
import com.graphgrid.sdk.ml.model.transformation.TransformationPolicy;

@JsonAutoDetect
@JsonInclude( JsonInclude.Include.NON_NULL )
@Getter
@Setter
@EqualsAndHashCode
public class InnerTransformation
{
    @NotBlank
    private String cypher;
    private Map<String,Object> parameters;
    @NotEmpty
    private Set<String> variables;
    @Valid
    @NotNull
    private DataDestination destination;
    @Valid
    private Assignment[] assignments;
    @Valid
    @NotEmpty
    private Map<String,Feature> feature;
    @Valid
    private TransformationOption option = new TransformationOption();

    public TransformationPolicy toTransformPolicy()
    {
        TransformationPolicy transformationPolicy = new TransformationPolicy();
        transformationPolicy.setOption( option );
        DataSource dataSource = new DataSource();
        dataSource.setCypher( cypher );
        dataSource.setParameters( parameters );
        dataSource.setVariables( variables );
        transformationPolicy.setSource( dataSource );
        transformationPolicy.setAssignments( assignments );
        transformationPolicy.setFeature( feature );
        if ( destination != null )
        {
            Collections.sort( destination.getNodeLabels() );
        }
        transformationPolicy.setDestination( destination );
        return transformationPolicy;
    }
}
