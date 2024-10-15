package com.example.usingrecyclerview;
import java.io.Serializable;


public class Nation implements Serializable{
    private String name;
    private String capital;
    private int flagResource;
    private int population;
    private int area;
    private int density;
    private double worldShare;


    public Nation(String name, String capital, int flagResource, int population, int area, int density, double worldShare) {
        this.name = name;
        this.capital = capital;
        this.flagResource = flagResource; // Kiểu int
        this.population = population;
        this.area = area;
        this.density = density;
        this.worldShare = worldShare;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getDensity() {
        return density;
    }

    public void setDensity(int density) {
        this.density = density;
    }

    public int getFlagResource() {
        return flagResource;
    }

    public void setFlagResource(int flagResource) {
        this.flagResource = flagResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getWorldShare() {
        return worldShare;
    }

    public void setWorldShare(double worldShare) {
        this.worldShare = worldShare;
    }

    public String toString() {
        return "Nation: " + name + "\n" +
                "Capital: " + capital + "\n" +
                "Population: " + population + "\n" +
                "Area: " + area + "\n" +
                "Density: " + density + "\n" +
                "World Share: " + worldShare + "%";
    }
}

