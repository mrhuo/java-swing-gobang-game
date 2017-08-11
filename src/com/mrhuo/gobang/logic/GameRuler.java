/*
 * Copyright  (c) mrhuo.com 2017.
 */

package com.mrhuo.gobang.logic;

import com.mrhuo.gobang.bean.ChessColor;

/**
 * 游戏规则类
 */
public class GameRuler {
    /**
     * 成5, 1000分 20种棋形
     * 活4，90分      16种棋形
     * 死4，60分
     * 活3，50分      12种棋形
     * 死3，30分
     * 活2，20分      4种棋形
     * 死2，10分
     * 单子   0分
     *
     * @return
     */
    private static final int FIVE = 1000;
    private static final int FOUR_LIVE = 90;
    private static final int FOUR_DEAD = 60;
    private static final int THREE_LIVE = 50;
    private static final int THREE_DEAD = 30;
    private static final int TWO_LIVE = 20;
    private static final int TWO_DEAD = 10;
    private ChessColor[][] chessColors;

    public void setChess(ChessColor[][] chess) {
        this.chessColors = chess;
    }

    public ChessColor[][] getChessColors() {
        return this.chessColors;
    }

    /**
     * 成5, 1000分 20种棋形
     * 活4，90分      16种棋形
     * 死4，60分      24种棋形
     * 活3，50分      12种棋形
     * 死3，30分      16种棋形
     * 活2，20分      4种棋形
     * 死2，10分      4种棋形
     * 单子   0分
     *
     * @return
     */
    public int calScore(int x, int y, ChessColor color) {
        //成5, 1000分 20种棋形
        int score = 0;
        //X0000
        if (x + 4 <= 14 && chessColors[x + 1][y] == color && chessColors[x + 2][y] == color && chessColors[x + 3][y] == color && chessColors[x + 4][y] == color) {
            score += FIVE;
        }
        //0X000
        if (x - 1 >= 0 && x + 3 <= 14 && chessColors[x - 1][y] == color && chessColors[x + 1][y] == color && chessColors[x + 2][y] == color && chessColors[x + 3][y] == color) {
            score += FIVE;
        }
        //00X00
        if (x - 2 >= 0 && x + 2 <= 14 && chessColors[x - 1][y] == color && chessColors[x - 2][y] == color && chessColors[x + 1][y] == color && chessColors[x + 2][y] == color) {
            score += FIVE;
        }
        //000X0
        if (x - 3 >= 0 && x + 1 <= 14 && chessColors[x - 1][y] == color && chessColors[x - 2][y] == color && chessColors[x - 3][y] == color && chessColors[x + 1][y] == color) {
            score += FIVE;
        }
        //0000X
        if (x - 4 >= 0 && chessColors[x - 1][y] == color && chessColors[x - 2][y] == color && chessColors[x - 3][y] == color && chessColors[x - 4][y] == color) {
            score += FIVE;
        }

        /**
         * x
         * 0
         * 0
         * 0
         * 0
         */
        if (y + 4 <= 14 && chessColors[x][y + 1] == color && chessColors[x][y + 2] == color && chessColors[x][y + 3] == color && chessColors[x][y + 4] == color) {
            score += FIVE;
        }
        /**
         * 0
         * x
         * 0
         * 0
         * 0
         */
        if (y - 1 >= 0 && y + 3 <= 14 && chessColors[x][y - 1] == color && chessColors[x][y + 1] == color && chessColors[x][y + 2] == color && chessColors[x][y + 3] == color) {
            score += FIVE;
        }
        /**
         * 0
         * 0
         * x
         * 0
         * 0
         */
        if (y - 2 >= 0 && y + 2 <= 14 && chessColors[x][y - 1] == color && chessColors[x][y - 2] == color && chessColors[x][y + 1] == color && chessColors[x][y + 2] == color) {
            score += FIVE;
        }
        /**
         * 0
         * 0
         * 0
         * x
         * 0
         */
        if (y - 3 >= 0 && y + 1 <= 14 && chessColors[x][y - 1] == color && chessColors[x][y - 2] == color && chessColors[x][y - 3] == color && chessColors[x][y + 1] == color) {
            score += FIVE;
        }
        /**
         * 0
         * 0
         * 0
         * 0
         * x
         */
        if (y - 4 >= 0 && chessColors[x][y - 1] == color && chessColors[x][y - 2] == color && chessColors[x][y - 3] == color && chessColors[x][y - 4] == color) {
            score += FIVE;
        }
        /**    x
         *    0
         *   0
         *  0
         * 0
         */
        if (x - 4 >= 0 && y + 4 <= 14 && chessColors[x - 1][y + 1] == color && chessColors[x - 2][y + 2] == color && chessColors[x - 3][y + 3] == color && chessColors[x - 4][y + 4] == color) {
            score += FIVE;
        }
        /**    0
         *    x
         *   0
         *  0
         * 0
         */
        if (x - 3 >= 0 && y - 1 >= 0 && x + 1 <= 14 && y + 3 <= 14 && chessColors[x + 1][y - 1] == color && chessColors[x - 1][y + 1] == color && chessColors[x - 2][y + 2] == color && chessColors[x - 3][y + 3] == color) {
            score += FIVE;
        }
        /**    0
         *    0
         *   x
         *  0
         * 0
         */
        if (x - 2 >= 0 && y - 2 >= 0 && x + 2 <= 14 && y + 2 <= 14 && chessColors[x + 1][y - 1] == color && chessColors[x + 2][y - 2] == color && chessColors[x - 1][y + 1] == color && chessColors[x - 2][y + 2] == color) {
            score += FIVE;
        }
        /**    0
         *    0
         *   0
         *  x
         * 0
         */
        if (x - 1 >= 0 && y - 3 >= 0 && x + 3 <= 14 && y + 1 <= 14 && chessColors[x + 1][y - 1] == color && chessColors[x + 2][y - 2] == color && chessColors[x + 3][y - 3] == color && chessColors[x - 1][y + 1] == color) {
            score += FIVE;
        }
        /**    0
         *    0
         *   0
         *  0
         * x
         */
        if (y - 4 >= 0 && x + 4 <= 14 && chessColors[x + 1][y - 1] == color && chessColors[x + 2][y - 2] == color && chessColors[x + 3][y - 3] == color && chessColors[x + 4][y - 4] == color) {
            score += FIVE;
        }
        /**
         * x
         *  0
         *   0
         *    0
         *     0
         */
        if (x + 4 <= 14 && y + 4 <= 14 && chessColors[x + 1][y + 1] == color && chessColors[x + 2][y + 2] == color && chessColors[x + 3][y + 3] == color && chessColors[x + 4][y + 4] == color) {
            score += FIVE;
        }
        /**
         * 0
         *  x
         *   0
         *    0
         *     0
         */
        if (x - 1 >= 0 && y - 1 >= 0 && x + 3 <= 14 && y + 3 <= 14 && chessColors[x - 1][y - 1] == color && chessColors[x + 1][y + 1] == color && chessColors[x + 2][y + 2] == color && chessColors[x + 3][y + 3] == color) {
            score += FIVE;
        }
        /**
         * 0
         *  0
         *   x
         *    0
         *     0
         */
        if (x - 2 >= 0 && y - 2 >= 0 && x + 2 <= 14 && y + 2 <= 14 && chessColors[x - 1][y - 1] == color && chessColors[x - 2][y - 2] == color && chessColors[x + 1][y + 1] == color && chessColors[x + 2][y + 2] == color) {
            score += FIVE;
        }
        /**
         * 0
         *  0
         *   0
         *    x
         *     0
         */
        if (x - 4 >= 0 && y - 4 >= 0 && chessColors[x - 1][y - 1] == color && chessColors[x - 2][y - 2] == color && chessColors[x - 3][y - 3] == color && chessColors[x - 4][y - 4] == color) {
            score += FIVE;
        }
        /**
         * 0
         *  0
         *   0
         *    0
         *     x
         */
        if (x - 4 >= 0 && y - 4 >= 0 && chessColors[x - 1][y - 1] == color && chessColors[x - 2][y - 2] == color && chessColors[x - 3][y - 3] == color && chessColors[x - 4][y - 4] == color) {
            score += FIVE;
        }
        //活4
        //_x000_
        if (x - 1 >= 0 && x + 4 <= 14 && chessColors[x - 1][y] == null && chessColors[x + 1][y] == color && chessColors[x + 2][y] == color && chessColors[x + 3][y] == color && chessColors[x + 4][y] == null) {
            score += FOUR_LIVE;
        }
        //_0x00_
        if (x - 2 >= 0 && x + 3 <= 14 && chessColors[x - 1][y] == color && chessColors[x - 2][y] == null && chessColors[x + 1][y] == color && chessColors[x + 2][y] == color && chessColors[x + 3][y] == null) {
            score += FOUR_LIVE;
        }
        //_00x0_
        if (x - 3 >= 0 && x + 2 <= 14 && chessColors[x - 1][y] == color && chessColors[x - 2][y] == color && chessColors[x - 3][y] == null && chessColors[x + 1][y] == color && chessColors[x + 2][y] == null) {
            score += FOUR_LIVE;
        }
        //_000x_
        if (x - 4 >= 0 && x + 1 <= 14 && chessColors[x - 1][y] == color && chessColors[x - 2][y] == color && chessColors[x - 3][y] == color && chessColors[x - 4][y] == null && chessColors[x + 1][y] == null) {
            score += FOUR_LIVE;
        }
        /**
         * _
         * x
         * 0
         * 0
         * 0
         * _
         */
        if (y - 1 >= 0 && y + 4 <= 14 && chessColors[x][y - 1] == null && chessColors[x][y + 1] == color && chessColors[x][y + 2] == color && chessColors[x][y + 3] == color && chessColors[x][y + 4] == null) {
            score += FOUR_LIVE;
        }
        /**
         * _
         * 0
         * x
         * 0
         * 0
         * _
         */
        if (y - 2 >= 0 && y + 3 <= 14 && chessColors[x][y - 1] == color && chessColors[x][y - 2] == null && chessColors[x][y + 1] == color && chessColors[x][y + 2] == color && chessColors[x][y + 3] == null) {
            score += FOUR_LIVE;
        }
        /**
         * _
         * 0
         * 0
         * x
         * 0
         * _
         */
        if (y - 3 >= 0 && y + 2 <= 14 && chessColors[x][y - 1] == color && chessColors[x][y - 2] == color && chessColors[x][y - 3] == null && chessColors[x][y + 1] == color && chessColors[x][y + 2] == null) {
            score += FOUR_LIVE;
        }
        /**
         * _
         * 0
         * 0
         * 0
         * x
         * _
         */
        if (y - 4 >= 0 && y + 1 <= 14 && chessColors[x][y - 1] == color && chessColors[x][y - 2] == color && chessColors[x][y - 3] == color && chessColors[x][y - 4] == null && chessColors[x][y + 1] == null) {
            score += FOUR_LIVE;
        }
        /**
         * _
         *  x
         *   0
         *    0
         *     0
         *      _
         */
        if (x - 1 >= 0 && y - 1 >= 0 && x + 4 <= 14 && y + 4 <= 14 && chessColors[x - 1][y - 1] == null && chessColors[x + 1][y + 1] == color && chessColors[x + 2][y + 2] == color && chessColors[x + 3][y + 3] == color && chessColors[x + 4][y + 4] == null) {
            score += FOUR_LIVE;
        }
        /**
         * _
         *  0
         *   x
         *    0
         *     0
         *      _
         */
        if (x - 2 >= 0 && y - 2 >= 0 && x + 3 <= 14 && y + 3 <= 14 && chessColors[x - 1][y - 1] == color && chessColors[x - 2][y - 2] == null && chessColors[x + 1][y + 1] == color && chessColors[x + 2][y + 2] == color && chessColors[x + 3][y + 3] == null) {
            score += FOUR_LIVE;
        }
        /**
         * _
         *  0
         *   0
         *    x
         *     0
         *      _
         */
        if (x - 3 >= 0 && y - 3 >= 0 && x + 2 <= 14 && y + 2 <= 14 && chessColors[x - 1][y - 1] == color && chessColors[x - 2][y - 2] == color && chessColors[x - 3][y - 3] == null && chessColors[x + 1][y + 1] == color && chessColors[x + 2][y + 2] == null) {
            score += FOUR_LIVE;
        }
        /**
         * _
         *  0
         *   0
         *    0
         *     X
         *      _
         */
        if (x - 4 >= 0 && y - 4 >= 0 && x + 1 <= 14 && y + 1 <= 14 && chessColors[x - 1][y - 1] == color && chessColors[x - 2][y - 2] == color && chessColors[x - 3][y - 3] == color && chessColors[x - 4][y - 4] == null && chessColors[x + 1][y + 1] == null) {
            score += FOUR_LIVE;
        }
        /**
         *      _
         *     x
         *    0
         *   0
         *  0
         * _
         */
        if (x - 4 >= 0 && y - 1 >= 0 && x + 1 <= 14 && y + 4 <= 14 && chessColors[x + 1][y - 1] == null && chessColors[x - 1][y + 1] == color && chessColors[x - 2][y + 2] == color && chessColors[x - 3][y + 3] == color && chessColors[x - 4][y + 4] == null) {
            score += FOUR_LIVE;
        }
        /**
         *      _
         *     0
         *    x
         *   0
         *  0
         * _
         */
        if (x - 3 >= 0 && y - 2 >= 0 && x + 2 <= 14 && y + 3 <= 14 && chessColors[x + 1][y - 1] == color && chessColors[x + 2][y - 2] == null && chessColors[x - 1][y + 1] == color && chessColors[x - 2][y + 2] == color && chessColors[x - 3][y + 3] == null) {
            score += FOUR_LIVE;
        }
        /**
         *      _
         *     0
         *    0
         *   x
         *  0
         * _
         */
        if (x - 2 >= 0 && y - 3 >= 0 && x + 3 <= 14 && y + 2 <= 14 && chessColors[x + 1][y - 1] == color && chessColors[x + 2][y - 2] == color && chessColors[x + 3][y - 3] == null && chessColors[x - 1][y + 1] == color && chessColors[x - 2][y + 2] == null) {
            score += FOUR_LIVE;
        }
        /**
         *      _
         *     0
         *    0
         *   0
         *  x
         * _
         */
        if (x - 1 >= 0 && y - 4 >= 0 && x + 4 <= 14 && y + 1 <= 14 && chessColors[x + 1][y - 1] == color && chessColors[x + 2][y - 2] == color && chessColors[x + 3][y - 3] == color && chessColors[x + 4][y - 4] == null && chessColors[x - 1][y + 1] == null) {
            score += FOUR_LIVE;
        }

        //死4
        //●x○○○_
        if ((x == 0 || (x - 1 >= 0 && chessColors[x - 1][y] != color)) && x + 4 <= 14 && chessColors[x + 1][y] == color && chessColors[x + 2][y] == color && chessColors[x + 3][y] == color && chessColors[x + 4][y] == null) {
            score += FOUR_DEAD;
        }
        //●○x○○_
        if ((x - 1 == 0 || (x - 2 >= 0 && chessColors[x - 2][y] != color)) && x - 1 >= 0 && x + 3 <= 14 && chessColors[x - 1][y] == color && chessColors[x + 1][y] == color && chessColors[x + 2][y] == color && chessColors[x + 3][y] == null) {
            score += FOUR_DEAD;
        }
        //●○○x○_
        if ((x - 2 == 0 || (x - 3 >= 0 && chessColors[x - 3][y] != color)) && x - 2 >= 0 && x + 2 <= 14 && chessColors[x - 1][y] == color && chessColors[x - 2][y] == color && chessColors[x + 1][y] == color && chessColors[x + 2][y] == null) {
            score += FOUR_DEAD;
        }
        //●○○○x_
        if ((x - 3 == 0 || (x - 4 >= 0 && chessColors[x - 4][y] != color)) && x - 3 >= 0 && x + 1 <= 14 && chessColors[x - 1][y] == color && chessColors[x - 2][y] == color && chessColors[x - 3][y] == color && chessColors[x + 1][y] == null) {
            score += FOUR_DEAD;
        }
        //_○○○x●
        if ((x == 14 || (x + 1 <= 14 && chessColors[x + 1][y] != color)) && x - 4 >= 0 && chessColors[x - 1][y] == color && chessColors[x - 2][y] == color && chessColors[x - 3][y] == color && chessColors[x - 4][y] == null) {
            score += FOUR_DEAD;
        }
        //_○○x○●
        if ((x + 1 == 14 || (x + 2 <= 14 && chessColors[x + 2][y] != color)) && x - 3 >= 0 && x + 1 <= 14 && chessColors[x + 1][y] == color && chessColors[x - 1][y] == color && chessColors[x - 2][y] == color && chessColors[x - 3][y] == null) {
            score += FOUR_DEAD;
        }
        //_○x○○●
        if ((x + 2 == 14 || (x + 3 <= 14 && chessColors[x + 3][y] != color)) && x - 2 >= 0 && x + 2 <= 14 && chessColors[x + 1][y] == color && chessColors[x + 2][y] == color && chessColors[x - 1][y] == color && chessColors[x - 2][y] == null) {
            score += FOUR_DEAD;
        }
        //_x○○○●
        if ((x == 14 || (x + 4 <= 14 && chessColors[x + 4][y] != color)) && x - 1 >= 0 && x + 3 <= 14 && chessColors[x + 1][y] == color && chessColors[x + 2][y] == color && chessColors[x + 3][y] == color && chessColors[x - 1][y] == null) {
            score += FOUR_DEAD;
        }

        /**
         * ●
         * x
         * ○
         * ○
         * ○
         * _
         */
        if ((y == 0 || (y - 1 >= 0 && y + 4 <= 14 && chessColors[x][y - 1] != color)) && y + 3 <= 14 && chessColors[x][y + 1] == color && chessColors[x][y + 2] == color && chessColors[x][y + 3] == color && chessColors[x][y + 4] == null) {
            score += FOUR_DEAD;
        }
        /**
         * ●
         * o
         * x
         * ○
         * ○
         * _
         */
        if ((y - 1 == 0 || (y - 2 >= 0 && chessColors[x][y - 2] != color)) && y - 1 >= 0 && y + 3 <= 14 && chessColors[x][y - 1] == color && chessColors[x][y + 1] == color && chessColors[x][y + 2] == color && chessColors[x][y + 3] == null) {
            score += FOUR_DEAD;
        }
        /**
         * ●
         * o
         * o
         * x
         * ○
         * _
         */
        if ((y - 2 == 0 || (y - 3 >= 0 && chessColors[x][y - 3] != color)) && y - 2 >= 0 && y + 2 <= 14 && chessColors[x][y - 1] == color && chessColors[x][y - 2] == color && chessColors[x][y + 1] == color && chessColors[x][y + 2] == null) {
            score += FOUR_DEAD;
        }
        /**
         * ●
         * o
         * o
         * o
         * x
         * _
         */
        if ((y - 3 == 0 || (y - 4 >= 0 && chessColors[x][y - 4] != color)) && y - 3 >= 0 && y + 1 <= 14 && chessColors[x][y - 1] == color && chessColors[x][y - 2] == color && chessColors[x][y - 3] == color && chessColors[x][y + 1] == null) {
            score += FOUR_DEAD;
        }
        /**
         * _
         * o
         * o
         * o
         * x
         * ●
         */
        if ((y == 14 || (y + 1 <= 14 && chessColors[x][y + 1] != color)) && y - 4 >= 0 && chessColors[x][y - 1] == color && chessColors[x][y - 2] == color && chessColors[x][y - 3] == color && chessColors[x][y - 4] == null) {
            score += FOUR_DEAD;
        }
        /**
         * _
         * o
         * o
         * x
         * o
         * ●
         */
        if ((y + 1 == 14 || (y + 2 <= 14 && chessColors[x][y + 2] != color)) && y - 3 >= 0 && y + 1 <= 14 && chessColors[x][y + 1] == color && chessColors[x][y - 1] == color && chessColors[x][y - 2] == color && chessColors[x][y - 3] == null) {
            score += FOUR_DEAD;
        }
        /**
         * _
         * o
         * x
         * o
         * o
         * ●
         */
        if ((y + 2 == 14 || (y + 3 <= 14 && chessColors[x][y + 3] != color)) && y - 2 >= 0 && y + 2 <= 14 && chessColors[x][y + 1] == color && chessColors[x][y + 2] == color && chessColors[x][y - 1] == color && chessColors[x][y - 2] == null) {
            score += FOUR_DEAD;
        }
        /**
         * _
         * x
         * o
         * o
         * o
         * ●
         */
        if ((y + 3 == 14 || (y + 4 <= 14 && chessColors[x][y + 4] != color)) && y - 1 >= 0 && y + 3 <= 14 && chessColors[x][y + 1] == color && chessColors[x][y + 2] == color && chessColors[x][y + 3] == color && chessColors[x][y - 1] == null) {
            score += FOUR_DEAD;
        }
        /**
         * ●
         *  x
         *   o
         *    o
         *     o
         *      _
         */
        if ((x == 0 || y == 0 || (x - 1 >= 0 && y - 1 >= 0 && chessColors[x - 1][y - 1] != color)) && x + 4 <= 14 && y + 4 <= 14 && chessColors[x + 1][y + 1] == color && chessColors[x + 2][y + 2] == color && chessColors[x + 3][y + 3] == color && chessColors[x + 4][y + 4] == null) {
            score += FOUR_DEAD;
        }
        /**
         * ●
         *  o
         *   x
         *    o
         *     o
         *      _
         */
        if ((x - 1 == 0 || y - 1 == 0 || (x - 2 >= 0 && y - 2 >= 0 && chessColors[x - 2][y - 2] != color)) && x - 1 >= 0 && y - 1 >= 0 && x + 3 <= 14 && y + 3 <= 14 && chessColors[x - 1][y - 1] == color && chessColors[x + 1][y + 1] == color && chessColors[x + 2][y + 2] == color && chessColors[x + 3][y + 3] == null) {
            score += FOUR_DEAD;
        }
        /**
         * ●
         *  o
         *   o
         *    x
         *     o
         *      _
         */
        if ((x - 2 == 0 || y - 2 == 0 || (x - 3 >= 0 && y - 3 >= 0 && chessColors[x - 3][y - 3] != color)) && x - 2 >= 0 && y - 2 >= 0 && x + 2 <= 14 && y + 2 <= 14 && chessColors[x - 1][y - 1] == color && chessColors[x - 2][y - 2] == color && chessColors[x + 1][y + 1] == color && chessColors[x + 2][y + 2] == null) {
            score += FOUR_DEAD;
        }
        /**
         * ●
         *  o
         *   o
         *    o
         *     x
         *      _
         */
        if ((x - 3 == 0 || y - 3 == 0 || (x - 4 >= 0 && y - 4 >= 0 && chessColors[x - 4][y - 4] != color)) && x - 3 >= 0 && y - 3 >= 0 && x + 1 <= 14 && y + 1 <= 14 && chessColors[x - 1][y - 1] == color && chessColors[x - 2][y - 2] == color && chessColors[x - 3][y - 3] == color && chessColors[x + 1][y + 1] == null) {
            score += FOUR_DEAD;
        }
        /**
         * _
         *  o
         *   o
         *    o
         *     x
         *      ●
         */
        if ((x == 14 || y == 14 || (x + 1 <= 14 && y + 1 <= 14 && chessColors[x + 1][y + 1] != color)) && x - 4 >= 0 && y - 4 >= 0 && chessColors[x - 1][y - 1] == color && chessColors[x - 2][y - 2] == color && chessColors[x - 3][y - 3] == color && chessColors[x - 4][y - 4] == null) {
            score += FOUR_DEAD;
        }
        /**
         * _
         *  o
         *   o
         *    x
         *     o
         *      ●
         */
        if ((x + 1 == 14 || y + 1 == 14 || (x + 2 <= 14 && y + 2 <= 14 && chessColors[x + 2][y + 2] != color)) && x - 3 >= 0 && y - 3 >= 0 && x + 1 <= 14 && y + 1 <= 14 && chessColors[x + 1][y + 1] == color && chessColors[x - 1][y - 1] == color && chessColors[x - 2][y - 2] == color && chessColors[x - 3][y - 3] == null) {
            score += FOUR_DEAD;
        }
        /**
         * _
         *  o
         *   x
         *    o
         *     o
         *      ●
         */
        if ((x + 2 == 14 || y + 2 == 14 || (x + 3 <= 14 && y + 3 <= 14 && chessColors[x + 3][y + 3] != color)) && x - 2 >= 0 && y - 2 >= 0 && x + 2 <= 14 && y + 2 <= 14 && chessColors[x + 1][y + 1] == color && chessColors[x + 2][y + 2] == color && chessColors[x - 1][y - 1] == color && chessColors[x - 2][y - 2] == null) {
            score += FOUR_DEAD;
        }
        /**
         * _
         *  x
         *   o
         *    o
         *     o
         *      ●
         */
        if ((x + 3 == 14 || y + 3 == 14 || (x + 4 <= 14 && y + 4 <= 14 && chessColors[x + 4][y + 4] != color)) && x - 1 >= 0 && y - 1 >= 0 && x + 3 <= 14 && y + 3 <= 14 && chessColors[x + 1][y + 1] == color && chessColors[x + 2][y + 2] == color && chessColors[x + 3][y + 3] == color && chessColors[x - 1][y - 1] == null) {
            score += FOUR_DEAD;
        }

        /**
         *      ●
         *     x
         *    o
         *   o
         *  o
         * _
         */
        if ((x == 14 || y == 0 || (x + 1 <= 14 && y - 1 >= 0 && chessColors[x + 1][y - 1] != color)) && x - 4 >= 0 && y + 4 <= 14 && chessColors[x - 1][y + 1] == color && chessColors[x - 2][y + 2] == color && chessColors[x - 3][y + 3] == color && chessColors[x - 4][y + 4] == null) {
            score += FOUR_DEAD;
        }

        /**
         *      ●
         *     o
         *    x
         *   o
         *  o
         * _
         */
        if ((x + 1 == 14 || y - 1 == 0 || (x + 2 <= 14 && y - 2 >= 0 && chessColors[x + 2][y - 2] != color)) && x - 3 >= 0 && y - 1 >= 0 && x + 1 <= 14 && y + 3 <= 14 && chessColors[x + 1][y - 1] == color && chessColors[x - 1][y + 1] == color && chessColors[x - 2][y + 2] == color && chessColors[x - 3][y + 3] == null) {
            score += FOUR_DEAD;
        }

        /**
         *      ●
         *     o
         *    o
         *   x
         *  o
         * _
         */
        if ((x + 2 == 14 || y - 2 == 0 || (x + 3 <= 14 && y - 3 >= 0 && chessColors[x + 3][y - 3] != color)) && x - 2 >= 0 && y - 2 >= 0 && x + 2 <= 14 && y + 2 <= 14 && chessColors[x + 1][y - 1] == color && chessColors[x + 2][y - 2] == color && chessColors[x - 1][y + 1] == color && chessColors[x - 2][y + 2] == null) {
            score += FOUR_DEAD;
        }
        /**
         *      ●
         *     o
         *    o
         *   o
         *  x
         * _
         */
        if ((x + 3 == 14 || y - 3 == 0 || (x + 4 <= 14 && y - 4 >= 0 && chessColors[x + 4][y - 4] != color)) && x - 1 >= 0 && y - 3 >= 0 && x + 3 <= 14 && y + 1 <= 14 && chessColors[x + 1][y - 1] == color && chessColors[x + 2][y - 2] == color && chessColors[x + 3][y - 3] == color && chessColors[x - 1][y + 1] == null) {
            score += FOUR_DEAD;
        }

        /**
         *      _
         *     o
         *    o
         *   o
         *  x
         * ●
         */
        if ((x == 0 || y == 14 || (x - 1 >= 0 && y + 1 <= 14 && chessColors[x - 1][y + 1] != color)) && y - 4 >= 0 && x + 4 <= 14 && chessColors[x + 1][y - 1] == color && chessColors[x + 2][y - 2] == color && chessColors[x + 3][y - 3] == color && chessColors[x + 4][y - 4] == null) {
            score += FOUR_DEAD;
        }
        /**
         *      _
         *     o
         *    o
         *   x
         *  o
         * ●
         */
        if ((x - 1 == 0 || y + 1 == 14 || (x - 2 >= 0 && y + 2 <= 14 && chessColors[x - 2][y + 2] != color)) && x - 1 >= 0 && y - 3 >= 0 && x + 3 <= 14 && y + 1 <= 14 && chessColors[x - 1][y + 1] == color && chessColors[x + 1][y - 1] == color && chessColors[x + 2][y - 2] == color && chessColors[x + 3][y - 3] == null) {
            score += FOUR_DEAD;
        }
        /**
         *      _
         *     o
         *    x
         *   o
         *  o
         * ●
         */
        if ((x - 2 == 0 || y + 2 == 14 || (x - 3 >= 0 && y + 3 <= 14 && chessColors[x - 3][y + 3] != color)) && x - 2 >= 0 && y - 2 >= 0 && x + 2 <= 14 && y + 2 <= 14 && chessColors[x - 1][y + 1] == color && chessColors[x - 2][y + 2] == color && chessColors[x + 1][y - 1] == color && chessColors[x + 2][y - 2] == null) {
            score += FOUR_DEAD;
        }
        /**
         *      _
         *     x
         *    o
         *   o
         *  o
         * ●
         */
        if ((x - 3 == 0 || y + 3 == 14 || (x - 4 >= 0 && y + 4 <= 14 && chessColors[x - 4][y + 4] != color)) && x - 3 >= 0 && y - 1 >= 0 && x + 1 <= 14 && y + 3 <= 14 && chessColors[x - 1][y + 1] == color && chessColors[x - 2][y + 2] == color && chessColors[x - 3][y + 3] == color && chessColors[x + 1][y - 1] == null) {
            score += FOUR_DEAD;
        }

        //活3
        //_xoo_
        if (x - 1 >= 0 && x + 3 <= 14 && chessColors[x - 1][y] == null && chessColors[x + 1][y] == color && chessColors[x + 2][y] == color && chessColors[x + 3][y] == null) {
            score += THREE_LIVE;
        }
        //_oxo_
        if (x - 2 >= 0 && x + 2 <= 14 && chessColors[x - 1][y] == color && chessColors[x - 2][y] == null && chessColors[x + 1][y] == color && chessColors[x + 2][y] == null) {
            score += THREE_LIVE;
        }
        //_oox_
        if (x - 3 >= 0 && x + 1 <= 14 && chessColors[x - 1][y] == color && chessColors[x - 2][y] == color && chessColors[x - 3][y] == null && chessColors[x + 1][y] == null) {
            score += THREE_LIVE;
        }
        /**
         * _
         * x
         * o
         * o
         * _
         */
        if (y - 1 >= 0 && y + 3 <= 14 && chessColors[x][y - 1] == null && chessColors[x][y + 1] == color && chessColors[x][y + 2] == color && chessColors[x][y + 3] == null) {
            score += THREE_LIVE;
        }
        /**
         * _
         * o
         * x
         * o
         * _
         */
        if (y - 2 >= 0 && y + 2 <= 14 && chessColors[x][y - 2] == null && chessColors[x][y - 1] == color && chessColors[x][y + 1] == color && chessColors[x][y + 2] == null) {
            score += THREE_LIVE;
        }
        /**
         * _
         * o
         * o
         * x
         * _
         */
        if (y - 3 >= 0 && y + 1 <= 14 && chessColors[x][y - 3] == null && chessColors[x][y - 1] == color && chessColors[x][y - 2] == color && chessColors[x][y + 1] == null) {
            score += THREE_LIVE;
        }
        /**
         * _
         *  x
         *   o
         *    o
         *     _
         */
        if (x - 1 >= 0 && y - 1 >= 0 && x + 3 <= 14 && y + 3 <= 14 && chessColors[x - 1][y - 1] == null && chessColors[x + 1][y + 1] == color && chessColors[x + 2][y + 2] == color && chessColors[x + 3][y + 3] == null) {
            score += THREE_LIVE;
        }
        /**
         * _
         *  o
         *   x
         *    o
         *     _
         */
        if (x - 2 >= 0 && y - 2 >= 0 && x + 2 <= 14 && y + 2 <= 14 && chessColors[x - 2][y - 2] == null && chessColors[x - 1][y - 1] == color && chessColors[x + 1][y + 1] == color && chessColors[x + 2][y + 2] == null) {
            score += THREE_LIVE;
        }

        /**
         * _
         *  o
         *   o
         *    x
         *     _
         */
        if (x - 3 >= 0 && y - 3 >= 0 && x + 1 <= 14 && y + 1 <= 14 && chessColors[x - 3][y - 3] == null && chessColors[x - 1][y - 1] == color && chessColors[x - 2][y - 2] == color && chessColors[x + 1][y + 1] == null) {
            score += THREE_LIVE;
        }
        /**
         *     _
         *    o
         *   o
         *  x
         * _
         */
        if (x - 1 >= 0 && y - 3 >= 0 && x + 3 <= 14 && y + 1 <= 14 && chessColors[x - 1][y + 1] == null && chessColors[x + 1][y - 1] == color && chessColors[x + 2][y - 2] == color && chessColors[x + 3][y - 3] == null) {
            score += THREE_LIVE;
        }
        /**
         *     _
         *    o
         *   x
         *  o
         * _
         */
        if (x - 2 >= 0 && y - 2 >= 0 && x + 2 <= 14 && y + 2 <= 14 && chessColors[x - 2][y + 2] == null && chessColors[x - 1][y + 1] == color && chessColors[x + 1][y - 1] == color && chessColors[x + 2][y - 2] == null) {
            score += THREE_LIVE;
        }
        /**
         *     _
         *    x
         *   o
         *  o
         * _
         */
        if (x - 3 >= 0 && y - 1 >= 0 && x + 1 <= 14 && y + 3 <= 14 && chessColors[x - 3][y + 3] == null && chessColors[x - 1][y + 1] == color && chessColors[x - 2][y + 2] == color && chessColors[x + 1][y - 1] == null) {
            score += THREE_LIVE;
        }
        //死三
        //●x○○_
        if ((x == 0 || (x - 1 >= 0 && chessColors[x - 1][y] != color)) && x + 3 <= 14 && chessColors[x + 1][y] == color && chessColors[x + 2][y] == color && chessColors[x + 3][y] == null) {
            score += THREE_DEAD;
        }
        //●○x○_
        if ((x - 1 == 0 || (x - 2 >= 0 && chessColors[x - 2][y] != color)) && x + 2 <= 14 && chessColors[x - 1][y] == color && chessColors[x + 1][y] == color && chessColors[x + 2][y] == null) {
            score += THREE_DEAD;
        }
        //●○○x_
        if ((x - 2 == 0 || (x - 3 >= 0 && chessColors[x - 3][y] != color)) && x + 1 <= 14 && chessColors[x - 1][y] == color && chessColors[x - 2][y] == color && chessColors[x + 1][y] == null) {
            score += THREE_DEAD;
        }
        //_○○x●
        if ((x == 14 || (x + 1 <= 14 && chessColors[x + 1][y] != color)) && x - 3 >= 0 && chessColors[x - 1][y] == color && chessColors[x - 2][y] == color && chessColors[x - 3][y] == null) {
            score += THREE_DEAD;
        }
        //_○x○●
        if ((x + 1 == 14 || (x + 2 <= 14 && chessColors[x + 2][y] != color)) && x - 2 >= 0 && x + 1 <= 14 && chessColors[x + 1][y] == color && chessColors[x - 1][y] == color && chessColors[x - 2][y] == null) {
            score += THREE_DEAD;
        }
        //_x○○●
        if ((x + 2 == 14 || (x + 3 <= 14 && chessColors[x + 3][y] != color)) && x - 1 >= 0 && x + 2 <= 14 && chessColors[x + 1][y] == color && chessColors[x + 2][y] == color && chessColors[x - 1][y] == null) {
            score += THREE_DEAD;
        }

        /**
         * ●
         * x
         * ○
         * ○
         * _
         */
        if ((y == 0 || (y - 1 >= 0 && chessColors[x][y - 1] != color)) && y + 3 <= 14 && chessColors[x][y + 1] == color && chessColors[x][y + 2] == color && chessColors[x][y + 3] == null) {
            score += THREE_DEAD;
        }
        /**
         * ●
         * o
         * x
         * ○
         * _
         */
        if ((y - 1 == 0 || (y - 2 >= 0 && chessColors[x][y - 2] != color)) && y + 2 <= 14 && chessColors[x][y - 1] == color && chessColors[x][y + 1] == color && chessColors[x][y + 2] == null) {
            score += THREE_DEAD;
        }
        /**
         * ●
         * o
         * o
         * x
         * _
         */
        if ((y - 2 == 0 || (y - 3 >= 0 && chessColors[x][y - 3] != color)) && y + 1 <= 14 && chessColors[x][y - 1] == color && chessColors[x][y - 2] == color && chessColors[x][y + 1] == null) {
            score += THREE_DEAD;
        }

        /**
         * _
         * o
         * o
         * x
         * ●
         */
        if ((y == 14 || (y + 1 <= 14 && chessColors[x][y + 1] != color)) && y - 3 >= 0 && chessColors[x][y - 1] == color && chessColors[x][y - 2] == color && chessColors[x][y - 3] == null) {
            score += THREE_DEAD;
        }
        /**
         * _
         * o
         * x
         * o
         * ●
         */
        if ((y + 1 == 14 || (y + 2 <= 14 && chessColors[x][y + 2] != color)) && y - 2 >= 0 && y + 1 <= 14 && chessColors[x][y + 1] == color && chessColors[x][y - 1] == color && chessColors[x][y - 2] == null) {
            score += THREE_DEAD;
        }
        /**
         * _
         * x
         * o
         * o
         * ●
         */
        if ((y + 2 == 14 || (y + 3 <= 14 && chessColors[x][y + 3] != color)) && y - 1 >= 0 && y + 2 <= 14 && chessColors[x][y + 1] == color && chessColors[x][y + 2] == color && chessColors[x][y - 1] == null) {
            score += THREE_DEAD;
        }

        /**
         * ●
         *  x
         *   o
         *    o
         *      _
         */
        if ((x == 0 || y == 0 || (x - 1 >= 0 && y - 1 >= 0 && chessColors[x - 1][y - 1] != color)) && x + 3 <= 14 && y + 3 <= 14 && chessColors[x + 1][y + 1] == color && chessColors[x + 2][y + 2] == color && chessColors[x + 3][y + 3] == null) {
            score += THREE_DEAD;
        }
        /**
         * ●
         *  o
         *   x
         *    o
         *      _
         */
        if ((x - 1 == 0 || y - 1 == 0 || (x - 2 >= 0 && y - 2 >= 0 && chessColors[x - 2][y - 2] != color)) && x - 1 >= 0 && y - 1 >= 0 && chessColors[x - 1][y - 1] == color && x + 2 <= 14 && y + 2 <= 14 && chessColors[x + 1][y + 1] == color && chessColors[x + 2][y + 2] == null) {
            score += THREE_DEAD;
        }
        /**
         * ●
         *  o
         *   o
         *    x
         *      _
         */
        if ((x - 2 == 0 || y - 2 == 0 || (x - 3 >= 0 && y - 3 >= 0 && chessColors[x - 3][y - 3] != color)) && x - 2 >= 0 && y - 2 >= 0 && x + 2 <= 14 && y + 2 <= 14 && chessColors[x - 1][y - 1] == color && chessColors[x - 2][y - 2] == color && chessColors[x + 1][y + 1] == null) {
            score += THREE_DEAD;
        }

        /**
         *  _
         *   o
         *    o
         *     x
         *      ●
         */
        if ((x == 14 || y == 14 || (x + 1 <= 14 && y + 1 <= 14 && chessColors[x + 1][y + 1] != color)) && x - 3 >= 0 && y - 3 >= 0 && chessColors[x - 1][y - 1] == color && chessColors[x - 2][y - 2] == color && chessColors[x - 3][y - 3] == null) {
            score += THREE_DEAD;
        }
        /**
         *  _
         *   o
         *    x
         *     o
         *      ●
         */
        if ((x + 1 == 14 || y + 1 == 14 || (x + 2 <= 14 && y + 2 <= 14 && chessColors[x + 2][y + 2] != color)) && x - 2 >= 0 && y - 2 >= 0 && x + 1 <= 14 && y + 1 <= 14 && chessColors[x + 1][y + 1] == color && chessColors[x - 1][y - 1] == color && chessColors[x - 2][y - 2] == null) {
            score += THREE_DEAD;
        }
        /**
         * _
         *   x
         *    o
         *     o
         *      ●
         */
        if ((x + 2 == 14 || y + 2 == 14 || (x + 3 <= 14 && y + 3 <= 14 && chessColors[x + 3][y + 3] != color)) && x - 1 >= 0 && y - 1 >= 0 && x + 2 <= 14 && y + 2 <= 14 && chessColors[x + 1][y + 1] == color && chessColors[x + 2][y + 2] == color && chessColors[x - 1][y - 1] == null) {
            score += THREE_DEAD;
        }

        /**
         *      ●
         *     x
         *    o
         *   o
         * _
         */
        if ((x == 14 || y == 0 || (x + 1 <= 14 && y - 1 >= 0 && chessColors[x + 1][y - 1] != color)) && x - 3 >= 0 && y + 3 <= 14 && chessColors[x - 1][y + 1] == color && chessColors[x - 2][y + 2] == color && chessColors[x - 3][y + 3] == null) {
            score += THREE_DEAD;
        }

        /**
         *      ●
         *     o
         *    x
         *   o
         * _
         */
        if ((x + 1 == 14 || y - 1 == 0 || (x + 2 <= 14 && y - 2 >= 0 && chessColors[x + 2][y - 2] != color)) && x - 2 >= 0 && y - 1 >= 0 && x + 1 <= 14 && y + 2 <= 14 && chessColors[x + 1][y - 1] == color && chessColors[x - 1][y + 1] == color && chessColors[x - 2][y + 2] == null) {
            score += THREE_DEAD;
        }

        /**
         *      ●
         *     o
         *    o
         *   x
         * _
         */
        if ((x + 2 == 14 || y - 2 == 0 || (x + 3 <= 14 && y - 3 >= 0 && chessColors[x + 3][y - 3] != color)) && x - 1 >= 0 && y - 2 >= 0 && x + 2 <= 14 && y + 1 <= 14 && chessColors[x + 1][y - 1] == color && chessColors[x + 2][y - 2] == color && chessColors[x - 1][y + 1] == null) {
            score += THREE_DEAD;
        }

        /**
         *     _
         *    o
         *   o
         *  x
         * ●
         */
        if ((x == 0 || y == 14 || (x - 1 >= 0 && y + 1 <= 14 && chessColors[x - 1][y + 1] != color)) && y - 3 >= 0 && x + 3 <= 14 && chessColors[x + 1][y - 1] == color && chessColors[x + 2][y - 2] == color && chessColors[x + 3][y - 3] == null) {
            score += THREE_DEAD;
        }
        /**
         *     _
         *    o
         *   x
         *  o
         * ●
         */
        if ((x - 1 == 0 || y + 1 == 14 || (x - 2 >= 0 && y + 2 <= 14 && chessColors[x - 2][y + 2] != color)) && x - 1 >= 0 && y - 2 >= 0 && x + 2 <= 14 && y + 1 <= 14 && chessColors[x - 1][y + 1] == color && chessColors[x + 1][y - 1] == color && chessColors[x + 2][y - 2] == null) {
            score += THREE_DEAD;
        }
        /**
         *     _
         *    x
         *   o
         *  o
         * ●
         */
        if ((x - 2 == 0 || y + 2 == 14 || (x - 3 >= 0 && y + 3 <= 14 && chessColors[x - 3][y + 3] != color)) && x - 2 >= 0 && y - 1 >= 0 && x + 1 <= 14 && y + 2 <= 14 && chessColors[x - 1][y + 1] == color && chessColors[x - 2][y + 2] == color && chessColors[x + 1][y - 1] == null) {
            score += THREE_DEAD;
        }

        //活2
        //_xo_
        if (x - 1 >= 0 && x + 2 <= 14 && chessColors[x - 1][y] == null && chessColors[x + 1][y] == color && chessColors[x + 2][y] == null) {
            score += TWO_LIVE;
        }
        //_ox_
        if (x - 2 >= 0 && x + 1 <= 14 && chessColors[x - 1][y] == color && chessColors[x - 2][y] == null && chessColors[x + 1][y] == null) {
            score += TWO_LIVE;
        }

        /**
         * _
         * x
         * o
         * _
         */
        if (y - 1 >= 0 && y + 2 <= 14 && chessColors[x][y - 1] == null && chessColors[x][y + 1] == color && chessColors[x][y + 2] == null) {
            score += TWO_LIVE;
        }
        /**
         * _
         * o
         * x
         * _
         */
        if (y - 2 >= 0 && y + 1 <= 14 && chessColors[x][y - 2] == null && chessColors[x][y - 1] == color && chessColors[x][y + 1] == null) {
            score += TWO_LIVE;
        }

        /**
         * _
         *  x
         *   o
         *    _
         */
        if (x - 1 >= 0 && y - 1 >= 0 && x + 2 <= 14 && y + 2 <= 14 && chessColors[x - 1][y - 1] == null && chessColors[x + 1][y + 1] == color && chessColors[x + 2][y + 2] == null) {
            score += TWO_LIVE;
        }
        /**
         * _
         *  o
         *   x
         *     _
         */
        if (x - 2 >= 0 && y - 2 >= 0 && x + 1 <= 14 && y + 1 <= 14 && chessColors[x - 2][y - 2] == null && chessColors[x - 1][y - 1] == color && chessColors[x + 1][y + 1] == null) {
            score += TWO_LIVE;
        }


        /**
         *    _
         *   o
         *  x
         * _
         */
        if (x - 1 >= 0 && y - 2 >= 0 && x + 2 <= 14 && y + 1 <= 14 && chessColors[x - 1][y + 1] == null && chessColors[x + 1][y - 1] == color && chessColors[x + 2][y - 2] == null) {
            score += TWO_LIVE;
        }
        /**
         *    _
         *   x
         *  o
         * _
         */
        if (x - 2 >= 0 && y - 1 >= 0 && x + 1 <= 14 && y + 2 <= 14 && chessColors[x - 2][y + 2] == null && chessColors[x - 1][y + 1] == color && chessColors[x + 1][y - 1] == null) {
            score += TWO_LIVE;
        }

        //死二
        //●x○_
        if ((x == 0 || (x - 1 >= 0 && x + 2 <= 14 && chessColors[x - 1][y] != color)) && chessColors[x + 1][y] == color && chessColors[x + 2][y] == null) {
            score += TWO_DEAD;
        }
        //●○x_
        if ((x - 1 == 0 || (x - 2 >= 0 && chessColors[x - 2][y] != color)) && x + 1 <= 14 && chessColors[x - 1][y] == color && chessColors[x + 1][y] == null) {
            score += TWO_DEAD;
        }

        //_○x●
        if ((x == 14 || (x + 1 <= 14 && chessColors[x + 1][y] != color)) && x - 2 >= 0 && chessColors[x - 1][y] == color && chessColors[x - 2][y] == null) {
            score += TWO_DEAD;
        }
        //_x○●
        if ((x + 1 == 14 || (x + 2 <= 14 && chessColors[x + 2][y] != color)) && x - 1 >= 0 && x + 1 <= 14 && chessColors[x + 1][y] == color && chessColors[x - 1][y] == null) {
            score += TWO_DEAD;
        }

        /**
         * ●
         * x
         * ○
         * _
         */
        if ((y == 0 || (y - 1 >= 0 && chessColors[x][y - 1] != color)) && y + 2 <= 14 && chessColors[x][y + 1] == color && chessColors[x][y + 2] == null) {
            score += TWO_DEAD;
        }
        /**
         * ●
         * o
         * x
         * _
         */
        if ((y - 1 == 0 || (y - 2 >= 0 && chessColors[x][y - 2] != color)) && y + 1 <= 14 && chessColors[x][y - 1] == color && chessColors[x][y + 1] == null) {
            score += TWO_DEAD;
        }

        /**
         * _
         * o
         * x
         * ●
         */
        if ((y == 14 || (y + 1 <= 14 && chessColors[x][y + 1] != color)) && y - 2 >= 0 && chessColors[x][y - 1] == color && chessColors[x][y - 2] == null) {
            score += TWO_DEAD;
        }
        /**
         * _
         * x
         * o
         * ●
         */
        if ((y + 1 == 14 || (y + 2 <= 14 && chessColors[x][y + 2] != color)) && y - 1 >= 0 && y + 1 <= 14 && chessColors[x][y + 1] == color && chessColors[x][y - 1] == null) {
            score += TWO_DEAD;
        }

        /**
         * ●
         *  x
         *   o
         *     _
         */
        if ((x == 0 || y == 0 || (x - 1 >= 0 && y - 1 >= 0 && chessColors[x - 1][y - 1] != color)) && x + 2 <= 14 && y + 2 <= 14 && chessColors[x + 1][y + 1] == color && chessColors[x + 2][y + 2] == null) {
            score += TWO_DEAD;
        }
        /**
         * ●
         *  o
         *   x
         *     _
         */
        if ((x - 1 == 0 || y - 1 == 0 || (x - 2 >= 0 && y - 2 >= 0 && chessColors[x - 2][y - 2] != color)) && x - 1 >= 0 && y - 1 >= 0 && x + 1 <= 14 && y + 1 <= 14 && chessColors[x - 1][y - 1] == color && chessColors[x + 1][y + 1] == null) {
            score += TWO_DEAD;
        }

        /**
         *   _
         *    o
         *     x
         *      ●
         */
        if ((x == 14 || y == 14 || (x + 1 <= 14 && y + 1 <= 14 && chessColors[x + 1][y + 1] != color)) && x - 2 >= 0 && y - 2 >= 0 && chessColors[x - 1][y - 1] == color && chessColors[x - 2][y - 2] == null) {
            score += TWO_DEAD;
        }
        /**
         *   _
         *    x
         *     o
         *      ●
         */
        if ((x + 1 == 14 || y + 1 == 14 || (x + 2 <= 14 && y + 2 <= 14 && chessColors[x + 2][y + 2] != color)) && x - 1 >= 0 && y - 1 >= 0 && x + 1 <= 14 && y + 1 <= 14 && chessColors[x + 1][y + 1] == color && chessColors[x - 1][y - 1] == null) {
            score += TWO_DEAD;
        }

        /**
         *      ●
         *     x
         *    o
         *  _
         */
        if ((x == 14 || y == 0 || (x + 1 <= 14 && y - 1 >= 0 && chessColors[x + 1][y - 1] != color)) && x - 2 >= 0 && y + 2 <= 14 && chessColors[x - 1][y + 1] == color && chessColors[x - 2][y + 2] == null) {
            score += TWO_DEAD;
        }

        /**
         *      ●
         *     o
         *    x
         *  _
         */
        if ((x + 1 == 14 || y - 1 == 0 || (x + 2 <= 14 && y - 2 >= 0 && chessColors[x + 2][y - 2] != color)) && x - 1 >= 0 && y - 1 >= 0 && x + 1 <= 14 && y + 1 <= 14 && chessColors[x + 1][y - 1] == color && chessColors[x - 1][y + 1] == null) {
            score += TWO_DEAD;
        }


        /**
         *    _
         *   o
         *  x
         * ●
         */
        if ((x == 0 || y == 14 || (x - 1 >= 0 && y + 1 <= 14 && chessColors[x - 1][y + 1] != color)) && y - 2 >= 0 && x + 2 <= 14 && chessColors[x + 1][y - 1] == color && chessColors[x + 2][y - 2] == null) {
            score += TWO_DEAD;
        }
        /**
         *    _
         *   x
         *  o
         * ●
         */
        if ((x - 1 == 0 || y + 1 == 14 || (x - 2 >= 0 && y + 2 <= 14 && chessColors[x - 2][y + 2] != color)) && x - 1 >= 0 && y - 1 >= 0 && x + 1 <= 14 && y + 1 <= 14 && chessColors[x - 1][y + 1] == color && chessColors[x + 1][y - 1] == null) {
            score += TWO_DEAD;
        }

        ////////////////////////////
        return score;
    }

    /**
     * 判断某个坐标横向某个棋子赢了
     *
     * @param x
     * @param y
     * @param color
     * @return
     */
    private boolean isWinHorizontal(int x, int y, ChessColor color) {
        if (x > 10) {
            return false;
        }
        boolean ret = true;
        for (int i = 0; i < 5; i++) {
            if (chessColors[x + i][y] != color) {
                ret = false;
                break;
            }
        }
        return ret;
    }

    /**
     * 判断某个坐标竖向某个棋子赢了
     *
     * @param x
     * @param y
     * @param color
     * @return
     */
    private boolean isWinVertical(int x, int y, ChessColor color) {
        if (y > 10) {
            return false;
        }
        boolean ret = true;
        for (int i = 0; i < 5; i++) {
            if (chessColors[x][y + i] != color) {
                ret = false;
                break;
            }
        }
        return ret;
    }

    /**
     * 判断某个坐标左斜方向某个棋子赢了
     *
     * @param x
     * @param y
     * @param color
     * @return
     */
    private boolean isWinLeftSlant(int x, int y, ChessColor color) {
        if (x < 4 || y > 10) {
            return false;
        }
        boolean ret = true;
        for (int i = 0; i < 5; i++) {
            if (chessColors[x - i][y + i] != color) {
                ret = false;
                break;
            }
        }
        return ret;
    }

    /**
     * 判断某个坐标右斜方向某个棋子赢了
     *
     * @param x
     * @param y
     * @param color
     * @return
     */
    private boolean isWinRightSlant(int x, int y, ChessColor color) {
        if (x > 10 || y > 10) {
            return false;
        }
        boolean ret = true;
        for (int i = 0; i < 5; i++) {
            if (chessColors[x + i][y + i] != color) {
                ret = false;
                break;
            }
        }
        return ret;
    }

    /**
     * 是否已经赢了
     * null 未结束
     * ChessColor.BLACK 黑胜
     * ChessColor.WHITE 白胜
     */
    public ChessColor isWin() throws Exception {
        /**
         * 一共四种情况，
         * 1、横排胜利
         * 2、竖排胜利
         * 3、左下斜排胜利
         * 4、右下斜排胜利
         */
        if (this.chessColors == null) {
            return null;
        }
        //是否和局（满盘和局）
        boolean isBalance = true;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (chessColors[i][j] == null) {
                    isBalance = false;
                }
                //1、黑方胜
                if (isWinHorizontal(i, j, ChessColor.BLACK) ||
                        isWinVertical(i, j, ChessColor.BLACK) ||
                        isWinLeftSlant(i, j, ChessColor.BLACK) ||
                        isWinRightSlant(i, j, ChessColor.BLACK)) {
                    return ChessColor.BLACK;
                }
                //2、白方胜
                if (isWinHorizontal(i, j, ChessColor.WHITE) ||
                        isWinVertical(i, j, ChessColor.WHITE) ||
                        isWinLeftSlant(i, j, ChessColor.WHITE) ||
                        isWinRightSlant(i, j, ChessColor.WHITE)) {
                    return ChessColor.WHITE;
                }
            }
        }
        if (isBalance) {
            throw new Exception("满盘和局");
        }
        return null;
    }
}