package com.thanhdt.a2048;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.widget.Gallery;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GamePlay {
    private static GamePlay gamePlay = new GamePlay();
    private ArrayList<Integer> listNumber = new ArrayList<>();



    private int mode;
    private int [][] matrix ;
    private int [] colors;
    private Random random = new Random();
    private int Score=0;
    private GamePlay(){}
    private Context context;

    public static GamePlay getGamePlay(){
        return gamePlay;
    }
    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
        matrix= new int[mode][mode];
    }
    public void initial(Context context){
        Score=0;
        for(int i=0;i<mode;i++){
            for(int j=0;j<mode;j++){
                matrix[i][j]=listNumber.get(mode*i+j);
            }
        }
        TypedArray typedArray = context.getResources().obtainTypedArray(R.array.colorItem);
        colors = new int[typedArray.length()];
        for(int i=0;i<typedArray.length();i++){
            colors[i] = typedArray.getColor(i,0);
        }
        typedArray.recycle();
        randomNum();
        changeListNum();
    }


    public ArrayList<Integer> getListNumber() {
        return listNumber;
    }

    public void setListNumber(ArrayList<Integer> listNumber) {
        this.listNumber = listNumber;
        for(int i=0;i<mode;i++){
            for(int j=0;j<mode;j++){
                matrix[i][j]=listNumber.get(mode*i+j);
            }
        }

    }

    public int color(int number){
         if(number ==0){
             return Color.WHITE;
         }
         else{
             int position = (int) (Math.log(number)/Math.log(2));
             return colors[position-1];
         }

    }
    public void randomNum(){
         int num =0;
         for(int i=0;i<mode;i++){
            if(listNumber.get(i)==0){
                num++;
            }

         }
         int countNewNum=0;
         if(num>1){
             countNewNum = random.nextInt(2)+mode/3;
         }else if(num==1){
             countNewNum=1;
         }
         while (countNewNum !=0){
             int i = random.nextInt(mode);
             int j= random.nextInt(mode);
             if(matrix[i][j]==0){
                 matrix[i][j] =2;
                 countNewNum--;
             }
         }
    }
    public void changeListNum(){
         listNumber.clear();
        for(int i=0;i<mode;i++){
            for(int j=0;j<mode;j++){
                listNumber.add(matrix[i][j]);

            }
        }
    }
    public void swipeLeft(){

         for(int i=0;i<mode;i++){
             for(int j=0;j<mode;j++){
                 int fnum=matrix[i][j];
                 if(fnum==0){
                     continue;
                 }else {
                    for(int k =j+1;k<mode;k++){
                        int sNum = matrix[i][k];
                        if(sNum==0){
                            continue;
                        }else{
                            if(sNum==fnum){
                                matrix[i][j]=fnum*2;
                                Score = Score + fnum*2;
                                matrix[i][k]=0;
                                break;
                            }else {
                                break;
                            }
                        }
                    }
                 }
             }
         }
         for(int i=0;i<mode;i++){
             for (int j=0;j<mode;j++){
                 int num = matrix[i][j];
                 if(num==0){
                     for(int k=j+1;k<mode;k++){
                         int sNum = matrix[i][k];
                         if(sNum==0){
                             continue;
                         }else{
                             matrix[i][j] = matrix[i][k];
                             matrix[i][k]=0;
                             break;
                         }
                     }
                 }
             }
         }
         randomNum();
         changeListNum();
    }
    public void swipeRight(){
        for(int i=mode-1;i>=0;i--){
            for(int j=mode-1;j>=0;j--){
                int fnum=matrix[i][j];
                if(fnum==0){
                    continue;
                }else {
                    for(int k =j-1;k>=0;k--){
                        int sNum = matrix[i][k];
                        if(sNum==0){
                            continue;
                        }else{
                            if(sNum==fnum){
                                matrix[i][j]=fnum*2;
                                matrix[i][k]=0;
                                Score = Score + fnum*2;
                                break;
                            }else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        for(int i=mode-1;i>=0;i--){
            for (int j=mode-1;j>=0;j--){
                int num = matrix[i][j];
                if(num==0){
                    for(int k=j-1;k>=0;k--){
                        int sNum = matrix[i][k];
                        if(sNum==0){
                            continue;
                        }else{
                            matrix[i][j] = matrix[i][k];
                            matrix[i][k]=0;
                            break;
                        }
                    }
                }
            }
        }
        randomNum();
        changeListNum();
    }
    public void swipeDown(){
        for(int i=0;i<mode;i++){
            for(int j=0;j<mode;j++){
                int fnum=matrix[j][i];
                if(fnum==0){
                    continue;
                }else {
                    for(int k =j+1;k<mode;k++){
                        int sNum = matrix[k][i];
                        if(sNum==0){
                            continue;
                        }else{
                            if(sNum==fnum){
                                matrix[j][i]=fnum*2;
                                matrix[k][i]=0;
                                Score = Score + fnum*2;
                                break;
                            }else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        for(int i=0;i<mode;i++){
            for (int j=0;j<mode;j++){
                int num = matrix[j][i];
                if(num==0){
                    for(int k=j+1;k<mode;k++){
                        int sNum = matrix[k][i];
                        if(sNum==0){
                            continue;
                        }else{
                            matrix[j][i] = matrix[k][i];
                            matrix[k][i]=0;
                            break;
                        }
                    }
                }
            }
        }
        randomNum();
        changeListNum();
    }
    public void swipeUp(){
        for(int i=mode-1;i>=0;i--){
            for(int j=mode-1;j>=0;j--){
                int fnum=matrix[j][i];
                if(fnum==0){
                    continue;
                }else {
                    for(int k =j-1;k>=0;k--){
                        int sNum = matrix[k][i];
                        if(sNum==0){
                            continue;
                        }else{
                            if(sNum==fnum){
                                matrix[j][i]=fnum*2;
                                matrix[k][i]=0;
                                Score = Score + fnum*2;
                                break;
                            }else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        for(int i=mode-1;i>=0;i--){
            for (int j=mode-1;j>=0;j--){
                int num = matrix[j][i];
                if(num==0){
                    for(int k=j-1;k>=0;k--){
                        int sNum = matrix[k][i];
                        if(sNum==0){
                            continue;
                        }else{
                            matrix[j][i] = matrix[k][i];
                            matrix[k][i]=0;
                            break;
                        }
                    }
                }
            }
        }
        randomNum();
        changeListNum();
    }
    public void updateScore(TextView score, TextView best){
        score.setText(Score+"");
        if(Integer.parseInt(score.getText()+"")> Integer.parseInt(best.getText()+"")){
            best.setText(score.getText()+"");
        }
    }
}
