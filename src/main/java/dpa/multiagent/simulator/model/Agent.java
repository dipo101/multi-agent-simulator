package dpa.multiagent.simulator.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.ArrayList;

public class Agent {

    private String name;
    private List<Point2D> path;

    public void updatePos(Point2D pt) {
        path.add(pt);
    }

    public Point2D getLatestPos() throws Exception {
        if (path.size() > 1) {
            return path.get(path.size() - 1);
        } else {
            throw new Exception();
        }
    }

    public Point2D getNextPos(double angle, int speedMPH, int FPS) {
        Point2D pt = new Point2D.Double();
        double x, y;
        double dist;
        int speedMPS = speedMPH / (60 * 60);

        // dist = speed * time
        // update is called once per frame

        dist = (1 / FPS) * speedMPS;

        x = Math.sin(angle) * dist;
        y = Math.cos(angle) * dist;

        pt.setLocation(x, y);

        return pt;
    }

    public Agent() {
        name = "";
        path = new ArrayList<>();
        path.add(new Point2D.Double(0, 0));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
