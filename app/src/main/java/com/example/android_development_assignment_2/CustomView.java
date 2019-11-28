package com.example.android_development_assignment_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;



public class CustomView extends View {
    private static final String TAG = CustomView.class.getSimpleName();

    private int nSquares;

    private int[] square1;
    private int[] square2;
    private int[] player1;
    private int[] player2;


    private Paint paint, paintPlayer1, paintPlayer2;
    private int squareDim;


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
        nSquares = 8;
        square1 = new int[]{0, 100, 0};
        square2 = new int[]{255, 250, 205};
        paint = new Paint();
        player1 = new int[]{178, 34, 34};
        player2 = new int[]{0, 0, 0};
        paintPlayer1 = new Paint();
        paintPlayer2 = new Paint();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count = 0;
        for (int row = 0; row < nSquares; row++) {
            if ((row & 1) == 0) {
                paint.setARGB(200, square1[0], square1[1], square1[2]);

                count++;
            } else {
                paint.setARGB(200, square2[0], square2[1], square2[2]);
                count++;
            }

            paint.setFlags(Paint.ANTI_ALIAS_FLAG);

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

        int radius = (squareDim / 2);

        paintPlayer2.setARGB(200, player2[0], player2[1], player2[2]);
        canvas.drawCircle(squareDim + ((squareDim / 2)), (squareDim / 2), radius, paintPlayer2);
        canvas.drawCircle(3 * squareDim + ((squareDim / 2)), (squareDim / 2), radius, paintPlayer2);
        canvas.drawCircle(5 * squareDim + ((squareDim / 2)), (squareDim / 2), radius, paintPlayer2);
        canvas.drawCircle(7 * squareDim + ((squareDim / 2)), (squareDim / 2), radius, paintPlayer2);


        canvas.drawCircle(((squareDim / 2)), squareDim + (squareDim / 2), radius, paintPlayer2);
        canvas.drawCircle((2 * squareDim + (squareDim / 2)), squareDim + (squareDim / 2), radius, paintPlayer2);
        canvas.drawCircle((4 * squareDim + (squareDim / 2)), squareDim + (squareDim / 2), radius, paintPlayer2);
        canvas.drawCircle((6 * squareDim + (squareDim / 2)), squareDim + (squareDim / 2), radius, paintPlayer2);


        canvas.drawCircle((3 * (squareDim / 2)), 2 * squareDim + 3 / 2 * (squareDim / 2), radius, paintPlayer2);
        canvas.drawCircle((2 * squareDim + 3 * (squareDim / 2)), 2 * squareDim + 3 / 2 * (squareDim / 2), radius, paintPlayer2);
        canvas.drawCircle((4 * squareDim + 3 * (squareDim / 2)), 2 * squareDim + 3 / 2 * (squareDim / 2), radius, paintPlayer2);
        canvas.drawCircle((6 * squareDim + 3 * (squareDim / 2)), 2 * squareDim + 3 / 2 * (squareDim / 2), radius, paintPlayer2);

        paint.setFlags(Paint.ANTI_ALIAS_FLAG);



        paintPlayer1.setARGB(200,player1[0],player1[1],player1[2]);

        canvas.drawCircle((3 * (squareDim / 2)) - squareDim, 4 * squareDim + 3 * (squareDim / 2), radius, paintPlayer1);
        canvas.drawCircle((3 * (squareDim / 2)) + squareDim, 4 * squareDim + 3 * (squareDim / 2), radius, paintPlayer1);
        canvas.drawCircle((3 * (squareDim / 2)) + 3 * squareDim, 4 * squareDim + 3 * (squareDim / 2), radius, paintPlayer1);
        canvas.drawCircle((3 * (squareDim / 2)) + 5 * squareDim, 4 * squareDim + 3 * (squareDim / 2), radius, paintPlayer1);

        canvas.drawCircle((squareDim + 3 * (squareDim / 2)) - squareDim, 5 * squareDim + 3 * (squareDim / 2), radius, paintPlayer1);
        canvas.drawCircle((3 * squareDim + 3 * (squareDim / 2)) - squareDim, 5 * squareDim + 3 * (squareDim / 2), radius, paintPlayer1);
        canvas.drawCircle((5 * squareDim + 3 * (squareDim / 2)) - squareDim, 5 * squareDim + 3 * (squareDim / 2), radius, paintPlayer1);
        canvas.drawCircle((7 * squareDim + 3 * (squareDim / 2)) - squareDim, 5 * squareDim + 3 * (squareDim / 2), radius, paintPlayer1);

        canvas.drawCircle(((squareDim / 2)), 6 * squareDim + 3 * (squareDim / 2), radius, paintPlayer1);
        canvas.drawCircle(2 * squareDim + ((squareDim / 2)), 6 * squareDim + 3 * (squareDim / 2), radius, paintPlayer1);
        canvas.drawCircle(4 * squareDim + (squareDim / 2), 6 * squareDim + 3 * (squareDim / 2), radius, paintPlayer1);
        canvas.drawCircle(6 * squareDim + (squareDim / 2), 6 * squareDim + 3 * (squareDim / 2), radius, paintPlayer1);







    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int dimension = (width == 0) ? height : (height == 0) ? width : (width < height) ? width : height;
        setMeasuredDimension(dimension, dimension);
        squareDim = width / nSquares;
    }


}
