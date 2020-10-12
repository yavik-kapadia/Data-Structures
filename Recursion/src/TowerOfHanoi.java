public class TowerOfHanoi {
    public static void main(String[] args) {
        towerOfHanoi(3, 'A','C', 'B');
    }
    /**
     * initally when the function is called we tell the program that we need to move n number of disks from a given rod
     * to an end rod
     *  @param n number of diskes to be moved
     * @param to_rod rod where the disk is to be moved
     * @param aux_rod helper rod
     * @param from_rod rod where the disk is present
     */
    public static void towerOfHanoi(int n, char from_rod, char to_rod, char aux_rod)
    {   // base case if n == 1 then move it from the from rod to the to rod
        if(n == 1)
        {
            System.out.printf("\nDisk 1 moved from %s to %s", from_rod,to_rod);
        }
        else
        {   // else  move the n - 1 disk from from_rod to aux_rod and to_rod acts as a aux rod
            towerOfHanoi(n - 1, from_rod, aux_rod, to_rod);
            System.out.printf("\nDisk %d moved from %s to %s", n,from_rod,to_rod);
            // move n-2 disk from the aux rod to to_rod
            towerOfHanoi(n - 1, aux_rod, to_rod, from_rod);
        }
    }

}
