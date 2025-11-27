package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConfigurationFile {
    private int min;
        private int max;
        private int attempts;
    public ConfigurationFile(){
        try(final InputStream in = getClass().getClassLoader().getResourceAsStream("config.yml")){
                if( in == null){
                    throw new IllegalStateException("config.yml not found");
                }
                final BufferedReader r = new BufferedReader(new InputStreamReader(in));
                String line;
                while((line = r.readLine())!=null){
                    line=line.trim();
                    final String[] arrayString = line.split(":");
                    if(arrayString.length == 2){
                        String key = arrayString[0].trim();
                        String value = arrayString[1].trim();
                        if(key.equals("minimum")){
                            min = Integer.parseInt(value);
                        } else if(key.equals("maximum")){
                            max = Integer.parseInt(value);
                        } else if (key.equals("attempts")) {
                            attempts = Integer.parseInt(value);
                        }
                    }
                }
        } catch (IOException e){
            throw new IllegalStateException("Unable to load config.yml", e);
        }
    }
    public int getMin() {
        return min;
    }
    public int getMax() {
        return max;
    }
    public int getAttempts() {
        return attempts;
    }
}

