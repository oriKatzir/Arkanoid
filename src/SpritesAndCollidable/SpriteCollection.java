package SpritesAndCollidable;

import java.util.ArrayList;
import java.util.List;

/**
 * Sprites.SpriteCollection class are a list of Sprites.Sprite objects.
 *
 * @author ori katzir
 * @version ass5
 * @since 2022/05/22
 */
public class SpriteCollection {
    private List<Sprite> spritesList;

    /**
     * A Sprites.SpriteCollection constructor, sets the Sprites.Sprite array list.
     */
    public SpriteCollection() {
        this.spritesList = new ArrayList<>();
    }

    /**
     * getter for a sprite in this collection, picked by index.
     *
     * @param index
     * @return a Sprites.Sprite in the index in this collection.
     */
    public Sprite get(int index) {
        return this.spritesList.get(index);
    }

    /**
     * getter for the size of this collection.
     *
     * @return the size of this collection
     */
    public int size() {
        return this.spritesList.size();
    }

    /**
     * Adds a sprite to this collection.
     *
     * @param s the sprite that's going to be added
     */
    public void addSprite(Sprite s) {
        this.spritesList.add(s);
    }

    /**
     * removes a Sprites.Sprite in the given index from this collection.
     *
     * @param index the index of the Sprites.Sprite thats going to be removed
     */
    public void removeSprite(int index) {
        this.spritesList.remove(index);
    }

    /**
     * removes the given Sprites.Sprite from this collection.
     *
     * @param s the S
     */
    public void removeSprite(Sprite s) {
        removeSprite(this.spritesList.indexOf(s));
    }

    /**
     * call timePassed() on all sprites.
     *
     * @see Sprite#timePassed()
     */
    public void notifyAllTimePassed() {
        if (this.spritesList.isEmpty()) {
            return;
        }
        for (int i = 0; i < this.spritesList.size(); i++) {
            this.spritesList.get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d the given DrawSurface
     * @see Sprite#drawOn(biuoop.DrawSurface)
     */
    public void drawAllOn(biuoop.DrawSurface d) {
        if (this.spritesList.isEmpty()) {
            return;
        }
        for (Sprite sprite : this.spritesList) {
            sprite.drawOn(d);
        }
    }
}