package gamelogic;

import gameobjects.*;

public interface Visitor {
    void visit(Ball ball);

    void visit(Block block);

    void visit(Paddle paddle);

    void visit(ScoreIndicator scoreIndicator);

    void visit(LivesIndicator livesIndicator);

    void visit(LevelIndicator levelIndicator);

    void visit(ImageBackground imageBackground);

    void visit(ColourBackground colourBackground);
}
