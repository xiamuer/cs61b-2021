package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

import java.sql.Time;

/**
 * Created by hug.
 */
public class TimeSLList {
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
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns=new AList<Integer>();
        AList<Double> times=new AList<Double>();
        AList<Integer> opCounts=new AList<Integer>();//这里依然是AList
        int MaxSize=128000*2;
        int size=1000;
        int M=10000;
        while(size!=MaxSize){
            SLList<Integer> t=new SLList<Integer>();//创建一个SLList(注意这里是SLList，不是Alist)
            for(int i=0;i<size;i++){
                t.addLast(i);
            }
            Stopwatch sw = new Stopwatch();//开始计算时间
            //执行M次 getLast;
            for(int i=0;i<M;i++){
                t.getLast();
            }
            times.addLast(sw.elapsedTime());
            Ns.addLast(size);
            opCounts.addLast(M);
            size=size*2;
        }
        printTimingTable(Ns,times,opCounts);
    }

}
