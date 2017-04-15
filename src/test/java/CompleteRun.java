import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import curvePlotter.GraphPanel;
import curvePlotter.Input;
import curvePlotter.Main;
import jsat.SimpleDataSet;
import jsat.classifiers.CategoricalData;
import jsat.classifiers.DataPoint;
import jsat.datatransform.PCA;
import jsat.datatransform.visualization.Isomap;
import jsat.linear.DenseMatrix;
import jsat.linear.Matrix;
import jsat.linear.Vec;
import jsat.linear.VecPaired;
import jsat.linear.distancemetrics.EuclideanDistance;
import jsat.linear.vectorcollection.VectorArray;
import net.ericaro.surfaceplotter.JSurfacePanel;
import net.ericaro.surfaceplotter.Mapper;
import net.ericaro.surfaceplotter.ProgressiveSurfaceModel;
import net.ericaro.surfaceplotter.surface.ArraySurfaceModel;

public class CompleteRun {

	JTextField ip;
	JFrame jf;
	JFrame nextFrame;
	final int MAXLINES = 1000;
	SimpleDataSet data;
	boolean isPcaB, isIMB;
	
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
        
		new CompleteRun().proceed();
	}
	
	public void setProperties(JFrame jf)
	{
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setFocusable(true);
		jf.setLocationRelativeTo(null);
		jf.setSize(500, 300);
	}
	
	void doFuncThings()
	{
		JCheckBox b2d = new JCheckBox("2-Dimensional Graph");
		JCheckBox b3d = new JCheckBox("3-Dimensional Graph");
		
		nextFrame = new JFrame();
		setProperties(nextFrame);
		
		JPanel jfp = new JPanel();
		nextFrame.add(jfp);
		jfp.add(b2d);
		jfp.add(b3d);
		
		ip = new JTextField(20);
		ip.setText("Enter your 3-D function first");
		jfp.add(ip);
		
		b2d.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				nextFrame.dispose();
				Main m = new Main();
				m.go();
			}
		});			
		
		b3d.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				String input3D = ip.getText();
				if (input3D.length() > 0)
				{
					nextFrame.dispose();
					draw3DFunction(input3D);
				}
			}
		});
	}
	
	SimpleDataSet getDataSetFromFile(String file)
	{
		file = file.replace("\\", "/");
		File f = new File(file);
		System.out.println(file);
		BufferedReader br;
		try {
			
			br = new BufferedReader(new FileReader(f));
			String line = br.readLine();
			String[] values = line.split(",");
			Matrix proj_data = new DenseMatrix(MAXLINES, values.length);
			
			int j, linecnt = 0;
			
			do {
				j = 0;
				for (String value : line.split(",")) {
					proj_data.set(linecnt, j, Double.parseDouble(value));
					j++;
				}
				linecnt++;
			}
			while((line = br.readLine()) != null);
			br.close();		
			
			SimpleDataSet ds = new SimpleDataSet(new CategoricalData[0], proj_data.cols());
	        for(j = 0; j < linecnt; j++)
	            ds.add(new DataPoint(proj_data.getRow(j)));
	        
	        return ds;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	SimpleDataSet toDataSet(Matrix proj_data)
	{
		SimpleDataSet ds = new SimpleDataSet(new CategoricalData[0], proj_data.cols());
        for(int j = 0; j < proj_data.rows(); j++)
            ds.add(new DataPoint(proj_data.getRow(j)));
        
        return ds;
	}
	
	public SimpleDataSet runPCA(SimpleDataSet ds, int dimesionRequired)
    {    
        PCA pca = new PCA(dimesionRequired);
        pca.fit(ds);
       
        Matrix m = ds.getDataMatrix();
        Matrix transformed = new DenseMatrix(m.rows(), dimesionRequired);
        for (int i = 0; i < m.rows(); i++)
        {
        	DataPoint dp = pca.transform(ds.getDataPoint(i));
        	Vec v = dp.getNumericalValues();
        	for (int j = 0; j < v.length(); j++)
        	{
        		System.out.print(v.get(j) + " , ");
        		transformed.set(i, j, v.get(j));
        	}
        	System.out.println();
        }           
        
        return toDataSet(transformed);
    }
	
	public SimpleDataSet runIsomap(SimpleDataSet proj, int dimesionRequired)
    {
        Isomap instance = new Isomap();
        
        final int K = 2;
        instance.setNeighbors(K);
        
        List<Set<Integer>> origNNs = new ArrayList<Set<Integer>>();
        VectorArray<VecPaired<Vec, Integer>> proj_vc = new VectorArray<VecPaired<Vec, Integer>>(new EuclideanDistance());
        for(int i = 0; i < proj.getSampleSize(); i++)
            proj_vc.add(new VecPaired<Vec, Integer>(proj.getDataPoint(i).getNumericalValues(), i));
        
        for(int i = 0; i < proj.getSampleSize(); i++)
        {
            Set<Integer> nns = new HashSet<Integer>();
            for(VecPaired<VecPaired<Vec, Integer>, Double> neighbor : proj_vc.search(proj_vc.get(i), K))
                nns.add(neighbor.getVector().getPair());
            origNNs.add(nns);
        }

        ArrayList<SimpleDataSet> list = new ArrayList<SimpleDataSet>();
        
        for(boolean cIsomap : new boolean[]{true, false})
        {
            instance.setCIsomap(cIsomap);    
            instance.setTargetDimension(dimesionRequired);
            SimpleDataSet transformed = instance.transform(proj);
            list.add(transformed);
            
            Matrix m = proj.getDataMatrix();
            for (int i = 0; i < m.rows(); i++)
            {
            	for (int j = 0; j < m.cols(); j++)
            	{
            		System.out.print(m.get(i, j) + " , ");
            	}
            	System.out.println();
            }
            
            System.out.println();

            m = transformed.getDataMatrix();
            for (int i = 0; i < m.rows(); i++)
            {
            	for (int j = 0; j < m.cols(); j++)
            	{
            		System.out.print(m.get(i, j) + " , ");
            	}
            	System.out.println();
            }
        }
        
        return list.get(0);
    }

	void draw2DPoints(SimpleDataSet ds) 
	{
		nextFrame = new JFrame();
		setProperties(nextFrame);
		nextFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		GraphPanel graphPanel = new GraphPanel(ds.getDataMatrix(), nextFrame.getWidth(), nextFrame.getHeight());
		nextFrame.add(graphPanel);
	}
	
	void draw3DPoints(SimpleDataSet ds)
	{
		JSurfacePanel jsp = new JSurfacePanel();
		jsp.setTitleText("3D Visualization Tool");

		nextFrame = new JFrame("test");
		nextFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nextFrame.getContentPane().add(jsp, BorderLayout.CENTER);
		nextFrame.pack();
		nextFrame.setVisible(true);

		double xmin, xmax, ymin, ymax;
		xmin = ymin = 1000;
		xmax = ymax = -1000;
		
		Matrix m = ds.getDataMatrix();
		for (int i = 0; i < m.rows(); i++)
		{
			xmin = Math.min(xmin, m.get(i, 0));
			xmax = Math.max(xmax, m.get(i, 0));
			ymin = Math.min(ymin, m.get(i, 1));
			ymax = Math.max(ymax, m.get(i, 1));
		}
		
		for (int i = 0; i < m.rows(); i++)
		{
			double temp = m.get(i, 0);
			m.set(i, 0, temp - xmin);
			temp = m.get(i, 1);
			m.set(i, 1, temp - ymin);
		}
		
		int bigger = Math.max((int)(xmax - xmin) + 1, (int)(ymax - ymin) + 1);

		float[][] z1 = new float[bigger][bigger];
		for (int i = 0; i < bigger; i++)
		{
			for (int j = 0; j < bigger; j++)
			{
				z1[i][j] = -1.01f;
			}
		}
		
		for (int i = 0; i < m.rows(); i++)
		{
			z1[(int)m.get(i, 0)][(int)m.get(i, 1)] = (float)m.get(i, 2); 
		}
		
		ArraySurfaceModel sm = new ArraySurfaceModel();
		sm.setValues(0f, (float)(xmax - xmin), 0f, (float)(ymax - ymin), bigger, z1, null);
		jsp.setModel(sm);		
	}
	
	void draw3DFunction(String func)
	{
		JSurfacePanel jsp = new JSurfacePanel();
		jsp.setTitleText("3D Visualization Tool");

		nextFrame = new JFrame("test");
		nextFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nextFrame.getContentPane().add(jsp, BorderLayout.CENTER);
		nextFrame.pack();
		nextFrame.setVisible(true);

		ProgressiveSurfaceModel model = new ProgressiveSurfaceModel() ;
		
		model.setMapper(new Mapper() {
			public  float f1( float x, float y)
			{
				float r = (x*x + y*y);
				if (r == 0) return 1f;
				return r;
//				return (float)( Math.sin(r)/(r));
			}
			
			public  float f2( float x, float y)
			{
				return (float)(Math.sin(x*y));
			}
		});
		model.plot().execute();
		jsp.setModel(model);
	}
	
	void doDataValuesThings()
	{
		nextFrame = new JFrame();
		setProperties(nextFrame);
		
		JPanel jfp = new JPanel();
		nextFrame.add(jfp);

		ip = new JTextField(40);
		jfp.add(ip);
		ip.setEditable(false);
		
		JButton browse = new JButton("Browse");
		browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
				int rVal = c.showOpenDialog(nextFrame);
				if (rVal == JFileChooser.APPROVE_OPTION) {
				    ip.setText(c.getSelectedFile().getAbsolutePath());
				}	
			}
		});
		
		jfp.add(browse);
		
		final JTextField dimensionRequired = new JTextField(20);
		isPcaB = isIMB = false;
		
		JButton upload = new JButton("Upload File");
		upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filePath = ip.getText();
				data = getDataSetFromFile(filePath);

				if (data.getNumFeatures() > 3 || dimensionRequired.getText().length() > 0) {
					if (isIMB)
						data = runIsomap(data, Integer.parseInt(dimensionRequired.getText()));
					else
						data = runPCA(data, Integer.parseInt(dimensionRequired.getText()));
				}
				
				if (data.getNumFeatures() == 1) {
					
					Matrix temp = data.getDataMatrix();
					Matrix copy = new DenseMatrix(temp.rows(), 2);
					for (int i = 0; i < temp.rows(); i++)
					{
						copy.set(i, 0, temp.get(i, 0));
						copy.set(i, 1, 0);
					}
					
					data = toDataSet(copy);
				}
				
				if (data.getNumFeatures() == 2) {
					nextFrame.dispose();
					draw2DPoints(data);
				}
				else {					
					nextFrame.dispose();
					draw3DPoints(data);
				}
			}
		});
		jfp.add(upload);				
		jfp.add(dimensionRequired);
		
		JCheckBox isPca = new JCheckBox("PCA");
		JCheckBox isIM = new JCheckBox("ISOMAP");
		
		jfp.add(isIM);
		jfp.add(isPca);
	
		isPca.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				isPcaB = true;
			}
		});
		
		isIM.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				isIMB = true;
			}
		});
	}
	
	public void proceed()
	{
		jf = new JFrame("Plotter");
		setProperties(jf);
		
		JPanel jp = new JPanel();
		jf.add(jp);
		
		JButton func, dataValues;
		func = new JButton("Function");
		dataValues = new JButton("Data Values");
		
		jp.add(func);
		jp.add(dataValues);
		
		func.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
				doFuncThings();
			}
		});
		
		dataValues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
				doDataValuesThings();
			}
		});
	}
}