package gameobjects;

import animations.Animation;

/**
 * Interface allows a submenu to be added and have options added to it to get their status'.
 *
 * @param <T> generic task.
 * @author Yonatan Segal
 * @version 1
 */
public interface Menu<T> extends Animation {
    /**
     * Method adds options for menu selection.
     *
     * @param key       String.
     * @param message   String.
     * @param returnVal T - our return value.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Method returns the option that's been selected.
     *
     * @return T - status.
     */
    T getStatus();

    /**
     * Method adds a submenu to select from.
     *
     * @param key     String.
     * @param message String.
     * @param subMenu Menu - submenu.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);

}
