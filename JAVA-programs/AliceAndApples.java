import java.util.Scanner;

class AliceAndApples {
    public static void main(String[] args) {
        /* There are trees growing in all four directions. Given that eacj tree grows k apples.
        N - no. of trees to north; do not have red apples.
        S - no. of trees to the south; do not have green apples.
        W – no. of trees in the west; have some red apples.
        E – no. of trees in the east; have some green apples.

        However, the colors of apples can not be distinguished outside Alice's magical house.
        So, the task is to find the minimum number of apples to be collected from the trees to guarantee M red apples.
        If it is not possible, print -1.
        */
        int n, s, e, w, m, k;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter N, S, E, W, K, M");
        n = sc.nextInt(); s = sc.nextInt();
        e = sc.nextInt(); w = sc.nextInt();
        k = sc.nextInt(); m = sc.nextInt();
        sc.close();

        System.out.print("Number of apples to pick to guarantee" + m + "red apples: ");
        if (m <= s*k)
            System.out.println(m);
        else if (m <= s*k + e + w)
            System.out.println(s*k + (m - s*k)*k);
        else
            System.out.println(-1);
        /* LOGIC: 
        North has no red apples so just ignore it!
        if m <= s*k (total no. of apples in south) then pick just m apples
        else m > s*k then pick s*k apples from south AND
        if m - s*k <= e*1 (or w*1 !) (number of red apples in east/west assuming each tree has atleast 1 red apple)
            then we need to pick all k apples from m-s*k trees (which we already checked will be less than e so we will have enough trees)
        else if m-s*k > e*1 or w*1 then we need to pick from east and west both directions
        if m - s*k <= e*1 + w*1 (number of red apples in east+west assuming each tree has atleast 1 red apple)
            then we need to pick all k apples from m-s*k trees from both eat and west(which we already checked will be less than e+w so we will have enough trees)
        */
    }
}
