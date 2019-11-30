package com.example.android_development_assignment_2;

public class ClickEvent {
    public static enum Type{
        Event_Nothing,
        Event_WrongPiece,
        Event_SelectPiece,
        Event_MovePiece,
        Event_MovePieceAndAskToContinue,
        Event_EatPiece,
    }

    public static Type lastEvent;
    public static int xIndex,yIndex;

}
