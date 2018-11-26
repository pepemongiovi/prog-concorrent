package lista1.questoes2_3_4.questao4;

import lista1.utils.Utils;

import java.util.concurrent.Callable;

/**
 * Class that indicates when {@link InfinityHttpRequest} should stop making requests.
 *
 * @author Thaynan Nunes
 */
public class DoneVerifier implements Callable<Long> {

    public DoneVerifier() { }

    @Override
    public Long call() {
        Utils.sleep(2000);
        return 2000l;
    }
}