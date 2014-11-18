package com.sapient.global.java;

public class PointOne {
    private Integer x;
    private Integer y;
 
    public PointOne(Integer x, Integer y) {
    	this.x = x;
    	this.y = y;
    }
    
    public PointOne(PointOne point){
        this.x = point.x;
        this.y = point.y;
    }
}
