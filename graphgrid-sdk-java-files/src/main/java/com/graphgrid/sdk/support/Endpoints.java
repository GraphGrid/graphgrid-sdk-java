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
package com.graphgrid.sdk.support;

public final class Endpoints
{

    public static final String UPLOAD = "upload";
    public static final String DOWNLOAD = "download";
    public static final String DELETE = "delete";
    public static final String STATUS = "status";
    public static final String CREATE_RELATIONSHIP = "createRelationship";
    public static final String FIND_BY_RESOURCE = "findByResource";
    private static final String FORWARD_SLASH = "/";
    public static final String CREATE_ONLY = UPLOAD + FORWARD_SLASH + "createOnly";

    private Endpoints()
    {
    }
}
