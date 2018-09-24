package kulikova.weather.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class LineView extends View {


    private Paint mPaint = new Paint();
    List<Float> points = new ArrayList<>();

    public LineView(Context context) {
        super(context);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPoints(List<Float> points) {
        this.points = points;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (points.size() == 0) {
            return;
        }

        super.onDraw(canvas);
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(10);
        float minY = Float.MAX_VALUE;
        float maxY = Float.MIN_VALUE;

        for (int i = 0; i < points.size(); i++) {
            if (points.get(i) < minY) {
                minY = points.get(i);
            }
            if (points.get(i) > maxY) {
                maxY = points.get(i);
            }
        }

        float height = maxY - minY;
        float heigthView = new Float(getHeight());
        float koef = (4 * heigthView) / (5 * height);

        float wDevice = getWidth();

        int stepX = (int) (wDevice / (points.size() + 2));

        for (int i = 0; i < points.size() - 1; i++) {
            mPaint.setStrokeWidth(10);
            mPaint.setARGB(255, 255, 255, 255);
            canvas.drawLine(stepX * (i + 1), (points.get(i) - minY + (heigthView / 20)) + (points.get(i) - minY) * koef,
                    stepX * (i + 2), (points.get(i + 1) - minY + (heigthView / 20)) + (points.get(i + 1) - minY) * koef, mPaint);

            mPaint.setStrokeWidth(2);
            mPaint.setARGB(255, 100, 100, 100);
            canvas.drawLine(stepX * (i + 2), heigthView / 20 ,stepX * (i + 2), heigthView -(heigthView / 10), mPaint);
        }
        mPaint.setStrokeWidth(5);
        mPaint.setARGB(255, 255, 255, 255);
        canvas.drawLine(stepX, (heigthView / 20), wDevice - stepX, (heigthView / 20), mPaint);
        canvas.drawLine(stepX, heigthView - (heigthView / 10), wDevice - stepX, heigthView - (heigthView / 10), mPaint);
        mPaint.setARGB(255, 255, 255, 0);
        mPaint.setTextSize(60);
        mPaint.setStrokeWidth(10);
        canvas.drawText("min: " + String.valueOf(maxY), (3*wDevice) / 5, heigthView / 8, mPaint);
        canvas.drawText("max: " + String.valueOf(minY), (3*wDevice) / 5, (7*heigthView )/ 8, mPaint);
    }
}