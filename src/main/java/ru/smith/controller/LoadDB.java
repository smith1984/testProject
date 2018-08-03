package ru.smith.controller;

import ru.smith.util.LoadDBFromCSV;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class LoadDB {

    public String load() {

        ClassLoader classLoader = getClass().getClassLoader();
        String path = classLoader.getResource("test_case.csv").getPath();
        //LoadDBFromCSV.load(path);

        return "/pages/main?faces-redirect=true";
    }
}
