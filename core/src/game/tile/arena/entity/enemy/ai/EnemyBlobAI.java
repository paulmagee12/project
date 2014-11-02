package game.tile.arena.entity.enemy.ai;

import game.tile.arena.util.Position;

public class EnemyBlobAI extends EnemyAI {

    Position currentMovement;

    public EnemyBlobAI() {
        super();
        currentMovement = new Position(Math.random()-0.5, Math.random()-0.5);
    }

    @Override
    public Position getRelativePosition() {
        if ((int)(Math.random()*100) == 1)
            currentMovement = new Position(Math.random()-0.5, Math.random()-0.5);
        if (!enemy.pos.add(currentMovement).inWorld(0))
            currentMovement = currentMovement.scale(-1);
        return currentMovement;
    }
}