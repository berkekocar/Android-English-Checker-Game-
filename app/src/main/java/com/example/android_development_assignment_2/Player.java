package com.example.android_development_assignment_2;

import android.util.Log;


public class Player {

    public static Player[] players = new Player[2];

    public static void endPlayerTurn() {
        for (Player pP : players) {
            if (pP.isPlayersTurn){
                for (int X = 0; X < pP.pieces.length; X++) {
                    for (int Y = 0; Y < pP.pieces.length; Y++) {
                        pP.pieces[X][Y].isHighlighted = Boolean.FALSE;
                    }
                }
            }
            pP.isPlayersTurn = !pP.isPlayersTurn;
        }
        ClickEvent.lastEvent = ClickEvent.Type.Event_Nothing;
    }

    public static Player getEnemyPlayer() {
        if (players[0].isPlayersTurn)
            return players[1];
        else
            return players[0];
    }

    public static Player getCurrentPlayer() {
        if (players[1].isPlayersTurn)
            return players[1];
        else
            return players[0];
    }


    boolean isTopPlayer;
    //     X->Y
    Piece[][] pieces;

    int score = 0;
    boolean isPlayersTurn;

    public Player(boolean isTopPlayer, int boxSize, int howManyY) {
        this.isTopPlayer = isTopPlayer;
        initPlayersPieces(isTopPlayer, boxSize, howManyY);

        if (!isTopPlayer) {
            isPlayersTurn = true;
            players[0] = this;
        } else {
            players[1] = this;
        }

    }

    public void selectPiece(int x, int y) {
        for (int X = 0; X < pieces.length; X++) {
            for (int Y = 0; Y < pieces.length; Y++) {
                pieces[X][Y].isHighlighted = Boolean.FALSE;
            }
        }
        pieces[x][y].isHighlighted= Boolean.TRUE;
        Log.i("Highlight piece", "x:" + x + " - y:" + y);
    }

    public ClickEvent.Type pieceActionTo(int selectedX, int selectedY, int moveX, int moveY) {
        Player enemyPlayer = getEnemyPlayer();

        int moveDirectionX = 0;
        int moveDirectionY = 0;

        //TODO: King icin farkli dusun
        if (moveX == selectedX + 1) {
            moveDirectionX = +1;
        } else if (moveX == selectedX - 1) {
            moveDirectionX = -1;
        } else {
            return ClickEvent.Type.Event_Nothing;
        }
        if (isTopPlayer) {
            moveDirectionY = -1;
        } else {
            moveDirectionY = +1;
        }
        if (selectedY + moveDirectionY != moveY)
            return ClickEvent.Type.Event_Nothing;



        if (enemyPlayer.hasPieceAtIndex(moveX, moveY)) {
            if( enemyPlayer.hasPieceAtIndex(moveX+moveDirectionX,moveY+moveDirectionY) || hasPieceAtIndex(moveX+moveDirectionX,moveY+moveDirectionY)){
                //yenilecek tasin arkasinda baska bir tas var ondan yiyemezsin
                return ClickEvent.Type.Event_Nothing;
            }
            //eat and move through
            movePiece(selectedX, selectedY, moveX + moveDirectionX, moveY + moveDirectionY);
            score++;
            //delete enemy piece
            enemyPlayer.pieces[moveX][moveY].isActive=false;
            //TODO: birden fazla tas yiyebiliyor ise farkli bir event donmek gerekebilir onu da diger tarafat kontrol edip yemeye devam et diyebilirsin
            return ClickEvent.Type.Event_EatPiece;
        } else if (!hasPieceAtIndex(moveX, moveY)) {
            //only move
            movePiece(selectedX, selectedY, moveX, moveY);
            return ClickEvent.Type.Event_MovePiece;
        }
        return ClickEvent.Type.Event_Nothing;
    }

    private void movePiece(int selectedX, int selectedY, int toX, int toY) {
        pieces[toX][toY] = pieces[selectedX][selectedY];
        pieces[selectedX][selectedY] = new Piece();
    }

    public boolean hasPieceAtIndex(int x, int y) {
        return pieces[x][y].isActive;
    }

    private void initPlayersPieces(boolean isTopPlayer, int boxSize, int howManyY) {
        pieces = new Piece[boxSize][boxSize];
        for (int x = 0; x < boxSize; x++) {
            for (int y = 0; y < boxSize; y++) {
                pieces[x][y]=new Piece();
            }
        }
        if (!isTopPlayer) {
            for (int x = 0; x < boxSize; x++) {
                for (int y = 0; y < howManyY; y++) {
                    if (y % 2 == 0) {
                        //soldan ilk kutu dolu olacak
                        if (x % 2 == 0)
                            pieces[x][y].setPiece();
                    } else {
                        //soldan ilk kutu bos olacak
                        if (x % 2 == 1)
                            pieces[x][y].setPiece();
                    }
                }
            }
        } else {
            for (int x = 0; x < boxSize; x++) {
                for (int y = boxSize - 1; y >= boxSize - howManyY; y--) {
                    if (y % 2 == 1) {
                        //soldan ilk kutu bos olacak
                        if (x % 2 == 1)
                            pieces[x][y].setPiece();
                    } else {
                        //soldan ilk kutu dolu olacak
                        if (x % 2 == 0)
                            pieces[x][y].setPiece();
                    }
                }
            }
        }
    }
}
