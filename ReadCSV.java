import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import org.jfree.ui.RefineryUtilities;

import java.text.*;



public class ReadCSV {



	public static void main(String[] args) {

    	String csvFile = "gfa25.csv";
    	String csvFile2 = "us_state_emplchange_2011-2012.txt";
    	int max=5000;
    	DecimalFormat df = new DecimalFormat("#.###");
    	
    	String[][] list1 = csvReader(csvFile, max);
    	String[][] list2 = csvReader(csvFile2, max);
    	
    	char[] type1 = checkType(list1);
    	char[] type2 = checkType(list2);
    	
    	double[][] dList1 = to2dDouble(list1, type1);
    	double[][] dList2 = to2dDouble(list2, type2);
    	
    	corAnalysis(dList1);
    	corAnalysis(dList2);
    	corAnalysis(dList1, dList2);
    	
//    	double[] one = arrayToDouble(list, 1, 5);
//    	//System.out.println(Arrays.toString(one));
//    	double[] two = arrayToDouble(list2, 1, 3);
//    	String[] te = list[4];
//    	Arrays.sort(te);
////    	System.out.println("first " + te[te.length-1]);
////    	double[] zero = arrayToDouble(list, 1, 0);
//    	System.out.println("The mean of "+ list[5][0]  +" is " + df.format(findMean(one)));
//    	System.out.println("The mean of" + list2[3][0] +" is " + df.format(findMean(two)));
//    	System.out.println("Correlation between " + list2[3][0] +" and "+ list[5][0]  +" is %" + df.format(Correlation(one, two)*100));
//    	
//    	LineChart_AWT chart1 = new LineChart_AWT( "Amount vs Count", one, list[5][0], numWords(max), "count");
//    	LineChart_AWT chart2 = new LineChart_AWT( "INIT_ESTB vs Count", two, list2[3][0], numWords(max), "count");
//        
//    	chart1.pack( );
//        RefineryUtilities.centerFrameOnScreen( chart1 );
//        chart1.setVisible( true );
//        
//        chart2.pack( );
//        RefineryUtilities.centerFrameOnScreen( chart2 );
//        chart2.setVisible( true );
//    	
//        
//        System.out.println("The interquartile of "+ list[5][0]  +" is " + df.format(interquartile(one)));
//        System.out.println("The interquartile of" + list2[3][0] +" is " + df.format(interquartile(two)));
//        System.out.println("The Median of "+ list[5][0]  +" is " + df.format(findMedian(one)));
//        System.out.println("The Median of" + list2[3][0] +" is " + df.format(findMedian(two)));
//        
//       	System.out.println("The max of "+ list[5][0]  +" is " + df.format(max(one)));
//    	System.out.println("The max of" + list2[3][0] +" is " + df.format(max(two)));
//    	System.out.println("The min of "+ list[5][0]  +" is " + df.format(min(one)));
//    	System.out.println("The min of" + list2[3][0] +" is " + df.format(min(two)));
//    	System.out.println("The difference of "+ list[5][0]  +" is " + df.format(difference(one)));
//    	System.out.println("The difference of" + list2[3][0] +" is " + df.format(difference(two)));
    	
    	
    	
    	
//
//    	range(one);
//    	
//    	System.out.println("Correlation between " + list2[3][0] +" and "+ list[5][0]  +" is %" + df.format(Correlation(one, two)*100));
//    	System.out.println("Correlation between zero and two is %" + df.format(Correlation(two, zero)*100));
//    	System.out.println("Correlation between zero and one is %" + df.format(Correlation(one, zero)*100));
//    	
//    	System.out.println(Arrays.toString(findNumRows(list)));
//    	double[][] iList = to2dDouble(list);
//    	for (int x = 0; x < list.length; x++) {
//			System.out.println(Arrays.toString(list[x]));
//    	}
//    	System.out.println("Employchange");
//    	for (int x = 0; x < list2.length; x++) {
//			System.out.println(Arrays.toString(list2[x]));
//    	}
    }
	
	public static void corAnalysis(double[][] list){
		DecimalFormat df = new DecimalFormat("#.###");
		for(int x =0; x<list.length-1; x++){
			for(int y =x+1; y<list.length; y++){
				double temp = Correlation(list[x], list[y])*100;
				System.out.println("Correlation between column " + x +" and column "+ y  +" is %" + df.format(temp));
				if(Math.abs(temp) > 60){
					LineChart_AWT chart1 = new LineChart_AWT( "x vs Count", list[x],"x" , numWords(list[x].length), "count");
			    	LineChart_AWT chart2 = new LineChart_AWT( "y vs Count", list[y], "y", numWords(list[y].length), "count");
			        
			    	chart1.pack( );
			        RefineryUtilities.centerFrameOnScreen( chart1 );
			        chart1.setVisible( true );
			        
			        chart2.pack( );
			        RefineryUtilities.centerFrameOnScreen( chart2 );
			        chart2.setVisible( true );
				}
			}
		}
	}
	
	public static void corAnalysis(double[][] list1, double[][] list2){
		DecimalFormat df = new DecimalFormat("#.###");
		for(int x =0; x<list1.length; x++){
			for(int y =0; y<list2.length; y++){
				double temp = Correlation(list1[x], list2[y])*100;
				System.out.println("Correlation between list 1 column " + x +" and list 2 column "+ y  +" is %" + df.format(temp));
				if(Math.abs(temp) > 10){
					LineChart_AWT chart1 = new LineChart_AWT( "x vs Count", list1[x],"x" , numWords(list1[x].length), "count");
					LineChart_AWT chart2 = new LineChart_AWT( "y vs Count", list2[y], "y", numWords(list2[y].length), "count");

					chart1.pack( );
					RefineryUtilities.centerFrameOnScreen( chart1 );
					chart1.setVisible( true );

					chart2.pack( );
					RefineryUtilities.centerFrameOnScreen( chart2 );
					chart2.setVisible( true );
				}
			}
		}

	}
	
	public static char[] checkType(String[][] list){
		char[] types = new char[list.length];
		for(int x =0; x<types.length; x++){
			types[x] = list[x][0].charAt(list[x][0].length()-1);
		}
		return types;
	}
	
	
    public static double[][] to2dDouble(String[][] list, char[] types){
    	
    	double[][] dList = new double[numD(types)][list[0].length-1];
    	int column =0;
    	for(int x =0; x<types.length;x++){
    		if(types[x]=='D'){
    			dList[column] = arrayToDouble(list, 1, x);
    			column++;
    		}
    	}
    	return dList;
    }
	
    public static int numD(char[] iRows){
    	int num =0;
    	for(int x =0; x<iRows.length; x++){
    		if(iRows[x] == 'D'){
    			num++;
    		}
    	}
    	return num;
    }
	
	
	public static double interquartile(double[] temp) {
    	double[] x = temp;
    	double firstq;
    	double thirdq;
    	double iqr;
    	
    	Arrays.sort(temp);
    	
    	if(temp.length % 4 !=0) {
    		firstq = temp[(temp.length/4) + 1];
    		thirdq = temp[((temp.length/4) *3) + 1];
    	}
    	
    	else {
    		firstq = temp[temp.length/4];
    		thirdq = temp[(temp.length/4) * 3];
    	}
    	
    	iqr = thirdq - firstq;
    	
    	findOutliers(temp, firstq, thirdq, iqr);
    	
    	return iqr;
    }
	
	
	
	
    
    public static double findMedian(double[] temp) {
    	double[] x = temp;
    	double median;
    	 
    	Arrays.sort(temp);
    	
    	if(temp.length % 2 == 0) {
    		median = (temp[temp.length/2] + temp[temp.length/2 + 1])/2;
    	}
    	
    	else {
    		median = temp[temp.length/2 + 1];
    	}
    	
    	return median;
    }
    
    public static double findOutliers(double[] temp, double firstq, double thirdq, double iqr) {
    	double lower_inner_fence;
    	double upper_inner_fence;
    	double lower_outer_fence;
    	double upper_outer_fence;
    	
    	lower_inner_fence = firstq - (1.5 * iqr);
    	upper_inner_fence = thirdq + (1.5 * iqr);
    	lower_outer_fence = firstq - (3 * iqr);
    	upper_outer_fence = thirdq + (3 * iqr);
    	
    	return upper_outer_fence;
    }
	
	
	
	
	
	
    
    public static String[] numWords(int max){
    	String[] temp = new String[max];
    	for(int x=0; x<max; x++){
    		String word = Integer.toString(x);
    		temp[x]= word;
    	}
    	return temp;
    }
    

    

    public static int numTrue(boolean[] iRows){
    	int num =0;
    	for(int x =0; x<iRows.length; x++){
    		if(iRows[x]){
    			num++;
    		}
    	}
    	return num;
    }
    public static boolean[] findNumRows(String[][] list){
    	boolean[] iRows = new boolean[list.length];
    	for(int x =0; x<list.length; x++){
    		boolean temp = true;
    		for(int y=1; y<15; y++){
    			if(list[x].length>x+1){
    				if(!list[x][y].matches("[-+]?\\d*\\.?\\d+")){
    					if((list[x][y].matches("[A-Za-z]"))){
    						temp = false;
    					}
    				}
    				if((list[x][y].equals("--")) || (list[x][y].equals(null))) {
    					temp = false;
    				}
    			}
    		}
    		iRows[x]=temp;
    	}
    	
    	return iRows;
    }
    
    public static double Correlation(double[] xs, double[] ys) {


        double sx = 0.0;
        double sy = 0.0;
        double sxx = 0.0;
        double syy = 0.0;
        double sxy = 0.0;

        int n = xs.length;

        for(int i = 0; i < n; ++i) {
          double x = xs[i];
          double y = ys[i];

          sx += x;
          sy += y;
          sxx += x * x;
          syy += y * y;
          sxy += x * y;
        }


        double cov = sxy / n - sx * sy / n / n;

        double sigmax = Math.sqrt(sxx / n -  sx * sx / n / n);

        double sigmay = Math.sqrt(syy / n -  sy * sy / n / n);


        return cov / sigmax / sigmay;
      }
    
    public static double[] range(double[] temp){
    	double[] x = temp;
    	Arrays.sort(temp);
    	
    	double [] range = {temp[0], temp[temp.length-1]};
//    	System.out.println(Arrays.toString(temp));
//    	System.out.println(Arrays.toString(range));
    	temp = x;
    	return range;
    	
    }
    public static double min(double[] temp){
    	double[] x = temp;
    	Arrays.sort(temp);
    	double min = temp[temp.length-1];
    	temp = x;
    	return min;
    			
    }
    
    public static double max(double[] temp){
    	double[] x = temp;
    	Arrays.sort(temp);
    	double max = temp[0];
    	temp = x;
    	return max;		
    }
    
    public static double difference(double[] temp){
    	double[] x = temp;
    	Arrays.sort(temp);
    	double dif =  temp[0]- temp[temp.length-1];
    	temp =x;
    	return dif;
    			
    }
    
    public static double findMean(double[] temp){
    	double sum =0;
    	for(int x =0; x<temp.length; x++){
    		sum = sum + temp[x];
    	}
    	double length = temp.length;
    	double mean = sum/length;
    	return mean;
    }
    
    public static double[] arrayToDouble(String[][] list, int start, int row){
    	double[] temp = new double[list[row].length-start-1];
    	for(int x = 0; x<temp.length; x++){
    		if((!list[row][start+x].equals(null))&&(!Character.isLetter(list[row][start+x].charAt(0)))){

    			Double i = Double.valueOf(list[row][start+x]);
    			temp[x] = i.doubleValue();

    		}

    	}
    	return temp;
    }
    

    
    public static String[][] csvReader(String csvFile, int max){
    	BufferedReader br = null;
    	String line = "";
    	String cvsSplitBy = ",";
    	int count =0;

    	String[][] list = null;
    	String[] one = null;
    	String quotes = "\"";
    	try {
    		

    		br = new BufferedReader(new FileReader(csvFile) );
    		while (((line = br.readLine()) != null)&& count<max){
    			
				if (count == 0) {
					String[] first = line.split(cvsSplitBy);
					one = new String[first.length];
					list = new String[one.length][max];
				}
				
				String[] temp = line.split(cvsSplitBy);
				for (int y = 0; y < one.length; y++) {
					if(y< temp.length){
					one[y] = temp[y];
					}

					
					
					
				}

				for (int x = 0; x < one.length; x++) {
					if (one[x] != null)
						list[x][count] = one[x];
					else
						list[x][count] = "-";
				}

				count++;

			}
//			for (int x = 0; x < list.length; x++) {
//				System.out.println(Arrays.toString(list[x]));
//			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		if (br != null) {
    			try {
    				br.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	return list;

    }
    
    public static String[] quoteFix(String[] temp, int y){
    	int q=0;
    	int x;
    	for( x =0; x<temp.length-y;x++){
    		for(int z =0; z<temp[y+x].length();z++){
    			if(temp[y+x].charAt(z)=='"'){
    				q++;
    			}
    			if(q ==2){
    				break;
    			}
    		}
    		if(q ==2){
				break;
			}
    	}
    	if(q!=2){
    		return temp;
    	}
    	else{
    		for(int a =1; a<=x; a++){
    			temp[y]= temp[y].concat(temp[y+a]);
    		}
    		for(y = y+1; y+x<temp.length-1; y++){
    			temp[y] = temp[y+x+1];
    		}
    	}
    	return temp;
    }

}