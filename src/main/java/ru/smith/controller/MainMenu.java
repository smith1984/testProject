package ru.smith.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class MainMenu {

    public String recordsWithTSMoreX(){

        return "/pages/recordswithtsmorex?faces-redirect=true";

    }

    public String stopUseForm(){

        return "/pages/stopuseform?faces-redirect=true";

    }

    public String ratingForms(){

        return "/pages/ratingforms?faces-redirect=true";

    }
}
