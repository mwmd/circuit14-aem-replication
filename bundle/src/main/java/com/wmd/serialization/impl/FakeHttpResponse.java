/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wmd.serialization.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Dummy HTTP response used to capture the JSON rendering into a temporary file.
 * @author Matthias Wermund, Acquity Group part of Accenture
 */
public class FakeHttpResponse implements HttpServletResponse {
    
    private File outputFile;
    
    private FileOutputStream os;
    
    private ServletOutputStream sos;
    
    private PrintWriter writer;

    public FakeHttpResponse() {
        try {
            outputFile = File.createTempFile("aem-", ".json");
            os = new FileOutputStream(outputFile);
            sos = new ServletOutputStream() {
                 @Override
                 public void write(int b) throws IOException {
                     os.write(b);
                 }
            };
            writer = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // ...
        } catch (IOException e) {
            //
        }
    }    
    
    public File getOutputFile() {
        
        return outputFile;
    }

    @Override
    public String getCharacterEncoding() {
        
        return "UTF-8";
    }

    @Override
    public String getContentType() {        
        return "application/json";
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {        
        return sos;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return writer;
    }

    @Override
    public void setCharacterEncoding(String string) {

    }

    @Override
    public void setContentLength(int i) {
        
    }

    @Override
    public void setContentType(String string) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {
    }

    @Override
    public void resetBuffer() {
    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {
    }

    @Override
    public void setLocale(Locale locale) {
    }

    @Override
    public Locale getLocale() {
        return Locale.getDefault();
    }

    @Override
    public void addCookie(Cookie cookie) {        
    }

    @Override
    public boolean containsHeader(String string) {        
        return false;
    }

    @Override
    public String encodeURL(String string) {        
        return null;
    }

    @Override
    public String encodeRedirectURL(String string) {
        return null;
    }

    @Override
    public String encodeUrl(String string) {
        return null;
    }

    @Override
    public String encodeRedirectUrl(String string) {
        return null;
    }

    @Override
    public void sendError(int i, String string) throws IOException {
    }

    @Override
    public void sendError(int i) throws IOException {
    }

    @Override
    public void sendRedirect(String string) throws IOException {
    }

    @Override
    public void setDateHeader(String string, long l) {
    }

    @Override
    public void addDateHeader(String string, long l) {
    }

    @Override
    public void setHeader(String string, String string1) {
    }

    @Override
    public void addHeader(String string, String string1) {
    }

    @Override
    public void setIntHeader(String string, int i) {
    }

    @Override
    public void addIntHeader(String string, int i) {
    }

    @Override
    public void setStatus(int i) {
    }

    @Override
    public void setStatus(int i, String string) {
    }
    
    
    
}
