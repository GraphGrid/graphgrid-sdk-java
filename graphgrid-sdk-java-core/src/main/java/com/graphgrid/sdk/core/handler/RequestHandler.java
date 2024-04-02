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
package com.graphgrid.sdk.core.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphgrid.sdk.core.model.GraphGridServiceRequest;
import com.graphgrid.sdk.core.utils.HttpMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

import java.io.IOException;

public interface RequestHandler
{

    HttpResponse executeRequest( GraphGridServiceRequest ggRequest, HttpMethod httpMethod ) throws IOException;

    HttpResponse executeRequest( GraphGridServiceRequest ggRequest, HttpMethod httpMethod, HttpClient client ) throws IOException;

    HttpResponse executeRequest( GraphGridServiceRequest ggRequest, HttpMethod httpMethod, ObjectMapper objectMapper, HttpClient client ) throws IOException;
}
