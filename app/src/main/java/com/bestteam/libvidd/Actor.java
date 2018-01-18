package com.bestteam.libvidd;

/**
 * Created by Arkadi on 16/01/2018.
 */

public class Actor
{
    private String ActorId;
    private String ActorName;
    private int ActorRating;

    public Actor() {
    }

    public Actor(String ActorId, String ActorName, int ActorRating) {
        ActorId = ActorId;
        this.ActorName = ActorName;
        this.ActorRating = ActorRating;
    }

    public String getActorId() {
        return ActorId;
    }

    public String getActorName() {
        return ActorName;
    }

    public int getActorRating() {
        return ActorRating;
    }
}
