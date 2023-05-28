import java.util.*;


class Process{
    int processId;
    boolean isActive;
    public Process(int id){
        this.processId=id;
        this.isActive=true;
    }

    public void election(List<Process> processes){
        int highestId=0;
        for(Process process: processes){
            if(process.processId>highestId){
                highestId=process.processId;
            }
        }
        if(this.processId==highestId){
            announceCoordinator();
        }
        else{
            for(Process process: processes){
                if(process.processId>processId){
                    sendelectionmessage(process);
                }
            }
        }
    }

    public void announceCoordinator(){
        System.out.println("Process "+processId+" is selected as the coordinator");
    }

    public void sendelectionmessage(Process process){
        System.out.println("Election message received by "+process.processId +" from "+processId);

        if(process.isActive){
            process.sendresponse(processId);
        }
    }
    public void sendresponse(int senderId){
        System.out.println("Process "+ senderId+" received Response from "+ processId);
    }
}
public class bully2{
    public static void main(String args[]){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter The number of Processes : ");
        int num=sc.nextInt();

        List<Process> processes= new ArrayList<>();
        for (int i=1;i<=num;i++){
            Process process=new Process(i);
            processes.add(process);
        }
        System.out.println("Enter The process to be made inactive : ");
        num=sc.nextInt();

        for(Process process : processes){
            if(process.processId==num){
                process.isActive=false;
            }
        }

        for(Process process : processes){
            if(process.isActive){
                process.election(processes);
            }
        }



    }
}