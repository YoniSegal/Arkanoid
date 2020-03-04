package gameobjects;

import biuoop.DrawSurface;
import gamelogic.SpriteVisitor;
import gamelogic.Visitable;

import java.util.ArrayList;
import java.util.List;

public class VisitableCollection {
    private List<Visitable> visitables;

    public VisitableCollection() {
        this.visitables = new ArrayList<>();
    }

    public List<Visitable> getVisitables() {
        return visitables;
    }

    public void addVisitable(Visitable visitable) {
        this.visitables.add(visitable);
    }

    public void visitAll(DrawSurface d) {
        SpriteVisitor spriteVisitor = new SpriteVisitor(d);
        for (Visitable visitable : visitables) {
            visitable.accept(spriteVisitor);
        }
    }

    /**
     * Method calls timePassed() on all sprites.
     *
     * @param dt change in time.
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < visitables.size(); i++) {
            //Inform all Sprites that time has passed.
            visitables.get(i).timePassed(dt);
        }
    }
}
