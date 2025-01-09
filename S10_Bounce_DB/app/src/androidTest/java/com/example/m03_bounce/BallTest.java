package com.example.m03_bounce;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BallTest {

    Ball ball1 = new Ball(111111, 1, 1, 1, 1);
    Ball ball2 = new Ball(222222, 2, 2, 2, 2);

    @Test
    public void test01_move_01() {
        ball1.move();
        assert (ball1.getX() == 2);
        assert (ball1.getY() == 2);
    }

    @Test
    public void test01_move_02() {
        ball2.move();
        assert (ball2.getX() == 4);
        assert (ball2.getY() == 4);
    }

    @Test
    public void test01_move_03() {
        ball2.move();
        assert (ball2.getX() != 3);
        assert (ball2.getY() != 3);
    }

    @Test
    public void test02_detectCollision_01() {
        Box box = new Box(111111);
        box.set(2, 2, 2,2);
        Ball ball = new Ball(111111, 3, 3, 1, 1);
        ball.moveWithCollisionDetection(box);

        assert (ball.getX() == -47);
        assert (ball.getY() == -47);
        assert (ball.getSpeedX() == -1);
        assert (ball.getSpeedY() == -1);
    }

    @Test
    public void test02_detectCollision_02() {
        Box box = new Box(111111);
        box.set(4, 4, 4,4);
        Ball ball = new Ball(111111, 1, 1, 1, 1);
        ball.moveWithCollisionDetection(box);

        assert (ball.getX() == -43);
        assert (ball.getY() == -43);
        assert (ball.getSpeedX() == -1);
        assert (ball.getSpeedY() == -1);
    }
}
