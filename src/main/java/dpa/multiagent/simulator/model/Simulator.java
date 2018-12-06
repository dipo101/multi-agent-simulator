package dpa.multiagent.simulator.model;

import java.awt.geom.Point2D;
import java.util.List;

public class Simulator {
    List<Agent> agents;
    SimulatorSettings settings;

    public boolean checkCollision(Agent caller, Point2D currPos, Point2D nextPos) throws Exception {
        Point2D currPosAgent2, nextPosAgent2;

        for (Agent a: agents)
        {
            if (a.equals(caller))
                continue;

            currPosAgent2 = a.getLatestPos();
            nextPosAgent2 = a.getNextPos(getAngle(), getSpeed());

            // Compare currPos currPosAgent2, etc to determine collision
        }

        return false;
    }

    public void update() throws Exception {
        Point2D pt;

        for (Agent a : agents) {
            pt = a.getNextPos(getAngle(), getSpeed());
            if(checkCollision(a, a.getLatestPos(), pt)) {
                a.updatePos(pt);
            }
        }
    }

    public double getAngle() {
        double ret = 0;

        switch (settings.getAngleSettings().angleSamplingStrategy) {
            case LINEAR:
                ret = (settings.minAnglePerFrame + settings.maxAnglePerFrame) / 2;
                break;
        }

        return ret;
    }

    public int getSpeed() {
        int ret = 0;

        switch (settings.getSpeedSettings().speedSamplingStrategy) {
            case LINEAR:
                ret = (settings.minSpeedPerFrame + settings.maxSpeedPerFrame) / 2;
                break;
        }

        return ret;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    public SimulatorSettings getSettings() {
        return settings;
    }

    public void setSettings(SimulatorSettings settings) {
        this.settings = settings;
    }

    public Simulator(SimulatorSettings settings, List<Agent> agents) {
        this.settings = settings;
        this.agents = agents;
    }
}
