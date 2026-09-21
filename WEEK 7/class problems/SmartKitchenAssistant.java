abstract class KitchenTool {

    private int speedLevel;

    public abstract String prepare();

    int getSpeedLevel() {
        return speedLevel;
    }

    void setSpeedLevel(int speedLevel) {
        if (speedLevel < 1 || speedLevel > 5) {
            return;
        }
        this.speedLevel = speedLevel;
    }
}

interface Washable {
    String clean();
}

class Blender extends KitchenTool implements Washable {

    public Blender() {
    }

    @Override
    public String prepare() {
        return "Blending at speed " + getSpeedLevel();
    }

    @Override
    public String clean() {
        return "Blender rinsed and dried";
    }
}

public class SmartKitchenAssistant {

    public static void main(String[] args) {
        Blender b = new Blender();

        b.setSpeedLevel(3);
        System.out.println(b.getSpeedLevel());

        b.setSpeedLevel(9); // rejected, speed level stays 3
        System.out.println(b.getSpeedLevel());

        System.out.println(b.prepare());
        System.out.println(b.clean());
    }
}
