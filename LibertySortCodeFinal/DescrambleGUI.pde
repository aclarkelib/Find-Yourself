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
