package curvePlotter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class Input extends JFrame
{
    private GraphPanel graphPanel;
    public Input(GraphPanel graphPanel)
    {
        this.graphPanel = graphPanel;
        initComponents();
    }
    
    public Input()
    {
        initComponents();
    }

    private void initComponents()
    {
        drawFx = new JButton();
        clearFx = new JButton();
        shadeArea = new JButton();
        clearArea = new JButton();
        calcArea = new JButton();
        calcSlope = new JButton();
        drawTangent = new JButton();
        clearTangent = new JButton();
        drawPolarCurve = new JButton();
        clearPolarCurve = new JButton();
        mapRelation = new JButton();
        clearRelation = new JButton();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        jLabel10 = new JLabel();
        jLabel11 = new JLabel();
        jLabel12 = new JLabel();
        jLabel13 = new JLabel();
        jSeparator1 = new JSeparator();
        jSeparator2 = new JSeparator();
        Fx = new JTextField();
        highLimit = new JTextField();
        textArea = new JTextField();
        lowLimit = new JTextField();
        tangentPoint = new JTextField();
        textSlope = new JTextField();
        polarFx = new JTextField();
        fromLimit = new JTextField("0.00");
        toLimit = new JTextField("6.28");
        textLHS = new JTextField();
        textRelation = new JTextField();
        textRHS = new JTextField();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        jLabel1.setText("Cartresian Functions");

        jLabel2.setText("F(x) :");

        drawFx.setText(" Draw ");
        drawFx.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                drawFxActionPerformed(evt);
            }
        });

        clearFx.setText("Clear");
        clearFx.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                clearFxActionPerformed(evt);
            }
        });

        jLabel3.setText("Integral :");
        jLabel4.setFont(new java.awt.Font("Wide Latin", 1, 48)); 

        jLabel5.setFont(new java.awt.Font("Agency FB", 1, 36)); 
        jLabel5.setText("S");

        shadeArea.setText("Shade");
        shadeArea.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                shadeAreaActionPerformed(evt);
            }
        });

        clearArea.setText("Clear");
        clearArea.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                clearAreaActionPerformed(evt);
            }
        });

        jLabel6.setText("Slope at :");

        calcArea.setText("Calculate Area");
        calcArea.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                calcAreaActionPerformed(evt);
            }
        });

        calcSlope.setText("Calculate Slope");
        calcSlope.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                calcSlopeActionPerformed(evt);
            }
        });

        drawTangent.setText(" Draw ");
        drawTangent.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                drawTangentActionPerformed(evt);
            }
        });

        clearTangent.setText("Clear");
        clearTangent.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                clearTangentActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        jLabel7.setText("Polar Functions");

        jLabel8.setText("R :");

        drawPolarCurve.setText(" Draw ");
        drawPolarCurve.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                drawPolarCurveActionPerformed(evt);
            }
        });

        clearPolarCurve.setText("Clear");
        clearPolarCurve.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                clearPolarCurveActionPerformed(evt);
            }
        });

        jLabel9.setText("From :");
        jLabel10.setText("To :");
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        jLabel11.setText("Relations");

        jLabel12.setText("LHS ");
        jLabel13.setText("RHS");



        mapRelation.setText(" Map ");
        mapRelation.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                mapRelationActionPerformed(evt);
            }
        });

        clearRelation.setText("Clear");
        clearRelation.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                clearRelationActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
                        .addGap(178, 178, 178))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(209, 209, 209))))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(calcSlope)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(fromLimit, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(toLimit, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(36, 36, 36)
                                .addComponent(polarFx, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(drawPolarCurve)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clearPolarCurve)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1))))
                .addGap(27, 27, 27))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(highLimit, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(Fx)
                                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(10, 10, 10)
                                                        .addComponent(jLabel5))
                                                    .addComponent(lowLimit, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
                                                .addGap(21, 21, 21)
                                                .addComponent(jLabel4))
                                            .addComponent(tangentPoint, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(calcArea)
                                                .addGap(46, 46, 46))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                    .addComponent(textArea)
                                                    .addComponent(textSlope))))))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(drawFx, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(shadeArea, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(drawTangent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(clearFx))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(clearArea))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(clearTangent)))
                                .addGap(23, 23, 23))))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel11))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(27, 27, 27)
                                .addComponent(textLHS)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textRelation, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textRHS, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addGap(40, 40, 40))))
            .addGroup(layout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addComponent(mapRelation)
                .addGap(37, 37, 37)
                .addComponent(clearRelation)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                    .addComponent(Fx, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(drawFx)
                    .addComponent(clearFx))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(highLimit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                    .addGap(1, 1, 1)
                                    .addComponent(lowLimit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(tangentPoint, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(textSlope, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(shadeArea)
                                .addComponent(clearArea))
                            .addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calcArea)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(drawTangent)
                            .addComponent(clearTangent))))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calcSlope)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(polarFx, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(drawPolarCurve)
                        .addComponent(clearPolarCurve)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(fromLimit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(toLimit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(textLHS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(textRelation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(textRHS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(mapRelation)
                    .addComponent(clearRelation))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
    
    private void drawFxActionPerformed(ActionEvent evt)
    {                                         
        String equation = Fx.getText();
        graphPanel.drawXYCurve(equation);
    }                                        

    private void clearFxActionPerformed(ActionEvent evt)
    {                                         
        graphPanel.clearXYCurve();
    }                                        

    private void calcAreaActionPerformed(ActionEvent evt)
    {
        String equation = Fx.getText();
        double lowLim = Double.parseDouble(lowLimit.getText());
        double highLim = Double.parseDouble(highLimit.getText());
        
        double area = graphPanel.getIntegral(equation, lowLim, highLim);
        if(area == GraphPanel.NOTFOUND)
        	textArea.setText("Can't be determined");
        else
        	textArea.setText(area + "");
    }

    private void shadeAreaActionPerformed(ActionEvent evt)
    {
    	String equation = Fx.getText();
    	double lowLim = Double.parseDouble(lowLimit.getText());
        double highLim = Double.parseDouble(highLimit.getText());
        
        graphPanel.shadeArea(equation, lowLim, highLim);
    }
        
    private void clearAreaActionPerformed(ActionEvent ect)
    {
    	graphPanel.clearArea();
    }

    private void drawTangentActionPerformed(ActionEvent evt)
    {                                         
    	String equation = Fx.getText();
    	double tPoint = Double.parseDouble(tangentPoint.getText());
    	
    	graphPanel.drawTangent(equation, tPoint);
    }                                        

    private void clearTangentActionPerformed(ActionEvent evt)
    {                                       
    	graphPanel.clearTangent();
    }                                        

    private void calcSlopeActionPerformed(ActionEvent evt)
    {
    	String equation = Fx.getText();
    	double tPoint = Double.parseDouble(tangentPoint.getText());
    	
    	double slope = graphPanel.getSlope(equation, tPoint);
    	if(slope == GraphPanel.NOTFOUND)
    		textSlope.setText("Can't be determined");
    	else
    		textSlope.setText(slope + "");
    }

    private void drawPolarCurveActionPerformed(ActionEvent evt)
    {                                       
    	String polarEquation = polarFx.getText();
    	double from = Double.parseDouble(fromLimit.getText());
    	double to = Double.parseDouble(toLimit.getText());
 
    	graphPanel.drawPolarCurve(polarEquation, from, to);
    }                                        

    private void clearPolarCurveActionPerformed(ActionEvent evt)
    {                                       
    	graphPanel.clearPolarCurve();
    }                           

    private void mapRelationActionPerformed(ActionEvent evt)
    {
    	String LHS = textLHS.getText();
    	String RHS = textRHS.getText();
    	String relation = textRelation.getText();
    	
    	graphPanel.drawRelation(LHS, relation, RHS);
    }   

    private void clearRelationActionPerformed(ActionEvent evt)
    {
        graphPanel.clearRelation();
    }           

    public static void main(String args[])
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

        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new Input().setVisible(true);
            }
        });
    }

    // Variables declaration                  
    private JButton drawFx;
    private JButton clearPolarCurve;
    private JButton mapRelation;
    private JButton clearRelation;
    private JButton clearFx;
    private JButton shadeArea;
    private JButton clearArea;
    private JButton calcArea;
    private JButton calcSlope;
    private JButton drawTangent;
    private JButton clearTangent;
    private JButton drawPolarCurve;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JTextField Fx;
    private JTextField highLimit;
    private JTextField textArea;
    private JTextField lowLimit;
    private JTextField tangentPoint;
    private JTextField textSlope;
    private JTextField polarFx;
    private JTextField fromLimit;
    private JTextField toLimit;
    private JTextField textLHS;
    private JTextField textRelation;
    private JTextField textRHS;
    // End of variables declaration                   
}
