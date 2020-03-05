package sn.ipd.liggeytool.ui.home;

import java.util.ArrayList;

public class Job {

    private int mID;
    private String mName;

    public Job(int id, String name) {

        mID=id;
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public int getID() {
        return mID;
    }

    private static int lastJobId = 0;

    public static ArrayList<Job> createjobsList(int numJobs, String nameJob) {
        ArrayList<Job> jobs = new ArrayList<Job>();

        for (int i = 1; i <= numJobs; i++) {
            jobs.add(new Job(i,"Jobs " + ++lastJobId));
        }

        return jobs;
    }
}