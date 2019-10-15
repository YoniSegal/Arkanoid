package gamelogic;

import gameobjects.Background;
import gameobjects.ColourBackground;
import gameobjects.ImageBackground;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class reads block definitions from a file.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class BlocksDefinitionReader {
    /**
     * Blank constructor.
     */
    public BlocksDefinitionReader() {
    }

    /**
     * Method returns a BlocksFromSymbolsFactory after reading from a file.
     *
     * @param reader java.io.Reader.
     * @return BlocksFromSymbolsFactory.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BufferedReader bufferedReader = new BufferedReader(reader);
        Map<String, BlockMaker> blockCreators = new HashMap<>();
        Map<String, Integer> spacerWidths = new HashMap<>();

        try {
            String line;
            BlockMaker blockMaker = new BlockMaker();
            while (((line = bufferedReader.readLine()) != null)) {
                if (line.contains("default")) {
                    blockMaker = new BlockMaker();
                    line = bufferedReader.readLine();
                    blockMaker = defaultBlock(line, blockMaker);
                }
                if (line.contains("bdef")) {
                    blockMaker = new BlockMaker();
                    blockMaker = definedBlock(line, blockMaker);
                    checkBlockMaker(blockMaker, blockCreators.get("default"));
                }
                if (line.contains("sdef")) {
                    spacerWidths.putAll(getSpacerDefs(line));
                }
                if (line.contains("default") || line.contains("bdef") || line.contains("sdef")) {
                    blockCreators.put(blockMaker.getSymbol(), blockMaker);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = new BlocksFromSymbolsFactory();
        blocksFromSymbolsFactory.setBlockCreators(blockCreators);
        blocksFromSymbolsFactory.setSpacerWidths(spacerWidths);
        return blocksFromSymbolsFactory;
    }

    /**
     * Method fills in default values.
     *
     * @param blockMaker        current blockmaker.
     * @param defaultBlockMaker default blockmaker.
     */
    public static void checkBlockMaker(BlockMaker blockMaker, BlockMaker defaultBlockMaker) {
        if (blockMaker.getWidth() == 0) {
            blockMaker.setWidth(defaultBlockMaker.getWidth());
        }
        if (blockMaker.getHeight() == 0) {
            blockMaker.setHeight(defaultBlockMaker.getHeight());
        }
        if (blockMaker.getHitPoints() == 0) {
            blockMaker.setHitPoints(defaultBlockMaker.getHitPoints());
        }
        if (blockMaker.getStroke() == null && defaultBlockMaker != null) {
            blockMaker.setStroke(defaultBlockMaker.getStroke());
        }
        if (blockMaker.getFill() == null && defaultBlockMaker != null) {
            blockMaker.setFill(defaultBlockMaker.getFill());
        }
    }

    /**
     * Method returns the blockmaker for the default block.
     *
     * @param line       String.
     * @param blockMaker blockMaker.
     * @return Blockmaker.
     */
    public static BlockMaker defaultBlock(String line, BlockMaker blockMaker) {
        Map<String, String> map = new HashMap<>();
        String[] parts = line.split(" ");
        for (int j = 1; j < parts.length; j++) {
            String[] mapping = parts[j].split(":");
            map.put(mapping[0], mapping[1]);
            checkMap(map, blockMaker, line);
        }
        if (blockMaker.getSymbol() == null) {
            blockMaker.setSymbol("default");
        }
        return blockMaker;
    }

    /**
     * Method returns the blockmaker for a non-default block.
     *
     * @param line       String.
     * @param blockMaker blockMaker.
     * @return Blockmaker.
     */
    public static BlockMaker definedBlock(String line, BlockMaker blockMaker) {
        String[] parts = line.split("bdef ");
        Map<String, String> map = splitByDef(parts[1]);
        checkMap(map, blockMaker, line);
        return blockMaker;
    }

    /**
     * Methodreturns a map from symbols to spacerwidths.
     *
     * @param line String.
     * @return Map.
     */
    public static Map<String, Integer> getSpacerDefs(String line) {
        Map<String, Integer> spacers = new HashMap<>();
        String[] parts = line.split("sdef |\n");
        for (int i = 1; i < parts.length; i++) {
            String[] mapping = parts[i].split(" ");
            String key = mapping[0].split(":")[1];
            String value = mapping[1].split(":")[1];
            spacers.put(key, Integer.parseInt(value));
        }
        return spacers;
    }

    /**
     * Method casts an int to a string.
     *
     * @param s String.
     * @return int.
     */
    public static int readInt(String s) {
        return Integer.parseInt(s);
    }

    /**
     * Method returns a background.
     *
     * @param s String.
     * @return Background.
     */
    public static Background readBackground(String s) {
        Map<Integer, Background> hitFillMap = new HashMap<>();
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
     * Method splits string and puts corresponding sections into a map.
     *
     * @param s String.
     * @return map.
     */
    public static Map splitByDef(String s) {
        String[] parts = s.split(" ");
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < parts.length; i++) {
            String[] pairs = parts[i].split(":");
            map.put(pairs[0], pairs[1]);
        }
        return map;
    }

    /**
     * Method checks if blockmakers members can be set in current line.
     *
     * @param map        map.
     * @param blockMaker blockmaker.
     * @param line       Stirng.
     */
    public static void checkMap(Map<String, String> map, BlockMaker blockMaker, String line) {
        ColorsParser toColor = new ColorsParser();

        if (map.containsKey("height")) {
            blockMaker.setHeight((readInt(map.get("height"))));
        }
        if (map.containsKey("width")) {
            blockMaker.setWidth((readInt(map.get("width"))));
        }
        if (map.containsKey("hit_points")) {
            blockMaker.setHitPoints((readInt(map.get("hit_points"))));
        }
        if (map.containsKey("stroke")) {
            blockMaker.setStroke((toColor.colorFromString(map.get("stroke"))));
        }
        if (line.contains("fill")) {
            checkFill(line, blockMaker, map);
        }
        if (map.containsKey("symbol")) {
            blockMaker.setSymbol(((map.get("symbol"))));
        }
    }

    /**
     * Mehod assigns a blockmaker's hitnumber to it's corresponding fill.
     *
     * @param s          Stirng.
     * @param blockMaker blockmaker.
     * @param map        map.
     */
    public static void checkFill(String s, BlockMaker blockMaker, Map<String, String> map) {
        String[] split = s.split(" ");
        Map<Integer, Background> fillHitMap = new HashMap<>();
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains("fill")) {
                if (split[i].contains("fill:")) {
                    fillHitMap.put(1, (readBackground(map.get("fill"))));
                } else if (split[i].contains("fill-")) {
                    String[] parts = split[i].split(":");
                    String fill = parts[0];
                    String[] getInt = fill.split("-|\n");
                    int fillNum = Integer.parseInt(getInt[1]);
                    Background background = readBackground(parts[1]);
                    fillHitMap.put(fillNum, background);
                }
            }
        }
        blockMaker.setFill(fillHitMap);
    }
}
