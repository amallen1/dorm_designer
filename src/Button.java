/**
 * (This purpose of this class is to control the various buttons that the user
 * can click on)
 * <p>
 * Bugs: (none known)
 *
 * @author (Aramide Dada)
 * @author (Aniya Allen)
 * 
 */
public class Button implements DormGUI {

	private static final int WIDTH = 96;
	private static final int HEIGHT = 32;
	protected PApplet processing;
	private float[] position;
	protected String label;

	public Button(float x, float y, PApplet processing) {
		this.processing = processing;

		float[] position = new float[2];
		position[0] = x;
		position[1] = y;
		this.position = position;

		String label = "Button";
		this.label = label;
	}

	public void update() {
		// draw and fill rectangle
		if (isMouseOver()) {
			processing.fill(100);
		} else {
			processing.fill(200);
		}
		processing.rect((position[0] - WIDTH / 2), (position[1] - HEIGHT / 2), (position[0] + WIDTH / 2),
				(position[1] + HEIGHT / 2));

		// text for the button's label
		processing.fill(0);
		processing.text(label, position[0], position[1]);
	}

	public void mouseDown(Furniture[] furniture) {
		if (isMouseOver()) {
			System.out.println("A button was pressed.");
		}
	}

	public void mouseUp() {
	}

	public boolean isMouseOver() {
		if ((processing.mouseX <= (position[0] + (WIDTH / 2)) && processing.mouseX >= (position[0] - (WIDTH / 2)))
				&& (processing.mouseY <= (position[1] + (HEIGHT / 2))
						&& processing.mouseY >= ((position[1] - (HEIGHT / 2))))) {

			return true;
		} else {
			return false;
		}
	}

}