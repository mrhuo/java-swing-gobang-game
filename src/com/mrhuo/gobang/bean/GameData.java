/*
 * Copyright  (c) mrhuo.com 2017.
 */

package com.mrhuo.gobang.bean;

import java.io.Serializable;

public class GameData implements Serializable {
    private User user;
    private ChessPoint point;
    private GameAction action;

    public GameData(GameAction action) {
        this.action = action;
    }

    public static GameData createData(GameAction action) {
        return new GameData(action);
    }

    public User getUser() {
        return user;
    }

    public GameData setUser(User user) {
        this.user = user;
        return this;
    }

    public ChessPoint getPoint() {
        return point;
    }

    public GameData setPoint(ChessPoint point) {
        this.point = point;
        return this;
    }

    public GameAction getAction() {
        return action;
    }

    public GameData setAction(GameAction action) {
        this.action = action;
        return this;
    }

    @Override
    public String toString() {
        return "GameData{" +
                "user=" + user +
                ", point=" + point +
                ", action=" + action +
                '}';
    }
}