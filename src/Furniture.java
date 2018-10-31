/**
 * (This purpose of this class is to create and display furniture based on the
 * button, create bed button or create sofa button, that the user presses.)
 *
 * <p>
 * Bugs: (none known)
 *
 * @author (Aramide Dada)
 * @author (Aniya Allen)
 * 
 */

public class Furniture implements DormGUI {

	private PApplet processing;
	private PImage image;
	private float[] position;
	private boolean isDragging;
	private int rotations;
	private String furniture;

	// initializes the fields of a new bed object positioned in the center of
	// the display
	public Furniture(String furniture, PApplet processing) {
		this.processing = processing;

		// bed position
		float[] position = new float[2];

		// create a new bed object positioned at the center.
		position[0] = processing.width / 2;
		position[1] = processing.height / 2;
		this.setPosition(position);

		// bed image
		PImage image = processing.loadImage("images/" + furniture + ".png");
		this.image = image;

		// if the bed is dragging
		this.isDragging = false;

		// Keeps track of number of rotations
		this.setRotations(0);

		// Stores String for furniture inputed
		this.setFurniture(furniture);

	}

	// ADD COMMMENT
	public String getFurniture() {
		return furniture;
	}

	// ADD COMMENT
	public void setFurniture(String furniture) {
		this.furniture = furniture;
	}

	// Draws furniture at specific positions saved by user
	public Furniture(String furnitureName, float xPosition, float yPosition, int rotations, PApplet processing) {
		this.processing = processing;

		// bed position
		float[] position = new float[2];

		// create a new bed object positioned at the center.
		position[0] = xPosition;
		position[1] = yPosition;
		this.setPosition(position);

		// bed image
		PImage image = processing.loadImage("images/" + furnitureName + ".png");
		this.image = image;

		// if the bed is dragging
		this.isDragging = false;

		// Keeps track of number of rotations
		this.setRotations(rotations);

		// Stores String for furniture inputed
		this.setFurniture(furnitureName);

	}

	// draws this bed at its current position
	public void update() {
		// Places the bed in the positions stored in positions.
		if (getRotations() >= 1) {
			processing.image(image, position[0], position[1], getRotations() * PApplet.PI / 2);
		} else {
			processing.image(image, position[0], position[1]);
		}
		// check if bed is dragging, if it is, put it at the position of the
		// mouse
		if (isDragging == true) {
			getPosition()[0] = processing.mouseX;
			getPosition()[1] = processing.mouseY;

		}

	}

	// Gets the position
	public float[] getPosition() {
		return position;
	}

	// Sets the position
	public void setPosition(float[] position) {
		this.position = position;
	}

	// used to start dragging the bed, when the mouse is over this bed when it
	// is pressed
	public void mouseDown(Furniture[] furniture) {
		if (isMouseOver()) {
			isDragging = true;

		}

	}

	// used to indicate that the bed is no longer being dragged
	public void mouseUp() {
		isDragging = false;

	}

	// helper method to determine whether the mouse is currently over this bed
	public boolean isMouseOver() {
		boolean mouseOver = false;

		if (getRotations() % 2 != 0) {
			if ((processing.mouseY <= (getPosition()[1] + (image.width / 2))
					&& processing.mouseY >= (getPosition()[1] - (image.width / 2)))
					&& (processing.mouseX <= (getPosition()[0] + (image.height / 2))
							&& processing.mouseX >= ((getPosition()[0] - (image.height / 2))))) {
				mouseOver = true;
			}

		} else if (getRotations() % 2 == 0) {
			if ((processing.mouseY <= (getPosition()[1] + (image.height / 2))
					&& processing.mouseY >= ((getPosition()[1] - (image.height / 2)))
					&& (processing.mouseX <= (getPosition()[0] + (image.width / 2))
							&& processing.mouseX >= ((getPosition()[0] - (image.width / 2)))))) {
				mouseOver = true;
			}
		} else {
			mouseOver = false;
		}

		return mouseOver;

	}

	// allows the user to rotate the bed.
	public void rotate() {
		if (isMouseOver()) {

			setRotations(getRotations() + 1);
		}

	}

	// Gets the rotations
	public int getRotations() {
		return rotations;
	}

	// Sets the rotations
	public void setRotations(int rotations) {
		this.rotations = rotations;
	}

}
