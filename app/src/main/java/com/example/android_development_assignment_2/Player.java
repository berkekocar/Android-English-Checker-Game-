package com.example.android_development_assignment_2;

public class Player {
    boolean isTopPlayer;
    //     X->Y
    boolean[][] pieces;

    public Player(boolean isTopPlayer, int boxSize, int howManyY) {
        this.isTopPlayer = isTopPlayer;
        pieces = new boolean[boxSize][boxSize];

        if (!isTopPlayer) {
            for (int x = 0; x < boxSize; x++) {
                for (int y = 0; y < howManyY; y++) {
                    if (y % 2 == 0) {
                        //soldan ilk kutu dolu olacak
                        if (x % 2 == 0)
                            pieces[x][y] = true;
                    } else {
                        //soldan ilk kutu bos olacak
                        if (x % 2 == 1)
                            pieces[x][y] = true;
                    }
                }
            }
        } else {
            for (int x = 0; x < boxSize; x++) {
                for (int y = boxSize - 1; y >= boxSize - howManyY; y--) {
                    if (y % 2 == 1) {
                        //soldan ilk kutu bos olacak
                        if (x % 2 == 1)
                            pieces[x][y] = true;
                    } else {
                        //soldan ilk kutu dolu olacak
                        if (x % 2 == 0)
                            pieces[x][y] = true;
                    }
                }
            }
        }
    }
}