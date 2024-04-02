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
import com.graphgrid.sdk.core.model.GraphGridServiceResponse;
import org.apache.http.HttpResponse;

import java.io.IOException;

public interface ResponseHandler<T extends GraphGridServiceResponse>
{

    T handle( HttpResponse httpResponse, ObjectMapper mapper, Class<T> responseType ) throws IOException;

    T handle( HttpResponse httpResponse, Class<T> responseType ) throws IOException;

}
