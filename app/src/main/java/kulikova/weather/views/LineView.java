package kulikova.weather.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
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
        mPaint.setStrokeWidth(5);
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
        float hDevice = new Float(getHeight());
        float koef = (4 * hDevice) / (5 * height);

        float wDevice = getWidth();

        int stepX = (int) (wDevice / (points.size() + 2));
        mPaint.setARGB(255, 255, 255, 255);

        for (int i = 0; i < points.size() - 1; i++) {
            canvas.drawLine(stepX * (i + 1), (points.get(i) - minY + (hDevice / 20)) + (points.get(i) - minY) * koef,
                    stepX * (i + 2), (points.get(i + 1) - minY + (hDevice / 20)) + (points.get(i + 1) - minY) * koef, mPaint);
        }
    }
}
