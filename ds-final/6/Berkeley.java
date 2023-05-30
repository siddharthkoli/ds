import java.util.*;
class Process {
    int id;
    long offset;

    Process(int id) {
        this.id = id;
        this.offset = 0;
    }

    long getTime() {
        return System.currentTimeMillis();
    }

    void updateClocks(long val) {
        this.offset = val;
        System.out.println("Clock time updated by " + val + " for process / machine " + id);
    }

    void syncClocks(List<Process> processes) {
        long offsetSum = 0, numOfProcess = 0;
        for (Process process: processes) {
            if (process.id != this.id) {
                long localTime = System.currentTimeMillis();
                Random random = new Random();
                int n = random.nextInt(10);
                try {
                    Thread.sleep(n * 100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                long remoteTime = process.getTime();
                long diff = remoteTime - localTime;
                offsetSum += diff;
                numOfProcess++;
            }
        }
        if (numOfProcess > 0) {
            long avgOffset = offsetSum / numOfProcess;
            updateClocks(avgOffset);
        }
    }
}
public class Berkeley {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no of processes: ");
        int n = sc.nextInt();

        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < n; i++)
            processes.add(new Process(i));

        for (Process process: processes)
            process.syncClocks(processes);

        sc.close();
    }
}
