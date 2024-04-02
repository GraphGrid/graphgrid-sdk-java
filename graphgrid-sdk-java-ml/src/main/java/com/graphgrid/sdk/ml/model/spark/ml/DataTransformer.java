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
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotBlank;

/**
 * Transform a Dataset to another Dataset.
 */
@JsonAutoDetect
@JsonInclude( JsonInclude.Include.NON_NULL )
@Getter
@Setter
@EqualsAndHashCode
public class DataTransformer
{
    @NotBlank
    private String name;
    @NotBlank
    private String dataFrame;
    private Map<String,String> outputs;

    public DataTransformer()
    {
        outputs = new HashMap<>();
    }
}
