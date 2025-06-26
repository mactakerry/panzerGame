package game.gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Panzer {
    private final KeyboardAdapter keyboardAdapter;
    private final Vector2 position = new Vector2();
    private final Vector2 angle = new Vector2();

    private final Texture texture;
    private final TextureRegion textureRegion;

    private float width = 64;
    private float height = 64;
    private float halfWidth = width/2;
    private float halfHeight = height/2;

    private int speed = 5;

    public Panzer(KeyboardAdapter keyboardAdapter, Texture texture, float x, float y) {
        this.keyboardAdapter = keyboardAdapter;
        this.texture = texture;
        textureRegion = new TextureRegion(texture);
        position.set(x, y);
    }

    public void render(Batch batch) {
        batch.draw(textureRegion, position.x, position.y, halfWidth, halfHeight, width, height, 1, 1, angle.angleDeg() - 90);
    }

    public void dispose() {
        texture.dispose();
    }

    public void moveTo() {
        position.add(keyboardAdapter.getDirection(speed));
    }

    public void moveTo(Vector2 to) {
        position.add(to);
    }

    public void rotateTo(Vector2 to) {
        angle.set(to).sub(position.x + halfWidth, position.y + halfHeight);
    }

    public Vector2 getPosition() {
        return position;
    }
}
