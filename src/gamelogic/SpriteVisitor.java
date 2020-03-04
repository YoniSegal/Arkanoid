package gamelogic;

import biuoop.DrawSurface;
import gameobjects.*;

import java.awt.*;

public class SpriteVisitor implements Visitor {
    private DrawSurface surface;

    public SpriteVisitor(DrawSurface drawSurface) {
        this.surface = drawSurface;
    }

    @Override
    public void visit(Block block) {
        //Fill circle.
        int upperleftX = (int) block.getCollisionRectangle().getUpperLeft().getX();
        int upperleftY = (int) block.getCollisionRectangle().getUpperLeft().getY();
        int width = (int) block.getCollisionRectangle().getWidth();
        int height = (int) block.getCollisionRectangle().getHeight();
        //Check if colour has been initialised.
        if (block.getColour() != null) {
            surface.setColor(block.getColour());
        }
        int hits = block.getHits().getValue();

        if (block.getFill() != null && block.getFill().get(hits) != null) {
            if (block.getFill().get(hits).getColour() != null) {
                surface.setColor((block.getFill().get(hits).getColour()));
                surface.fillRectangle(upperleftX, upperleftY, width, height);
            } else {
                surface.drawImage(upperleftX, upperleftY, block.getFill().get(hits).getImage());
            }
        }

        if (block.getStroke() != null) {
            surface.setColor(block.getStroke());
            surface.drawRectangle(upperleftX, upperleftY, width, height);
        }
    }

    @Override
    public void visit(Paddle paddle) {
        int upperleftX = (int) paddle.getCollisionRectangle().getUpperLeft().getX();
        int upperleftY = (int) paddle.getCollisionRectangle().getUpperLeft().getY();
        int width = (int) paddle.getCollisionRectangle().getWidth();
        int height = (int) paddle.getCollisionRectangle().getHeight();
        //Fill rectangle.
        surface.setColor(Color.YELLOW);
        surface.fillRectangle(upperleftX, upperleftY, width, height);
    }

    @Override
    public void visit(ScoreIndicator scoreIndicator) {
        int upperleftX = (int) scoreIndicator.getUpperBlock().getCollisionRectangle().getUpperLeft().getX();
        int upperleftY = (int) scoreIndicator.getUpperBlock().getCollisionRectangle().getUpperLeft().getY();
        int width = (int) scoreIndicator.getUpperBlock().getCollisionRectangle().getWidth();
        int height = (int) scoreIndicator.getUpperBlock().getCollisionRectangle().getHeight();
        //Check if colour has been initialised.
        if (scoreIndicator.getUpperBlock().getColour() == null) {
            surface.setColor(Color.LIGHT_GRAY);
        } else {
            surface.setColor(scoreIndicator.getUpperBlock().getColour());
        }
        //Draw rectangles.
        surface.fillRectangle(upperleftX, upperleftY, width, height);
        surface.setColor(Color.BLACK);
        surface.drawRectangle(upperleftX, upperleftY, width, height);
        //Draw score.
        String hit = "Score: " + Integer.toString(scoreIndicator.getScore().getValue());
        int x = (int) scoreIndicator.getUpperBlock().getCollisionRectangle().getTop().middle().getX() - 30;
        int y = (int) scoreIndicator.getUpperBlock().getCollisionRectangle().getLeft().middle().getY() + 10;
        surface.setColor(Color.BLACK);
        surface.drawText(x, y, hit, 20);

    }

    @Override
    public void visit(LivesIndicator livesIndicator) {
        //Draw score.
        String hit = "Lives: " + Integer.toString(livesIndicator.getLives().getValue());
        int x = (int) livesIndicator.getUpperBlock().getCollisionRectangle().getLeft().middle().getX();
        int y = (int) livesIndicator.getUpperBlock().getCollisionRectangle().getLeft().middle().getY() + 10;
        surface.setColor(Color.BLACK);
        surface.drawText(x, y, hit, 20);
    }

    @Override
    public void visit(LevelIndicator levelIndicator) {
        //Draw level.
        String hit = "Level: " + levelIndicator.getLevelInformation().levelName();
        int x = (int) levelIndicator.getUpperBlock().getCollisionRectangle().getRight().middle().getX() - 180;
        int y = (int) levelIndicator.getUpperBlock().getCollisionRectangle().getRight().middle().getY() + 10;
        surface.setColor(Color.BLACK);
        surface.drawText(x, y, hit, 20);
    }

    @Override
    public void visit(ImageBackground imageBackground) {
        // Draw the image on a DrawSurface
        surface.drawImage(10, 20, imageBackground.getImage()); // draw the image at location 10, 20.
    }

    @Override
    public void visit(ColourBackground colourBackground) {
        surface.setColor(colourBackground.getColour());
        surface.fillRectangle(ColourBackground.getBorderGap(), 3 * ColourBackground.getBorderGap(), ColourBackground.getMaxWidth(), ColourBackground.getMaxHeight());
    }


    @Override
    public void visit(Ball ball) {
        //Fill circle.
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) ball.getX(), (int) ball.getY(), ball.getR());
        surface.setColor(ball.getColor());
        surface.fillCircle((int) ball.getX(), (int) ball.getY(), ball.getR());
    }
}
