
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args){
        FileOpener foo = new FileOpener();

        List<String> myFileStrings = new ArrayList<>();

        myFileStrings = foo.grabFile();
        String[] str9 = myFileStrings.get(0).split(" ");
        int nummProbs = Integer.parseInt(str9[0]);
        int lineNum = 0;
        lineNum++;
        for(int y = 0; y < nummProbs; y ++){
            String[] temp = myFileStrings.get(lineNum).split(" ");
            int numInts = Integer.parseInt(temp[0]);
            int t = Integer.parseInt(temp[1]);
            int data[] = new int[numInts];
            int dataIndex = 0;
            lineNum++;
            temp = myFileStrings.get(lineNum).split(" ");
            while(numInts > 0){
                for(int j = 0; j < temp.length; j++){
                    data[dataIndex] = Integer.parseInt(temp[j]);
                    dataIndex++;
                    numInts--;
                }
                lineNum++;
            }

            List<String> test = new ArrayList<>();
            List<String> test2 = new ArrayList<>();
            // Welcome Message

            // Set our N and K values
            int maxN = data.length;
            int maxK = maxN;

            // Holds our values
            int[][] values = new int [maxN+1][maxN+1];
            int[][] dividers = new int[maxN+1][maxN+1];

            /// Initialize base case #1. DONE
            for(int i=1; i<=maxN; i++) {
                values[1][i] = baseCase1(t, data);
            }

            // Initialize base case #2.
            for(int n=2; n<=maxN; n++) {
                values[n][1] =(int)Math.pow((t - sigmaFunction(0,n,data)),2);

            }
            int cost;
            // Evaluate Main Recurrence
            for(int r = 2; r<=maxN; r++) {
                for(int w = 2; w<=maxN; w++) {
                    values[r][w] = 100000;
                    for(int z = 1; z<= r; z++) {
                        cost = (Math.max(values[z][w-1], (int)Math.pow(t-sigmaFunction(z,r, data),2)));
                        if(values[r][w] > cost) {
                            values[r][w] = cost;
                            dividers[r][w] = z;
                        }
                    }
                }
            }

            for(int xy = 1; xy <= maxN; xy++) {
                test.add("-1"+ " " + xy) ;
                test2.add("-1"+ " " + xy);
                construct_partition(data, dividers, maxN, xy, test);
            }
            int[] bestK = new int[maxN];

            int xx = 0;
            int bestRange = -1;
            int bestRangeVal = 100000;

            for(int aa = 0; aa < test.size(); aa++){
                boolean check = false;
                String[] str2 = test.get(aa).split(" ");
                if(Integer.parseInt(str2[0]) == -1){
                    int inequality = 0;
                    int sum = 0;
                    for(int ab = aa+1; ab <=(aa+Integer.parseInt(str2[1])); ab++){
                        sum=Integer.parseInt(test.get(ab));
                        inequality += (int)Math.pow(t - sum,2);
                        if(sum > t){
                            check = true;
                        }
                    }
                    bestK[xx] = inequality;
                    if((bestRangeVal > inequality)&&(check == false)){
                        bestRange =Integer.parseInt(str2[1]);
                        bestRangeVal = inequality;
                    }
                    xx++;
                }
            }

            System.out.print(bestRange + " ");

            for(int aa = 0; aa < test.size(); aa++){
                String[] str2 = test.get(aa).split(" ");
                if((Integer.parseInt(str2[0]) == -1)&&(Integer.parseInt(str2[1])==bestRange)){
                    // We can output it now.
                    int count = 0;
                    for(int ab = aa+1; ab <=(aa+Integer.parseInt(str2[1])); ab++){
                        int sum = Integer.parseInt(test.get(ab));
                        int tally = 0;
                        while(sum > 0){
                            sum = sum - data[count];
                            count++;
                            tally++;
                        }
                        System.out.print(tally+ " ");

                    }
                    System.out.println(" ");
                }

            }
          }

    }

    public static int baseCase1(int t, int[] data) {
        return (int)Math.pow((t-data[0]), 2);
    }

        // from i to n.
    public static int sigmaFunction(int i, int n, int[] data) {
        int sum = 0;
        for(int j = i; j < n; j++){
            sum += data[j];
        }
        return sum;
    }

    public static void printArray(int[][] values){
        for (int[] arr : values) {
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void construct_partition(int[] data, int[][] dividers, int n, int k, List test){
        if(k==1) {
            print_dividers(data, 1, n, test);
        }
        else {
            construct_partition(data, dividers, dividers[n][k],k-1, test);
            print_dividers(data, dividers[n][k]+1, n, test);
        }
    }

    public static void print_dividers(int data[], int start, int end, List test){
        int i;
        start = start-1;
        int sum = 0;
        int counter= 0;
        for(i=start; i<end;i++){
            sum += data[i];
            counter++;
        }
        test.add(Integer.toString(sum))  ;
    }

}
