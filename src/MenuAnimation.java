import biuoop.DrawSurface;
import java.util.List;

public class MenuAnimation implements Menu{
    private List<String> key;
    private List<String> message;
    private List<Object> returnVal;

    @Override
    public void addSelection(String key, String message, Object returnVal) {
        this.key.add(key);
        this.message.add(message);
        this.returnVal.add(returnVal);
    }

    @Override
    public Object getStatus() {
        return null;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
