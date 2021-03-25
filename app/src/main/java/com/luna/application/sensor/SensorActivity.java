package com.luna.application.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.luna.application.R;

import java.util.List;

public class SensorActivity extends AppCompatActivity {

    private SensorManager    sensorManager;

    private TextView         mtextViewx, mtextViewy, mtextViewz;

    private TextView         step, count;

    private MySensorListener mySensorListener;

    // 定义显示指南针的图片
    private ImageView        compassImage;
    // 记录指南针图片转过的角度
    private float            currentDegree = 0f;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_layout);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sb = new StringBuilder();
        sb.append("当前设备支持传感器数：" + sensorList.size() + "   分别是：\n\n");
        for (Sensor s : sensorList) {
            switch (s.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    sb.append("加速度传感器(Accelerometer sensor)" + "\n");
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    sb.append("陀螺仪传感器(Gyroscope sensor)" + "\n");
                    break;
                case Sensor.TYPE_LIGHT:
                    sb.append("光线传感器(Light sensor)" + "\n");
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    sb.append("磁场传感器(Magnetic field sensor)" + "\n");
                    break;
                case Sensor.TYPE_STEP_COUNTER:
                    sb.append("记步传感器(stop counter sensor)" + "\n");
                    break;
                case Sensor.TYPE_PRESSURE:
                    sb.append("气压传感器(Pressure sensor)" + "\n");
                    break;
                case Sensor.TYPE_PROXIMITY:
                    sb.append("距离传感器(Proximity sensor)" + "\n");
                    break;
                case Sensor.TYPE_TEMPERATURE:
                    sb.append("温度传感器(Temperature sensor)" + "\n");
                    break;
                default:
                    sb.append("其他传感器" + "\n");
                    break;
            }
            sb.append("设备名称：" + s.getName() + "\n 设备版本：" + s.getVersion() + "\n 供应商："
                + s.getVendor() + "\n\n");
        }
        Log.d("TAG", "sb.toString()----:" + sb.toString());
        register();

    }

    public void register() {
        mtextViewx = findViewById(R.id.sensor_x);
        mtextViewy = findViewById(R.id.sensor_y);
        mtextViewz = findViewById(R.id.sensor_z);
        mySensorListener = new MySensorListener();
        sensorManager.registerListener(mySensorListener,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL);

        count = findViewById(R.id.sensor_count);
        step = findViewById(R.id.sensor_step);
        // 统计
        sensorManager.registerListener(mySensorListener,
            sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER),
            SensorManager.SENSOR_DELAY_NORMAL);

        // 单次数记步数
        sensorManager.registerListener(mySensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR),
            SensorManager.SENSOR_DELAY_NORMAL);

        // 获取界面中显示指南针的图片
        compassImage = (ImageView)findViewById(R.id.compassImage);

        // 方向传感器
        sensorManager.registerListener(mySensorListener,
            sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
            SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onPause() {
        sensorManager.unregisterListener(mySensorListener);
        super.onPause();
    }

    @Override
    protected void onStop() {
        // 取消注册
        sensorManager.unregisterListener(mySensorListener);
        super.onStop();
    }

    /**
     * 传感器监听器
     */
    class MySensorListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            switch (sensorEvent.sensor.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    accelerometer(sensorEvent);
                    break;
                case Sensor.TYPE_STEP_COUNTER:
                    float allCount = sensorEvent.values[0];
                    count.setText("count：" + allCount);
                    break;
                case Sensor.TYPE_STEP_DETECTOR:
                    float stepCount = sensorEvent.values[0];
                    step.setText("step：" + stepCount);
                    break;
                case Sensor.TYPE_ORIENTATION:
                    orientation(sensorEvent);
                    break;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }

        /**
         * 加速度传感器
         * 
         * @param sensorEvent
         */
        public void accelerometer(SensorEvent sensorEvent) {
            float X_lateral = sensorEvent.values[0];
            float Y_longitudinal = sensorEvent.values[1];
            float Z_vertical = sensorEvent.values[2];
            mtextViewx.setText("x:" + X_lateral);
            mtextViewy.setText("y:" + Y_longitudinal);
            mtextViewz.setText("z:" + Z_vertical);
        }

        /**
         * 方向传感器
         * 
         * @param sensorEvent
         */
        public void orientation(SensorEvent sensorEvent) {
            // 获取绕Z轴转过的角度
            float degree = sensorEvent.values[0];
            // 创建旋转动画（反向转过degree度）
            RotateAnimation ra = new RotateAnimation(currentDegree, -degree,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            // 设置动画的持续时间
            ra.setDuration(200);
            // 运行动画
            compassImage.startAnimation(ra);
            currentDegree = -degree;
        }
    }

}
