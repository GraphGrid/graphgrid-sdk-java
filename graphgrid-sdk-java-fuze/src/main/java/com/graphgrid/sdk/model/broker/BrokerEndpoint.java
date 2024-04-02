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
package com.graphgrid.sdk.model.broker;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect
@JsonIgnoreProperties( ignoreUnknown = true )
public class BrokerEndpoint
{
    private BrokerType broker;

    private String topic;

    private String exchange;
    private String exchangeType = ExchangeTypes.TOPIC;
    private String routingKey;
    private boolean durable = true;
    private boolean exclusive = false;
    private boolean autoDelete = false;

    private String queue;

    private String region;
    private String bucket;

    public BrokerEndpoint()
    {
    }

    public static BrokerEndpoint kafkaEndpoint( String topic )
    {
        BrokerEndpoint brokerEndpoint = new BrokerEndpoint();

        brokerEndpoint.broker = BrokerType.KAFKA;
        brokerEndpoint.topic = topic;

        return brokerEndpoint;
    }

    public static BrokerEndpoint rabbitmqEndpoint( String exchange, String routingKey, String queue )
    {
        BrokerEndpoint brokerEndpoint = new BrokerEndpoint();

        brokerEndpoint.broker = BrokerType.RABBITMQ;
        brokerEndpoint.exchange = exchange;
        brokerEndpoint.routingKey = routingKey;
        brokerEndpoint.queue = queue;

        return brokerEndpoint;
    }

    public static BrokerEndpoint rabbitmqEndpoint( String exchange, String routingKey, String queue, boolean durable, boolean exclusive, boolean autoDelete )
    {
        BrokerEndpoint brokerEndpoint = new BrokerEndpoint();

        brokerEndpoint.broker = BrokerType.RABBITMQ;
        brokerEndpoint.exchange = exchange;
        brokerEndpoint.routingKey = routingKey;
        brokerEndpoint.queue = queue;
        brokerEndpoint.durable = durable;
        brokerEndpoint.exclusive = exclusive;
        brokerEndpoint.autoDelete = autoDelete;

        return brokerEndpoint;
    }

    public static BrokerEndpoint sqsEndpoint( String queue, String region, String bucket )
    {
        BrokerEndpoint brokerEndpoint = new BrokerEndpoint();

        brokerEndpoint.broker = BrokerType.SQS;
        brokerEndpoint.queue = queue;
        brokerEndpoint.region = region;
        brokerEndpoint.bucket = bucket;

        return brokerEndpoint;
    }

    public BrokerType getBroker()
    {
        return broker;
    }

    public void setBroker( BrokerType broker )
    {
        this.broker = broker;
    }

    public String getTopic()
    {
        return topic;
    }

    public void setTopic( String topic )
    {
        this.topic = topic;
    }

    public String getExchange()
    {
        return exchange;
    }

    public void setExchange( String exchange )
    {
        this.exchange = exchange;
    }

    public String getExchangeType()
    {
        return exchangeType;
    }

    public void setExchangeType( String exchangeType )
    {
        this.exchangeType = exchangeType;
    }

    public String getRoutingKey()
    {
        return routingKey;
    }

    public void setRoutingKey( String routingKey )
    {
        this.routingKey = routingKey;
    }

    public boolean isDurable()
    {
        return durable;
    }

    public void setDurable( boolean durable )
    {
        this.durable = durable;
    }

    public boolean isExclusive()
    {
        return exclusive;
    }

    public void setExclusive( boolean exclusive )
    {
        this.exclusive = exclusive;
    }

    public boolean isAutoDelete()
    {
        return autoDelete;
    }

    public void setAutoDelete( boolean autoDelete )
    {
        this.autoDelete = autoDelete;
    }

    public String getQueue()
    {
        return queue;
    }

    public void setQueue( String queue )
    {
        this.queue = queue;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion( String region )
    {
        this.region = region;
    }

    public String getBucket()
    {
        return bucket;
    }

    public void setBucket( String bucket )
    {
        this.bucket = bucket;
    }

    public enum BrokerType
    {
        KAFKA,
        RABBITMQ,
        SQS,
        DIRECT
    }

    public abstract class ExchangeTypes
    {
        public static final String DIRECT = "direct";
        public static final String TOPIC = "topic";
        public static final String FANOUT = "fanout";
        public static final String HEADERS = "headers";
        public static final String SYSTEM = "system";
    }
}
