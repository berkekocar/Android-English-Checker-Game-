package com.example.android_development_assignment_2;

public class Piece {
    Boolean isActive=false;
    Boolean isHighlighted;
    Boolean isKing;

    public Piece(){
        isActive=Boolean.FALSE;
        isHighlighted=Boolean.FALSE;
        isKing=Boolean.FALSE;
    }

    public void setPiece(){
        isActive=Boolean.TRUE;
    }
}
