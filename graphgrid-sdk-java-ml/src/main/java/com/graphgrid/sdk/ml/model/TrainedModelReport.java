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
package com.graphgrid.sdk.ml.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

import com.graphgrid.sdk.core.model.GraphGridServiceResponse;
import com.graphgrid.sdk.ml.model.dataframe.ColumnSchema;

@JsonAutoDetect
@Getter
@Setter
public class TrainedModelReport extends GraphGridServiceResponse
{

    private String task;
    private String policyName;
    private String grn;
    private String createdAt;
    private String updatedAt;
    private List<String> pipelineStages;
    private Long trainingDataCount;
    private Long testDataCount;
    private List<ColumnSchema> inputSchema;
    private List<ColumnSchema> outputSchema;
    private Map<String,Double> metrics;
}
