/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmd.workflow.impl;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.replication.Agent;
import com.day.cq.replication.AgentFilter;
import com.day.cq.replication.AgentIdFilter;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.ReplicationOptions;
import com.day.cq.replication.Replicator;
import javax.jcr.Session;
import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.osgi.framework.Constants;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Workflow process implementation. Will send an activation request to a single
 * replication agent, identified by OSGi configuration of the agent id.
 * @author Matthias Wermund, Acquity Group part of Accenture
 */
@Component(metatype = true, label = "Filtered Activation Workflow Process")
@Service
@Properties({
        @Property(name = "process.label", value = "Filtered Activation"),
        @Property(name = Constants.SERVICE_DESCRIPTION, value = "Activation of content to only selected Publish instances.")
})
public class FilteredActivateProcess implements WorkflowProcess {
    
    private static final Logger LOG = LoggerFactory.getLogger(FilteredActivateProcess.class);
    
    @Property(value = "preview_publish")
    private static final String PREVIEW_AGENT_ID = "preview_agent_id";
    
    @Reference
    private Replicator replicator;
    
    private String previewAgentId;
    
    protected void activate(ComponentContext ctx) {
        
        previewAgentId = StringUtils.defaultString((String) ctx.getProperties().get(PREVIEW_AGENT_ID));
    }

    public void execute(WorkItem wi, WorkflowSession ws, MetaDataMap mdm) throws WorkflowException {
        
        LOG.info("Entering filtering action workflow process.");
 
        String path = (String) wi.getWorkflowData().getPayload();
        if (StringUtils.isBlank(path)) {
            LOG.error("No payload.");
        } else {
            ReplicationOptions opts = new ReplicationOptions();
            opts.setSuppressStatusUpdate(true);
            opts.setSuppressVersions(true);            
            opts.setFilter(new AgentIdFilter(previewAgentId));            
            try {
                replicator.replicate(ws.adaptTo(Session.class), 
                        ReplicationActionType.ACTIVATE, path, opts);
            } catch (ReplicationException e) {
                LOG.error("Error during filtered replication.", e);
            }
        }
    }
  
    
    
}
