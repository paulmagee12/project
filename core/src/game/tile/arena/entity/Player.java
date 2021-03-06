package game.tile.arena.entity;

import game.tile.arena.Game;
import game.tile.arena.entity.attack.Attack;
import game.tile.arena.entity.attack.AttackBow;
import game.tile.arena.entity.attack.AttackList;
import game.tile.arena.util.Position;
import game.tile.arena.util.input.WeaponSwitchInput;

public class Player extends Entity {

    private Attack currentAttack;
    private int currentAttackIndex = -1;
    private AttackList attackList = new AttackList();

    public Player(Position p) {
        super("player", p, 166, Game.ALLY, 20, 8);
        addAttack(new AttackBow.Builder().setFireTime(600).create());
        Game.rawInput.addInputProcessor(new WeaponSwitchInput());
    }

    public void switchWeapon() {
        currentAttackIndex = attackList.getWrapIndex(currentAttackIndex+1);
        switchWeapon(currentAttackIndex);
    }

    public void switchWeapon(int index) {
        if (currentAttack != null)
            currentAttack.dequip();
        currentAttack = attackList.get(index);
        currentAttack.equip();
    }

    public void addAttack(Attack a) {
        attackList.add(a);
        if (currentAttack == null)
            switchWeapon();
    }

    @Override
    public boolean update(double delta) {
        updatePosition(Game.input.getMovement(), delta);
        currentAttack.update(delta, Game.input.getAttack(), Game.ALLY);
        return true;
    }
}
