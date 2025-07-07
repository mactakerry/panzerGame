package game.gdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Starter extends ApplicationAdapter {
    private SpriteBatch batch;

    private Panzer me;
    private List<Panzer> enemies = new ArrayList<>();
    private KeyboardAdapter keyboardAdapter = new KeyboardAdapter();

    @Override
    public void create() {

        Gdx.input.setInputProcessor(keyboardAdapter);
        batch = new SpriteBatch();
        me = new Panzer(new Texture("panzer_me.png"), 100, 200);

        List<Panzer> newEnemies = IntStream.range(0, 15).mapToObj(i -> {
            int x = MathUtils.random(Gdx.graphics.getWidth());
            int y = MathUtils.random(Gdx.graphics.getHeight());
            return new Panzer(new Texture("panzer_enemy.png"), x, y);
        }).collect(Collectors.toList());

        enemies.addAll(newEnemies);
    }

    @Override
    public void render() {
        me.moveTo(keyboardAdapter.getDirection(5));
        me.rotateTo(keyboardAdapter.getMousePosition());

        ScreenUtils.clear(1f, 1f, 1f, 1f);
        batch.begin();
        me.render(batch);
        enemies.forEach(enemy -> {
            enemy.render(batch);
            enemy.rotateTo(me.getPosition());
        });
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        me.dispose();
    }
}
