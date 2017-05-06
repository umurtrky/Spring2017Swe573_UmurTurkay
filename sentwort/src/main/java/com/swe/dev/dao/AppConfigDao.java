package com.swe.dev.dao;

import java.util.List;

import com.swe.dev.model.AppConfig;
 
 
public interface AppConfigDao {
     
    public List<AppConfig> getConfig();
    public AppConfig findByAppname(String appname);
    public void updateSinceId(AppConfig config, String sinceid);
    //public void updateMaxId(AppConfig config, Integer maxid);
 
}