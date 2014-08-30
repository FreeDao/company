package com.era.util;
 import java.awt.Color;

import java.awt.Font;

import java.util.ArrayList;

import java.util.List;

 

import javax.swing.JFrame;

import javax.swing.JPanel;

 

import org.jfree.chart.ChartFactory;

import org.jfree.chart.ChartPanel;

import org.jfree.chart.JFreeChart;

import org.jfree.chart.block.BlockContainer;

import org.jfree.chart.block.BorderArrangement;

import org.jfree.chart.labels.StandardPieSectionLabelGenerator;

import org.jfree.chart.plot.PiePlot;

import org.jfree.chart.title.LegendTitle;

import org.jfree.data.general.DefaultPieDataset;

import org.jfree.data.general.PieDataset;

import org.jfree.ui.HorizontalAlignment;

import org.jfree.ui.RectangleEdge;

import org.jfree.ui.RefineryUtilities;

import org.jfree.util.Rotation;

 

 

public class JFreeChartDemo {

   

    public PieDataset createDataSet(List<Integer> data, List<String> datadescription) {

       if (data != null && datadescription != null )

           if (data.size() == datadescription.size()) {

              final DefaultPieDataset dataset = new DefaultPieDataset();

              for ( int i = 0; i < data.size(); i++)

                  dataset.setValue(datadescription.get(i),

                         data.get(i));

              return dataset;

           }

       return null ;

    }

   

    public JFreeChart createChart(PieDataset pieDataset, List<String> pieKeys) {

       JFreeChart jfreeChart = ChartFactory.createPieChart( " 饼形图 " , // 大标题

              pieDataset,

              false ,  // 不显示图例

              false ,

              false

       );

       jfreeChart.setBackgroundPaint(Color. white );  // 设置背景色 ，如图中的白色背景

       jfreeChart.setBorderVisible( true );  // 设置显示边框，如图中的黑色边框

       final PiePlot piePlot = (PiePlot) jfreeChart.getPlot();

      

       piePlot.setLegendLabelGenerator( new StandardPieSectionLabelGenerator( "{0}({1} 次 )" ));  // 设置标签显示数值， {1} 为数值， {2} 为百分比

       piePlot.setBackgroundPaint( new Color(229, 246, 250)); // 图片背景色

       piePlot.setShadowPaint(Color. gray );    // 设置图的阴影颜色

       piePlot.setLabelFont( new Font( "SansSerif" , Font. PLAIN , 12)); // 设置标识每块的字体和大小

       piePlot.setNoDataMessage( "No data available" ); // 没有数据时显示这句话

       piePlot.setLabelGenerator( new StandardPieSectionLabelGenerator( "{0} ({1} 次 )" )); // 显示每块的数据值

       piePlot.setCircular( true ); // 设置饼为正圆型， false 为椭圆

       piePlot.setLabelLinksVisible( true );  // 设置显示每块的饼和数据之间的线

       piePlot.setLabelBackgroundPaint( new Color(229, 246, 50));  // 设置标签的背景色，如图的黄色部分

       piePlot.setLabelOutlinePaint( new Color(229, 46, 250));  // 设置标签的边框颜色，如图中的红色边框

      

//     piePlot.setLabelLinkStyle(PieLabelLinkStyle.CUBIC_CURVE);

       piePlot.setLabelShadowPaint( new Color(229, 46, 0)); // 设置标签的背景色，如图中的红色阴影部分

       piePlot.setMaximumLabelWidth(0.25D);  // 设置标签的最长宽度

       piePlot.setLabelLinkMargin(0.05D);  // 设置分类标签与图的连接线边距

       piePlot.setIgnoreZeroValues( true );  // 忽略无值的分类

       piePlot.setOutlinePaint( new Color(29, 46, 250));  // 设置饼图的边框，如图中的蓝色边框

       piePlot.setLabelGap(0.00D); // 设置饼与边框的距离

       jfreeChart.getTitle().setFont( new Font( "SansSerif" , Font. BOLD , 16)); // 设置标题的字体

//     jfreeChart.getLegend().setItemFont(

//            new Font("SansSerif", Font.PLAIN, 12));

       // 设置分饼颜色

       piePlot.setSectionPaint(pieKeys.get(0), new Color(254, 0, 2));

       piePlot.setSectionPaint(pieKeys.get(1), new Color(245, 250, 25));

       piePlot.setSectionPaint(pieKeys.get(2), new Color(0, 0, 254));

       piePlot.setSectionPaint(pieKeys.get(3), new Color(254, 0, 153));

       piePlot.setSectionPaint(pieKeys.get(4), new Color(155, 2, 253));

       piePlot.setSectionPaint(pieKeys.get(5), new Color(3, 254, 143));

       piePlot.setSectionPaint(pieKeys.get(6), new Color(159, 253, 5));

       piePlot.setSectionPaint(pieKeys.get(7), new Color(0, 254, 0));

       piePlot.setSectionPaint(pieKeys.get(8), new Color(0, 254, 254));

       piePlot.setSectionPaint(pieKeys.get(9), new Color(255, 1, 255));

       //piePlot.setDepthFactor(0.16d);

       piePlot.setStartAngle(45);

       piePlot.setDirection(Rotation. CLOCKWISE );

      

        piePlot.setForegroundAlpha(0.5f);   // 设置图的透明度

       // 指定饼图轮廓线的颜色

       piePlot.setBaseSectionOutlinePaint(Color. RED );

       piePlot.setBaseSectionPaint(Color. RED );

      

       // 将图例显示在图片的右边

       LegendTitle legend = new LegendTitle(piePlot);

       BlockContainer wrapper = new BlockContainer( new BorderArrangement());

       legend.setBackgroundPaint(Color. gray );   // 设置图例的背景色，如图中灰色部分

       BlockContainer items = legend.getItemContainer();

        items.setPadding(2, 10, 5, 2);

        wrapper.add(items);

        legend.setWrapper(wrapper);

        legend.setPosition(RectangleEdge. RIGHT );  // 将图例显示在图片的右边

        legend.setHorizontalAlignment(HorizontalAlignment. LEFT );

        jfreeChart.addSubtitle(legend);

       return jfreeChart;

    }

   

   

    public static void main(String[] args) {

       final List<Integer> data = new ArrayList<Integer>();

       final List<String> keys = new ArrayList<String>();

       for ( int i=0; i<10; i++) {

           data.add((( int )Math.random())*10+i*10);

           keys.add(i + " 月 " );

       }

       JFreeChartDemo demo = new JFreeChartDemo();

       PieDataset dataset = demo.createDataSet(data, keys);

      

       JPanel panel = new ChartPanel(demo.createChart(dataset,keys));

       panel.setPreferredSize( new java.awt.Dimension(550, 300));

       JFrame jframe = new JFrame();

       jframe.setContentPane(panel);

       jframe.pack();

        RefineryUtilities.centerFrameOnScreen(jframe);

        jframe.setVisible( true );

    }

 

} 