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
<<<<<<< HEAD
  img = loadImage(sketchPath() + "/Images/endframecrop.jpg");
=======
  img = loadImage("Images/endframecrop.jpg");
>>>>>>> 750cdd9df694bda2fe83451516cf84b878b6f1e8
  img.resize(imageSizeWidth, imageSizeHeight);
  image(img, imagePosX, imagePosY);
  loaded = true;
  }
}
