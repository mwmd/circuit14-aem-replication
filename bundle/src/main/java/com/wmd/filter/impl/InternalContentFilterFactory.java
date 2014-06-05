/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wmd.filter.impl;

import com.day.cq.replication.ReplicationAction;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationContentFilter;
import com.day.cq.replication.ReplicationContentFilterFactory;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory for the custom content filter. Will only apply the filter for ACTIVATE
 * replication question.
 * @author Matthias Wermund, Acquity Group part of Accenture
 */
@Component
@Service
public class InternalContentFilterFactory implements ReplicationContentFilterFactory {
    
    private static final Logger LOG = LoggerFactory.getLogger(InternalContentFilterFactory.class);
    
    private InternalContentFilter internalFilter;
    
    protected void activate(ComponentContext context) {
        
        internalFilter = new InternalContentFilter();
    }

    public ReplicationContentFilter createFilter(ReplicationAction action) {
        
        ReplicationContentFilter filter = null;
        if (ReplicationActionType.ACTIVATE.equals(action.getType())) {
            LOG.info("Agent {}", action.getConfig().getAgentId());
            filter = internalFilter;
        }
        return filter;
    }
    
}
