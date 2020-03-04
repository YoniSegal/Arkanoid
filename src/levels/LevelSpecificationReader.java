package levels;

import gamelogic.ColorsParser;
import gamelogic.Velocity;
import gameobjects.Background;
import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.ColourBackground;
import gameobjects.ImageBackground;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class reads level definitions from a file.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class LevelSpecificationReader {
    private boolean isXChecked = false;
    private boolean isYChecked = false;
    private boolean isBlockSymbolsChecked = false;
    private String tempLine;
    private List<String> symbols = new ArrayList<>();
    private static final int RECT_WIDTH = 79;
    private static final int MAX_WIDTH = 800 - 20;
    private static final int MAX_HEIGHT = 600;

    /**
     * Blank constructor.
     */
    public LevelSpecificationReader() {
    }

    /**
     * Method returns a list of levelInformations after reading from a file.
     *
     * @param reader java.io.Reader.
     * @return List.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<LevelInformation> levelInformations = new ArrayList<>();
        try {
            Level level = new Level();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                while ((line = bufferedReader.readLine()) != "END_LEVEL") {
                    if (line.contains("START_BLOCKS")) {
                        while ((line = bufferedReader.readLine()) != "END_BLOCKS") {
                            if (line.contains("END_BLOCKS")) {
                                break;
                            }
                            this.symbols.add(line);
                        }
                        this.isBlockSymbolsChecked = true;
                    }
                    //Check for defaults.
                    checkLine(line, level);
                    if (line.contains("END_LEVEL")) {
                        break;
                    }
                }
                levelInformations.add(level);
                this.symbols = new ArrayList<>();
                level = new Level();
            }
            return levelInformations;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Method reads ints form a line.
     *
     * @param s String.
     * @return int.
     */
    public int readInt(String s) {
        String[] parts = s.split(":|\n");
        String num = parts[1];
        return Integer.parseInt(num);
    }

    /**
     * Method reads a Background from a string and returns it.
     *
     * @param s String.
     * @return Background.
     */
    public Background readBackground(String s) {
        if (s.contains("color")) {
            ColorsParser colorsParser = new ColorsParser();
            Color color = colorsParser.colorFromString(s);
            return new ColourBackground(color);
        } else if (s.contains("image")) {
            String[] parts = s.split("\\(|\\)");
            File file = new File(parts[1]);
            return new ImageBackground(parts[1]);
        }
        return null;
    }

    /**
     * Method checks if a line contains particular information to set it.
     *
     * @param line  String.
     * @param level Level.
     */
    public void checkLine(String line, Level level) {
        if (line.contains("# Level")) {
            level.setLevelNumber(readLevelNumber(line));
        }
        if (line.contains("level_name")) {
            level.setLevelname(readLevelName(line));
        }
        if (line.contains("ball_velocities")) {
            List<Velocity> ballVelocities = readVelocities(line);
            level.setBalls(readBalls(ballVelocities));
            level.setNumOfBalls(level.getBalls().size());
        }
        if (line.contains("background")) {
            level.setBackground(readBackground(line));
        }
        if (line.contains("paddle_speed")) {
            level.setPaddleSpeed(readInt(line));
        }
        if (line.contains("paddle_width")) {
            level.setPaddleWidth(readInt(line));
        }
        if (line.contains("block_definitions") || (isBlockSymbolsChecked && isXChecked && isYChecked)) {
            if (isBlockSymbolsChecked && isXChecked && isYChecked) {
                BlocksFromSymbolsFactory bfsf = getFactory(this.tempLine);
                level.setBlocks((readBlocks(symbols, bfsf, level.getStartX(), level.getStartY(), level.rowHeight())));
                isYChecked = false;
                isXChecked = false;
                isBlockSymbolsChecked = false;
            } else {
                this.tempLine = line;
            }
        }
        if (line.contains("blocks_start_x")) {
            this.isXChecked = true;
            level.setStartX(readInt(line));
        }
        if (line.contains("blocks_start_y")) {
            this.isYChecked = true;
            level.setStartY(readInt(line));
        }
        if (line.contains("row_height")) {
            level.setRowHeight(readInt(line));
        }
        if (line.contains("num_blocks")) {
            level.setBlocksToRemove(readInt(line));
        }
    }

    /**
     * Method returns BlocksFromSymbolsFactory from a line.
     *
     * @param line String.
     * @return BlocksFromSymbolsFactory.
     */
    public BlocksFromSymbolsFactory getFactory(String line) {
        String[] parts = line.split(":|\n");
        String filePath = parts[1];
        BlocksDefinitionReader blocksDefinitionReader = new BlocksDefinitionReader();
        try {

            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            Reader reader = inputStreamReader;
            BlocksFromSymbolsFactory blocksFromSymbolsFactory = blocksDefinitionReader.fromReader(reader);
            return blocksFromSymbolsFactory;

        } catch (Exception e) {
            System.out.println("File not found");
        }
        return null;
    }

    /**
     * Method returns a list of blocks.
     *
     * @param symbolsFromFile list.
     * @param blocksFactory   BlocksFromSymbolsFactory.
     * @param xpos            int.
     * @param ypos            int.
     * @param rowHeight       int.
     * @return List of blocks.
     */
    public static List<Block> readBlocks(List<String> symbolsFromFile, BlocksFromSymbolsFactory blocksFactory,
                                         int xpos, int ypos, int rowHeight) {
        List<Block> blocks = new ArrayList<>();
        int width = 0;
        int height = 0;
        for (int i = 0; i < symbolsFromFile.size(); i++) {
            for (int j = 0; j < symbolsFromFile.get(i).length(); j++) {
                String singleChar = symbolsFromFile.get(i).substring(j, j + 1);
                if (blocksFactory.isSpaceSymbol(singleChar)) {
                    width += blocksFactory.getSpaceWidth(singleChar);
                } else if (blocksFactory.isBlockSymbol(singleChar)) {
                    Block block = blocksFactory.getBlock(singleChar, xpos + width, ypos + height);
                    blocks.add(block);
                    width += blocksFactory.getBlockWidth(singleChar);
                }
            }
            width = 0;
            height += rowHeight;
        }
        return blocks;
    }

    /**
     * Method returns a level number as an int.
     *
     * @param line String.
     * @return int.
     */
    public int readLevelNumber(String line) {
        String[] parts = line.split("# Level ");
        return Integer.parseInt(parts[1]);
    }

    /**
     * Method reads the name of a level.
     *
     * @param line String.
     * @return String.
     */
    public String readLevelName(String line) {
        String[] parts = line.split(":");
        return parts[1];
    }

    /**
     * Method returns a list of velocities from a string.
     *
     * @param line String.
     * @return List.
     */
    public List<Velocity> readVelocities(String line) {
        List<Velocity> velocities = new ArrayList<>();
        String[] parts = line.split(":");
        String[] pairs = parts[1].split(" ");
        for (int i = 0; i < pairs.length; i++) {
            String[] parameters = pairs[i].split(",");
            double speed = Double.parseDouble(parameters[0]);
            double angle = Double.parseDouble(parameters[1]);
            velocities.add(Velocity.fromAngleAndSpeed(speed, angle));
        }
        return velocities;
    }

    /**
     * Method returns a List of balls.
     *
     * @param velocities List of velocities.
     * @return List of balls.
     */
    public List<Ball> readBalls(List<Velocity> velocities) {
        List<Ball> balls = new ArrayList<>();
        for (int i = 0; i < velocities.size(); i++) {
            Ball ball = new Ball(MAX_WIDTH / 2, MAX_HEIGHT - RECT_WIDTH, 5, Color.WHITE);
            ball.setVelocity(velocities.get(i));
            balls.add(ball);
        }
        return balls;
    }
}

