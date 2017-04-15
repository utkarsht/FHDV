import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicArrowButton;

import net.ericaro.surfaceplotter.JSurfacePanel;
import net.ericaro.surfaceplotter.Mapper;
import net.ericaro.surfaceplotter.ProgressiveSurfaceModel;
import net.ericaro.surfaceplotter.surface.AbstractSurfaceModel;
import net.ericaro.surfaceplotter.surface.ArraySurfaceModel;
import net.ericaro.surfaceplotter.surface.SurfaceVertex;

public class SimpleRun {

	public void testSomething() {
		JSurfacePanel jsp = new JSurfacePanel();
		jsp.setTitleText("3D Visualization Tool");

		JFrame jf = new JFrame("test");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().add(jsp, BorderLayout.CENTER);
		jf.pack();
		jf.setVisible(true);

/*		ProgressiveSurfaceModel model = new ProgressiveSurfaceModel() ;
		
		model.setMapper(new Mapper() {
			public  float f1( float x, float y)
			{
				float r = x*x+y*y;
				
				if (r == 0 ) return 1f;
				return (float)( Math.sin(r)/(r));
			}
			
			public  float f2( float x, float y)
			{
				return (float)(Math.sin(x*y));
			}
		});
		model.plot().execute();
*/
		/*
		File f = new File("B://Test//fjava.txt");
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		String dot = ".";
		Scanner in = new Scanner(System.in);
		while(in.hasNext())
		{
			temp = in.nextLine();
			if(temp.equals(dot))	break;				//		enter . to exit from Input
			bw.write(temp + "\n");
		}
		bw.close();
		
		//	Reading start from File
		BufferedReader br = new BufferedReader(new FileReader(f));
		while((temp = br.readLine()) != null)
		{
			System.out.println(temp);
		}
		br.close();
		*/
		Random rand = new Random();
		int max = 10;
		float[][] z1 = new float[max][max];
		float[][] z2 = new float[max][max];
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < max; j++) {
				
				if (i == 2)
					continue;
				z1[i][j] = (float)Math.sin(i*j);//rand.nextFloat() * 20 - 10f;
				z2[i][j] = rand.nextFloat() * 20 - 10f;
			}
		}
		
		z1[3][4] = 4;
		z1[3][5] = -4;
		
		ArraySurfaceModel sm = new ArraySurfaceModel();
		sm.setValues(0f,10f,0f,10f,max, z1, null);
//		jsp.setModel(model);
		jsp.setModel(sm);
		// sm.doRotate();

		// canvas.doPrint();
		// sm.doCompute();
	}

	public static float f1(float x, float y) {		
		return (float) (Math.sin(x * x + y * y) / (x * x + y * y));
		// return (float)(10*x*x+5*y*y+8*x*y -5*x+3*y);
	}

	public static float f2(float x, float y) {
		return (float) (Math.sin(x * x - y * y) / (x * x + y * y));
		// return (float)(10*x*x+5*y*y+15*x*y-2*x-y);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				new SimpleRun().testSomething();
			}
		});

	}

}
