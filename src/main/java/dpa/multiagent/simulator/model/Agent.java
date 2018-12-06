package dpa.multiagent.simulator.model;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Agent {

    private String name;
    private List<Point> path;

    public Agent()
    {
        name = "";
        path = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
