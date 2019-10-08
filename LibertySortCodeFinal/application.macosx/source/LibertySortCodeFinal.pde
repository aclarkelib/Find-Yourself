import java.awt.TextField;


int button_scrambleMenuX, button_scrambleMenuY;      // Position of scramble menu button
int button_descrambleMenuX, button_descrambleMenuY;  // Position of descramble menu button
int button_backX, button_backY;
int mainMenuButtonSize;     // Diameter of main menu button
color buttonColor;
color buttonHighlight;
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

void setup() {
  size(1200, 800);
  wordString = "";
  loadMainMenu();
}

void loadMainMenu() {
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

void draw() {
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

void update(int x, int y) {
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

void mousePressed() {
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

void keyPressed() {
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

boolean overRect(int x, int y, int width, int height)  {
  if (mouseX >= x && mouseX <= x+width && 
      mouseY >= y && mouseY <= y+height) {
    return true;
  } else {
    return false;
  }
}
