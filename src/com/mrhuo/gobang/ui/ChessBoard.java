/*
 * Copyright  (c) mrhuo.com 2017.
 */

package com.mrhuo.gobang.ui;

import com.mrhuo.gobang.bean.ChessColor;
import com.mrhuo.gobang.bean.ChessPoint;
import com.mrhuo.gobang.bean.GameAction;
import com.mrhuo.gobang.bean.GameData;
import com.mrhuo.gobang.common.CONSTANT;
import com.mrhuo.gobang.events.DropChessListener;
import com.mrhuo.gobang.events.OnGameOverListener;
import com.mrhuo.gobang.events.OnReceiveServerActionListener;
import com.mrhuo.gobang.logic.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;

import static com.mrhuo.gobang.common.CONSTANT.*;

/**
 * 棋盘
 */
public class ChessBoard extends JPanel implements DropChessListener, OnReceiveServerActionListener, OnGameOverListener {

    /**
     * 封装了游戏逻辑
     */
    private static final GameLogic gameLogic = GameLogic.getInstance();
    private final GameInfo gameInfo;
    private final Image black, white, empty;
    private int moveX, moveY;

    /**
     * 构造方法
     *
     * @param gameInfo
     */
    public ChessBoard(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
        this.setSize(500, 500);
        this.setBackground(CONSTANT.defaultChessBoardColor);

        black = CONSTANT.getImage("black.png");
        white = CONSTANT.getImage("white.png");
        empty = CONSTANT.getImage("empty.png");

        gameLogic.setDropChessListener(this);
        gameLogic.setOnReceiveServerActionListener(this);
        gameLogic.setOnGameOverListener(this);

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (!gameLogic.isGameStart()) {
                    return;
                }
                moveX = e.getX();
                moveY = e.getY();
                repaint();
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameLogic.dropChess(e.getPoint());
            }
        });
    }

    /**
     * 重写 paint 方法
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintGrid(g);
        paintChessMan(g);
        if (gameLogic.isGameStart())
            paintCursor(g);
    }

    /**
     * 画光标位置
     *
     * @param g
     */
    private void paintCursor(Graphics g) {
        if (!gameLogic.isAvaliableArea(moveX, moveY)) {
            return;
        }
        ChessPoint chessPoint = ChessPoint.transformPoint(moveX, moveY);
        Point point = ChessPoint.transformToClientPoint(chessPoint.getX(), chessPoint.getY());
        g.drawImage(empty, (int) point.getX(), (int) point.getY(), 20, 20, null);
    }

    /**
     * 画棋盘
     *
     * @param g
     */
    private void paintGrid(Graphics g) {
        for (int i = 0; i < 15; i++) {
            g.drawLine(offsetSizeX + 0, offsetSizeY + i * gridSize, offsetSizeX + 14 * gridSize, offsetSizeY + i * gridSize);
            g.drawLine(offsetSizeX + i * 30, offsetSizeY + 0, offsetSizeX + i * gridSize, offsetSizeY + 14 * gridSize);

            //画棋盘上的黑点和最中间的点
            if (i == 3 || i == 11 || i == 7) {
                g.fillOval(offsetSizeX + i * gridSize - 3, offsetSizeY + i * gridSize - 3, 6, 6);
                //第四行和第十二行有两个点
                if (i == 3) {
                    g.fillOval(offsetSizeX + i * gridSize - 3, offsetSizeY + 11 * gridSize - 3, 6, 6);
                } else if (i == 11) {
                    g.fillOval(offsetSizeX + i * gridSize - 3, offsetSizeY + 3 * gridSize - 3, 6, 6);
                }
            }

            //画边界线上的数字
            g.drawString((i + 1) + "", offsetSizeX + i * gridSize - 4, offsetSizeY - 8);
            //小于10的数字稍微往右画一些
            if (i < 9) {
                g.drawString((i + 1) + "", offsetSizeX - 14, offsetSizeY + i * gridSize + 6);
            } else {
                g.drawString((i + 1) + "", offsetSizeX - 20, offsetSizeY + i * gridSize + 6);
            }
        }
        Graphics2D g2 = (Graphics2D) g;
        //边界线比较粗
        g2.setStroke(new BasicStroke(2.0f));
        g.drawLine(offsetSizeX + 0, offsetSizeY + 0 * gridSize, offsetSizeX + 14 * gridSize, offsetSizeY + 0 * gridSize);
        g.drawLine(offsetSizeX + 0, offsetSizeY + 14 * gridSize, offsetSizeX + 14 * gridSize, offsetSizeY + 14 * gridSize);
    }

    /**
     * 画棋子
     *
     * @param g
     */
    private void paintChessMan(Graphics g) {
        ChessColor[][] currentChess = gameLogic.getChess();
        Image image;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                ChessColor color = currentChess[i][j];
                Point point = ChessPoint.transformToClientPoint(i, j);
                if (color != null) {
                    image = color == ChessColor.BLACK ? black : white;
                    g.drawImage(image,
                            (int) point.getX(),
                            (int) point.getY(),
                            20, 20, null);
                }
            }
        }
    }

    @Override
    public void onDropChess(int x, int y, ChessColor color) {
        if (gameLogic.isGameStart()) {
            this.gameInfo.updateGameStatus("轮到" +
                    gameLogic.getCurrentChessColor().getChessColorCNName() +
                    "方下棋"
            );
            repaint();
        }
    }

    @Override
    public void onReceiveServerAction(Socket clientSocket, GameAction gameAction, GameData gameData) {
        if (GameAction.USER_DROP_CHESS.equals(gameAction)) {
            this.gameInfo.updateGameStatus("轮到" +
                    gameLogic.getCurrentChessColor().getChessColorCNName() +
                    "方下棋"
            );
        } else if (GameAction.SERVER_RELOGIN.equals(gameAction)) {
            CONSTANT.debug("请勿重复登录");
        }
        repaint();
    }

    @Override
    public void onGameOver(ChessColor winner) {
        if (winner == null) {
            this.gameInfo.updateGameStatus("游戏结束，满盘和局");
            CONSTANT.alertUser("游戏结束，满盘和局");
        } else {
            this.gameInfo.updateGameStatus("游戏结束，" + winner.getChessColorCNName() + "方胜");
            CONSTANT.alertUser("游戏结束，" + winner.getChessColorCNName() + "方胜");
        }
        repaint();
    }
}