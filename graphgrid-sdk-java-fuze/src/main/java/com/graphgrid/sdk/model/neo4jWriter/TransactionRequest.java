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
package com.graphgrid.sdk.model.neo4jWriter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.graphgrid.sdk.model.Neo4jCredentials;
import com.graphgrid.sdk.model.broker.BrokerEndpoint;

@JsonAutoDetect
@JsonIgnoreProperties( ignoreUnknown = true )
public class TransactionRequest
{
    private List<TransactionStatement> statements;

    private String fuzeId = UUID.randomUUID().toString();
    private String batchId;
    private String parentId;
    private Map<String,Object> metadata;
    private AccessMode requestType = AccessMode.WRITE;
    private BrokerEndpoint sendResultsTo;

    private RequestStatus status = RequestStatus.NOT_RUN;

    private List<String> requiredBookmarks;
    private String returnedBookmark;
    private int queryAttemptNumber;

    private long creationTime = System.currentTimeMillis();
    private Long timeToCreate;
    private long startTime;
    private long endTime;

    private boolean splitAllowed = true;

    private List<TransactionRequest> children;
    private Integer errorIndex;
    private String errorCode;
    private String errorMessage;

    private List<Neo4jCredentials> neo4jCredentials;

    public TransactionRequest()
    {
    }

    public TransactionRequest( List<TransactionStatement> statements )
    {
        this.statements = statements;
    }

    public List<TransactionStatement> getStatements()
    {
        return statements;
    }

    public void setStatements( List<TransactionStatement> statements )
    {
        this.statements = statements;
    }

    public String getFuzeId()
    {
        return fuzeId;
    }

    public void setFuzeId( String fuzeId )
    {
        this.fuzeId = fuzeId;
    }

    public String getBatchId()
    {
        return batchId;
    }

    public void setBatchId( String batchId )
    {
        this.batchId = batchId;
    }

    public String getParentId()
    {
        return parentId;
    }

    public void setParentId( String parentId )
    {
        this.parentId = parentId;
    }

    public Map<String,Object> getMetadata()
    {
        return metadata;
    }

    public void setMetadata( Map<String,Object> metadata )
    {
        this.metadata = metadata;
    }

    public AccessMode getRequestType()
    {
        return requestType;
    }

    public void setRequestType( AccessMode requestType )
    {
        this.requestType = requestType;
    }

    public BrokerEndpoint getSendResultsTo()
    {
        return sendResultsTo;
    }

    public void setSendResultsTo( BrokerEndpoint sendResultsTo )
    {
        this.sendResultsTo = sendResultsTo;
    }

    public RequestStatus getStatus()
    {
        return status;
    }

    public void setStatus( RequestStatus status )
    {
        this.status = status;
    }

    public List<String> getRequiredBookmarks()
    {
        return requiredBookmarks;
    }

    public void setRequiredBookmarks( List<String> requiredBookmarks )
    {
        this.requiredBookmarks = requiredBookmarks;
    }

    public String getReturnedBookmark()
    {
        return returnedBookmark;
    }

    public void setReturnedBookmark( String returnedBookmark )
    {
        this.returnedBookmark = returnedBookmark;
    }

    public int getQueryAttemptNumber()
    {
        return queryAttemptNumber;
    }

    public void setQueryAttemptNumber( int queryAttemptNumber )
    {
        this.queryAttemptNumber = queryAttemptNumber;
    }

    public String getCreationTime()
    {
        return Instant.ofEpochMilli( creationTime ).toString();
    }

    public void setCreationTime( String creationTime )
    {
        this.creationTime = Instant.parse( creationTime ).toEpochMilli();
    }

    public Long getTimeToCreate()
    {
        return timeToCreate;
    }

    public void setTimeToCreate( Long timeToCreate )
    {
        this.timeToCreate = timeToCreate;
    }

    public String getStartTime()
    {
        return Instant.ofEpochMilli( startTime ).toString();
    }

    public void setStartTime( String startTime )
    {
        this.startTime = Instant.parse( startTime ).toEpochMilli();
    }

    public String getEndTime()
    {
        return Instant.ofEpochMilli( endTime ).toString();
    }

    public void setEndTime( String endTime )
    {
        this.endTime = Instant.parse( endTime ).toEpochMilli();
    }

    public boolean isSplitAllowed()
    {
        return splitAllowed;
    }

    public void setSplitAllowed( boolean splitAllowed )
    {
        this.splitAllowed = splitAllowed;
    }

    public List<TransactionRequest> getChildren()
    {
        return children;
    }

    public void setChildren( List<TransactionRequest> children )
    {
        this.children = children;
    }

    public Integer getErrorIndex()
    {
        return errorIndex;
    }

    public void setErrorIndex( Integer errorIndex )
    {
        this.errorIndex = errorIndex;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode( String errorCode )
    {
        this.errorCode = errorCode;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage( String errorMessage )
    {
        this.errorMessage = errorMessage;
    }

    public List<Neo4jCredentials> getNeo4jCredentials()
    {
        return neo4jCredentials;
    }

    public void setNeo4jCredentials( List<Neo4jCredentials> neo4jCredentials )
    {
        this.neo4jCredentials = neo4jCredentials;
    }

    @JsonAutoDetect
    @JsonIgnoreProperties( ignoreUnknown = true )
    public static class TransactionStatement
    {
        private String statement;
        private Map<String,Object> parameters;
        private Map<String,Object> metadata;

        private Actions actions;
        private Boolean executed = false;
        private long startTime;
        private long endTime;
        private StatementStatus status = StatementStatus.NOT_RUN;

        public TransactionStatement()
        {
        }

        public TransactionStatement( String statement, Map<String,Object> parameters )
        {
            this.statement = statement;
            this.parameters = parameters;
        }

        public String getStatement()
        {
            return statement;
        }

        public void setStatement( String statement )
        {
            this.statement = statement;
        }

        public Map<String,Object> getParameters()
        {
            return parameters;
        }

        public void setParameters( Map<String,Object> parameters )
        {
            this.parameters = parameters;
        }

        public Map<String,Object> getMetadata()
        {
            return metadata;
        }

        public void setMetadata( Map<String,Object> metadata )
        {
            this.metadata = metadata;
        }

        public Actions getActions()
        {
            return actions;
        }

        public void setActions( Actions actions )
        {
            this.actions = actions;
        }

        public Boolean getExecuted()
        {
            return executed;
        }

        public void setExecuted( Boolean executed )
        {
            this.executed = executed;
        }

        public String getStartTime()
        {
            return Instant.ofEpochMilli( startTime ).toString();
        }

        public void setStartTime( String startTime )
        {
            this.startTime = Instant.parse( startTime ).toEpochMilli();
        }

        public String getEndTime()
        {
            return Instant.ofEpochMilli( endTime ).toString();
        }

        public void setEndTime( String endTime )
        {
            this.endTime = Instant.parse( endTime ).toEpochMilli();
        }

        public long getExecutionDuration()
        {
            if ( endTime > 0L )
            {
                return endTime - startTime;
            }
            else
            {
                // execution is not yet complete
                return -1;
            }
        }

        public StatementStatus getStatus()
        {
            return status;
        }

        public void setStatus( StatementStatus status )
        {
            this.status = status;
        }

        @JsonAutoDetect
        @JsonIgnoreProperties( ignoreUnknown = true )
        public static class Actions
        {
            private List<TransactionStatement> onSuccess;
            private List<TransactionStatement> onFailure;

            public Actions()
            {
            }

            public Actions( List<TransactionStatement> onSuccess, List<TransactionStatement> onFailure )
            {
                this.onSuccess = onSuccess;
                this.onFailure = onFailure;
            }

            public List<TransactionStatement> getOnSuccess()
            {
                return onSuccess;
            }

            public void setOnSuccess( List<TransactionStatement> onSuccess )
            {
                this.onSuccess = onSuccess;
            }

            public List<TransactionStatement> getOnFailure()
            {
                return onFailure;
            }

            public void setOnFailure( List<TransactionStatement> onFailure )
            {
                this.onFailure = onFailure;
            }
        }

        public enum StatementStatus
        {
            NOT_RUN,
            IN_PROGRESS,
            SUCCEEDED,
            FAILED
        }
    }

    public enum AccessMode
    {
        READ,
        WRITE
    }

    public enum RequestStatus
    {
        DOES_NOT_EXIST,
        NOT_RUN,
        BATCH,
        SPLIT,
        IN_PROGRESS,
        SUCCEEDED,
        FAILED_WITH_RETRY,
        FAILED
    }
}
