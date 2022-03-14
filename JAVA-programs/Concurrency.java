import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;

class Concurrency {
    public static void main(String[] args) {
        Write writer = new Write();
        Read reader = new Read();
        // if reader fails to read the last few messages then its
        // a producer consumer problem where concurrent threads
        // can't communicate properly
        writer.start();
        reader.start();
    }
}

class Read extends Thread {
    public void run() {
        try {
            // sleep(1000); // wait for the writer to create a new file
            // not needed as writer creates the file even before reader begins its job
            BufferedReader reader = new BufferedReader(new FileReader("./textFile.txt"));
            boolean threadRunning = true;
            String mssg;
            while (threadRunning) {
                mssg = reader.readLine();
                System.out.println("Read: \'" + mssg + "\'");
                if (mssg != null) {
                    sleep(5000); // reads every 5 seconds
                } else {
                    reader.close();
                    reader = new BufferedReader(new FileReader("./textFile.txt"));
                    // if mssg = null then reader missed few lines in the file and 
                    // the file content was erased so start reading over from the top
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Write extends Thread {
    public void run() {
        File file = new File("./textFile.txt");
        // create a file if doesnt exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            boolean threadRunning = true;
            String mssg = "";
            int count = 0;
            while (threadRunning) {
                if (count != 5) {
                    mssg = "I am writing: " + count++ + "\n";
                    writer.write(mssg); writer.flush();
                    sleep(3000); // writes every 3 seconds
                } else {
                    // as soon as count == 5 we will erase the contents of file
                    writer.close();
                    writer = new BufferedWriter(new FileWriter(file));
                    count = 0; // and start from 0 again
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
