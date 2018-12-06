package dpa.multiagent.simulator.model;

import java.util.List;

public class Simulator {
    List<Agent> agents;
    SimulatorSettings settings;

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
