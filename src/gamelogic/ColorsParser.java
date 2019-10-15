package gamelogic;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * Class holds a map from stirng to colours for casting.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class ColorsParser {
    private Map<String, Color> colorMap = new HashMap<>();

    /**
     * Constructor assigns strings to colours and puts them in a map.
     */
    public ColorsParser() {
        colorMap.put("black", Color.BLACK);
        colorMap.put("blue", Color.BLUE);
        colorMap.put("cyan", Color.CYAN);
        colorMap.put("darkgray", Color.DARK_GRAY);
        colorMap.put("gray", Color.GRAY);
        colorMap.put("green", Color.GREEN);
        colorMap.put("lightGray", Color.LIGHT_GRAY);
        colorMap.put("magenta", Color.MAGENTA);
        colorMap.put("orange", Color.ORANGE);
        colorMap.put("pink", Color.PINK);
        colorMap.put("white", Color.WHITE);
        colorMap.put("yellow", Color.YELLOW);
        colorMap.put("red", Color.RED);

        colorMap.put("BLACK", Color.BLACK);
        colorMap.put("BLUE", Color.BLUE);
        colorMap.put("CYAN", Color.CYAN);
        colorMap.put("DARKGRAY", Color.DARK_GRAY);
        colorMap.put("GRAY", Color.GRAY);
        colorMap.put("GREEN", Color.GREEN);
        colorMap.put("LIGHTGRAY", Color.LIGHT_GRAY);
        colorMap.put("MAGENTA", Color.MAGENTA);
        colorMap.put("ORANGE", Color.ORANGE);
        colorMap.put("PINK", Color.PINK);
        colorMap.put("WHITE", Color.WHITE);
        colorMap.put("YELLOW", Color.YELLOW);
        colorMap.put("RED", Color.RED);


    }

    /**
     * parse color definition and return the specified color.
     *
     * @param s Stirng.
     * @return color.
     */
    public java.awt.Color colorFromString(String s) {
        if (s.contains("RGB")) {
            String[] parts = s.split("\\(|\\)");
            String[] parameter = parts[2].split(",");
            int r = Integer.parseInt(parameter[0]);
            int g = Integer.parseInt(parameter[1]);
            int b = Integer.parseInt(parameter[2]);
            return new Color(r, g, b);
        }
        if (s.contains("color")) {
            String[] parts = s.split("\\(|\\)");
            String color = parts[1];
            return colorMap.get(color);
        }
        return null;

    }

}
