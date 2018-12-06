package dpa.multiagent.simulator.controller;

import dpa.multiagent.simulator.model.Agent;
import dpa.multiagent.simulator.model.Simulator;
import dpa.multiagent.simulator.model.SimulatorSettings;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

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
    public Map<Agent, Point2D> getNext()  throws Exception{
        Map<Agent, Point2D> coords = new HashMap<>();
        if (sm.update()) {
            for (Agent agent : sm.getAgents()) {
                coords.put(agent, agent.getLatestPos());
            }
        }
        return coords;
    }
}