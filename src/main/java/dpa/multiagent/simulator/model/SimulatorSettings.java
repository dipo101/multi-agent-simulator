package dpa.multiagent.simulator.model;

public class SimulatorSettings {
    int numAgents;
    static final int FRAMES_PER_SECOND = 20;
    AngleSettings.ANGLE_SAMPLING_STRATEGY angleSamplingStrategy;
    double maxAnglePerFrame;
    double minAnglePerFrame;
    int maxSpeedPerFrame;
    int minSpeedPerFrame;
    AngleSettings angleSettings;

    public AngleSettings getAngleSettings() {
        return angleSettings;
    }

    public void setAngleSettings(AngleSettings angleSettings) {
        this.angleSettings = angleSettings;
    }

    public SpeedSettings getSpeedSettings() {
        return speedSettings;
    }

    public void setSpeedSettings(SpeedSettings speedSettings) {
        this.speedSettings = speedSettings;
    }

    SpeedSettings speedSettings;


    public int getNumAgents() {
        return numAgents;
    }

    public void setNumAgents(int numAgents) {
        this.numAgents = numAgents;
    }

    public static int getFramesPerSecond() {
        return FRAMES_PER_SECOND;
    }

    public AngleSettings.ANGLE_SAMPLING_STRATEGY getAngleSamplingStrategy() {
        return angleSamplingStrategy;
    }

    public void setAngleSamplingStrategy(AngleSettings.ANGLE_SAMPLING_STRATEGY angleSamplingStrategy) {
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

    public SpeedSettings.SPEED_SAMPLING_STRATEGY getSpeedSamplingStrategy() {
        return speedSamplingStrategy;
    }

    public void setSpeedSamplingStrategy(SpeedSettings.SPEED_SAMPLING_STRATEGY speedSamplingStrategy) {
        this.speedSamplingStrategy = speedSamplingStrategy;
    }

    SpeedSettings.SPEED_SAMPLING_STRATEGY speedSamplingStrategy;


    SimulatorSettings(int numAgents, AngleSettings angleSettings, SpeedSettings speedSettings) {
        this.numAgents = numAgents;
        this.angleSamplingStrategy = angleSettings.angleSamplingStrategy;
        this.minAnglePerFrame = angleSettings.minAnglePerFrame;
        this.maxAnglePerFrame = angleSettings.maxAnglePerFrame;
        this.maxSpeedPerFrame = speedSettings.maxSpeedPerFrame;
        this.minSpeedPerFrame = speedSettings.minSpeedPerFrame;
        this.speedSamplingStrategy = speedSettings.speedSamplingStrategy;
        this.speedSettings = speedSettings;
        this.angleSettings = angleSettings;
    }


}
