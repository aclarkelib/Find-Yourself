import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;

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
