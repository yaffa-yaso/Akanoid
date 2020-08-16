import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {

    private List<Sprite> sprites = new ArrayList<>();

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Notify all time passed.
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        // Make a copy of the sprites before iterating over them.
        List<Sprite> tempSprites = new ArrayList<>(this.sprites);
        for (Sprite s : tempSprites) {
            s.timePassed();
        }
    }

    /**
     * Draw all on.
     * call drawOn(d) on all sprites.
     * @param d the d
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}