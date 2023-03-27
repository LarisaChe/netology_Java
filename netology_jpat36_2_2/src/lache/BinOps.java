package lache;

public class BinOps {
    public String sum(String a, String b) {
    /*Переведите числа из двоичной записи в
      строках в int, сложите и переведите
      обратно в двоичную запись*/
        //Для парсинга из двоичной записи используйте Integer.parseInt(binStr, 2), для конвертации обратно - Integer.toBinaryString.
        int aInt = Integer.parseInt(a, 2);
        int bInt = Integer.parseInt(b, 2);
        return Integer.toBinaryString(aInt + bInt);
    }

    public String mult(String a, String b) {
    /*Переведите числа из двоичной записи в
      строках в int, перемножьте и переведите
      обратно в двоичную запись*/
        int aInt = Integer.parseInt(a, 2);
        int bInt = Integer.parseInt(b, 2);
        return Integer.toBinaryString(aInt * bInt);
    }
}
