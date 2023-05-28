import java.util.*;

class Process {
    int id;
    boolean active;

    Process(int id) {
        this.id = id;
        this.active = true;
    }

    public void election(List<Process> processes) {
        int highestId = 0;
        for (Process process: processes)
            if (process.active)
                highestId = Math.max(highestId, process.id);

        if (highestId == this.id) {
            announceCoordinator();
        } else {
            for (Process process: processes)
                if (this.id < process.id)
                    this.sendElectionMessageToProcess(process);
        }
    }

    public void announceCoordinator() {
        System.out.println(this.id + " is selected as coordinator.");
    }

    public void sendElectionMessageToProcess(Process process) {
        System.out.println("Election msg received by " + process.id + " from " + this.id);

        if (process.active)
            this.sendResponse(process.id);
    }

    public void sendResponse(int receiverId) {
        System.out.println("Process " + receiverId + " received msg from " + this.id);
    }
}

public class Bully {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter The number of Processes : ");
        int num=sc.nextInt();

        List<Process> processes= new ArrayList<>();
        for (int i=1;i<=num;i++){
            processes.add(new Process(i));
        }
        System.out.println("Enter The process to be made inactive : ");
        num=sc.nextInt();

        processes.get(num-1).active = false;

        for(Process process : processes){
            if(process.active) {
                process.election(processes);
            }
        }

        sc.close();
    }
}
