package Utils;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author M.H. Guitarte <guimhur@gmail.com>
 */
public class customMenu {

    TreeMap<String, Runnable> customSwitch;

    public customMenu() {
        customSwitch = new TreeMap<>();
        this.customSwitch.put("fin", () -> System.exit(0));
    }

    public void addOption(String s, Runnable r) {
        this.customSwitch.put(s, r);
    }

    private static void toRun(Runnable r) {
        r.run();
    }

    public void selectOption(String key) throws Exception {
        Set st = customSwitch.keySet();
        Iterator it = st.iterator();
        Runnable run = null;
        try {

            while (it.hasNext()) {
                if (key.equals(it.next())) {
                    run = customSwitch.get(key);
                    break;
                }
            }

            if (run == null) {
                System.out.println("No se encuentra la orden especificada");
            } else {
                toRun(run);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

}
