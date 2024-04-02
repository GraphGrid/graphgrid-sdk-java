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
package com.graphgrid.sdk.core.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * can be used for testing and endpoints that dont require token authentication
 */
public class EmptySecurityConfigs extends SecurityConfig
{

    private static final Logger LOGGER = LoggerFactory.getLogger( EmptySecurityConfigs.class );

    public EmptySecurityConfigs()
    {
        LOGGER.warn( "Empty security configs are used. Should only be used for testing or endpoint that dont require authentication." );
    }
}
