public class CreateBedButton extends Button implements DormGUI {



	public CreateBedButton(float x, float y, PApplet processing) {
		super(x, y, processing);
		this.processing = processing;

		super.label = "Create Bed";

	}

	

	@Override
	public void mouseDown(Furniture [] furniture) {
		Furniture bed = new Furniture("bed", processing);
		if (isMouseOver()) {
			//Furniture bed = new Furniture("bed", processing);
			
			for(int i = 0; i < furniture.length; i++) {
				if(furniture[i] == null) {
					furniture[i] = bed;
					break;
				}
			}

			
		}
	} // After step 10, this method will instead return Furniture


}
