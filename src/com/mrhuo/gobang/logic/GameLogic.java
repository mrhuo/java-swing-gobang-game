/*
 * Copyright  (c) mrhuo.com 2017.
 */

package com.mrhuo.gobang.logic;

import com.mrhuo.gobang.bean.*;
import com.mrhuo.gobang.common.CONSTANT;
import com.mrhuo.gobang.events.DropChessListener;
import com.mrhuo.gobang.events.LoginListener;
import com.mrhuo.gobang.events.OnGameOverListener;
import com.mrhuo.gobang.events.OnReceiveServerActionListener;
import com.mrhuo.gobang.ui.LoginFrame;

import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static com.mrhuo.gobang.common.CONSTANT.*;

public class GameLogic {

    private static GameLogic gameLogicInstance;
    private User currentUser;
    private LoginListener loginListener;
    private DropChessListener dropChessListener;
    private OnReceiveServerActionListener onReceiveServerActionListener;
    private OnGameOverListener onGameOverListener;
    private ChessColor[][] chessColors;
    //当前正在下的棋子颜色
    private ChessColor currentChessColor = ChessColor.BLACK;
    private boolean isGameStart = false;
    private GameMode gameMode = null;
    private Socket clientSocket;
    private GameRuler ruler;
    private Thread receiveThread;
    private boolean isAutoTest = false;

    private GameLogic() {
        this.chessColors = new ChessColor[15][15];
        this.ruler = new GameRuler();
    }

    /**
     * 单例模式
     *
     * @return
     */
    public synchronized static GameLogic getInstance() {
        if (gameLogicInstance == null) {
            gameLogicInstance = new GameLogic();
        }
        return gameLogicInstance;
    }

    /**
     * 设置登录监听器
     *
     * @param listener
     */
    public void setLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }

    /**
     * 设置接收服务器消息的监听器
     *
     * @param listener
     */
    public void setOnReceiveServerActionListener(OnReceiveServerActionListener listener) {
        this.onReceiveServerActionListener = listener;
    }

    /**
     * 设置落子监听器
     *
     * @param dropChessListener
     */
    public void setDropChessListener(DropChessListener dropChessListener) {
        this.dropChessListener = dropChessListener;
    }

    /**
     * 设置游戏结束监听器
     *
     * @param onGameOverListener
     */
    public void setOnGameOverListener(OnGameOverListener onGameOverListener) {
        this.onGameOverListener = onGameOverListener;
    }

    /**
     * 获取当前应该下的棋子颜色
     *
     * @return
     */
    public ChessColor getCurrentChessColor() {
        return currentChessColor;
    }

    /**
     * 创建到服务器的连接
     *
     * @return
     */
    private synchronized boolean startSocket() {
        try {
            if (this.clientSocket != null && !this.clientSocket.isClosed()) {
                this.clientSocket.close();
            }
            if (this.receiveThread != null) {
                this.receiveThread.interrupt();
            }
        } catch (Exception ignored) {
        }
        try {
            this.clientSocket = new Socket(CONSTANT.serverAddress, CONSTANT.serverPort);

            receiveThread = new Thread(new clientReceiveThread(this.clientSocket));
            receiveThread.start();

            //Thread heartBeatThread = new Thread(new heartBeatThread(this.clientSocket));
            //heartBeatThread.start();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 登陆到服务器
     */
    public void loginToServer() {
        if (!startSocket()) {
            CONSTANT.alertUser("连接服务器失败，无法登录，稍后再试~~");
            return;
        }
        try {
            CONSTANT.sendData(this.clientSocket.getOutputStream(),
                    GameData.createData(GameAction.USER_LOGIN)
                            .setUser(this.currentUser)
            );
            //this.loginListener.onLoginSuccess(user);
        } catch (Exception ex) {
            CONSTANT.alertUser("登录失败，稍后再试~~");
            ex.printStackTrace();
        }
    }

    /**
     * 登录功能
     */
    public void login() {
        if (this.loginListener == null) {
            CONSTANT.debug("login(): loginListener can't be null");
            return;
        }
        LoginFrame loginFrame = new LoginFrame(null, true);
        loginFrame.setVisible(true);
        User user = loginFrame.getLoginedUser();
        if (user == null) {
            this.loginListener.onLoginFailed("取消登录");
            return;
        }

        this.currentUser = user;
        this.loginListener.onLoginSuccess(user);
    }

    /**
     * 退出登录功能
     */
    public void logout() {
        if (this.loginListener == null) {
            CONSTANT.debug("login(): loginListener can't be null");
            return;
        }
        //人人对弈时需要登录服务器
        if (GameMode.WITH_PEOPLE == this.getGameMode()) {
            try {
                CONSTANT.sendData(this.clientSocket.getOutputStream(),
                        GameData.createData(GameAction.USER_LOGOUT)
                                .setUser(this.currentUser)
                );
                this.clientSocket.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        this.chessColors = new ChessColor[15][15];
        this.currentUser = null;
        this.isGameStart = false;
        this.loginListener.onLoginFailed("退出登录");
    }

    /**
     * 在鼠标点击区域落子
     *
     * @param point
     */
    public void dropChess(Point point) {
        if (!this.isUserLogined()) {
            CONSTANT.alertUser("请您先登录！");
            return;
        }
        if (!this.isGameStart()) {
            CONSTANT.alertUser("游戏还未开始！");
            return;
        }
        if (currentChessColor != this.getCurrentUser().getUserChessColor()) {
            CONSTANT.alertUser("当前轮到" + currentChessColor.getChessColorCNName() + "方下棋");
            return;
        }
        if (!isAvaliableArea(point)) {
            return;
        }
        ChessPoint chessPoint = ChessPoint.transformPoint(point);
        if (this.hasChessOnPoint(chessPoint)) {
            return;
        }
        //如果当前位置没有棋子
        if (GameMode.WITH_PEOPLE == gameMode) {
            try {
                GameData gameData = GameData.createData(GameAction.USER_DROP_CHESS)
                        .setUser(this.currentUser)
                        .setPoint(chessPoint);
                CONSTANT.sendData(this.clientSocket.getOutputStream(), gameData);
                CONSTANT.debug("已发送数据到服务器：" + gameData);

                this.putChess(chessPoint);
                if (this.dropChessListener != null) {
                    this.dropChessListener.onDropChess(chessPoint.getX(), chessPoint.getY(), currentChessColor);
                }
            } catch (Exception ex) {
                CONSTANT.alertUser("发送数据到服务器失败，稍后再试~~");
                ex.printStackTrace();
            }
        } else {
            ruler.setChess(chessColors);
            this.putChess(chessPoint);
            if (this.dropChessListener != null) {
                this.dropChessListener.onDropChess(chessPoint.getX(), chessPoint.getY(), currentChessColor);
            }
            computerDropChess();
        }
    }

    /**
     * 电脑随机下棋
     */
    private void computerDropChess() {
        //电脑增加了AI
        ChessPoint bestPoint = this.getBestPoint();
        this.putChess(bestPoint);
        if (this.dropChessListener != null) {
            this.dropChessListener.onDropChess(bestPoint.getX(), bestPoint.getY(), currentChessColor);
        }
    }

    //电脑计算得到最优点
    private ChessPoint getBestPoint() {
        int maxScore = 0;
        java.util.List<ChessPoint> pointList = new ArrayList<>();
        java.util.List<ChessPoint> allPoint = new ArrayList<ChessPoint>();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (chessColors[i][j] != null) continue;
                int tempScore = ruler.calScore(i, j, ChessColor.BLACK) + ruler.calScore(i, j, ChessColor.WHITE);
                if (maxScore < tempScore) maxScore = tempScore;
                ChessPoint p = new ChessPoint(i, j, tempScore);
                allPoint.add(p);
            }
        }

        java.util.List<ChessPoint> bestPoint = new ArrayList<ChessPoint>();
        for (ChessPoint p : allPoint) {
            if (p.getScore() == maxScore) {
                bestPoint.add(p);
            }
        }
        return bestPoint.get((int) (Math.random() * bestPoint.size()));
    }

    /**
     * 电脑先走一步（人机对战，电脑执黑时）
     */
    private void computerFirstStep() {
        ChessPoint point = new ChessPoint(7, 7);
        this.putChess(point);
        if (this.dropChessListener != null) {
            this.dropChessListener.onDropChess(point.getX(), point.getY(), currentChessColor);
        }
    }

    /**
     * 游戏是否开始
     *
     * @return
     */
    public boolean isGameStart() {
        return this.isGameStart;
    }

    /**
     * 用户是否已登录
     *
     * @return
     */
    public boolean isUserLogined() {
        return this.getCurrentUser() != null;
    }

    /**
     * 开启新的一盘游戏
     */
    public void startNewGame(GameMode gameMode, ChessColor userChessColor) {
        this.chessColors = new ChessColor[15][15];
        this.isGameStart = true;
        this.gameMode = gameMode;
        this.currentChessColor = ChessColor.BLACK;
        this.currentUser.setUserChessColor(userChessColor);

        if (GameMode.WITH_ROBOT == gameMode && userChessColor == ChessColor.WHITE) {
            computerFirstStep();
        }
    }

    /**
     * 获取当前游戏模式
     *
     * @return
     */
    public GameMode getGameMode() {
        return gameMode;
    }

    /**
     * 获得当前棋盘状况
     *
     * @return
     */
    public ChessColor[][] getChess() {
        return this.chessColors;
    }

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    public User getCurrentUser() {
        return this.currentUser;
    }

    /**
     * 交换下棋
     */
    private void turnSwap() {
        if (currentChessColor == ChessColor.BLACK) {
            currentChessColor = ChessColor.WHITE;
        } else if (currentChessColor == ChessColor.WHITE) {
            currentChessColor = ChessColor.BLACK;
        }
        CONSTANT.debug("交换场地：" + currentChessColor.getChessColorCNName() + "方");
    }

    /**
     * 落子逻辑
     *
     * @param point
     */
    private void putChess(ChessPoint point) {
        if (!this.isGameStart()) {
            return;
        }
        CONSTANT.debug("putChess: (" + point.getX() + ", " + point.getY() + ", " + point.getScore() + ") " + currentChessColor.getChessColorName());
        this.chessColors[point.getX()][point.getY()] = currentChessColor;
        ruler.setChess(chessColors);
        try {
            ChessColor isWin = ruler.isWin();
            if (isWin == null) {
                this.turnSwap();
            } else {
                //WIN!!
                if (GameMode.WITH_PEOPLE == gameMode) {
                    try {
                        GameData gameData = GameData.createData(GameAction.GAME_OVER)
                                .setUser(this.currentUser);
                        CONSTANT.sendData(this.clientSocket.getOutputStream(), gameData);
                        CONSTANT.debug("已发送数据到服务器：" + gameData);
                    } catch (Exception ex) {
                        CONSTANT.alertUser("发送数据到服务器失败，稍后再试~~");
                        ex.printStackTrace();
                    }
                }
                gameOver(isWin);
            }
        } catch (Exception ex) {
            gameOver(null);
        }
    }

    private void gameOver(ChessColor winColor) {
        this.isGameStart = false;
        if (this.onGameOverListener == null) {
            return;
        }
        this.onGameOverListener.onGameOver(winColor);
    }

    /**
     * 点击区域是否有效
     *
     * @param point
     * @return
     */
    public boolean isAvaliableArea(Point point) {
        return isAvaliableArea(point.getX(), point.getY());
    }

    /**
     * 这个坐标是否有效（在棋盘内）
     *
     * @return
     */
    public boolean isAvaliableArea(double x, double y) {
        int maxSize = 15 * gridSize;

        if (x >= offsetSizeX - 10 &&
                x <= maxSize + offsetSizeX - 10 &&
                y >= offsetSizeY - 10 &&
                y <= maxSize + offsetSizeY - 10) {
            return true;
        }
        return false;
    }

    /**
     * 落子处是否已经有了棋子
     *
     * @param point 这里的坐标点是转换后的棋盘内的坐标
     * @return
     */
    public boolean hasChessOnPoint(ChessPoint point) {
        return this.chessColors[point.getX()][point.getY()] != null;
    }

    /**
     * 停止自动测试
     */
    public void stopAutoTest() {
        if (!isAutoTest) {
            return;
        }
        isAutoTest = false;
        CONSTANT.debug("手动退出自动测试.");
        ruler.setChess(null);
        gameOver(null);
    }

    /**
     * 自动测试工具
     *
     * @return
     */
    public boolean startAutoTest() {
        if (isAutoTest) {
            return false;
        }
        if (!this.isUserLogined()) {
            CONSTANT.alertUser("请您先登录！");
            return false;
        }
        if (!this.isGameStart()) {
            CONSTANT.alertUser("游戏还未开始！");
            return false;
        }
        if (currentChessColor != this.getCurrentUser().getUserChessColor()) {
            CONSTANT.alertUser("当前轮到" + currentChessColor.getChessColorCNName() + "方下棋");
            return false;
        }
        CONSTANT.debug("开始自动测试...");
        isAutoTest = true;
        new Thread(() -> {
            while (isAutoTest) {
                try {
                    ChessColor isWin = ruler.isWin();
                    if (isWin == null) {
                        ChessPoint bestPoint;
                        if (ruler.getChessColors() == null) {
                            bestPoint = new ChessPoint(7, 7);
                        } else {
                            bestPoint = getBestPoint();
                        }
                        ruler.setChess(chessColors);
                        putChess(bestPoint);
                        if (dropChessListener != null) {
                            dropChessListener.onDropChess(bestPoint.getX(), bestPoint.getY(), currentChessColor);
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    isAutoTest = false;
                    gameOver(null);
                    break;
                }
            }
            CONSTANT.debug("自动退出自动测试.");
        }).start();
        return true;
    }

    /**
     * 心跳线程
     */
    class heartBeatThread implements Runnable {
        private Socket socket;

        public heartBeatThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (CONSTANT.isSocketConnected(socket)) {
                try {
                    Thread.sleep(30000);
                    CONSTANT.sendData(
                            socket.getOutputStream(),
                            GameData.createData(GameAction.HEART_BEAT).setUser(getCurrentUser()));
                } catch (Exception e) {
                    e.printStackTrace();
                    socket = null;
                    break;
                }
            }
        }
    }

    /**
     * 客户端接收线程
     */
    class clientReceiveThread implements Runnable {
        private Socket socket;

        public clientReceiveThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (CONSTANT.isSocketConnected(socket)) {
                try {
                    GameData gameData = CONSTANT.receiveData(socket.getInputStream());
                    if (gameData.getAction() == GameAction.USER_DROP_CHESS) {
                        if (!gameData.getUser().equals(currentUser)) {
                            chessColors[gameData.getPoint().getX()][gameData.getPoint().getY()]
                                    = gameData.getUser().getUserChessColor();
                            turnSwap();
                            if (onReceiveServerActionListener != null) {
                                onReceiveServerActionListener.onReceiveServerAction(
                                        socket,
                                        gameData.getAction(),
                                        gameData);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    socket = null;
                    break;
                }
            }
            CONSTANT.debug("SOCKET 已断开~");
        }
    }
}