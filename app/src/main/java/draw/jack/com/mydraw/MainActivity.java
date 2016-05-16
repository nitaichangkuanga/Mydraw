package draw.jack.com.mydraw;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, View.OnTouchListener {
    private TextView tv_red;
    private TextView tv_green;
    private TextView tv_blue;
    private SeekBar seekBar;
    private ImageView iv_draw;
    private Paint paint;
    private Canvas canvas;
    float startX;
    float startY;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_red = (TextView) findViewById(R.id.tv_red);
        tv_green = (TextView) findViewById(R.id.tv_green);
        tv_blue = (TextView) findViewById(R.id.tv_blue);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        iv_draw = (ImageView) findViewById(R.id.iv_draw);

        tv_red.setOnClickListener(this);
        tv_green.setOnClickListener(this);
        tv_blue.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);
        iv_draw.setOnTouchListener(this);

        bitmap = Bitmap.createBitmap(600, 600, Bitmap.Config.RGB_565);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.tv_red:
                paint.setColor(Color.RED);
                Toast.makeText(this,"红色被选择",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_green:
                paint.setColor(Color.GREEN);
                Toast.makeText(this,"绿色被选择",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_blue:
                paint.setColor(Color.BLUE);
                Toast.makeText(this,"蓝色被选择",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //0-100
        int progress = seekBar.getProgress();
        float f = progress * 10/100f;
        //变成0-2
        paint.setStrokeWidth(f);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = event.getX();
                float endY = event.getY();
                //画线
                canvas.drawLine(startX,startY,endX,endY,paint);
                iv_draw.setImageBitmap(bitmap);
                //重置起点位置
                startX = endX;
                startY = endY;
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        //down自己消费了
        return true;
    }
}
