import model.Battlefield;

public class Main {
    public static void main(String[] args){
        Battlefield b1 = new Battlefield("battlefields/battlefield1.json");
        int[][] battlefield = b1.getBattlefield();
        for (int j = 0; j < battlefield[0].length; j++) {
            for (int i = 0; i < battlefield.length; i++) {
                System.out.print(battlefield[i][j]+"   ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
