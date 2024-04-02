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
package com.graphgrid.sdk.ml.model.inference;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.graphgrid.sdk.ml.model.training.InnerTransformation;

@JsonAutoDetect
@JsonInclude( JsonInclude.Include.NON_NULL )
@Getter
@Setter
public class CypherDataInput
{

    @Valid
    private InnerTransformation transformation;
    @NotBlank
    private String cypher;
    private Map<String,Object> parameters;
}
