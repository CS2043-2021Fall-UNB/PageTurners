package pageturners.controls;

import java.util.ArrayList;

public class ControlDirectory {
    private final ArrayList<ControlBase> controls;

    public ControlDirectory() {
        controls = new ArrayList<ControlBase>();
    }

    public ControlBase[] getControls() {
        return controls.toArray(new ControlBase[0]);
    }

    public ControlBase getControl(Class<?> classType) {
        for (ControlBase control : controls) {
            if (control.getClass() == classType) {
                return control;
            }
        }
        
        return null;
    }

    public void clearControls() {
        controls.clear();
    }

    public boolean addControl(ControlBase control) {
        if (getControl(control.getClass()) != null) {
            return false;
        }

        controls.add(control);

        return true;
    }
}
