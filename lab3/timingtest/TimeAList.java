package timingtest;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        int MaxSize=128000;
        int Size=1000;
        AList<Integer> Ns=new AList<Integer>();
        AList<Double> times=new AList<Double>();
        AList<Integer> opCounts=new AList<Integer>();
        AList<Integer> t=new AList<Integer>();
        while(Size!=MaxSize){
            Stopwatch sw = new Stopwatch();//开始计算时间
            for(int i=0;i<Size;i++){
                t.addLast(i);
                i++;
            }
            //System.out.println(i);
            Ns.addLast(Size);
            times.addLast(sw.elapsedTime());
            opCounts.addLast(Size);
            Size*=2;
        }
        printTimingTable(Ns,times,opCounts);
    }
}
