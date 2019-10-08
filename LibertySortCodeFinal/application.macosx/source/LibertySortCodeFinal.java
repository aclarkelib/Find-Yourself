import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.awt.TextField; 
import java.io.File; 
import java.io.IOException; 
import java.net.URL; 
import java.nio.file.*; 
import java.io.File; 
import java.io.IOException; 
import java.net.URL; 
import java.nio.file.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class LibertySortCodeFinal extends PApplet {




int button_scrambleMenuX, button_scrambleMenuY;      // Position of scramble menu button
int button_descrambleMenuX, button_descrambleMenuY;  // Position of descramble menu button
int button_backX, button_backY;
int mainMenuButtonSize;     // Diameter of main menu button
int buttonColor;
int buttonHighlight;
boolean scrambleMenuButtonOver = false;
boolean descrambleMenuButtonOver = false;
boolean backButtonOver = false;
boolean scrambleButtonOver = false;
boolean descrambleButtonOver = false;

boolean imageSelectionOver = false;

boolean loaded = false;
boolean scrambling = false;
boolean descrambling = false;

//Menu booleans
boolean main = true;
boolean scrambleMenu = false;
boolean descrambleMenu = false;

public void setup() {
  
  wordString = "";
  loadMainMenu();
}

public void loadMainMenu() {
  background(255);
  buttonColor = color(0);
  buttonHighlight = color(51);
  mainMenuButtonSize = width/3;
  button_descrambleMenuX = width/2+mainMenuButtonSize/2 - 90;
  button_descrambleMenuY = height/2-mainMenuButtonSize/2;
  button_scrambleMenuX = width/2-mainMenuButtonSize - 90;
  button_scrambleMenuY = height/2-mainMenuButtonSize/2;
  loaded = true;
}

public void draw() {
  update(mouseX, mouseY);
  if (main) {
    if (scrambleMenuButtonOver) {
      fill(buttonHighlight);
    } else {
      fill(buttonColor);
    }
    stroke(255);
    rect(button_scrambleMenuX, button_scrambleMenuY, mainMenuButtonSize, mainMenuButtonSize);
    
    if (descrambleMenuButtonOver) {
      fill(buttonHighlight);
    } else {
      fill(buttonColor);
    }
    stroke(0);
    rect(button_descrambleMenuX, button_descrambleMenuY, mainMenuButtonSize, mainMenuButtonSize);
    
    textSize(48);
    fill(0);
    text("De/Scramble Image", width/2 - 200, height/8);
    
    //Button Text
    textSize(42);
    fill(255);
    text("Scramble", button_scrambleMenuX + 115, button_scrambleMenuY + 170);
    text("Image", button_scrambleMenuX + 140, button_scrambleMenuY + 250);
    text("Descramble", button_scrambleMenuX + 680, button_scrambleMenuY + 170);
    text("Image", button_scrambleMenuX + 740, button_scrambleMenuY + 250);
  }
  if (scrambleMenu && !scrambling) {
    //println("here");
    loadScrambleGUI();
    if(wordString != "") {
      textSize(20);
      fill(0);
      text(wordString, width/2 + 40, height - 80);
    }
  }  
  if (descrambleMenu && !descrambling) {
    loadDescrambleGUI();
    if(wordString != "") {
      //println("got here wordString not null");
      textSize(20);
      fill(0);
      text(wordString, width/2 + 40, height - 80);
    }
  }
}

public void update(int x, int y) {
  if ( main && overRect(button_descrambleMenuX, button_descrambleMenuY, mainMenuButtonSize, mainMenuButtonSize)) {
    descrambleMenuButtonOver = true;
    scrambleMenuButtonOver = false;
  } else if ( main && overRect(button_scrambleMenuX, button_scrambleMenuY, mainMenuButtonSize, mainMenuButtonSize)) {
    scrambleMenuButtonOver = true;
    descrambleMenuButtonOver = false;
  } else if ( scrambleMenu && overRect(button_backX, button_backY, scrambleMenuButtonWidth, scrambleMenuButtonHeight)){
    backButtonOver = true;
  } else if ( descrambleMenu && overRect(button_backX, button_backY, descrambleMenuButtonWidth, descrambleMenuButtonHeight)){
    backButtonOver = true;
  } else if ( scrambleMenu && overRect(button_scrambleX, button_scrambleY, scrambleMenuButtonWidth, scrambleMenuButtonHeight)){
    scrambleButtonOver = true;
  } else if ( descrambleMenu && overRect(button_descrambleX, button_descrambleY, descrambleMenuButtonWidth, descrambleMenuButtonHeight)){
    descrambleButtonOver = true;
  } else if ( scrambledImages != null && descrambleMenu && overImageSelector()){
    imageSelectionOver = true;
  } else {
    descrambleMenuButtonOver = scrambleMenuButtonOver = backButtonOver = scrambleButtonOver = descrambleButtonOver = imageSelectionOver = false;
  }
}

public void mousePressed() {
  if (descrambleMenuButtonOver) {
    main = false;
    descrambleMenu = true;
    loaded = false;
  }
  if (scrambleMenuButtonOver) {
    main = false;
    scrambleMenu = true;
    loaded = false;
  }
  if (backButtonOver) {
    loaded = false;
    loadMainMenu();
    scrambleMenu = false;
    descrambleMenu = false;
    main = true;
    wordString = "";
  }
  if (scrambleButtonOver && wordString != "") {
    scrambling = true;
    scrambleImage();
  }
  if (descrambleButtonOver && wordString != "") {
    println("clicked on descramble button");
    descrambling = true;
    descrambleImage();
  }
  if (scrambledImages != null && imageSelectionOver && overWhichNumber() != 0){
    img = scrambledImages.get(overWhichNumber() - 1);
    image(img, imagePosX, imagePosY);
    wordString = "";
    stroke(255);
    fill(255);
    rect(0, (height * 7)/8, width, height/8);
  }
}

public void keyPressed() {
  if (key!=CODED) {
    if (key==BACKSPACE) {
      stroke(255);
      fill(255);
      rect(0, (height * 7)/8, width, height/8);
      if (wordString.length()>0) {
        wordString=wordString.substring(0, wordString.length()-1);
      } // if
    } // if
    else if (key==RETURN || key==ENTER) {
  if (scrambleMenu && wordString != "") {
    scrambling = true;
    scrambleImage();
  }
  if (descrambleMenu && wordString != "") {
    descrambleImageCode = "Liberty";
    descrambling = true;
    descrambleImage();
  }
    }
    else {
      wordString+=key;
    }
  }
}

public boolean overRect(int x, int y, int width, int height)  {
  if (mouseX >= x && mouseX <= x+width && 
      mouseY >= y && mouseY <= y+height) {
    return true;
  } else {
    return false;
  }
}

int button_descrambleX, button_descrambleY;
int descrambleMenuButtonWidth;     // Width of descramble menu button
int descrambleMenuButtonHeight;     // Height of descramble menu button
ArrayList<PImage> scrambledImages;
TextField descrambleTextField;

public void loadDescrambleGUI() {
  if (!loaded) {
    loadScrambledImages();
    background(255);
  
    imageSizeWidth = (width * 3)/5;
    imageSizeHeight = (height * 3)/5;
    imagePosX = (width/2) - (imageSizeWidth/2);
    imagePosY = height/7;
    
    img = scrambledImages.get(0);
    image(img, imagePosX, imagePosY);
  }
  
  button_backX = width / 14;
  button_backY = height - (height / 6);
  button_descrambleX = width - (width / 14) - (width/10);
  button_descrambleY = height - (height / 6);
  descrambleMenuButtonWidth = width/10;
  descrambleMenuButtonHeight = height/10;
  
  if (backButtonOver) {
    fill(buttonHighlight);
  } else {
    fill(buttonColor);
  }
  stroke(255);
  rect(button_backX, button_backY, descrambleMenuButtonWidth, descrambleMenuButtonHeight);
  
  
  if (descrambleButtonOver) {
    fill(0, 160, 0);
  } else {
    fill(0, 230, 20);
  }
  stroke(0, 160, 0);
  rect(button_descrambleX, button_descrambleY, descrambleMenuButtonWidth, descrambleMenuButtonHeight);
  
  textSize(48);
  fill(0);
  text("Descramble Image", width/2 - 200, height/8);
  
  //Button Text
  textSize(20);
  fill(255);
  text("Back", button_backX + 40, button_backY + 47);
  fill(0);
  text("Descramble", button_descrambleX + 5, button_descrambleY + 35);
  text("Image", button_descrambleX + 30, button_descrambleY + 60);
  
  textSize(20);
  fill(0);
  text("Type word to descramble image with: ", width/3 - 150, height - 80);
  
  if (!loaded){
  loaded = true;
  }
  placeScrambledImages();
}

private void loadScrambledImages() {
  scrambledImages = new ArrayList<PImage>();
  File fp = new File(sketchPath() + "/Images/scrambledImages");
  File[] listOfImageFiles = fp.listFiles();
  for(int i = 0; i < listOfImageFiles.length; i++) {
    if(listOfImageFiles[i].getPath().endsWith(".png")) {
      PImage newImage = loadImage(listOfImageFiles[i].getPath());
      scrambledImages.add(newImage);
    }
  }
}

private void placeScrambledImages() {
  int rows = scrambledImages.size()/4;
  
  for(int row = 0; row <= rows; row++) {
    for(int column = 0; column + (row * 4) < scrambledImages.size() && column < 4; column++) {
      stroke(0);
      fill(255);
      rect(30 * (column + 1) + (column * 3), 30 * (row + 1) + (row * 3), 30, 30);
      
      fill(0);
      textSize(16);
      text((column + (row * 4)) + 1, 30 * (column + 1) + (column * 3) + 10, 30 * (row + 1) + (row * 3) + 20);
    }
  }
}

private int overWhichNumber() {
  
  int rows = scrambledImages.size()/4;
  int overNumber = 0;
  
  for(int row = 0; row <= rows; row++) {
    for(int column = 0; column + (row * 4) < scrambledImages.size() && column < 4; column++) {
      if(((30 * (column + 1) + (column * 3)) < mouseX && mouseX < 30 * (column + 1) + (column * 3) + 30) && 
      ( 30 * (row + 1) + (row * 3) < mouseY && mouseY <  30 * (row + 1) + (row * 3) + 30)) {
        overNumber = column + (row * 4) + 1;
      }
    }
  }
  return overNumber;
}

private boolean overImageSelector() {
  int rows = scrambledImages.size()/4;
  
  if((30 < mouseX && mouseX < 159) && (30 < mouseY && mouseY < 30 * (rows + 1) + (rows * 3) + 30)) {
   return true; 
  } else {
    return false;
  }
}





String descrambleImageCode;

private void descrambleImage() {
  println("descrambling..." + descrambleImageCode);

  char[] charArray = wordString.toLowerCase().toCharArray();
  CharPosition[] orginalPositionOfChars = charPositionArray(charArray);
  CharPosition[] alphabeticalArray = arrangeAlphabetically(orginalPositionOfChars);
  parts = alphabeticalArray.length;
  splitImage(img);
  placeParts(createImageCharPositions(loadParts(), alphabeticalArray));
  descrambling = false;
}

private ArrayList<ImageCharPosition> createImageCharPositions(ArrayList<PImage> imageList, CharPosition[] charPositionArray) {
  
  ArrayList<ImageCharPosition> preDescramblingList = new ArrayList<ImageCharPosition>();
  
  for(int i = 0; i < imageList.size(); i++) {
    ImageCharPosition newImageCharPosition = new ImageCharPosition(charPositionArray[i], imageList.get(i));
    preDescramblingList.add(newImageCharPosition);
  }
  
  return preDescramblingList;
}

class ImageCharPosition {
  CharPosition charPosition;
  PImage image;
  
  public ImageCharPosition(CharPosition charPosition, PImage image) {
    this.charPosition = charPosition;
    this.image = image;
  }
}
PImage img;
int parts;
String wordString;
String imagesPath;

int imageSizeWidth;
int imageSizeHeight;
int imagePosX;
int imagePosY;

/**
 * Saves portion of image depending on 
 */
private void savePortionOfImage(PImage refImage, int posX, int posY, int sizeX, int sizeY, int index) {
  println("saving file...");
  PImage croppedImage = refImage.get(posX, posY, sizeX, sizeY);
  croppedImage.save("Images/Parts/portionOfImage_partNo_" + index + ".png");
  println("saved file: portionOfImage_partNo_" + index + ".png");
}

private void splitImage(PImage refImage) {
  println("splitting image...");
  int index = 1;
    for(double x = 1; x <= parts; x++) {
    println("split part " + x + " from " + (imagePosX + (int)(imageSizeWidth * ((x-1)/parts))) + " x " + imagePosY + " of size " + imageSizeWidth/parts + " x " + imageSizeHeight);
      savePortionOfImage(refImage, (int)(imageSizeWidth * ((x-1)/parts)), 0, imageSizeWidth/parts, imageSizeHeight, index);
      index++;
    }
}

private ArrayList<PImage> loadParts() {
  println("loading parts...");
  ArrayList<PImage> imageList = new ArrayList<PImage>();
  
  for(int i = 1; i <= parts; i++) {
    println("loading image portionOfImage_partNo_" + i + ".png");
    PImage image = loadImage(imagesPath + "Parts/portionOfImage_partNo_" + i + ".png");
    imageList.add(image);
    println("added image to array");
  }
  
  return imageList;
}

private void placeParts(ArrayList<PImage> imageList, CharPosition[] alphabeticalArray) {
  println("placing parts...");
  
    for(double x = 0; x < parts; x++) {
      println("placing part: " + (x + 1) + " at coords " + (imagePosX + (int)(imageSizeWidth * (x/parts))) + " x " + imagePosY);
      image(imageList.get(alphabeticalArray[(int)x].position), imagePosX + (int)(imageSizeWidth * (x/parts)), imagePosY);
    }
}

private void placeParts(ArrayList<ImageCharPosition> imageCharPositionList) {
  println("placing parts...");
      for(double x = 0; x < parts; x++) {
      PImage image = getImageFromPosition(imageCharPositionList, (int)x);
      println("placing part: " + (x + 1) + " at coords " + (imagePosX + (int)(imageSizeWidth * (x/parts))) + " x " + imagePosY);
      image(image, imagePosX + (int)(imageSizeWidth * (x/parts)), imagePosY);
    }
}

private PImage getImageFromPosition(ArrayList<ImageCharPosition> imageCharPositionList, int position) {
  PImage image = new PImage();
  for(int i = 0; i < imageCharPositionList.size(); i++) {
    if(imageCharPositionList.get(i).charPosition.position == position) {
      println(imageCharPositionList.get(i).charPosition.charValue);
      image = imageCharPositionList.get(i).image;
    }
  }
  
  return image;
}

private CharPosition[] arrangeAlphabetically(CharPosition[] word) {
  
  CharPosition placeHolder;
  
  for(int charPosition = 0; charPosition < word.length; charPosition++) {
    for(int arrayPointer = 0; arrayPointer < word.length; arrayPointer++) {
      if (word[charPosition].charValue < word[arrayPointer].charValue) {
          placeHolder = word[charPosition];
          word[charPosition] = word[arrayPointer];
          word[arrayPointer] = placeHolder;
      }
    }
  }
    
    return word;
}

public CharPosition[] charPositionArray(char[] charArray) {
  CharPosition[] charPositionArray = new CharPosition[charArray.length];
  
  for(int i = 0; i < charArray.length; i++) {
    CharPosition newCharPosition = new CharPosition(charArray[i], i);
    charPositionArray[i] = newCharPosition;
  }
  
  return charPositionArray;
}

private class CharPosition {
  char charValue;
  int position;
  public CharPosition (char charValue, int position) {
    this.charValue = charValue;
    this.position = position;
  }
}
int button_scrambleX, button_scrambleY;
int scrambleMenuButtonWidth;     // Width of scramble menu button
int scrambleMenuButtonHeight;     // Height of scramble menu button
TextField scrambleTextField;

public void loadScrambleGUI() {
  if (!loaded) {
    background(255);
  }
  button_backX = width / 14;
  button_backY = height - (height / 6);
  button_scrambleX = width - (width / 14) - (width/10);
  button_scrambleY = height - (height / 6);
  //println(button_backY);
  scrambleMenuButtonWidth = width/10;
  scrambleMenuButtonHeight = height/10;
  
  if (backButtonOver) {
    fill(buttonHighlight);
  } else {
    fill(buttonColor);
  }
  stroke(255);
  rect(button_backX, button_backY, scrambleMenuButtonWidth, scrambleMenuButtonHeight);
  
  if (scrambleButtonOver) {
    fill(0, 160, 0);
  } else {
    fill(0, 230, 20);
  }
  stroke(0, 160, 0);
  rect(button_scrambleX, button_scrambleY, scrambleMenuButtonWidth, scrambleMenuButtonHeight);
  
  textSize(48);
  fill(0);
  text("Scramble Image", width/2 - 200, height/8);
  
  //Button Text
  textSize(20);
  fill(255);
  text("Back", button_backX + 40, button_backY + 47);
  fill(0);
  text("Scramble", button_scrambleX + 20, button_scrambleY + 35);
  text("Image", button_scrambleX + 30, button_scrambleY + 60);
  
  textSize(20);
  fill(0);
  text("Type word to scramble image with: ", width/3 - 150, height - 80);
  
  if (!loaded){
  
  imageSizeWidth = (width * 3)/5;
  imageSizeHeight = (height * 3)/5;
  imagePosX = (width/2) - (imageSizeWidth/2);
  imagePosY = height/7;
  img = loadImage("Images/endframecrop.jpg");
  img.resize(imageSizeWidth, imageSizeHeight);
  image(img, imagePosX, imagePosY);
  loaded = true;
  }
}







private void scrambleImage() {
  System.out.println("scrambling...");
  //this.wordString = wordString;
  char[] charArray = wordString.toLowerCase().toCharArray();
  CharPosition[] orginalPositionOfChars = charPositionArray(charArray);
  CharPosition[] alphabeticalArray = arrangeAlphabetically(orginalPositionOfChars);
  System.out.println(alphabeticalArray);
  parts = alphabeticalArray.length;
  System.out.println("parts: " + parts);
  splitImage(img);
  placeParts(loadParts(), alphabeticalArray);
  scrambling = false;
  saveScrambledImage();
}

public void saveScrambledImage(){
  PImage saveImage = get(imagePosX, imagePosY, imageSizeWidth, imageSizeHeight);
  saveImage.save("Images/scrambledImages/" + wordString + "_scrambled.png");
}

//void setup() {
//  imagesPath = "../Images";
//  //size(1000, 1000);
//  img = loadImage(imagesPath + "/endframecrop.jpg");
//  img.resize(imageSizeWidth, imageSizeHeight);
//  //scrambleImage();
  
//  deleteParts();
//}
  public void settings() {  size(1200, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "LibertySortCodeFinal" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
