import java.util.ArrayList;


public class linkedList {
    public static void main(String[] args) {
        
        int[] f = {31, 92, 55, 15, 44, 46, 38, 90, 79, 35};
        ArrayList<Integer> f1 = new ArrayList<>();
        ArrayList<Integer> f2 = new ArrayList<>();

        boolean toF1 = true; 
        f1.add(f[0]);

        for (int i = 0; i < f.length - 1; i++) {
            int current = f[i];
            int next = f[i+1];

            if (next < current) {
                toF1 = !toF1; 
            }

            if (toF1) {
                f1.add(next);
                //System.out.println("f1 " + f1);
            } else {
                f2.add(next);
                //System.out.println("f2 " + f2);
            }
        }

        System.out.println("f1 " + f1);
        System.out.println("f2 " + f2);
    }
}

/*public class linkedList{

    public static void main(String[] args) {
        
        //ArrayList<Integer> f = new ArrayList<>(List.of(31,92,55,15,44,46,38,90,79,35));
        int[] f = {31,92,55,15,44,46,38,90,79,35};
        ArrayList<Integer> f1 = new ArrayList<>();

        ArrayList<Integer> f2 = new ArrayList<>();

        f1.add(f[0]);
         
        for (int i = 1; i < f.length - 1; i++) {

            if ( f[i] >= f1.get(f1.size() - 1))  {
                f1.add(f[i]);
                //System.out.println("f1 " + f1);
            } 
            else if (f2.isEmpty() || (f[i] >= f2.get(f2.size() - 1)) ){
                f2.add(f[i]);
                //System.out.println("f2 " + f2);
            }
            else 
                f1.add(f[i]);

        }System.out.println("f1 " + f1);
        System.out.println("f2 " + f2);


    }
}*/