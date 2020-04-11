package mydemo.com.textinterface.interfacs;

/**
 * 作者: Wang on 2019/6/8 11:15
 * 寄语：加油！相信自己可以！！！
 */


public abstract class FuncationWithParamWithResult<Result,Param> extends Funcation {
    public FuncationWithParamWithResult(String name) {
        super(name);
    }

    public abstract Result funcation(Param param);
}
