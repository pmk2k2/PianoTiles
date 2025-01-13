package presentation;

import javafx.scene.layout.Pane;

/**

 @author    Hilal Yazici, My Khanh Phan, Souman Qadeer und Zonera Iqbal
 Die ViewController Klasse stellt eine Basis für alle Controller Klassen.
 **/
public abstract class ViewController<T> {
    protected Pane rootView;
    protected T application;
    public ViewController(T application) {
        this.application = application;
    }
    public void setApplication(T application) {
        this.application = application;
    }

    public Pane getRootView() {
        return rootView;
    }

    abstract public void initialize();
}
