/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wmd.serialization.impl;

import com.day.cq.replication.ContentBuilder;
import com.day.cq.replication.ReplicationAction;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationContent;
import com.day.cq.replication.ReplicationContentFactory;
import com.day.cq.replication.ReplicationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.jcr.Session;
import javax.servlet.ServletException;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.engine.SlingRequestProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Transforms replicated data to JSON payload for HTTP request.
 * @author Matthias Wermund, Acquity Group part of Accenture
 */
@Component
@Service
@Properties({
    @Property(name = "name", value = "JSON")
})
public class JSONContentBuilder implements ContentBuilder {
    
    private static final Logger LOG = LoggerFactory.getLogger(JSONContentBuilder.class);
    
    @Reference
    private SlingRequestProcessor sling;
    
    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    public ReplicationContent create(Session sn, ReplicationAction action, ReplicationContentFactory rcf) throws ReplicationException {
        
        return create(sn, action, rcf, null);
    }

    @Override
    public ReplicationContent create(Session sn, ReplicationAction action, 
            ReplicationContentFactory rcf, Map<String, Object> map) 
            throws ReplicationException {
        
        if (ReplicationActionType.ACTIVATE.equals(action.getType())) {
            try {
                // get JSON view of node data
                Map<String,Object> prop = new HashMap<String,Object>();
                prop.put("user.jcr.session", sn);
                ResourceResolver resolver = resolverFactory.getResourceResolver(prop);
                FakeHttpResponse response = new FakeHttpResponse();
                sling.processRequest(
                        new FakeHttpRequest(action.getPath() + "/_jcr_content.infinity.json"), 
                        response, resolver);
                response.getWriter().close();
                return rcf.create("application/json", response.getOutputFile(), true);
            } catch (Exception e) {
                throw new ReplicationException(e);
            }
        } else {
            return ReplicationContent.VOID;
        }
    }

    @Override
    public String getName() {
        return "JSON";
    }

    @Override
    public String getTitle() {
        return "JSON content";
    }
    
    
}
