package com.example.android_development_assignment_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class CustomView extends View {
    private static final String TAG = CustomView.class.getSimpleName();

    Context ctx;
    private int nSquares;


    private int[] square1;
    private int[] square2;
    private int[] player1Color;
    private int[] player2Color;

    private boolean touches[];
    private float touchx[], touchy[];
    private int first;
    private boolean touch;




    private Paint paint, paintPlayer1, paintPlayer2, paintHighlighted;
    private int squareDim;

    private int x, y;


    public CustomView(Context c) {
        super(c);
        init();
    }

    public CustomView(Context c, AttributeSet as) {
        super(c, as);
        init();
    }

    public CustomView(Context c, AttributeSet as, int default_style) {
        super(c, as, default_style);
        init();
    }


    private void init() {

        ClickEvent.lastEvent = ClickEvent.Type.Event_Nothing;

        nSquares = 8;


        square1 = new int[]{0, 100, 0};
        square2 = new int[]{255, 250, 205};
        paint = new Paint();
        player1Color = new int[]{178, 34, 34};
        player2Color = new int[]{0, 0, 0};
        int[] highlightColor = new int[]{0, 0, 255};
        paintPlayer1 = new Paint();
        paintPlayer2 = new Paint();
        paintPlayer1.setARGB(200, player1Color[0], player1Color[1], player1Color[2]);
        paintPlayer2.setARGB(200, player2Color[0], player2Color[1], player2Color[2]);
        paintHighlighted = new Paint();
        paintHighlighted.setARGB(200, highlightColor[0], highlightColor[1], highlightColor[2]);

        touches = new boolean[16];
        touchx = new float[16];
        touchy = new float[16];

        touch = false;





    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = (squareDim / 2);
        int count = 0;


        for (int row = 0; row < nSquares; row++) {
            if ((row & 1) == 0) {
                paint.setARGB(200, square1[0], square1[1], square1[2]);
                count++;
            } else {
                paint.setARGB(200, square2[0], square2[1], square2[2]);
                count++;
            }

//            paint.setFlags(Paint.ANTI_ALIAS_FLAG);

            for (int col = 0; col < nSquares; col++) {
                int a = col * squareDim;
                int b = row * squareDim;
                canvas.drawRect(a, b, a + squareDim, b + squareDim, paint);
                if (count == 2) {
                    paint.setARGB(200, square1[0], square1[1], square1[2]);
                    count = 1;
                } else {
                    paint.setARGB(200, square2[0], square2[1], square2[2]);
                    count = 2;
                }


            }

        }

        for (int row = 0; row < nSquares; row++) {
            for (int col = 0; col < nSquares; col++) {
                if (Player.players[0].pieces[row][col] == null) {
                }
                if (Player.players[0].pieces[row][col].isActive) {
                    if (Player.players[0].pieces[row][col].isHighlighted) {
                        canvas.drawCircle(row * squareDim + radius, (nSquares - col - 1) * squareDim + radius, radius, paintHighlighted);
                    } else {
                        canvas.drawCircle(row * squareDim + radius, (nSquares - col - 1) * squareDim + radius, radius, paintPlayer1);
                    }
                }

                if (Player.players[1].pieces[row][col].isActive) {
                    if (Player.players[1].pieces[row][col].isHighlighted) {
                        canvas.drawCircle(row * squareDim + radius, (nSquares - col - 1) * squareDim + radius, radius, paintHighlighted);
                    } else {
                        canvas.drawCircle(row * squareDim + radius, (nSquares - col - 1) * squareDim + radius, radius, paintPlayer2);
                    }
                }
            }
        }


//        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {

            int pointer_id = event.getPointerId(event.getActionIndex());
            touches[pointer_id] = true;
            touchx[pointer_id] = event.getX();
            touchy[pointer_id] = event.getY();
            touch = true;

            first = pointer_id;
//            invalidate();
            return true;
        } else if (event.getActionMasked() == MotionEvent.ACTION_UP) {

            int squareIndexAtX = (int) (event.getX() / squareSizeX);
            int squareIndexAtY = nSquares - (int) (event.getY() / squareSizeY) - 1;

            Player currentPlayer = Player.getCurrentPlayer();

            switch (ClickEvent.lastEvent) {
                case Event_Nothing:
                    if (currentPlayer.hasPieceAtIndex(squareIndexAtX, squareIndexAtY)) {
                        ClickEvent.xIndex = squareIndexAtX;
                        ClickEvent.yIndex = squareIndexAtY;
                        ClickEvent.lastEvent = ClickEvent.Type.Event_SelectPiece;
                        if (!currentPlayer.checkHighlight(squareIndexAtX,squareIndexAtY)){
                            currentPlayer.selectPiece(squareIndexAtX, squareIndexAtY);
                        }else{
                            currentPlayer.unSelectPiece(squareIndexAtX, squareIndexAtY);
                        }


                        invalidate();

                        return true;
                    } else
                        ClickEvent.lastEvent = ClickEvent.Type.Event_WrongPiece;
                    break;
                case Event_WrongPiece:
                    ClickEvent.lastEvent = ClickEvent.Type.Event_Nothing;
                    Toast.makeText(ctx, "Please Select your piece", Toast.LENGTH_SHORT).show();
                    break;
                case Event_isSelected:
                    if (currentPlayer.hasPieceAtIndex(squareIndexAtX,squareIndexAtY)){
                        currentPlayer.unSelectPiece(squareIndexAtX, squareIndexAtY);
                        invalidate();

                    }


                case Event_SelectPiece:
                    Player enemyPlayer = Player.getEnemyPlayer();
                    ClickEvent.Type action = currentPlayer.pieceActionTo(ClickEvent.xIndex, ClickEvent.yIndex, squareIndexAtX, squareIndexAtY);

                    if (action == ClickEvent.Type.Event_EatPiece) {
                        Log.i("Eat piece", squareIndexAtX + " - " + squareIndexAtY);
                        Player.endPlayerTurn();
                    } else if (action == ClickEvent.Type.Event_MovePiece) {
                        Player.endPlayerTurn();
                    } else if (action == ClickEvent.Type.Event_MovePieceAndAskToContinue) {
                        Player.endPlayerTurn();

                    }

                    break;
            }
            invalidate();
            return true;

//            touches[pointer_id] = false;
//            first = pointer_id;
//            touch = false;
//            invalidate();
//            return super.onTouchEvent(event);

        }
//        else if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
//
//            for (int i = 0; i < 16; i++) {
//                int pointer_index = event.findPointerIndex(i);
//                if (pointer_index != -1) {
//                    touchx[i] = event.getX(pointer_index);
//                    touchy[i] = event.getY(pointer_index);
//                }
//            }
//            invalidate();
//            return true;
//        } else if (event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {
//            int pointer_id = event.getPointerId(event.getActionIndex());
//            touches[pointer_id] = true;
//            touchx[pointer_id] = event.getX(pointer_id);
//            touchy[pointer_id] = event.getY(pointer_id);
//            invalidate();
//            return true;
//        } else if (event.getActionMasked() == MotionEvent.ACTION_POINTER_UP) {
//            int pointer_id = event.getPointerId(event.getActionIndex());
//            touches[pointer_id] = false;
//            if (pointer_id == first) {
//                for (int i = 0; i < 16; i++) {
//                    if (touches[i]) {
//                        first = i;
//                        break;
//                    }
//                }
//                invalidate();
//                return true;
//            }
//        }
        return super.onTouchEvent(event);


    }

    float squareSizeX;
    float squareSizeY;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        squareSizeX = width / 8.0f;
        squareSizeY = height / 8.0f;

        int dimension = (width == 0) ? height : (height == 0) ? width : (width < height) ? width : height;
        setMeasuredDimension(dimension, dimension);
        squareDim = width / nSquares;
    }
    public void reset(){
        init();
        Log.i("Reset", "Reset!!!");


    }


}
