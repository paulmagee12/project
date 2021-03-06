package game.tile.arena.util.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.Game;
import game.tile.arena.sprite.Image;
import game.tile.arena.util.Position;
import game.tile.arena.util.input.JoystickInput;

public class Joystick {

    boolean captured;
    final int radius;
    Position center;
    Position position;
    int lastPointer;
    Image stick;
    Image bg;

    JoystickInput inputProcessor;

    Joystick(Position pos, int size, String stickImg, String bgImg) {
        center = pos;
        position = pos;
        radius = size;

        stick = new Image(stickImg);
        bg = new Image(bgImg);

        inputProcessor = new JoystickInput(this);
        Game.rawInput.addInputProcessor(inputProcessor);
    }

    public Position getStickPosition() {
        return position.subtract(center).scale(1d/radius);
    }

    public boolean onDown(Position pos, int pointer) {
        if (center.inRange(radius, pos)) {
            captured = true;
            position = pos;
            lastPointer = pointer;
            return true;
        }
        return false;
    }

    public boolean onDrag(Position pos, int pointer) {
        if (captured && lastPointer == pointer) {
            lastPointer = pointer;
            position = center.limit(radius, pos);
            return true;
        }
        return false;
    }

    public boolean onUp(Position pos, int pointer) {
        if (captured && lastPointer == pointer) {
            captured = false;
            position = center;
            lastPointer = pointer;
            return true;
        }
        return false;
    }

    void renderStick(SpriteBatch batch, double delta) {
        bg.renderNoCamera(batch, center, radius / bg.getWidth());
        stick.renderNoCamera(batch, position, 4);
    }
}