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
