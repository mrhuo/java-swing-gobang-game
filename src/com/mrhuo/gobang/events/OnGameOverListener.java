/*
 * Copyright  (c) mrhuo.com 2017.
 */

package com.mrhuo.gobang.events;

import com.mrhuo.gobang.bean.ChessColor;

/**
 * 游戏结束监听器
 */
public interface OnGameOverListener {
    void onGameOver(ChessColor winner);
}
