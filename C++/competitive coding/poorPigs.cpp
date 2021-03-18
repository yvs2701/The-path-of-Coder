/* There are n buckets of water. A pig will instantly drink all the water from a bucket. Only 1 bucket has posionous water. Poison will kill a pig in m minutes.
While you are observing a pig no other can drink. What minimum number of Pigs you need to tell which bucket is poisonous.
eg - 25 buckets, time to test = 1 hour, kill time = 15minutes, Pig=? */
#include <iostream>
#include <cmath>
using namespace std;

int poorPigs(int b, int Time, int killTime){
    // This fn was not my idea
        int pigs=0;
        while(pow((Time/killTime + 1),pigs)<b){
            pigs++;
        }
        return pigs;
    // the best way to LOCATE the toxic bucket is using the grid system one pig drinks the rows and other the column (for this ex let it be 5x5 matrix)
    // Having 60 minutes and tests taking 15 minutes means we can run four tests. If the row pig dies in the third test, the poison is in the third row. 
    //If the column pig doesn't die at all, the poison is in the fifth column (this is why we can cover five rows/columns even though we can only run four tests).
    //so we can determine one more than numberOfObservattions ie we have data for this much buckets : totalTime/killingTime + 1 FOR EACH PIG 
    }
int countPigs(int b, int Time, int killTime){
    // this is what i thought of doing initially but i was not able to solve it further, this approach is based on the n coin problem - 
    // in which only one coin has lesser wt than 9 other.
    int pigs=0;
    return pigs;
    //since pigs drink water instantly the only time consumed is in observing them dying totalTime = numberOfObservations x killingTime
    //if we divide these buckets in 2 sets and set one pig to drink 1st half set
    // buckets left = n/2 if pig dies then we know poison is in this set we will repeat the same till we are left with one bucket 
    /*When trying to create a general formula for this method I found this is quite similar too the above solution, however I'm still not able to solve it :( */
}

int main(){
    int bckt,totalTime,killTime,pigs;
    cin>>bckt>>totalTime>>killTime;
    cout<<poorPigs(bckt,totalTime,killTime)<<"\n";
    // cout<<countPigs(bckt,totalTime,killTime)<<endl;
    return 0;
}