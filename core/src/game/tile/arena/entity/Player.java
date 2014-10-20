package game.tile.arena.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.Game;
import game.tile.arena.entity.attack.Attack;
import game.tile.arena.entity.attack.AttackBow;
import game.tile.arena.entity.projectile.LinearProjectile;
import game.tile.arena.util.Position;

public class Player extends Entity {

    private Attack attack;

    public Player(Position p) {
        super("player", p);
        attack = new AttackBow(400);
    }

    @Override
    public void update(int delta) {
        updatePosition(Game.joysticks.getPosition(Game.joysticks.MOVEMENT).scale(delta*speed));

        attack.update(delta);
    }

    @Override
    public void render(SpriteBatch batch, int delta) {
        super.render(batch, delta);
    }
}
