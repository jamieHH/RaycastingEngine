package com.jamie.raycasting.entities;

public class AreaAlertEntity extends Entity
{
    public String message;


    public AreaAlertEntity(String message) {
        radius = 1;
        isSolid = false;

        this.message = message;
    }
}
