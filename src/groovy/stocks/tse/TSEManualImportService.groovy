package stocks.tse

/**
 * Created with IntelliJ IDEA.
 * User: Farzin
 * Date: 7/29/14
 * Time: 1:29 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class TSEManualImportService<T> extends TSEImportService<T> {

    @Override
    protected def getParameter(int index){
        currentParameter[index]
    }

    private List currentParameter

    protected List<T> execute(List parameter){
        currentParameter = parameter
        def startDate = new Date()
        def list = list(parameter)
        println("total execution time: ${(new Date().time - startDate.time) / 1000}")
        list
    }
}
