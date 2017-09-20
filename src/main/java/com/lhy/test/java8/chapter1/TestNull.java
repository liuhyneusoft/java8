 
public class TestNull {
    public static void main(String ax[]){
        InTest a = () -> null;
        System.out.println(a.getx()); // null


        InTest d = new InTest() {
            @Override
            public String getx() {
                return null;
            }
        };
        System.out.println(d.getx()); // null

        InTest b = () -> "bbbbb";
        System.out.println(b.getx()); // bbbbb

        InTest c = null;
        System.out.println(c.getx());// NullPointerException
    }
}
//函数式接口
interface InTest{
    String getx();
}
