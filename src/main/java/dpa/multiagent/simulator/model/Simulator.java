package dpa.multiagent.simulator.model;

import javafx.util.Pair;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.List;

public class Simulator {
    List<Agent> agents;
    SimulatorSettings settings;

    public boolean checkCollision(Point2D startPosA, Point2D startPosB, Point2D endPosA, Point2D endPosB)
            throws Exception {
        if (!Line2D.linesIntersect(startPosA.getX(), startPosA.getY(), endPosA.getX(), endPosA.getY(),
                startPosB.getX(), startPosB.getY(), endPosB.getX(), endPosB.getY())) {
            return false;
        }
        Line lineA = new Line(startPosA.getX(), startPosA.getY(), endPosA.getX(), endPosA.getY());
        Line lineB = new Line(startPosB.getX(), startPosB.getY(), endPosB.getX(), endPosB.getY());

        if (lineA.equals(lineB)) {
            return true;
        }
        Point2D intersectionPoint = lineA.intersect(lineB);

        double aDistance = getDistance(intersectionPoint, startPosA);
        double bDistance = getDistance(intersectionPoint, startPosB);


        return (aDistance / getSpeed()) == (bDistance / getSpeed());
    }

    private double getDistance(Point2D posA, Point2D posB) {
        return Math.hypot(posA.getX() - posB.getX(), posA.getY() - posB.getY());
    }

    public void update() throws Exception {
        HashMap<Agent, Pair> agentPairPositionMap = new HashMap<>();
        for (Agent agent : agents) {
            Point2D nextPosition = agent.getNextPos(getAngle(), getSpeed(), SimulatorSettings.getFramesPerSecond());
            Point2D currentPosition = agent.getLatestPos();
            Pair<Point2D, Point2D> positions = new Pair<>(currentPosition, nextPosition);
            agentPairPositionMap.put(agent, positions);
        }

        for (Agent agent : agentPairPositionMap.keySet()) {
            for (Agent agent2 : agentPairPositionMap.keySet()) {
                Point2D startPosA = (Point2D) agentPairPositionMap.get(agent).getKey();
                Point2D startPosB = (Point2D) agentPairPositionMap.get(agent2).getKey();
                Point2D endPosA = (Point2D) agentPairPositionMap.get(agent).getKey();
                Point2D endPosB = (Point2D) agentPairPositionMap.get(agent2).getValue();
                if (!agent.equals(agent2)) {
                    if (checkCollision(startPosA, startPosB, endPosA, endPosB)) {
                        return;
                    }
                }
            }
        }

        for (Agent agent : agentPairPositionMap.keySet()) {
            agent.updatePos((Point2D) ((agentPairPositionMap.get(agent)).getValue()));
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
        int ret = 1;

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

    class Line {
        double m, c;
        double x1, y1, x2, y2;

        Line(double x1, double y1, double x2, double y2) {
            this.m = x2 == x1 ? Integer.MAX_VALUE : (y2 - y1) / (x2 - x1);
            this.c = y1 - (this.m * x1);
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }

        double getYIntercept() {
            return c;
        }

        double getGradient() {
            return m;
        }

        public Point2D intersect(Line line) {
            if (this.m == line.m) {

            }
            double x = (line.c - this.c) / (this.m - line.m);
            double y = this.m * x + this.c;

            return new Point2D.Double(x, y);
        }


        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Line)) {
                return false;
            }
            Line o = (Line) obj;
            return o.m == m && o.c == c;
        }
    }
}
