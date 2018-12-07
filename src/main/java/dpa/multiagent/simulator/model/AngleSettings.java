package dpa.multiagent.simulator.model;

class AngleSettings {
    ANGLE_SAMPLING_STRATEGY angleSamplingStrategy;
    double maxAnglePerFrame;
    double minAnglePerFrame;

    public AngleSettings(String angleSamplingStrategy, double maxAnglePerFrame, double minAnglePerFrame) {
        this.angleSamplingStrategy = ANGLE_SAMPLING_STRATEGY.valueOf(angleSamplingStrategy.toUpperCase());
        this.maxAnglePerFrame = maxAnglePerFrame;
        this.minAnglePerFrame = minAnglePerFrame;
    }

    enum ANGLE_SAMPLING_STRATEGY {
        LINEAR("linear"),
        RANDOM("random");
        String name;

        ANGLE_SAMPLING_STRATEGY(String name) {
            this.name = name;
        }
    }
}
