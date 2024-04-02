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
package com.graphgrid.sdk.core.utils;

import org.apache.commons.lang3.StringUtils;

import com.graphgrid.sdk.core.exception.GraphGridSdkInvalidArgumentException;

/**
 * Helper methods for input validation.
 *
 * @author bradnussbaum
 */
public class Preconditions
{

    public final static String NULL_ERROR = "cannot be null";
    public final static String EMPTY_ERROR = NULL_ERROR + " or empty";
    public final static String UNKNOWN_ARG = "na.";

    public static <T> T checkNotNull( T obj, String variableName, String errorMessage )
    {
        if ( obj == null )
        {
            throw new GraphGridSdkInvalidArgumentException( buildErrorMessage( variableName, errorMessage ) );
        }
        return obj;
    }

    public static <T> T checkNotNull( T obj, String variableName )
    {
        return checkNotNull( obj, variableName, NULL_ERROR );
    }

    public static <T> T checkNotNull( T obj )
    {
        return checkNotNull( obj, UNKNOWN_ARG, NULL_ERROR );
    }

    public static String checkNotEmpty( String s, String variableName )
    {
        if ( StringUtils.isBlank( s ) )
        {
            throw new GraphGridSdkInvalidArgumentException( buildErrorMessage( variableName, EMPTY_ERROR ) );
        }
        return s;
    }

    public static String checkNotEmpty( String s )
    {
        return checkNotEmpty( s, UNKNOWN_ARG );
    }

    private static String buildErrorMessage( String variableName )
    {
        return buildErrorMessage( variableName, NULL_ERROR );
    }

    private static String buildErrorMessage( String variableName, String errorMessage )
    {
        return variableName + " " + errorMessage;
    }
}
