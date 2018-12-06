package dpa.multiagent.simulator.model;

public class SimulatorSettings {
    int numAgents;
    static final int FRAMES_PER_SECOND = 20;
    ANGLE_SAMPLING_STRATEGY angleSamplingStrategy;
    double maxAnglePerFrame;
    double minAnglePerFrame;
    int maxSpeedPerFrame;
    int minSpeedPerFrame;

    public int getNumAgents() {
        return numAgents;
    }

    public void setNumAgents(int numAgents) {
        this.numAgents = numAgents;
    }

    public static int getFramesPerSecond() {
        return FRAMES_PER_SECOND;
    }

    public ANGLE_SAMPLING_STRATEGY getAngleSamplingStrategy() {
        return angleSamplingStrategy;
    }

    public void setAngleSamplingStrategy(ANGLE_SAMPLING_STRATEGY angleSamplingStrategy) {
        this.angleSamplingStrategy = angleSamplingStrategy;
    }

    public double getMaxAnglePerFrame() {
        return maxAnglePerFrame;
    }

    public void setMaxAnglePerFrame(double maxAnglePerFrame) {
        this.maxAnglePerFrame = maxAnglePerFrame;
    }

    public double getMinAnglePerFrame() {
        return minAnglePerFrame;
    }

    public void setMinAnglePerFrame(double minAnglePerFrame) {
        this.minAnglePerFrame = minAnglePerFrame;
    }

    public int getMaxSpeedPerFrame() {
        return maxSpeedPerFrame;
    }

    public void setMaxSpeedPerFrame(int maxSpeedPerFrame) {
        this.maxSpeedPerFrame = maxSpeedPerFrame;
    }

    public int getMinSpeedPerFrame() {
        return minSpeedPerFrame;
    }

    public void setMinSpeedPerFrame(int minSpeedPerFrame) {
        this.minSpeedPerFrame = minSpeedPerFrame;
    }

    public SPEED_SAMPLING_STRATEGY getSpeedSamplingStrategy() {
        return speedSamplingStrategy;
    }

    public void setSpeedSamplingStrategy(SPEED_SAMPLING_STRATEGY speedSamplingStrategy) {
        this.speedSamplingStrategy = speedSamplingStrategy;
    }

    SPEED_SAMPLING_STRATEGY speedSamplingStrategy;


    SimulatorSettings(int numAgents, AngleSettings angleSettings, SpeedSettings speedSettings) {
        this.numAgents = numAgents;
        this.angleSamplingStrategy = angleSettings.angleSamplingStrategy;
        this.minAnglePerFrame = angleSettings.minAnglePerFrame;
        this.maxAnglePerFrame = angleSettings.maxAnglePerFrame;
        this.maxSpeedPerFrame = speedSettings.maxSpeedPerFrame;
        this.minSpeedPerFrame = speedSettings.minSpeedPerFrame;
        this.speedSamplingStrategy = speedSettings.speedSamplingStrategy;
    }

    class AngleSettings {
        ANGLE_SAMPLING_STRATEGY angleSamplingStrategy;
        double maxAnglePerFrame;
        double minAnglePerFrame;

        public AngleSettings(ANGLE_SAMPLING_STRATEGY angleSamplingStrategy, double maxAnglePerFrame, double minAnglePerFrame) {
            this.angleSamplingStrategy = angleSamplingStrategy;
            this.maxAnglePerFrame = maxAnglePerFrame;
            this.minAnglePerFrame = minAnglePerFrame;
        }
    }

    class SpeedSettings {
        int maxSpeedPerFrame;
        int minSpeedPerFrame;
        SPEED_SAMPLING_STRATEGY speedSamplingStrategy;

        public SpeedSettings(int maxSpeedPerFrame, int minSpeedPerFrame, SPEED_SAMPLING_STRATEGY speedSamplingStrategy) {
            this.maxSpeedPerFrame = maxSpeedPerFrame;
            this.minSpeedPerFrame = minSpeedPerFrame;
            this.speedSamplingStrategy = speedSamplingStrategy;
        }
    }


    enum ANGLE_SAMPLING_STRATEGY {
        LINEAR("linear");
        String name;

        ANGLE_SAMPLING_STRATEGY(String name) {
            this.name = name;
        }
    }

    enum SPEED_SAMPLING_STRATEGY {
        LINEAR("linear");
        String name;

        SPEED_SAMPLING_STRATEGY(String name) {
            this.name = name;
        }
    }

}
