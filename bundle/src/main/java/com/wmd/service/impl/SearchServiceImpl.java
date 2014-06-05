/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wmd.service.impl;

import com.wmd.service.SearchService;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dummy service implementation. Only logs invocations.
 * @author Matthias Wermund, Acquity Group part of Accenture
 */
@Component
@Service
public class SearchServiceImpl implements SearchService {
    
    private static final Logger LOG = LoggerFactory.getLogger(SearchServiceImpl.class);

    public void index(String path) {
        LOG.info("Reindexing {}", path);
    }
    
    public void drop(String path) {
        LOG.info("Dropping {}", path);
    }
    
}
