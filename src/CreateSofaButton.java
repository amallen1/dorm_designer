
public class CreateSofaButton extends Button implements DormGUI{



	public CreateSofaButton(float x, float y, PApplet processing) {
		super(x, y, processing);
		this.processing = processing;

		super.label = "Create Sofa";

	}

	

	@Override
	public void mouseDown(Furniture [] furniture) {
		Furniture sofa = new Furniture("sofa", processing);
		
		if (isMouseOver()) {
			//Furniture sofa = new Furniture("sofa", processing);
			
			for(int i = 0; i < furniture.length; i++) {
				if(furniture[i] == null) {
					furniture[i] = sofa;
					break;
				}
			}
			
		}
	} // After step 10, this method will instead return Furniture



}
