package game.tile.arena.entity;

import java.util.LinkedList;
import java.util.List;

import game.tile.arena.Game;
import game.tile.arena.entity.attack.Attack;
import game.tile.arena.entity.attack.AttackBow;
import game.tile.arena.util.Position;
import game.tile.arena.util.input.WeaponSwitchInput;

public class Player extends Entity {

    private Attack currentAttack;
    private int currentAttackIndex = -1;
    private List<Attack> attackList = new LinkedList<Attack>();

    public Player(Position p) {
        super("player", p, Game.ALLY);
        addAttack(new AttackBow.Builder().setFireTime(1200).setSpreadShots(5).setSpreadAngle(15).create());
        addAttack(new AttackBow.Builder().setFireTime(900).setSpreadShots(3).setSpreadAngle(5).create());
        addAttack(new AttackBow.Builder().setFireTime(60).setReloadTime(1200).setBarrageShots(3).setPierce(2).create());
        addAttack(new AttackBow.Builder().setFireTime(600).setPierce(2).create());
        Game.input.addInputProcessor(new WeaponSwitchInput());
    }

    public void switchWeapon() {
        if (currentAttackIndex < 0 || currentAttackIndex >= attackList.size()) {
            switchWeapon(0);
            currentAttackIndex = 1;
        } else {
            switchWeapon(currentAttackIndex);
            currentAttackIndex++;
        }
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
    public void update(double delta) {
        updatePosition(Game.joysticks.getPosition(Game.joysticks.MOVEMENT), delta);
        currentAttack.update(delta, Game.joysticks.getPosition(Game.joysticks.ATTACK), Game.ALLY);
    }
}
