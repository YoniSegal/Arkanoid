package gamelogic;

import levels.GameLevel;

public abstract class CounterHitListener implements HitListener {
    protected Counter counter;
    protected GameLevel gameLevel;

    public CounterHitListener(Counter counter, GameLevel gameLevel) {
        this.counter = counter;
        this.gameLevel = gameLevel;
    }
}
