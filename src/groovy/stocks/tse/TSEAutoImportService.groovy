package stocks.tse

/**
 * Created with IntelliJ IDEA.
 * User: Farzin
 * Date: 7/29/14
 * Time: 1:29 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class TSEAutoImportService<T> extends TSEImportService<T> {

    protected abstract List<List> getParameters()

    @Override
    protected def getParameter(int index){
        parameters[currentParameterSetIndex][index]
    }

    private int currentParameterSetIndex = 0

    private int setCurrentParameterSetIndex(int value){
        currentParameterSetIndex = value
    }

    public void execute() {
        def startDate = new Date()
        parameters.eachWithIndex { parameter, index ->
            setCurrentParameterSetIndex(index)
            list(parameter)
        }
        println("total execution time: ${(new Date().time - startDate.time) / 1000}")
    }
}
