import java.util.*;

public class Ring {
    static int inactiveCount = 0, coordinator = 0;

    static void deactivate(List<Process> processes, int id) {
        if (id > processes.size() || id < 1) {
            System.out.println("Invalid id");
            return;
        }
        // check if already inactive
        if (!processes.get(id - 1).active) {
            System.out.println("Process is already inactive");
            return;
        }

        processes.get(id - 1).active = false;
        inactiveCount++;
    }

    static void pingCoordinator(List<Process> processes, int id) {
        if (!processes.get(id - 1).active) {
            System.out.println("Process inactive");
            return;
        }

        if (id - 1 == coordinator) {
            if (processes.get(coordinator).active)
                System.out.println("Cooridinator alive");
            else
                System.out.println("Coordinator is inactive. Initiate election from other process");
        }

        System.out.println("Sending msg from " + id + " to coordinator " + (coordinator + 1));
        if (!processes.get(coordinator - 1).active) {
            System.out.println("Coordinator is dead. Conducting election");
            election(processes, id);
        } else {
            System.out.println("Cooridinator alive");
        }
    }

    static void view(List<Process> processes) {
        if (inactiveCount == processes.size()) {
            System.out.println("all processes inactive.");
            return;
        }
        System.out.print("Active processes: ");
        for (Process process: processes) {
            if (process.active)
                System.out.print(process.id + " ");
        }
    }

    static void election(List<Process> processes, int id) {
        // check if id is valid
        // check if all processes are not inactive
        // check if id is active

        // perform election
        id--;
        int current_coordinator = id;
        int token = (id + 1) % processes.size();

        System.out.println("Election initiator " + (id + 1));
        while (token != id) {
            System.out.println("Token at process " + (token + 1));
            if (processes.get(token).active && token > processes.get(token).id) {
                current_coordinator = token;
            }
            token = (token + 1) % processes.size();
        }
        coordinator = current_coordinator;
        System.out.println("Elected coordinator: " + (coordinator + 1));
    }

    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no of prcesses: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            processes.add(new Process(i + 1));
        }
        System.out.println("Process " + n + " is selected as initial coordinator");
        coordinator = n - 1;

        int choice = 0;
        while (choice < 5) {
            System.out.println("1. deacrive 2. ping coordinator 3. view ring 4. election 5. exit");
            choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.println("Enter processs to deactivate");
                    int id = sc.nextInt();
                    deactivate(processes, id);
                    break;
                }

                case 2: {
                    System.out.println("Enter process: ");
                    int id = sc.nextInt();
                    pingCoordinator(processes, id);
                    break;
                }

                case 3: {
                    view(processes);
                    break;
                }

                case 4: {
                    System.out.println("Enter initiator process: ");
                    int id = sc.nextInt();
                    election(processes, id);
                    break;
                }

                case 5: {
                    break;
                }
            }
        }
    }
}
