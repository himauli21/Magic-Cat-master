package com.gamemasters.magiccat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import java.util.Random;

public class Enemy {

    private Context context;

    protected int xPosition;
    protected int yPosition;


    private int width;

    // Sign is for the type of enemy, false for horizontal, true for vertical
    protected boolean sign;

    protected boolean horizontalSign;

    Rect hitBox;

    Bitmap monsterImage;

    public Enemy(Context c,int screenHeight, int screenWidth) {

        this.context = c;

        this.xPosition = this.generateRandomXCoordinate(screenWidth);
        this.yPosition = this.generateRandomYCoordinate(screenHeight);
        this.width = 100;
        this.sign = this.generateRandomSignBoolean();
        this.horizontalSign = this.generateRandomSignBoolean();

        this.hitBox = new Rect(this.xPosition, this.yPosition, this.xPosition + this.width, this.xPosition + yPosition);
        generateMonsterImage();
    }

    public void updateHitbox() {

        // update the position of the hit-box
        this.hitBox.top = this.yPosition;
        this.hitBox.left = this.xPosition;
        this.hitBox.right = this.xPosition + 100;
        this.hitBox.bottom = this.yPosition + 100;
    }

    public void generateMonsterImage(){
        if(this.sign){
            this.monsterImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.monster);
        } else{
            if(horizontalSign){
                this.monsterImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.ghost);
            } else{
                this.monsterImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.ghosty);
            }

        }
    }

    public void updateEnemyPosition(int playerXPosition,int playerYPosition){
        // Use the distance formula to move it towards player
        double xDist = playerXPosition - this.xPosition;
        double yDist = playerYPosition - this.yPosition;

        double distance = Math.sqrt((xDist * xDist) + (yDist * yDist));

        double xSpeed = xDist/distance;
        double ySpeed = yDist/distance;

        this.xPosition += (int)(xSpeed*10);
        this.yPosition += (int)(ySpeed*10);
        this.updateHitbox();
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public Rect getHitbox() {
        return this.hitBox;
    }

    public int generateRandomXCoordinate(int screenWidth){

        // Function to generate X Coordinate for 30% left side of screen and 30% right side of screen
        Random rand = new Random();

        int leftCoordinate = (int)0.3*screenWidth;
        int rightCoordinate = screenWidth - (int)0.3*screenWidth;

        int randomNumber;
        do{
            randomNumber = rand.nextInt(screenWidth);
        }while(randomNumber<=leftCoordinate || randomNumber>=rightCoordinate);

        return randomNumber;
    }

    public int generateRandomYCoordinate(int screenHeight){

        // Function to generate Y coordinate for 0 to screen height's value
        Random rand = new Random();

        int upCoordinate = (int)0.3*screenHeight;
        int downCoordinate = screenHeight - (int)0.3*screenHeight;

        int randomNumber;
        do{
            randomNumber = rand.nextInt(screenHeight);
        }while(randomNumber<=upCoordinate || randomNumber>=downCoordinate);

        return randomNumber;
    }

    public boolean generateRandomSignBoolean(){
        Random rand = new Random();
        return rand.nextBoolean();
    }

    public boolean getSignBoolean(){
        return this.sign;
    }

    public boolean getHorizontalSignBoolean(){
        return this.horizontalSign;
    }
}
