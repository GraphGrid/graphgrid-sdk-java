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
package com.graphgrid.sdk.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

@JsonAutoDetect
@JsonIgnoreProperties( ignoreUnknown = true )
public class UploadFileMetadata implements Serializable
{

    private static final long serialVersionUID = 6462965008303588174L;

    private CreateProperties createProperties;
    private List<CreateRelationship> createRelationships;
    private FileNode fileNode;

    public UploadFileMetadata withCreateProperties( final CreateProperties createProperties )
    {
        this.createProperties = createProperties;
        return this;
    }

    public UploadFileMetadata withCreateRelationships( final List<CreateRelationship> createRelationships )
    {
        this.createRelationships = createRelationships;
        return this;
    }

    public UploadFileMetadata withFileNode( final FileNode fileNode )
    {
        this.fileNode = fileNode;
        return this;
    }

    public CreateProperties getCreateProperties()
    {
        return createProperties;
    }

    public void setCreateProperties( final CreateProperties createProperties )
    {
        this.createProperties = createProperties;
    }

    public List<CreateRelationship> getCreateRelationships()
    {
        return createRelationships;
    }

    public void setCreateRelationships( final List<CreateRelationship> createRelationships )
    {
        this.createRelationships = createRelationships;
    }

    public FileNode getFileNode()
    {
        return fileNode;
    }

    public void setFileNode( FileNode fileNode )
    {
        this.fileNode = fileNode;
    }

    @Override
    public boolean equals( final Object o )
    {
        if ( this == o )
        {
            return true;
        }

        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }

        final UploadFileMetadata that = (UploadFileMetadata) o;

        return new EqualsBuilder().append( createProperties, that.createProperties ).append( createRelationships, that.createRelationships ).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder( 17, 37 ).append( createProperties ).append( createRelationships ).toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder( this ).append( "createProperties", createProperties ).append( "createRelationships", createRelationships ).toString();
    }

    @JsonAutoDetect
    @JsonIgnoreProperties( ignoreUnknown = true )
    public static class CreateProperties implements Serializable
    {
        private static final long serialVersionUID = 1974863950920455525L;

        private String md5;
        private String sha1;
        private String sha256;
        private String description;

        public CreateProperties withMd5( final String md5 )
        {
            this.md5 = md5;
            return this;
        }

        public CreateProperties withSha1( final String sha1 )
        {
            this.sha1 = sha1;
            return this;
        }

        public CreateProperties withSha256( final String sha256 )
        {
            this.sha256 = sha256;
            return this;
        }

        public CreateProperties withDescription( final String description )
        {
            this.description = description;
            return this;
        }

        public String getMd5()
        {
            return md5;
        }

        public void setMd5( final String md5 )
        {
            this.md5 = md5;
        }

        public String getSha1()
        {
            return sha1;
        }

        public void setSha1( final String sha1 )
        {
            this.sha1 = sha1;
        }

        public String getSha256()
        {
            return sha256;
        }

        public void setSha256( final String sha256 )
        {
            this.sha256 = sha256;
        }

        public String getDescription()
        {
            return description;
        }

        public void setDescription( final String description )
        {
            this.description = description;
        }

        @Override
        public boolean equals( final Object o )
        {
            if ( this == o )
            {
                return true;
            }

            if ( o == null || getClass() != o.getClass() )
            {
                return false;
            }

            final CreateProperties that = (CreateProperties) o;

            return new EqualsBuilder().append( md5, that.md5 ).append( sha1, that.sha1 ).append( sha256, that.sha256 ).append( description, that.description )
                    .isEquals();
        }

        @Override
        public int hashCode()
        {
            return new HashCodeBuilder( 17, 37 ).append( md5 ).append( sha1 ).append( sha256 ).append( description ).toHashCode();
        }

        @Override
        public String toString()
        {
            return new ToStringBuilder( this ).append( "md5", md5 ).append( "sha1", sha1 ).append( "sha256", sha256 ).append( "description", description )
                    .toString();
        }
    }

    @JsonAutoDetect
    @JsonIgnoreProperties( ignoreUnknown = true )
    public static class CreateRelationship implements Serializable
    {
        private static final long serialVersionUID = -4018085291713081001L;

        private String grn;
        private String type;
        private String direction;

        public CreateRelationship withGrn( final String grn )
        {
            this.grn = grn;
            return this;
        }

        public CreateRelationship withType( final String type )
        {
            this.type = type;
            return this;
        }

        public CreateRelationship withDirection( final String direction )
        {
            this.direction = direction;
            return this;
        }

        public String getGrn()
        {
            return grn;
        }

        public void setGrn( final String grn )
        {
            this.grn = grn;
        }

        public String getType()
        {
            return type;
        }

        public void setType( final String type )
        {
            this.type = type;
        }

        public String getDirection()
        {
            return direction;
        }

        public void setDirection( final String direction )
        {
            this.direction = direction;
        }

        @Override
        public boolean equals( final Object o )
        {
            if ( this == o )
            {
                return true;
            }

            if ( o == null || getClass() != o.getClass() )
            {
                return false;
            }

            final CreateRelationship that = (CreateRelationship) o;

            return new EqualsBuilder().append( grn, that.grn ).append( type, that.type ).append( direction, that.direction ).isEquals();
        }

        @Override
        public int hashCode()
        {
            return new HashCodeBuilder( 17, 37 ).append( grn ).append( type ).append( direction ).toHashCode();
        }

        @Override
        public String toString()
        {
            return new ToStringBuilder( this ).append( "grn", grn ).append( "type", type ).append( "direction", direction ).toString();
        }
    }
}

