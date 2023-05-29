import java.util.*;

class Process {
    int id;
    long offset;

    Process(int id) {
        this.id = id;
        this.offset = 0;
    }

    long gettime() {
        return System.currentTimeMillis();
    }

    void updateClocks(long val) {
        this.offset = val;
        System.out.println("Clock time updated by " + val + " for processes / machine " + this.id);
    }

    void syncClocks(List<Process> processes) {
        int numProcesses = 0;
        long sumOffsets = 0;

        for (Process process: processes) {
            if (process.id != this.id) {
                long localTime = System.currentTimeMillis();
                Random random = new Random();
                int n = random.nextInt(10);
                Thread.sleep(n * 100);

                long remoteTime = process.gettime();

                long diff = remoteTime - localTime;
                sumOffsets += diff;
                numProcesses++;
            }
        }

        if (numProcesses > 0) {
            long avgOffset = sumOffsets / numProcesses;
            this.updateClocks(avgOffset);
        }
    }
}

public class Berkeley {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the Number of Processes");
        int num = sc.nextInt();
        List<Process> processes = new ArrayList<>();

        for (int i = 0; i < n; i++)
            processes.add(new Process(i));

        for (Process process: processes)
            process.syncClocks(processes);
    }
}
