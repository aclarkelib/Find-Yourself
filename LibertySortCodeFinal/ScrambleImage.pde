import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;



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
<<<<<<< HEAD
  try {
    PImage saveImage = get(imagePosX, imagePosY, imageSizeWidth, imageSizeHeight);
    saveImage.save(sketchPath() + "/Images/scrambledImages/" + wordString + "_scrambled.png");
  } catch (Exception e) {
    println(e);
  }
}
=======
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
>>>>>>> 750cdd9df694bda2fe83451516cf84b878b6f1e8
