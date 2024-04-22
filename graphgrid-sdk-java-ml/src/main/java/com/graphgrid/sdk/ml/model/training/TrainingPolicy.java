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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.graphgrid.sdk.ml.model.MLPolicy;
import com.graphgrid.sdk.ml.model.spark.ml.Evaluator;
import com.graphgrid.sdk.ml.model.spark.ml.PipelineStage;

@JsonAutoDetect
@Getter
@Setter
@EqualsAndHashCode( callSuper = false )
public class TrainingPolicy extends MLPolicy
{

    private String task;
    @Valid
    @NotNull
    private DataFrameSource dataFrame;
    @Valid
    @NotEmpty
    private PipelineStage[] pipelineStages;
    @Valid
    @NotNull
    private TrainingData trainingData;
    @NotEmpty
    private String[] trainingPipelines;
    @Valid
    private Evaluator[] evaluators;
}
