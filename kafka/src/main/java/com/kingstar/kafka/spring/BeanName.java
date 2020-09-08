package com.kingstar.kafka.spring;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.util.Properties;

/**
 * @author xiayc
 * @date 2020/9/7 20:22
 */
public class BeanName implements ResourceLoaderAware {

    private Properties settings;
    private ResourceLoader loader;
    private static final String SETTINGS_FILE = "classpath:/config/settings.properties";
    public Properties getSettings() {
        return settings;
    }
    public void setProperty(String key,String value){
        settings.setProperty(key, value);

    }
    public String getProperty(String key){
        return settings.getProperty(key);
    }

    public double getDouble(String key){
        return Double.parseDouble(settings.getProperty(key));
    }
    public int getInteger(String key){
        return Integer.parseInt(settings.getProperty(key));
    }
    public void save(){
        try {
            OutputStream outputStream = new FileOutputStream(loader.getResource(SETTINGS_FILE).getFile());
            settings.store(outputStream, "");
            outputStream.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void setResourceLoader(ResourceLoader loader) {
        this.loader = loader;
        try {
            FileInputStream inputStream  = new FileInputStream(loader.getResource(SETTINGS_FILE).getFile());
            settings = new Properties();
            settings.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
