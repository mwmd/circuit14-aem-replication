/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wmd.filter.impl;

import com.day.cq.replication.ReplicationContentFilter;
import com.day.cq.wcm.api.NameConstants;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filters author information out of replicated data.
 * @author Matthias Wermund, Acquity Group part of Accenture
 */
public class InternalContentFilter implements ReplicationContentFilter {

    private static final Logger LOG = LoggerFactory.getLogger(InternalContentFilter.class);
    
    private static final Set<String> FILTER_PROPERTIES = new HashSet<String>();
    
    static {
        FILTER_PROPERTIES.add(NameConstants.PN_CREATED_BY);
        FILTER_PROPERTIES.add(NameConstants.PN_LAST_MOD_BY);
        FILTER_PROPERTIES.add(NameConstants.PN_PAGE_LAST_MOD_BY);        
    }
    
    public boolean accepts(Node node) {
        
        return true;
    }

    public boolean accepts(Property prop) {
        
        try {
            String name = prop.getName();
            return !FILTER_PROPERTIES.contains(name);
        } catch (RepositoryException e) {
            LOG.error("Error while analyzing property.", e);
            return true;
        }        
    }

    public boolean allowsDescent(Node node) {
        
        return true;
    }

    public List<String> getFilteredPaths() {
        
        return null;
    }
    
    
}
