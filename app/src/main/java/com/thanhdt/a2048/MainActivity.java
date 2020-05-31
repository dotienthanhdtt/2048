package com.thanhdt.a2048;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private  GridView gridView;
    private  Adapter adapter;
    private OnTouchListener listener;
    private TextView tvScore, tvBestScore;
    private float X,Y;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int bestScore;
    public Button newGame, selectMode;
    private ArrayList<Integer> list = new ArrayList<>();
    private int mode=10;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gvGame);
        tvScore = findViewById(R.id.score);
        tvBestScore = findViewById(R.id.best);
        newGame = findViewById(R.id.newGame);
        selectMode = findViewById(R.id.mode);
        sharedPreferences = getSharedPreferences("ec", MODE_PRIVATE);;
        editor= sharedPreferences.edit();
        bestScore = sharedPreferences.getInt("Best_Score",0);
        tvBestScore.setText(bestScore+"");
        selectMode.setText("10X10");
        startGame();
        adapter = new Adapter(MainActivity.this, 0, list, mode);


        listener = new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        X = event.getX();
                        Y = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:

                        if(Math.abs(event.getX()-X)> Math.abs(event.getY()-Y)){

                            if(event.getX()>X){//right
                                GamePlay.getGamePlay().swipeRight();
                                update();
                                if(GamePlay.getGamePlay().isGameOver()){
                                    Toast.makeText(MainActivity.this, "You lose", Toast.LENGTH_LONG).show();
                                }
                            }
                            else if(event.getX()<X){//left
                                GamePlay.getGamePlay().swipeLeft();
                                update();
                                if(GamePlay.getGamePlay().isGameOver()){
                                    Toast.makeText(MainActivity.this, "You lose", Toast.LENGTH_LONG).show();
                                }
                            }
                        }else{
                            //up
                            if(event.getY()>Y){
                                GamePlay.getGamePlay().swipeUp();
                                update();
                                if(GamePlay.getGamePlay().isGameOver()){
                                    Toast.makeText(MainActivity.this, "You lose", Toast.LENGTH_LONG).show();
                                }
                            }
                            //down
                            else{
                                GamePlay.getGamePlay().swipeDown();
                                update();
                                if(GamePlay.getGamePlay().isGameOver()){
                                    Toast.makeText(MainActivity.this, "You lose", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                        break;
                }
                return true;
            }
        };
        selectMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentSelectMode selectMode= new FragmentSelectMode();
                selectMode.show(getSupportFragmentManager(),"c");
            }
        });
        selectMode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.clear();

                startGame();
                adapter.setList(list);
            }
        });
        gridView.setAdapter(adapter);
        gridView.setOnTouchListener(listener);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                adapter.clear();
                for(int i=0;i<mode*mode;i++){
                    list.add(0);
                }
                GamePlay.getGamePlay().setListNumber(list);
                GamePlay.getGamePlay().initial(MainActivity.this);
                tvScore.setText("0");
            }
        });
    }

    private void startGame() {
        list.clear();
        tvScore.setText("0");
        String s = selectMode.getText()+"";
        s=s.substring(0, s.length()/2);
        mode= Integer.parseInt(s);
        gridView.setNumColumns(mode);
        GamePlay.getGamePlay().setMode(mode);
        int test = GamePlay.getGamePlay().getMode();
        for(int i=0;i<mode*mode;i++){
            list.add(0);
        }
        GamePlay.getGamePlay().setListNumber(list);
       if(adapter != null){
           adapter.setMode(mode);
       }
        GamePlay.getGamePlay().initial(MainActivity.this);

    }

    public void update(){
        adapter.notifyDataSetChanged();
        GamePlay.getGamePlay().updateScore(tvScore, tvBestScore);
        bestScore = Integer.parseInt(tvBestScore.getText().toString());
        editor.putInt("Best_Score", bestScore);
        editor.commit();
    }
}
