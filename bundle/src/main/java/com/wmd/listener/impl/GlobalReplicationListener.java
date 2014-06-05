/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wmd.listener.impl;

import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationEvent;
import com.wmd.service.SearchService;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

/**
 * OSGi event listener for replication events. Will trigger a service for 
 * received events. Replication API is used to get replication meta data.
 * @author Matthias Wermund, Acquity Group part of Accenture
 */
@Component
@Service
@Properties({
    @Property(name=EventConstants.EVENT_TOPIC, value = ReplicationEvent.EVENT_TOPIC)
})
public class GlobalReplicationListener implements EventHandler { 

    public void handleEvent(Event event) {        
        ReplicationEvent repEvent = ReplicationEvent.fromEvent(event);
        ReplicationActionType type = repEvent.getReplicationAction().getType();
        String path = repEvent.getReplicationAction().getPath();
        switch (type) {
            case ACTIVATE: 
                searchService.index(path);
                break;
            case DEACTIVATE:
            case DELETE:
                searchService.drop(path);
        }        
    }
    
    
    @Reference
    private SearchService searchService;    
    
}
