package com.example.edwards_inc.gestureoverlay;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GestureLibrary lib;
    private GestureOverlayView gesture;
    private TextView txtResult;
    private PlayingField toDraw;
    private LinearLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lib = GestureLibraries.fromRawResource(this, R.raw.gesture);
        if(!lib.load())
            finish();
        txtResult = findViewById(R.id.txtResult);
        gesture = findViewById(R.id.gesture);
        gesture.addOnGesturePerformedListener(gScan);

        layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        toDraw = new PlayingField(this);
        addContentView(toDraw, layoutParams);
    }

    GestureOverlayView.OnGesturePerformedListener gScan = new GestureOverlayView.OnGesturePerformedListener() {
        @Override
        public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {
            ArrayList<Prediction> predictionArrayList = lib.recognize(gesture);
            double largest = 0;
            for(Prediction prediction:predictionArrayList) {
                if(prediction.score > largest) {
                    largest = prediction.score;
                    txtResult.setText(prediction.name);
                }
            }
            if(!toDraw.Parse(gesture, txtResult.getText().toString()))
                System.out.println("Function 'Parse' in class PlayingField returned false.");
            toDraw.invalidate();
        }
    };
}
