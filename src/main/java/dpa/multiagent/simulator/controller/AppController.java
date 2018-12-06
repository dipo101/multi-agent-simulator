package dpa.multiagent.simulator.controller;

import dpa.multiagent.simulator.model.SimulatorSettings;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class AppController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(SimulatorSettings settings) {
        // set simulator settings based on `settings` object
        // run the simulator
        // return the next coord

        return settings.toString();
    }

}