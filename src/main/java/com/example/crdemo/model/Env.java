package com.example.crdemo.model;

import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

public class Env {
    private String appMode;
    private String[] valuesArray;
    private Map<String, Integer> valuesMap;

    public String[] getValuesArray() {
        return valuesArray;
    }

    public void setValuesArray(String[] valuesArray) {
        this.valuesArray = valuesArray;
    }

    public Map<String, Integer> getValuesMap() {
        return valuesMap;
    }

    public void setValuesMap(Map<String, Integer> valuesMap) {
        this.valuesMap = valuesMap;
    }

    public String getAppMode() {
        return appMode;
    }

    public void setAppMode(String appMode) {
        this.appMode = appMode;
    }
}
