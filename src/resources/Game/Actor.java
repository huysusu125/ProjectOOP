package resources.Game;

import resources.View.GUI;

import java.awt.*;
import java.util.ArrayList;

public class Actor {
    protected static final int LEFT = 1;
    protected static final int RIGHT = 2;
    protected static final int UP = 3;
    protected static final int DOWN = 4;
    public static final int ALIVE = 1;
    protected static final int DEAD = 0;
    public static final int BOMBER = 1;

    public static int getLEFT() {
        return LEFT;
    }

    public static int getRIGHT() {
        return RIGHT;
    }

    public static int getUP() {
        return UP;
    }

    public static int getDOWN() {
        return DOWN;
    }

    public static int getDEAD() {
        return DEAD;
    }

    public static final int MONSTER = 2;
    public static final int BOSS = 3;
    public static final int BOMB = 4;


    protected int x, y, type, orient, speed, width, height, runBomb;
    protected Image img;

    // dựng hình ảnh tác nhân
    public void drawActor(Graphics2D g2d) {
        switch (type) {
            case BOMBER:
                g2d.drawImage(img, x, y - 20, null);
                break;
            case MONSTER:
                g2d.drawImage(img, x, y - 23, null);
                break;
            case BOMB:
                g2d.drawImage(img, x, y, null);
                break;

            default:
                break;
        }
    }

    // di chuyển cho người chơi
    public boolean move(int count, ArrayList<Bomb> arrBomb, ArrayList<Box> arrBox) {
        if (count % speed != 0) {
            return true;
        }
        switch (orient) {
            case LEFT:
                if (x <= 0) {
                    return false;
                }
                x = x - 1;
                for (Bomb bomb : arrBomb) {
                    if (bomb.isImpactBombvsActor(this) == 1) {
                        x = x + 1;
                        return false;
                    }
                }
                for (Box box : arrBox) {
                    int kq = box.isImpactBoxvsActor(this);
                    if (kq != 0) {
                        if (kq >= -20 && kq <= 20) {
                            if (kq > 0) {
                                y = y + 1;
                            } else {
                                y = y - 1;
                            }
                        }
                        x = x + 1;
                        return false;
                    }
                }
                break;
            case RIGHT:
                if (x > (675 - width)) {
                    return false;
                }
                x = x + 1;
                for (Bomb bomb : arrBomb) {
                    if (bomb.isImpactBombvsActor(this) == 1) {
                        x = x - 1;
                        return false;
                    }
                }
                for (Box box : arrBox) {
                    int kq = box.isImpactBoxvsActor(this);
                    if (kq != 0) {
                        if (kq >= -20 && kq <= 20) {
                            if (kq > 0) {
                                y = y + 1;
                            } else {
                                y = y - 1;
                            }
                        }
                        x = x - 1;
                        return false;
                    }
                }
                break;
            case UP:
                if (y <= 0) {
                    return false;
                }
                y = y - 1;
                for (Bomb bomb : arrBomb) {
                    if (bomb.isImpactBombvsActor(this) == 1) {
                        y = y + 1;
                        return false;
                    }
                }
                for (Box box : arrBox) {
                    int kq = box.isImpactBoxvsActor(this);
                    if (kq != 0) {
                        if (kq >= -20 && kq <= 20) {
                            if (kq > 0) {
                                x = x + 1;
                            } else {
                                x = x - 1;
                            }
                        }
                        y = y + 1;
                        return false;
                    }
                }
                break;
            case DOWN:
                if (y >= (GUI.HEIGHTJF - 25 - height)) {
                    return false;
                }
                y = y + 1;
                for (Bomb bomb : arrBomb) {
                    if (bomb.isImpactBombvsActor(this) == 1) {
                        y = y - 1;
                        return false;
                    }
                }
                for (Box box : arrBox) {
                    int kq = box.isImpactBoxvsActor(this);
                    if (kq != 0) {
                        if (kq >= -20 && kq <= 20) {
                            if (kq > 0) {
                                x = x + 1;
                            } else {
                                x = x - 1;
                            }
                        }
                        y = y - 1;
                        return false;
                    }
                }
                break;

            default:
                break;
        }
        return true;
    }

    //thay đổi phương hướng
    public void changeOrient(int orient) {
        this.orient = orient;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getOrient() {
        return orient;
    }

    public void setRunBomb(int runBomb) {
        this.runBomb = runBomb;
    }

    public int getRunBomb() {
        return runBomb;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (speed < 3) {
            return;
        }
        this.speed = speed;
    }

    public int getType() {
        return type;
    }


}