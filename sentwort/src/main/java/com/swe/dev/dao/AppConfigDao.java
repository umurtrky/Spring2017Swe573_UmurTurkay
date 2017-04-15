package com.swe.dev.dao;

import java.util.List;

import com.swe.dev.model.AppConfig;
 
 
public interface AppConfigDao {
     
    public List<AppConfig> getConfig();
    public AppConfig findByAppname(String appname);
 
}