import java.util.ArrayList;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (Dorm Designer 5000)
// Files:           (a list of all source files used by that program)
// Course:          (300, Spring, 2018)
//
// Author:          (Aniya Allen)
// Email:           (aallen27@@wisc.edu)
// Lecturer's Name: (Gary Dahl)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (Aramide Dada)
// Partner Email:   (adada@wisc.edu)
// Lecturer's Name: (Mouna Kacem)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   __X_ Write-up states that pair programming is allowed for this assignment.
//   __X_ We have both read and understand the course Pair Programming Policy.
//   _X__ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * (This purpose of this class is to create a game where one can design a dorm
 * room by arranging the furniture as one pleases)
 *
 * <p>
 * Bugs: (none known)
 *
 * @author (Aramide Dada)
 * @author (Aniya Allen)
 * 
 */

public class Main {

	private PApplet processing;
	private PImage backgroundImage;

	private ArrayList<DormGUI> guiObjects;
	// Max number of furniture that LoadButton will be allowed to load at once.
	private final static int MAX_LOAD_FURNITURE = 100;

	public Main(PApplet processing) {
		this.processing = processing;

		// Creates the ArrayList
		guiObjects = new ArrayList<DormGUI>();

		// Draw and upload background
		PImage backgroundImage = processing.loadImage("images/background.png");
		this.backgroundImage = backgroundImage;

		// Initialize and create bed button
		CreateFurnitureButton bedButton = new CreateFurnitureButton("bed", 50, 24, processing);
		guiObjects.add(bedButton);

		// Initialize and create sofa button
		CreateFurnitureButton sofaButton = new CreateFurnitureButton("sofa", 150, 24, processing);
		guiObjects.add(sofaButton);

		// Initialize and create dresser button
		CreateFurnitureButton dresserButton = new CreateFurnitureButton("dresser", 250, 24, processing);
		guiObjects.add(dresserButton);

		// Initialize and create desk button
		CreateFurnitureButton deskButton = new CreateFurnitureButton("desk", 350, 24, processing);
		guiObjects.add(deskButton);

		// Initialize and create sink button
		CreateFurnitureButton sinkButton = new CreateFurnitureButton("sink", 450, 24, processing);
		guiObjects.add(sinkButton);
		// Initialize and create clear button
		ClearButton clearButton = new ClearButton(550, 24, processing);
		guiObjects.add(clearButton);

		// Initialize and create save button
		SaveButton saveButton = new SaveButton(650, 24, processing);
		guiObjects.add(saveButton);

		// Initialize and create load button
		LoadButton loadButton = new LoadButton(750, 24, processing);
		guiObjects.add(loadButton);

	}

	public static void main(String[] args) {

		Utility.startApplication();

	}

	/*
	 * This purpose of this method is to update the game as the user interacts with
	 * it.
	 * 
	 * 
	 */
	public void update() {

		// Displays background
		processing.background(100, 150, 250);
		// Update background
		processing.image(backgroundImage, processing.width / 2, processing.height / 2);
		// WHAT IS DIS DOING AGAIN
		for (int i = 0; i < extractFurnitureFromGUIObjects().length; i++) {
			// The user should be able to click anywhere on the bed.
			if (extractFurnitureFromGUIObjects()[i] != null) {
				extractFurnitureFromGUIObjects()[i].update();
			}
		}

		// This calls the update() method for the objects in guiObjects
		for (int i = 0; i < guiObjects.size(); i++) {
			guiObjects.get(i).update();
		}

	}

	/*
	 * This purpose of this method is to enable the user to drag the bed around.
	 * 
	 * 
	 */
	public void mouseDown() {
		// Stores furniture from guiObjects ArrayList
		Furniture[] furniture = extractFurnitureFromGUIObjects();

		// Used to call mouseDown method of each button
		// Also allows user to drag furniture objects
		for (int i = 0; i < guiObjects.size(); i++) {
			guiObjects.get(i).mouseDown(furniture);
		}

		// Places all the furniture into the ArrayList
		replaceFurnitureInGUIObjects(furniture);

	}

	/*
	 * This purpose of this method is to stop the bed from following the mouse when
	 * the user is not clicking on a bed.
	 * 
	 */

	public void mouseUp() {

		// This calls the mouseUp() method for the objects in guiObjects
		for (int i = 0; i < guiObjects.size(); i++) {
			guiObjects.get(i).mouseUp();
		}
	}

	/*
	 * This purpose of this method is to delete or rotate furniture when the user
	 * presses the key 'd' or 'D', or 'r' or 'R'.
	 * 
	 */
	public void keyPressed() {
		// Stores furniture from guiObjects ArrayList
		Furniture[] furniture = extractFurnitureFromGUIObjects();

		// Deletes bed
		if (processing.key == 'D' || processing.key == 'd') {
			for (int i = 0; i < furniture.length; i++) {
				if (furniture[i] != null && furniture[i].isMouseOver()) {
					furniture[i] = null;
					// Break from loop, so we do not delete other beds
					break;
				}
			}
			// Rotates bed
		} else if (processing.key == 'R' || processing.key == 'r') {
			for (int i = 0; i < furniture.length; i++) {
				if (furniture[i] != null && furniture[i].isMouseOver() == true) {
					furniture[i].rotate();
					break;
				}
			}
		}
		// Places non-null references in the guiObjects ArrayList
		replaceFurnitureInGUIObjects(furniture);
	}

	/**
	 * This method creates a new Furniture[] for the old mouseDown() methods to make
	 * use of. It does so by copying all Furniture references from this.guiObjects
	 * into a temporary array of size MAX_LOAD_FURNITURE.
	 * 
	 * @return that array of Furniture references.
	 */
	private Furniture[] extractFurnitureFromGUIObjects() {
		Furniture[] furniture = new Furniture[MAX_LOAD_FURNITURE];
		int nextFreeIndex = 0;
		for (int i = 0; i < guiObjects.size() && nextFreeIndex < furniture.length; i++)
			if (guiObjects.get(i) instanceof Furniture)
				furniture[nextFreeIndex++] = (Furniture) guiObjects.get(i);
		return furniture;
	}

	/**
	 * This method first removes all Furniture references from this.guiObjects, and
	 * then adds back in all of the non-null references from it's parameter.
	 * 
	 * @param furniture
	 *            contains the only furniture that will be left in this.guiObjects
	 *            after this method is invoked (null references ignored).
	 */
	private void replaceFurnitureInGUIObjects(Furniture[] furniture) {
		for (int i = 0; i < guiObjects.size(); i++)
			if (guiObjects.get(i) instanceof Furniture)
				guiObjects.remove(i--);
		for (int i = 0; i < furniture.length; i++)
			if (furniture[i] != null)
				guiObjects.add(furniture[i]);
	}

}