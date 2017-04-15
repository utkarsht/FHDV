package curvePlotter;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Main extends JFrame
{
	int widthLimit, heightLimit;
	GraphPanel graphPanel;
	Input inputpanel;
	Solve solve;	
	
	public static void main(String[] args) 
	{
		try
        {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(Input.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
		new Main().go();
	}
	
	public void go()
	{	
		this.setTitle("Graph Plotter v1.0");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setFocusable(true);
		this.setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		heightLimit = this.getHeight();
		widthLimit = this.getWidth();
		
		solve = new Solve();
		
		graphPanel = new GraphPanel(this, solve);
		this.add(graphPanel);

		inputpanel = new Input(graphPanel);
		inputpanel.setTitle("Graph Plotter v1.0 Input");
		inputpanel.setVisible(true);										
		inputpanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
	}
}