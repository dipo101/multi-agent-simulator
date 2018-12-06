package dpa.multiagent.simulator.model;

class SpeedSettings {
    int maxSpeedPerFrame;
    int minSpeedPerFrame;
    SPEED_SAMPLING_STRATEGY speedSamplingStrategy;

    public SpeedSettings(int maxSpeedPerFrame, int minSpeedPerFrame, String speedSamplingStrategy) {
        this.maxSpeedPerFrame = maxSpeedPerFrame;
        this.minSpeedPerFrame = minSpeedPerFrame;
        this.speedSamplingStrategy = SPEED_SAMPLING_STRATEGY.valueOf(speedSamplingStrategy.toUpperCase());
    }

    enum SPEED_SAMPLING_STRATEGY {
        LINEAR("linear");
        String name;

        SPEED_SAMPLING_STRATEGY(String name) {
            this.name = name;
        }
    }
}
