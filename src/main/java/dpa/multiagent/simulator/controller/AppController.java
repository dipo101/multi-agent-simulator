package dpa.multiagent.simulator.controller;

import dpa.multiagent.simulator.model.Simulator;
import dpa.multiagent.simulator.model.SimulatorSettings;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.awt.geom.Point2D;

@Controller
public class AppController {

    private Simulator sm;

    @MessageMapping("/settings")
    @SendTo("/topic/simulate")
    public String greeting(SimulatorSettings settings) {
        // set simulator settings based on `settings` object
        // run the simulator
        // return the next coord
        this.sm = new Simulator(settings);
        return settings.toString();
    }


    @MessageMapping("/next-coords")
    @SendTo("/topic/new-coords")
    public Point2D getNext() {

        return new Point2D.Double(1.0, 1.0);
    }
}