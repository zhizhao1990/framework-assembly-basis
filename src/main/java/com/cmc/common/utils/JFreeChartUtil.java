package com.cmc.common.utils;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.TextAnchor;

/**
 * JFreeChart 工具类
 * 
 * @author Thomas Lee
 * @since 2016年12月28日 下午5:22:11
 */
public class JFreeChartUtil {

    public static void createPieChart() {
        DefaultPieDataset dpd = new DefaultPieDataset(); //建立一个默认的饼图
        dpd.setValue("管理人员", 25); //输入数据
        dpd.setValue("市场人员", 25);
        dpd.setValue("开发人员", 45);
        dpd.setValue("其他人员", 10);

        // 创建主题样式
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        // 设置标题字体
        standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
        // 设置图例的字体
        standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
        // 设置轴向的字体
        standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
        // 应用主题样式
        ChartFactory.setChartTheme(standardChartTheme);

        JFreeChart chart = ChartFactory.createPieChart("某公司人员组织数据图", dpd, true, true, false);
        // 可以查具体的API文档,第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示Legend，第四个参数表示是否显示提示，第五个参数表示图中是否存在URL

        ChartFrame chartFrame = new ChartFrame("某公司人员组织数据图", chart);
        // chart要放在Java容器组件中，ChartFrame继承自java的JFrame类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
        chartFrame.pack(); //以合适的大小展现图形
        chartFrame.setVisible(true);//图形是否可见
    }

    public static void createBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(10, "a", "管理人员");
        dataset.setValue(20, "b", "市场人员");
        dataset.setValue(40, "c", "开发人员");
        dataset.setValue(15, "d", "其他人员");

        // 创建主题样式
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        // 设置标题字体
        standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
        // 设置图例的字体
        standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
        // 设置轴向的字体
        standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
        // 应用主题样式
        ChartFactory.setChartTheme(standardChartTheme);
        JFreeChart chart = ChartFactory.createBarChart("hi", "人员分布", "人员数量", dataset, PlotOrientation.VERTICAL, true, true, false); //创建一个JFreeChart
        chart.setTitle(new TextTitle("某公司组织结构图", new Font("宋体", Font.BOLD + Font.ITALIC, 20)));//可以重新设置标题，替换“hi”标题
        CategoryPlot plot = (CategoryPlot) chart.getPlot();//获得图标中间部分，即plot
        CategoryAxis categoryAxis = plot.getDomainAxis();//获得横坐标
        categoryAxis.setLabelFont(new Font("微软雅黑", Font.BOLD, 12));//设置横坐标字体

        ChartFrame chartFrame = new ChartFrame("某公司人员组织数据图", chart);
        // chart要放在Java容器组件中，ChartFrame继承自java的JFrame类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
        chartFrame.pack(); //以合适的大小展现图形
        chartFrame.setVisible(true);//图形是否可见
    }

    public static void createLineChart() {
        // A网站的访问量统计  
        TimeSeries timeSeries = new TimeSeries("A网站访问量统计", Month.class);
        // 添加数据  如果你是从数据库中获取数据，你就写个循环塞值就行了。  
        timeSeries.add(new Month(1, 2013), 100);
        timeSeries.add(new Month(2, 2013), 200);
        timeSeries.add(new Month(3, 2013), 300);
        timeSeries.add(new Month(4, 2013), 400);
        timeSeries.add(new Month(5, 2013), 560);
        timeSeries.add(new Month(6, 2013), 600);
        timeSeries.add(new Month(7, 2013), 750);
        timeSeries.add(new Month(8, 2013), 890);
        timeSeries.add(new Month(9, 2013), 120);
        timeSeries.add(new Month(10, 2013), 400);
        timeSeries.add(new Month(11, 2013), 1200);
        timeSeries.add(new Month(12, 2013), 1600);

        // B网站的访问量统计  
        //如果有更多的就继续添加就行了  
        TimeSeries timeSeries2 = new TimeSeries("B网站访问量统计", Month.class);
        // 添加数据  
        timeSeries2.add(new Month(1, 2013), 50);
        timeSeries2.add(new Month(2, 2013), 100);
        timeSeries2.add(new Month(3, 2013), 150);
        timeSeries2.add(new Month(4, 2013), 200);
        timeSeries2.add(new Month(5, 2013), 220);
        timeSeries2.add(new Month(6, 2013), 300);
        timeSeries2.add(new Month(7, 2013), 340);
        timeSeries2.add(new Month(8, 2013), 400);
        timeSeries2.add(new Month(9, 2013), 450);
        timeSeries2.add(new Month(10, 2013), 500);
        timeSeries2.add(new Month(11, 2013), 70);
        timeSeries2.add(new Month(12, 2013), 800);

        // 定义时间序列的集合  
        TimeSeriesCollection lineDataset = new TimeSeriesCollection();
        lineDataset.addSeries(timeSeries);
        lineDataset.addSeries(timeSeries2);

        // 创建主题样式
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        // 设置标题字体
        standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
        // 设置图例的字体
        standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
        // 设置轴向的字体
        standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
        // 应用主题样式
        ChartFactory.setChartTheme(standardChartTheme);
        JFreeChart chart = ChartFactory.createTimeSeriesChart("访问量统计时间折线图", "月份", "访问量", lineDataset, true, true, true);

        //设置主标题  
        chart.setTitle(new TextTitle("A,B网站访问量统计对比图", new Font("隶书", Font.ITALIC, 15)));
        //设置子标题  
        TextTitle subtitle = new TextTitle("2016年度", new Font("黑体", Font.BOLD, 12));
        chart.addSubtitle(subtitle);
        chart.setAntiAlias(true);

        //设置时间轴的范围。  
        XYPlot plot = (XYPlot) chart.getPlot();
        DateAxis dateaxis = (DateAxis) plot.getDomainAxis();
        dateaxis.setDateFormatOverride(new java.text.SimpleDateFormat("M月"));
        dateaxis.setTickUnit(new DateTickUnit(DateTickUnit.MONTH, 1));

        //设置曲线是否显示数据点  
        XYLineAndShapeRenderer xylinerenderer = (XYLineAndShapeRenderer) plot.getRenderer();
        xylinerenderer.setBaseShapesVisible(true);

        //设置曲线显示各数据点的值  
        XYItemRenderer xyitem = plot.getRenderer();
        xyitem.setBaseItemLabelsVisible(true);
        xyitem.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
        xyitem.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
        xyitem.setBaseItemLabelFont(new Font("Dialog", 1, 12));
        plot.setRenderer(xyitem);

        ChartFrame chartFrame = new ChartFrame("某公司人员组织数据图", chart);
        // chart要放在Java容器组件中，ChartFrame继承自java的JFrame类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
        chartFrame.pack(); //以合适的大小展现图形
        chartFrame.setVisible(true);//图形是否可见
    }

    public static void main(String[] args) {
        createLineChart();
    }

}