package gamelogic;

public interface Visitable {
    void accept(Visitor visitor);

    void timePassed(double dt);
}
