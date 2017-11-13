import java.awt.event.ActionEvent;
/**
 * Controller for a TestCaseFrame. 
 * Takes text inputed in the TestCaseFrame and creates a KNN test case for it
 * 
 * @author Gabrielle
 *
 */
public class TestCaseFrameController extends MainController {

	private View view;
	private TestCaseFrame frame;
	
	public TestCaseFrameController(View view, TestCaseFrame testCaseFrame) {
		this.view = view;
		frame = testCaseFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
