package in.task.base;

import in.task.data.DataManagerLogic;

/**
 * Created by vivek on 21/07/17.
 */

public class BaseViewModel<V> {

    private V callback;

    private final DataManagerLogic dataManagerLogic;

    public BaseViewModel(DataManagerLogic dataManagerLogic) {
        this.dataManagerLogic = dataManagerLogic;
    }

    public void setCallback(V callback) {
        this.callback = callback;
    }

    public DataManagerLogic getDataManagerLogic() {
        return dataManagerLogic;
    }

    public V getCallback() {
        return callback;
    }
}
