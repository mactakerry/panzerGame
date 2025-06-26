package game.gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class Panzer {
    private final Vector2 position = new Vector2();
    private final Texture texture;

    public Panzer(Texture texture, float x, float y) {
        this.texture = texture;
        position.set(x, y);
    }

    public void render(Batch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void dispose() {
        texture.dispose();
    }
}
