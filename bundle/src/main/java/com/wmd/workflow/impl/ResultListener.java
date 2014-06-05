/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmd.workflow.impl;

import com.day.cq.replication.Agent;
import com.day.cq.replication.ReplicationAction;
import com.day.cq.replication.ReplicationListener;
import com.day.cq.replication.ReplicationLog;
import com.day.cq.replication.ReplicationResult;

/**
 * Listener used for synchronous replication. Will capture the replication
 * result for a specific agent id to make it available for custom code.
 * @author Matthias Wermund, Acquity Group part of Accenture
 */
public class ResultListener implements ReplicationListener {
    
    private ReplicationResult result;
    
    private String agentId;
    
    public ResultListener(String agentId) {
        this.agentId = agentId;
    }

    public void onStart(Agent agent, ReplicationAction action) {
        //
    }

    public void onMessage(ReplicationLog.Level level, String string) {
        //
    }

    public void onEnd(Agent agent, ReplicationAction action, ReplicationResult rr) {
        if (agent.getId().equals(agentId)) {
            result = rr;
        }
    }

    public void onError(Agent agent, ReplicationAction action, Exception excptn) {
        //
    }
    
    public ReplicationResult getResult() {
        
        return result;
    }
    
}
