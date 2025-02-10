//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Walking Simulator
// Course:   CS 300 Fall 2024
//
// Author:   Harshvardhan Singh Rathore
// Email:    hvardhan2@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
// 
// No pair programming done
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
// 
// Online Sources:  ChatGPT, I was confused how to implement loadImage method
// from utility class. Specifically how to use loadImage method.
// Also for the return statement of isMouseOver, I was confused with the syntax.
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.util.Random;
import processing.core.PImage;

public class WalkingSim {
  private static Random randGen;
  private static int bgColor;
  private static PImage[] frames;
  private static Walker[] walkers;

  public static void main(String[] args) {
    Utility.runApplication();
  }

  

  public static void draw() {
    Utility.background(bgColor);


    for (int i = 0; i < walkers.length; i++) {
      Walker walker = walkers[i];
      if (walker != null) {

        if (walker.isWalking()) {
          float newXvalue = walker.getPositionX() + 3;
          if (newXvalue > Utility.width()) {
            newXvalue = 0;
          }
          walker.setPositionX(newXvalue);
          walker.update();
        }


        int frameIndex = walker.getCurrentFrame();
        Utility.image(frames[frameIndex], walker.getPositionX(), walker.getPositionY());


        if (isMouseOver(walker)) {
          System.out.println("Mouse is over a walker!");
        }
      }
    }
  }

  
  
  public static void setup() {
    randGen = new Random();
    bgColor = randGen.nextInt();


    frames = new PImage[Walker.NUM_FRAMES];


    for (int i = 0; i < frames.length; i++) {
      frames[i] = Utility.loadImage("images" + File.separator + "walk-" + i + ".png");
    }


    walkers = new Walker[8];


    int num_Walkers = randGen.nextInt(walkers.length) + 1;
    for (int i = 0; i < num_Walkers; i++) {
      int x = randGen.nextInt(Utility.width());
      int y = randGen.nextInt(Utility.height());
      walkers[i] = new Walker(x, y);
    }
  }
  

  


  public static void mousePressed() {
    for (Walker walker : walkers) {
      if (walker != null && isMouseOver(walker)) {
        walker.setWalking(true);
        break;
      }
    }
  }
  public static boolean isMouseOver(Walker walker) {
    int mouseX = Utility.mouseX();
    int mouseY = Utility.mouseY();

    float walkerXpos = walker.getPositionX();
    float walkerYpos = walker.getPositionY();

    int imageWidth = frames[0].width;
    int imageHeight = frames[0].height;

    return (mouseX > walkerXpos - imageWidth / 2 && mouseX < walkerXpos + imageWidth / 2
        && mouseY > walkerYpos - imageHeight / 2 && mouseY < walkerYpos + imageHeight / 2);
  }

  public static void keyPressed(char cursor) {
    if (cursor == 'a') {

      for (int i = 0; i < walkers.length; i++) {
        if (walkers[i] == null) {
          int x = randGen.nextInt(Utility.width());
          int y = randGen.nextInt(Utility.height());
          walkers[i] = new Walker(x, y);
          break;
        }
      }
    } else if (cursor == 's') {

      for (Walker walker : walkers) {
        if (walker != null) {
          walker.setWalking(false);
        }
      }
    }
  }
}
