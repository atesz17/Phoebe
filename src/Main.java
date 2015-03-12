import com.gto.phoebe.Glue;
import com.gto.phoebe.Level;
import com.gto.phoebe.Oil;
import com.gto.phoebe.Robot;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by atesz17 on 3/5/2015.
 */
public class Main {

    public static void main(String[] args) throws Exception  {
        ArrayList<ArrayList<String>> a = new ArrayList<ArrayList<String>>();
        a.add(new ArrayList<String>());
        String s = new String("Wat");
        a.get(0).add(s);
        a.get(0).add("Asd");
        System.out.print(a.get(0).indexOf("Asd"));
    }
}
