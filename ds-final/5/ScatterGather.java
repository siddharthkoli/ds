import mpi.MPI;
import mpi.*;

public class ScatterGather {
    public static void main(String[] args) throws Exception {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int root = 0, unitsize = 5;

        int send_buffer[] = new int[unitsize * size];
        int receive_buffer[] = new int[unitsize];
        int new_receive_buffer[] = new int[size];

        if (rank == root) {
            for (int i = 0; i < unitsize * size; i++) {
                send_buffer[i] = i;
            }
        }

        MPI.COMM_WORLD.Scatter(
                send_buffer,
                0,
                unitsize,
                MPI.INT,
                receive_buffer,
                0,
                unitsize,
                MPI.INT,
                root
        );

        for (int i = 1; i < unitsize; i++) {
            receive_buffer[0] += receive_buffer[i];
        }
        System.out.println("Intermediate sum: " + receive_buffer[0]);

        MPI.COMM_WORLD.Gather(
            receive_buffer,
            0,
            1,
                MPI.INT,
                new_receive_buffer,
                0,
                1,
                MPI.INT,
                root
        );

        if (rank == root) {
            int totalSum = 0;
            for (int i = 0; i < size; i++)
                totalSum += new_receive_buffer[i];
            System.out.println("Total sum: " + totalSum);
        }

        MPI.Finalize();
    }
}
