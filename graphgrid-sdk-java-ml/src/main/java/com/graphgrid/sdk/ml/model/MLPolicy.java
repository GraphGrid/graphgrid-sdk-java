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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonAutoDetect
@Getter
@Setter
public abstract class MLPolicy
{
    private String policyName;
    private boolean savePolicy = true;
    private boolean overwrite;
    private Metadata metadata = new Metadata();

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }

        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }

        MLPolicy mlPolicy = (MLPolicy) o;

        return new EqualsBuilder().append( savePolicy, mlPolicy.savePolicy ).append( overwrite, mlPolicy.overwrite ).append( policyName,
                mlPolicy.policyName ).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder( 17, 37 ).append( policyName ).append( savePolicy ).append( overwrite ).toHashCode();
    }
}
