/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wmd.service;

/**
 * Example for an external service.
 * @author Matthias Wermund, Acquity Group part of Accenture
 */
public interface SearchService {
    
    void index(String path);
    
    void drop(String path);
    
}
